package com.frid.view;

import com.frid.data.FridApplication;

import cn.pedant.SweetAlert.CallDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
/**
 * 老爹Activity 大多数继承
 * */
public class RFActivity extends Activity{
	private SweetAlertDialog Loadingdialog;//加载动画.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FridApplication.SUM_LIST.add(this);
	}
	
	public void Loading(String title) {

		Loadingdialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
		.setTitleText(title==null?"加载中..":title);
		Loadingdialog.show();
		Loadingdialog.setCancelable(false);
	}

	/** 
	 * String  msg    窗口显示文字
	 * boolean state  状态 显示动画不同
	 * CallDialog cd  窗口自动关闭后的回调
	 * */ 
	public void LoadingClose(String msg,boolean state,CallDialog cd) {
		if(Loadingdialog!=null){
			if(state) 
				Loadingdialog.setTitleText(msg==null?"成功":msg).changeAlertType(SweetAlertDialog.SUCCESS_TYPE_CLOSE,cd);
			else 
				Loadingdialog.setTitleText(msg==null?"失败":msg).changeAlertType(SweetAlertDialog.ERROR_TYPE_CLOSE,cd);
		}
	}

	public void Toast(String string) {
		Toast.makeText(getApplicationContext(), string==null?"未知提示":string, Toast.LENGTH_SHORT).show();
	}
}
