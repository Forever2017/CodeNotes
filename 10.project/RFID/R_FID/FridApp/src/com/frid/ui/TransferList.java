package com.frid.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.frid.adapter.TransferListAdapter;
import com.frid.data.TestMsg;
import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonStock;
import com.frid.tool.ASHttp;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.view.NormalTitleBar;
import com.frid.view.RFActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

/*待移库/list列表*/
public class TransferList extends RFActivity implements OnRefreshListener,OnItemClickListener {
	private NormalTitleBar ntb;
	private List<GsonItem> list;
	private PullToRefreshListView listView;
	private TransferListAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_list);
		init();
	}
	@SuppressWarnings("unchecked")
	private void init() {
		ntb = (NormalTitleBar) findViewById(R.id.ListTitle);
		ntb.setLeftVisible(true);
		ntb.setTitle("待移库单");
		list = new ArrayList<GsonItem>();
		listView = (PullToRefreshListView) findViewById(R.id.CheckList);
		mAdapter = new TransferListAdapter(this, list);
		// 设置一个侦听器被调用时应该刷新列表。
		listView.setOnRefreshListener(this);
		listView.setOnItemClickListener(this);
		listView.setAdapter(mAdapter);

		data();
	} 

	private void data() {
		ASHttp.QueryMyList(this,new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("QueryMyList", msg);
				if(b){
					/**这里需要做逻辑操作*/
					GsonStock gc = new Gson().fromJson(msg, GsonStock.class);

					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/

						list.clear();
						list.addAll(gc.getList());
						mAdapter.notifyDataSetChanged();
						//停止刷新的状态
						listView.onRefreshComplete();
					}
				}
			};
		} );
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent = new Intent(this,TransferInfo.class);
		intent.putExtra("Type", list.get(position-1).getState());
		intent.putExtra("externalId", list.get(position-1).getId());
		startActivity(intent);
	}
	@Override
	public void onRefresh(PullToRefreshBase refreshView) {
		String label = DateUtils.formatDateTime(this, System.currentTimeMillis(),
				DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// 更新LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// 工作在这里刷新列表。
		data();
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		data();
	}
}
