package com.frid.ui;

import com.frid.fridapp.R;
import com.frid.view.RFActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/** Question
1、轮询上传 （自动同步）
2、核对扫描 service访问频率过高，返回数据延迟
3、设置（扫描强度设置、轮询间隔（自动同步时间）、）
*/

public class Welcome extends RFActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(Welcome.this,LoginActivity.class));
				finish();
			} 
		}, 2000);
	}
}