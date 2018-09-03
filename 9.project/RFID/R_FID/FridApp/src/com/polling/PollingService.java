package com.polling;
import com.frid.tool.FTool;
import com.frid.tool.SynchTool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
public class PollingService extends Service{
	private SynchTool st;
	public static final String ACTION = "com.ryantang.service.PollingService";
	@Override
	public IBinder onBind(Intent intent) { return null; }

	@Override
	public void onCreate() {
		super.onCreate();
		st = new SynchTool(this);
	}
	
	@Override 
	public void onStart(Intent intent, int startId) {
		//具体逻辑	 	
		Log.e("[轮询]自动同步MSG：", "自动同步：   第 "+startId +" 条   Time:  "+FTool.getCurrentDate());
		//service();
		st.upload();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("Service:onDestroy");
	}
}
