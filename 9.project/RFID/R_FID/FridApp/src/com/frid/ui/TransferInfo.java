package com.frid.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.frid.adapter.MItemAdapter;
import com.frid.adapter.TransferListAdapter;
import com.frid.data.TestMsg;
import com.frid.fridapp.R;
import com.frid.pojo.DBLog;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonStock;
import com.frid.tool.ASHttp;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.tool.SynchTool;
import com.frid.view.NormalTitleBar;
import com.frid.view.RFActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
//querydetail
public class TransferInfo extends RFActivity implements OnClickListener {
	private NormalTitleBar ntb;
	private ListView listView;
	private MItemAdapter mAdapter;
	private List<GsonItem> list;

	private Button CheckUpload;
	private FrameLayout CheckLinearButtons;

	private int Type;
	private String externalId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		Type = getIntent().getIntExtra("Type", 0);
		externalId = getIntent().getStringExtra("externalId");
		init();
		data();
	}
	private void init() {
		ntb = (NormalTitleBar) findViewById(R.id.CheckTitle);
		ntb.setLeftVisible(true);
		ntb.setTitle("待接收商品");
		listView = (ListView) findViewById(R.id.CheckAList);
		CheckUpload = (Button) findViewById(R.id.CheckUpload);
		CheckLinearButtons = (FrameLayout) findViewById(R.id.CheckLinearButtons);

		CheckUpload.setText("确认接收");
		CheckUpload.setOnClickListener(this);
		CheckLinearButtons.setVisibility(View.GONE);

		if(Type!=2) CheckUpload.setVisibility(View.GONE);

		list = new ArrayList<GsonItem>();
		mAdapter = new MItemAdapter(this, list,0);
		listView.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();

	}

	private void data() {
		ASHttp.QueryDetail(this,externalId, new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("querydetail", msg);
				if(b){
					Gson g = new Gson();
					GsonStock gc = g.fromJson(msg, GsonStock.class);
					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
						list.clear();
						list.addAll(correctList(gc.getList()));
						mAdapter.notifyDataSetChanged();
					}
				}
			};
		} );
	}
	@Override
	public void onClick(View CheckUpload) {
		ASHttp.Inconfirm(this,externalId,new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("Success", msg);
				if(b){//{"responseCode":"0000","responseMessage":"Success"}
					Gson g = new Gson();
					GsonStock gc = g.fromJson(msg, GsonStock.class);
					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
						/*打LOG*/
						new SynchTool(TransferInfo.this).saveLog(externalId, DBLog.INCONFIRMED);
						Toast("接收成功.");
						finish();
					}else Toast("Error:"+gc.getResponseMessage());
				}
			};

		} );
	}

	/**修正LIST，主要是商品数量拆分为多个商品*/
	private List<GsonItem>  correctList(List<GsonItem> gi) {
		List<GsonItem> temp = new ArrayList<>();
		if(gi==null||gi.size()==0) return temp;

		for (GsonItem gsonItem : gi) {
			int i = Integer.parseInt(gsonItem.getNumber());
			gsonItem.setNumber(gsonItem.getId());

			if(i>1){//多个商品
				for (int j = 0; j < i; j++)
					temp.add((GsonItem) gsonItem.clone());
			}else//1个商品
				temp.add(gsonItem);
		}
		return temp;
	}

}
