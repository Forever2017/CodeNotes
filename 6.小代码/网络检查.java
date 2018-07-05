package com.ui;

public class 网络检查 {

	//网络检查
	public class NetworkUtils {

		public static final int NETWORN_NONE = 0;
		public static final int NETWORN_WIFI = 1;//WIFI
		public static final int NETWORN_MOBILE = 2;//3G

		public static int getNetworkState(Context context){
			ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			//Wifi
			State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			if(state == State.CONNECTED||state == State.CONNECTING){
				return NETWORN_WIFI;
			}

			//3G
			state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if(state == State.CONNECTED||state == State.CONNECTING){
				return NETWORN_MOBILE;
			}
			return NETWORN_NONE;
		}
	}

}
