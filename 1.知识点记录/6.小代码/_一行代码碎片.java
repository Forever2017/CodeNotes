package 7.小代码;

public class _一行代码碎片{

	private void content() {
		//1.清空手机上Cookie
		CookieSyncManager.createInstance(getApplicationContext());
		CookieManager.getInstance().removeAllCookie();
		//获取外部存储的路径返回绝对路径的,其实就是你的SD卡的文件路径
		public static final String SDPATH = Environment .getExternalStorageDirectory().getAbsolutePath();

	}
}
