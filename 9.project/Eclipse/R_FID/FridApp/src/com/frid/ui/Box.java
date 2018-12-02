package com.frid.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.frid.adapter.BoxItemAdapter;
import com.frid.data.TestMsg;
import com.frid.db.TDao;
import com.frid.fridapp.R;
import com.frid.pojo.BillList;
import com.frid.pojo.DBGsonProduct;
import com.frid.pojo.DBLog;
import com.frid.pojo.GsonProductList;
import com.frid.pojo.GsonState;
import com.frid.tool.ASHttp;
import com.frid.tool.SynchTool;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.tool.VTool;
import com.frid.tool.VTool.CallbackVT;
import com.frid.view.RFActivity;
import com.google.gson.Gson;
import com.pnikosis.materialishprogress.ProgressWheel;

import device.frid.Device;
import device.frid.Device.LoopEpc;

public class Box extends RFActivity implements OnItemClickListener,OnClickListener {
	private SynchTool st;
	private ListView listView;
	private BoxItemAdapter mAdapter;

	private TDao<DBGsonProduct> PDao;
	private List<DBGsonProduct> list;

	private ProgressWheel pw;//旋转条
	private Button CheckScan,CheckUpload;

	private Device device;
	private List<String> scrapEpcList;//不需要对比的epc
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		init();
		data();
	}
	private void init() {
		device = new Device(this);
		scrapEpcList = new ArrayList<String>();
		st = new SynchTool(this);

		//		PDao = new DaoProduct(this);
		PDao = new TDao<DBGsonProduct>(this, DBGsonProduct.class);

		CheckScan = (Button) findViewById(R.id.CheckScan);
		CheckUpload = (Button) findViewById(R.id.CheckUpload);
		listView = (ListView) findViewById(R.id.CheckAList);
		pw = (ProgressWheel) findViewById(R.id.CheckProgressWheel);
		pw.spin();

		listView.setOnItemClickListener(this);
		CheckScan.setOnClickListener(this);
		CheckUpload.setOnClickListener(this);

		CheckUpload.setText("返库");
	}

	private void data() {
		list = PDao.queryAll();
		mAdapter = new BoxItemAdapter(getApplicationContext(),list);
		listView.setAdapter(mAdapter);
//		Toast("本地商品数："+list.size());

		if(list==null||list.size()==0) 
			network();
	} 

	private void network() {
		ASHttp.QueryProductItemList(this,new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("QueryProductItemList", msg);
				if(b){
					/**这里需要做逻辑操作*/
					GsonProductList gc = new Gson().fromJson(msg, GsonProductList.class);
					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
						list.addAll(gc.getList());
						//						PDao.setProductList(list);
						PDao.insertList(list);
						mAdapter.notifyDataSetChanged();
					}
				}
			};
		} );
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int i, long arg3) {
		final DBGsonProduct temp = list.get(i);
		switch (temp.getState()) {
		case DBGsonProduct.INSTOCK:/** 正常 */
			VTool.Interaction(true,this,R.drawable.test_box,temp.getName(),temp.getEpc(),"取消操作", "销售展示", new CallbackVT() {
				@Override
				public void InteractionYes() {
					super.InteractionYes();
					/*打LOG*/
					st.saveLog(temp.getEpc(), DBLog.SHOWPRODUCT2CUSTOMER);
					temp.setState(DBGsonProduct.SHOWING);
					PDao.update(temp);
					mAdapter.notifyDataSetChanged();
				}
			});
			break;
		case DBGsonProduct.SHOWING:/** 展示中 */
			VTool.Interaction(true,this,R.drawable.test_box,temp.getName(),temp.getEpc(),"未购买", "客户购买", new CallbackVT() {
				@Override
				public void InteractionYes() {//
					super.InteractionYes();
					/*打LOG*/
					st.saveLog(temp.getEpc(), DBLog.CUSTOMERBUY);
					/*客户购买的逻辑*/
					ASHttp.GetWaybillList(Box.this, new AsyncHttp() {
						@Override
						public void onResult(boolean b, String msg) {
							super.onResult(b, msg);

							msg = TestMsg.updateMSG("GetWaybillList", msg);
							BillList blist = null;
							if(b){
								blist = new Gson().fromJson(msg, BillList.class);
							}else Toast("网络异常,无法匹配运单号！");

							VTool.inputDialog(Box.this,blist.getWayBillList(),new CallbackVT() {
								@Override
								public void ReturnData(String msg) {
									super.ReturnData(msg);
									//购买成功
									temp.setState(DBGsonProduct.SOLD);
									temp.setInputWaybillNumber(msg);
									PDao.update(temp);
									mAdapter.notifyDataSetChanged();
								}
							});
						}
					});
				}
				@Override
				public void InteractionNo() {//未购买
					super.InteractionNo();
					/*打LOG*/
					st.saveLog(temp.getEpc(), DBLog.CUSTOMERNOTBUY);
					temp.setState(DBGsonProduct.INSTOCK);
					PDao.update(temp);
					mAdapter.notifyDataSetChanged();
				}
			});
			break;
		case DBGsonProduct.SOLD:/** 已售出 */
			//已售出的点击无反应
			break;
		case DBGsonProduct.ABNORMAL:/** 异常 */
			VTool.InteractionBox(this,temp.getName(),temp.getEpc(),"标签损毁", "货品丢失","关闭", new CallbackVT() {
				@Override
				public void InteractionYes() {//货品丢失
					super.InteractionYes();
					temp.setState(DBGsonProduct.LOST);
					PDao.update(temp);
					mAdapter.notifyDataSetChanged();
				}
				@Override
				public void InteractionNo() {//标签损毁
					super.InteractionNo();
					temp.setState(DBGsonProduct.LABLEBROKEN);
					PDao.update(temp);
					mAdapter.notifyDataSetChanged();
				}
				@Override
				public void InteractionClose() {
					super.InteractionClose();
					//	Toast("点击关闭！,前面已经关闭，不用做任何操作~");
				}
			});
			break;
		case DBGsonProduct.LOST:/** 丢失 */
			//丢失的点击无反应
			break;
		case DBGsonProduct.LABLEBROKEN:/** ID损坏 */
			//ID损坏的点击无反应
			break;
		default:
			//没有的话就当正常处理吧。。。
			VTool.Interaction(true,this,R.drawable.test_box,temp.getName(),temp.getEpc(),"取消操作", "销售展示", new CallbackVT() {
				@Override
				public void InteractionYes() {
					super.InteractionYes();
					/*打LOG*/
					st.saveLog(temp.getEpc(), DBLog.SHOWPRODUCT2CUSTOMER);
					temp.setState(DBGsonProduct.SHOWING);
					PDao.update(temp);
					mAdapter.notifyDataSetChanged();
				}
			});
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.CheckUpload:
			VTool.Interaction(this,"警告","确认进行反库操作吗?","取消反库","确认反库",  new CallbackVT() {
				@Override
				public void InteractionYes() {//货品丢失
					super.InteractionYes();
					confirmstockback();
				}
			});
			break;
		case R.id.CheckScan://扫描
			/*如果没有正常需要扫描(正常、异常)的货品，提示 没有可扫描货品*/
			if(list==null||list.size()==0) {
				Toast("无盘点货物.");
				return;
			}
			if(CheckScan.getText().toString().equals(getResources().getString(R.string.Scan))){//点击扫描
				//1.改变文字 2.锁定上传 3.显示旋转动画 4.执行真实操作
				CheckScan.setText(getResources().getString(R.string.Stop));
				CheckUpload.setEnabled(false);
				CheckUpload.setBackground(getResources().getDrawable(R.drawable.button_circle_gray_style));
				pw.setVisibility(View.VISIBLE);
				//真实扫描
				device.biBi(true);
				itemCheck();
				scanFrid();
			}else{//点击停止
				//1.改变文字  2.锁定上传 3.显示旋转动画 4.执行真实操作
				CheckScan.setText(getResources().getString(R.string.Scan));
				CheckUpload.setEnabled(true);
				CheckUpload.setBackground(getResources().getDrawable(R.drawable.button_circle_blue_style));
				pw.setVisibility(View.GONE);
				//真实停止扫描
				scanStopFrid();
			}
			break;
		default:
			break;
		}
	}

	/**扫描时，排除 展示中、已售出、丢失、损毁  的货品不做扫描，直接默认为已找到*/
	private void itemCheck() {
		for (DBGsonProduct gi : list) {
			if(gi.getState()==DBGsonProduct.SHOWING||gi.getState()==DBGsonProduct.SOLD||
					gi.getState()==DBGsonProduct.LOST||gi.getState()==DBGsonProduct.LABLEBROKEN)
			{
				scrapEpcList.add(gi.getEpc());
				gi.setIsExist(1);
				mAdapter.notifyDataSetChanged();
			}


		}
	}

	/**真实扫描逻辑*/
	private void scanFrid() {
		isStop = false;
		device.startSearch(new LoopEpc() {
			@Override
			public void ReturnEpc(String epc) {
				super.ReturnEpc(epc);

				Log.e("EPC扫描结果", epc);

				if(scrapEpcList.contains(epc)) return;

				for (DBGsonProduct gi : list) {
					if(gi.getEpc().equals(epc)){
						/**改变状态为1，找到*/
						gi.setIsExist(1);

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								device.biBi(true);
								mAdapter.notifyDataSetChanged();
							}
						});


					}
				}
				/**属于废弃epc*/
				scrapEpcList.add(epc);
				//判断是否全部找到，全部找到就自动停止
				boolean b = true;
				for (DBGsonProduct temp : list) if(temp.getIsExist() != 1) b = false;
				//全部找到 
				if(b) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(isStop) return;//如果已经停止，就不再调用停止
							
							Toast("全部找到.");
							//1.改变文字  2.锁定上传 3.显示旋转动画 4.执行真实操作
							CheckScan.setText(getResources().getString(R.string.Scan));
							CheckUpload.setEnabled(true);
							CheckUpload.setBackground(getResources().getDrawable(R.drawable.button_circle_blue_style));
							pw.setVisibility(View.GONE);
							//真实停止扫描
							scanStopFrid();
						}
					});
				}
			}
		});
	}

	/**真实停止扫描逻辑*/
	boolean isStop = false;//SDK的多线程有时会覆盖导致结果错误，加个锁..
	private void scanStopFrid() {
		isStop = true;//真实停止...
		device.biBi(false);
		device.stopSearch();
		scrapEpcList.clear();
		for (DBGsonProduct dbg : list) {
			if(dbg.getState() == DBGsonProduct.INSTOCK&&dbg.getIsExist()==0){
				//状态为正常的商品，扫描时未找到！
				dbg.setState(DBGsonProduct.ABNORMAL);
				//同步到数据库
				PDao.update(dbg);
			}
			if(dbg.getState() == DBGsonProduct.ABNORMAL&&dbg.getIsExist()==1){
				//状态为异常的商品，扫描时找到了！
				dbg.setState(DBGsonProduct.INSTOCK);
				//同步到数据库
				PDao.update(dbg);
			}
			dbg.setIsExist(0);
		}
		mAdapter.notifyDataSetChanged();
	}

	/**返库流程*/
	private void confirmstockback() {
		if(list!=null&&list.size()>0){
			for (DBGsonProduct dbp : list) {
				if(dbp.getState()==DBGsonProduct.SHOWING){
					Toast("有展示中货品,不可返库.");
					return;
				}else if(dbp.getState()==DBGsonProduct.ABNORMAL){
					Toast("有异常货品,请处理后返库.");
					return;
				}
			}	
		}else {
			Toast("无返库货品.");
			return;
		}
		ASHttp.Confirmstockback(this, new AsyncHttp() {
			@Override
			public void onResult(boolean b, String msg) {
				super.onResult(b, msg);
				msg = TestMsg.updateMSG("Success", msg);
				if(b){
					/**这里需要做逻辑操作*/
					GsonState gs = new Gson().fromJson(msg, GsonState.class);
					if(gs.getResponseCode().equals("0000")){/**获取数据成功*/
						//						PDao.clear();
						PDao.deleteAll();
						list.clear();
						Toast("返库成功!");
						mAdapter.notifyDataSetChanged();
					}else
						Toast("返库失败！Error:"+gs.getResponseMessage());
				}else{
					Toast("网络异常,返库失败！");
				}
			}
		});
	}

}
