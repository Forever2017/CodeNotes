package com.frid.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import joker.kit.base.FragmentJoker;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.frid.adapter.QueryMItemAdapter;
import com.frid.adapter.QueryTableMItemAdapter;
import com.frid.data.TestMsg;
import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonState;
import com.frid.pojo.GsonStock;
import com.frid.tool.ASHttp;
import com.frid.tool.JsonTool;
import com.frid.tool.VTool;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.tool.VTool.CallbackVT;
import com.google.gson.Gson;
import com.pnikosis.materialishprogress.ProgressWheel;

import device.frid.Device;
import device.frid.Device.LoopEpc;
/**核对\查询*/
public class QueryFragment extends FragmentJoker implements OnClickListener,QueryMItemAdapter.Remove{
	private ProgressWheel pw;//旋转条
	private Button QueryScan,QueryUpload,QueryNumber;
	private List<GsonItem> list;
	private ListView QueryTableList;
	private QueryTableMItemAdapter mAdapter;
	private GsonItem currentEPC;//当前扫描显示的epc
	private Device device;
	private boolean isSubmit = false;
	
	
	
	private TextView TableDetailSum,TableDetailCurrent;

	public QueryFragment() { super(R.layout.fragment_query2); }

	@Override
	public void init() {
		device = new Device(getActivity());
		scrapEpcList = new ArrayList<String>();
		UnreadEpcList = new ArrayList<String>();
		QueryTableList = (ListView) findViewById(R.id.QueryTableCenter);
		pw = (ProgressWheel) findViewById(R.id.QueryProgressWheel);
		QueryScan = (Button) findViewById(R.id.QueryCheckScan);
		QueryUpload = (Button) findViewById(R.id.QueryUpload);
		QueryNumber = (Button) findViewById(R.id.QueryNumber);
		
		TableDetailSum = (TextView) findViewById(R.id.TableDetailSum);
		TableDetailCurrent = (TextView) findViewById(R.id.TableDetailCurrent);
		

		QueryScan.setOnClickListener(this);
		QueryUpload.setOnClickListener(this);
		QueryNumber.setOnClickListener(this);
		
		pw.spin();
		data();
	}
	//明细
	private void detail() {
		int sum = 0;
		int current = 0;
		for (GsonItem gsonItem : list) {
			sum = sum + Integer.parseInt(gsonItem.getNumber());
			current = current + gsonItem.getCurrent();
		}
		TableDetailSum.setText(sum+"");
		TableDetailCurrent.setText(current+"");
	}
	
