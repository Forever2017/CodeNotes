package com.frid.ui.fragment;

import java.util.ArrayList;
import java.util.List;
import joker.kit.base.FragmentJoker;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.frid.adapter.PListAdapter;
import com.frid.data.TestMsg;
import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonStock;
import com.frid.tool.ASHttp;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.ui.Check;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class ListFragment extends FragmentJoker implements OnRefreshListener,OnItemClickListener {
	public List<GsonItem> mFRListItem;
	public PullToRefreshListView mPullRefreshListView;
	public PListAdapter mAdapter;
	public ListFragment() { super(R.layout.fragment_list); }

	@Override
	public void init() {
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.CheckList);
		// 设置一个侦听器被调用时应该刷新列表。
		mPullRefreshListView.setOnRefreshListener(this);
		mPullRefreshListView.setOnItemClickListener(this);

		mFRListItem = new ArrayList<GsonItem>();
		mAdapter = new PListAdapter(getActivity(), mFRListItem);
		mPullRefreshListView.setAdapter(mAdapter);
  
		
		data();
	}

	public void data() {
		ASHttp.GetStockCountTasks(getActivity(),new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("GetStockCountTasks", msg);
				if(b){
					
					/**这里需要做逻辑操作*/
					GsonStock gc = new Gson().fromJson(msg, GsonStock.class);

					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/
						mFRListItem.clear();
						mFRListItem.addAll(gc.getList());
						mAdapter.notifyDataSetChanged();
						//停止刷新的状态
						mPullRefreshListView.onRefreshComplete();
					}
				}

			};

		} );
	}

	/** 设置一个侦听器被调用时应该刷新列表。*/
	@Override
	public void onRefresh(PullToRefreshBase refreshView) {
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
				DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// 更新LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// 工作在这里刷新列表。
		data();
	} 

	@Override 
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		/**Toast("点击的ID : "+mFRListItem.get(position-1).getId());*/ 
		Intent intent = new Intent(getActivity(),Check.class);
		intent.putExtra("StockCountCode", mFRListItem.get(position-1).getId());
		startActivity(intent);
		
		VIEW_REFRESH = true;

	}

	/**
	 * 重新进入时刷新，用以上传成功回来后进行更新数据*/
	private boolean VIEW_REFRESH = false;
	@Override
	public void onResume() {
		super.onResume();
		if(VIEW_REFRESH) {
			data();
			VIEW_REFRESH = false;
		}
	}
}
