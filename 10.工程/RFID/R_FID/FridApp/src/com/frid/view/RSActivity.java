package com.frid.view;
import com.frid.tool.VTool;
import com.frid.tool.VTool.CallbackVT;

import android.view.KeyEvent;
/**
 * 子类型Activity，用在少数Activity
 * */
public class RSActivity extends RFActivity {

	// 按键捕获
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 退出系统提示
	private void exit() {
		VTool.Interaction(this, "提示", "是否退出系统?", "取消退出", "确认退出", new CallbackVT() {
			@Override
			public void InteractionYes() {
				System.exit(0); 
				super.InteractionYes();
			}
		});
	}

}
