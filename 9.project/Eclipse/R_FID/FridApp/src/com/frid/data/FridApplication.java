package com.frid.data;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;

public class FridApplication extends Application{
	public static SharedPreferences sp;
	/**服务器地址*/
	public static String Server;//"http://47.97.223.76:80";
	public static String DeviceNumber;//A001

	//基本数据
	public static String UserID;
	public static String userName;
	public static String passWord;
	/** 客户端启动时随机产生的唯一标识*/
	public static String DEVICE_ID;

	public static String token;

	public static int Identity; /**  0仓管(盘点 核对 设置)    1送货(保险箱+设置) */

	public static int FRID_POWER;//扫描频率（最高不可超过 30 dBm）

	public static int POLLING_TIME;//轮询周期 （秒）

	public static List<Activity> SUM_LIST = null;//Activity关闭用的集合

	@Override
	public void onCreate() {
		super.onCreate();
		/**Bugly  BUG收集*/
		//CrashReport.initCrashReport(getApplicationContext(), "0000000", false);
		sp = getSharedPreferences(AppData.SP, Context.MODE_PRIVATE);
		DEVICE_ID = ((TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		refreshData();

		SUM_LIST = new ArrayList<Activity>();
	}

	public static void killActivity() {
		for (Activity activity : SUM_LIST) {
			activity.finish();
		}
	}

	/**
	 * 刷新存储数据
	 * */
	public static void refreshData() {
		Server = sp.getString("Server", "http://47.97.223.76:80");
		DeviceNumber = sp.getString("DeviceNumber", "A001");

		userName = sp.getString("userName", "");
		passWord = sp.getString("passWord", "");
		UserID = sp.getString("UserID", "");
		token = sp.getString("token", "");
		Identity = sp.getInt("Identity", 0);
		FRID_POWER = sp.getInt("FRID_POWER", 15);
		POLLING_TIME = sp.getInt("POLLING_TIME", 60);
	}

	/**
	 * 插入数据 String
	 * */
	public static void insertData(String[]...string) {
		//insertData(new String[]{"aaa","bbb"},s,s2);
		//editor.putString(strings[0], strings[1]);
		Editor editor = sp.edit();
		for (String[] ss : string) {
			editor.putString(ss[0], ss[1]);
		}
		editor.commit();
		refreshData();
	}
	/**
	 * 插入数据 String
	 * */
	public static void insertIdentity(String name,int vaule) {
		//insertData(new String[]{"aaa","bbb"},s,s2);
		//editor.putString(strings[0], strings[1]);
		Editor editor = sp.edit();
		editor.putInt(name, vaule);
		editor.commit();
		refreshData();
	}
	/**
	 * 插入数据 String
	 * */
	public static void insertIdentity(String name,String vaule) {
		//insertData(new String[]{"aaa","bbb"},s,s2);
		//editor.putString(strings[0], strings[1]);
		Editor editor = sp.edit();
		editor.putString(name, vaule);
		editor.commit();
		refreshData();
	}

}
