package com.ui;

public class 打电话 {

	Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneno));
	startActivity(intent);

	//同时，我们需要增加拨打电话的权限，在manifest中修改：
	<uses-permission android:name="android.permission.CALL_PHONE"/>
	
}

