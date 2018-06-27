package 7.小代码;

public class _一行代码碎片{

	private void content() {
		//1.清空手机上Cookie
		CookieSyncManager.createInstance(getApplicationContext());
		CookieManager.getInstance().removeAllCookie();


	}
}
