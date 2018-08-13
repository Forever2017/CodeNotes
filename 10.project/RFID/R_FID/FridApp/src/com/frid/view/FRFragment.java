package com.frid.view;

import com.frid.fridapp.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint("ValidFragment") 
public class FRFragment extends Fragment {
	public View FRVIEW = null;
	private int Layout;
	
	/**是否当前显示页*/
	public boolean MAIN_FACE;
	
	public FRFragment(int Layout) {
		this.Layout = Layout;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		FRVIEW = inflater.inflate(Layout, (ViewGroup) getActivity().findViewById(R.id.id_viewpager), false);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 这句话必须加
		ViewGroup p = (ViewGroup) FRVIEW.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		init();
		return FRVIEW;
        
	}
	
	
	/**用来做页面操作代码*/
	public void init() {}

	public View findViewById(int id) {
		return FRVIEW.findViewById(id);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			//fragment可见时执行加载数据或者进度条等
			qryDataFromServer();
			MAIN_FACE = true;
		} else {
			//不可见时不执行操作
			MAIN_FACE = false;
		}
	}
	private void qryDataFromServer() {

	}

	public void Toast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
	}

	@Override  
	public void onSaveInstanceState(Bundle outState) {  
		//修正Android的BUG，更正点击Home键退出
		outState.putString("KEY",  "NOT_BUG");  
		super.onSaveInstanceState(outState);  
	} 
}