	private void data() {
		list = new ArrayList<GsonItem>();
		mAdapter = new QueryTableMItemAdapter(getActivity(), list);
		QueryTableList.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();

		iniQueryList("6666666");
	}
	/**更新核对单数据*/
	private String StockTransferExternalId;//当前核对单号
	private void iniQueryList(String StockTransferExternalId) {
		this.StockTransferExternalId = StockTransferExternalId;
		ASHttp.QueryDetail(getActivity(),StockTransferExternalId, new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("querydetail", msg);
				if(b){
					Gson g = new Gson();
					GsonStock gc = g.fromJson(msg, GsonStock.class);
					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/

						list.clear();
						list.addAll(gc.getList());
//						list.addAll(correctList(gc.getList()));
						
						mAdapter.notifyDataSetChanged();
						checkSubmit();
						
						detail();
					}
				}
			};

		} );
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.QueryCheckScan://扫描
			if(QueryScan.getText().toString().equals(getResources().getString(R.string.ScanGood))){

				if(checkList(false)) {
					Toast("所有商品已核实.");
					return;
				}
				
				//核对单商品为空时不做扫描
				if(list.size()==0){Toast("没有核对货品."); return;}

				//1.改变文字 2.锁定上传 3.显示旋转动画 4.执行真实操作
				QueryScan.setText(getResources().getString(R.string.Stop));
				pw.setVisibility(View.VISIBLE);
				//真实扫描
				device.biBi(true);
				scanFrid();




				/** 测试输出扫描到的所有RFID */
				//真实停止扫描
				//device.stopSearch();

				/*final List<String> tempList = new ArrayList<>();
				device.biBi(true);
				device.startSearch(new LoopEpc() {
					@Override
					public void ReturnEpc(final String epc) {
						super.ReturnEpc(epc);

						if(!tempList.contains(epc)){
							tempList.add(epc);
							//Log.e("测试读取EPC", "读取到EPC："+epc);
						}
					}
				});*/


			}else{//点击停止
				//1.改变文字  2.锁定上传 3.显示旋转动画 4.执行真实操作
				QueryScan.setText(getResources().getString(R.string.ScanGood));
				pw.setVisibility(View.GONE);
				//真实停止扫描
				device.stopSearch();
				device.biBi(false);

				scrapEpcList.clear();
				UnreadEpcList.clear();
			}
			break;

		case R.id.QueryVerify://单商品核实
			
			//测试提交按钮
			/*for (GsonItem gsonItem : list) {
				gsonItem.setState(1);
			}
			mAdapter.notifyDataSetChanged(); 
			Toast("提交成功.");
			checkSubmit();*/
			
			if(currentEPC!=null){
				device.biBi(true);
				for (GsonItem gsonItem : list) {
					//0 未核实  1已核实
					if(gsonItem.getState()==0&&gsonItem.getId().equals(currentEPC.getId())){
						//成功对上号
						gsonItem.setNumber(currentEPC.getNumber());
						gsonItem.setState(1);
						mAdapter.notifyDataSetChanged(); 
						//扫描epc设置为空
						updateEPC(false);
						Toast("提交成功.");
						checkSubmit();
						break;
					}
				}
			}else Toast("无可核实商品.");
			
			break;
		case R.id.QueryUpload://提交核实
			if(isSubmit){
				ASHttp.UploadEpc(getActivity(),new JsonTool().getEPC(StockTransferExternalId, list), new AsyncHttp() {
					public void onResult(boolean b, String msg) {
						msg = TestMsg.updateMSG("Success", msg);
						if(b){
							GsonState gc = new Gson().fromJson(msg, GsonState.class);
							if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
								Toast("提交成功.");
								list.clear();
								QueryNumber.setText("");
								scrapEpcList.clear();
								mAdapter.notifyDataSetChanged();
								checkSubmit();
							}else Toast("Error:"+gc.getResponseMessage());
						}
					};
				} );
			}
			break;
		case R.id.QueryNumber://选择单号
			VTool.querySttingDialog(getActivity(), new CallbackVT() {
				@Override
				public void ReturnData(String msg) {
					super.ReturnData(msg);
					QueryNumber.setText("单号:"+msg);
					iniQueryList(msg);
					scrapEpcList.clear();
				}
			});
			break;
		default:
			break;
		}
	}

	/**修正LIST，主要是商品数量拆分为多个商品*/
	/*private List<GsonItem>  correctList(List<GsonItem> gi) {
		List<GsonItem> temp = new ArrayList<>();
		if(gi==null||gi.size()==0) return temp;

		for (GsonItem gsonItem : gi) {
			int i = Integer.parseInt(gsonItem.getNumber());
			if(i>1){//多个商品
				gsonItem.setNumber("1");
				for (int j = 0; j < i; j++)
					temp.add((GsonItem) gsonItem.clone());
			}else{//1个商品
				temp.add(gsonItem);
			}
		}
		return temp;
	}*/

	/**检查是否所有商品已核实
	 * show 临时加的，是否显示Toast*/
	private boolean checkList(boolean show) {
		if(list.size() == 0) {
			if(show) Toast("核实单为空.");
			return false;
		}
		for (GsonItem gi : list) 
			if(gi.getState()==0) {
				if(show) Toast("Error:尚有未核实货品.");
				return false;
			}
		return true;
	}

	/**
	检索扫描到的EPC思路
	1、不匹配的epc直接跳过，一个用来装废弃epc的list？
	2、已经匹配到的epc不做检索，直接丢入废弃epc的list还是新建一个list？
	3、避免过多的去调用checkEpcid的方法，先本地确认当前扫描到的epc需不需要去服务器获取epc的物品信息
	4、 废弃epc的list（已经使用过的、查询过 但是没有查到对应物品的、）*/
	private void scanFrid() {
		isCheck = true;
		/*已找到的epc加入到废弃epc*/
		for (GsonItem gi : list) {
			if(gi.getState() == 1)
				scrapEpcList.add(gi.getNumber());
		}

		device.startSearch(new LoopEpc() {
			@Override
			public void ReturnEpc(final String epc) {
				super.ReturnEpc(epc);
				//E28068100000003C344F60E2
				if(scrapEpcList.contains(epc)) return;

				UnreadEpcList.add(epc);

				if(isCheck){//网络开关  打开
					epcCheck();
				}
			}
		});
	}


	/**不需要对比的epc*/
	private List<String> scrapEpcList;
	/**保存未读的epc*/
	private List<String> UnreadEpcList;
	/**check的开关*/
	private boolean isCheck = true;
	/**检查epc货品*/
	private void epcCheck() {
		isCheck = false;
		if(UnreadEpcList.size()>0){//有未读的epc
			ASHttp.CheckRfid(getActivity(), UnreadEpcList.get(0), new AsyncHttp() {
				@Override
				public void onResult(boolean b, String msg) {
					super.onResult(b, msg);
					msg = TestMsg.testCheckFrid(UnreadEpcList.get(0), msg);

					if(b){
						GsonItem gi = new Gson().fromJson(msg, GsonItem.class);
						if(gi.getId()!=null&&gi.getId().length()>0){
							for (GsonItem gitem : list) {  
								if(gitem.getState()!=1&&gitem.getId().equals(gi.getId())){
									/*productExternalId匹配到一样*/
									currentEPC = gitem;
									currentEPC.setNumber(UnreadEpcList.get(0));
									getActivity().runOnUiThread(new Runnable() {
										@Override
										public void run() {
											updateEPC(true);
											//停止扫描UI
											QueryScan.setText(getResources().getString(R.string.ScanGood));
											pw.setVisibility(View.GONE);
											QueryVerify.setEnabled(true);
											QueryVerify.setBackground(getResources().getDrawable(R.drawable.button_circle_style));
											//真实停止扫描
											device.stopSearch();
											device.biBi(false);
										}
									});
									scrapEpcList.clear();
									UnreadEpcList.clear();
								}
							}

							scrapEpcList.add(UnreadEpcList.get(0));
							UnreadEpcList.remove(0);
							epcCheck();

						}else{
							scrapEpcList.add(UnreadEpcList.get(0));
							UnreadEpcList.remove(0);
							epcCheck();
						}
					}else{
						epcCheck();
					}
				}
			});
		}else{//没有未读的epc
			isCheck = true;
		}

	}

	@Override
	public void RemoveResult(int i) {
		//清除已扫描到item
		//从丢弃池中去除
		scrapEpcList.remove(list.get(i).getNumber());
		//状态和UI改变
		list.get(i).setNumber("1");
		list.get(i).setState(0);
		mAdapter.notifyDataSetChanged();
		//
		checkSubmit();
	}


	/**判断是否可以“提交”，按钮 蓝色\灰色（全部核实即可提交）*/
	private void checkSubmit() {
		isSubmit = true;

		for (GsonItem gi : list)  if(gi.getState() == 0) isSubmit = false;
		
		if(list.size()==0) isSubmit = false;

		if(isSubmit) 
			QueryUpload.setBackgroundColor(getResources().getColor(R.color.CheckBlue));
		else  
			QueryUpload.setBackgroundColor(getResources().getColor(R.color.gray_btn_bg_color));

	}
}










