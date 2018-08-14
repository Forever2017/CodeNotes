package com.frid.ui;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.frid.adapter.MItemAdapter;
import com.frid.data.TestMsg;
import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonState;
import com.frid.pojo.GsonStock;
import com.frid.tool.ASHttp;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.tool.JsonTool;
import com.frid.view.NormalTitleBar;
import com.frid.view.RFActivity;
import com.google.gson.Gson;
import com.pnikosis.materialishprogress.ProgressWheel;

import device.frid.Device;
import device.frid.Device.LoopEpc;

/**入库 and 盘点-详情*/
public class Check extends RFActivity implements OnClickListener{
	private NormalTitleBar CheckTitle;
	private Button CheckScan,CheckUpload;
	private ProgressWheel pw;//旋转条
	private GsonStock gc;//数据集合
	private List<GsonItem> list;
	private ListView listView;
	private MItemAdapter mAdapter;

	private String StockCountCode;

	private Device device;
	private List<String> scrapEpcList;//不需要对比的epc

	@Override   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		StockCountCode = getIntent().getStringExtra("StockCountCode");
		ini(); 
	}
	private void ini() {
		device = new Device(this);
		scrapEpcList = new ArrayList<String>();
		CheckTitle = (NormalTitleBar) findViewById(R.id.CheckTitle);
		CheckScan = (Button) findViewById(R.id.CheckScan);
		CheckUpload = (Button) findViewById(R.id.CheckUpload);
		listView = (ListView) findViewById(R.id.CheckAList);
		pw = (ProgressWheel) findViewById(R.id.CheckProgressWheel);
		pw.spin();
		CheckScan.setOnClickListener(this);
		CheckUpload.setOnClickListener(this);

		CheckTitle.setTitle(getString(R.string.Check));
		CheckScan.setText(getResources().getString(R.string.Scan));
		CheckUpload.setText(getResources().getString(R.string.Upload));
		data();

	}
	//数据交互
	private void data() {
		/**这里未做 入库 or 盘点 的判断。*/
		ASHttp.GetStockCountRfidList(this,StockCountCode,new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("GetStockCountRfidList", msg);
				if(b){
					/**这里需要做逻辑操作*/
					gc = new Gson().fromJson(msg, GsonStock.class);
					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
						list = gc.getList();
						mAdapter = new MItemAdapter(getApplicationContext(),list,0);
						listView.setAdapter(mAdapter);
						mAdapter.notifyDataSetChanged();
					}
				}
			};
		} );
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.CheckUpload://上传
			if(gc.getList().size() == 0){
				Toast("货品为空.");
			}else{
				/*盘点上传*/
				ASHttp.UploadStockCountResult(this,new JsonTool().getSCR(StockCountCode, gc.getList()),new AsyncHttp() {
					public void onResult(boolean b, String msg) {
						/**这里需要做逻辑操作*/
						if(b){
							GsonState gs = new Gson().fromJson(msg, GsonState.class);
							if(gs.getResponseCode().equals("0000"))/**获取数据成功*/
							{
								Toast("上传成功.");
								finish();
							}	
							else
								Toast("上传失败. msg:"+msg);
						}else
							Toast("服务器异常. msg:"+msg);
					};
				} );
			}
			break;

		case R.id.CheckScan://扫描

			if(list==null||list.size()==0) {
				Toast("无盘点货物.");
				return;
			}
			if(EPC_NUMBER>=list.size()) //全部找到
			{
				Toast("已全部找到.");
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
				scanFrid();
			}else{//点击停止
				stopScan();
			}
			break;
		default:
			break;
		}
	}
	/*停止掃描*/
	private void stopScan() {
		//1.改变文字  2.锁定上传 3.显示旋转动画 4.执行真实操作
		CheckScan.setText(getResources().getString(R.string.Scan));
		CheckUpload.setEnabled(true);
		CheckUpload.setBackground(getResources().getDrawable(R.drawable.button_circle_blue_style));
		pw.setVisibility(View.GONE);
		//真实停止扫描
		device.biBi(false);
		device.stopSearch();
		//scrapEpcList.clear();
	}


	private int EPC_NUMBER = 0;
	private void scanFrid() {
		device.startSearch(new LoopEpc() {
			@Override
			public void ReturnEpc(String epc) {
				super.ReturnEpc(epc);

				Log.e("EPC扫描结果", epc);

				if(scrapEpcList.contains(epc)) return;

				for (GsonItem gi : list) {
					if(gi.getId().equals(epc)){
						/**改变状态为1，找到*/
						gi.setState(1);
						EPC_NUMBER++;

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								device.biBi(true);
								mAdapter.notifyDataSetChanged();
								if(EPC_NUMBER>=list.size()) //全部找到
								{
									stopScan();
								}
							}
						});



					}
				}

				/**属于废弃epc*/
				scrapEpcList.add(epc);

			}
		});
	}
}
