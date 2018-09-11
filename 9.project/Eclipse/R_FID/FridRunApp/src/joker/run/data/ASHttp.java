package joker.run.data;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import joker.run.sqliteorm.RunRecord;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


public class ASHttp {
	private static AsyncHttpClient client = new AsyncHttpClient();  
	static {  
		/**设置链接超时，如果不设置，默认为10s*/  
		client.setTimeout(5000); 
	}  
	/** epc list
	 *  */
	public static void downloadrfidinfo(Context context,final AsyncHttp Asyn){
		/*{
			　　"Token":"BF2C1E181F65446C940A6A31327E6B84",
			　　"DeviceCode":"A001"
			}*/
		StringEntity se = JsonTool.createJson(new String[]{"Token",HOST.Token},new String[]{"DeviceCode",HOST.DeviceCode});

		request(context,"downloadrfidinfo",se,Asyn);
	}

	public static void uploadtcresult(Context context,String uploadMsg,final AsyncHttp Asyn){
		StringEntity se = null;
		try {
			se = new StringEntity(uploadMsg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request(context,"uploadtcresult",se,Asyn);
	}



	/**获取 上传的【盘点】结果 
	 * String StockCountCode  盘点单ID */
	public String getResultJson(List<RunRecord> list) {
		return new Gson().toJson(new Result(list));
	}

	class Result{
		private String trackDate;
		private String Token;
		private String DeviceCode;
		private List<ResultItem> result = new ArrayList<ResultItem>();

		public Result(List<RunRecord> list) {
			// "trackDate":"2018-09-26T18:52:36.263"
			this.trackDate = getCurrentDate();
			this.Token = HOST.Token;
			this.DeviceCode = HOST.DeviceCode;
			for (RunRecord rr : list) 
				result.add(
						new ResultItem(rr.getEpc(), rr.getLap(), rr.getSumTurn(), rr.getTime())
						);
		}
	}

	class ResultItem{
		private String epc;
		private String DistancePerCycle;
		private String CycleCount;
		private String Duration;

		public ResultItem(String epc, String distancePerCycle,
				String cycleCount, String duration) {
			super();
			this.epc = epc;
			DistancePerCycle = distancePerCycle;
			CycleCount = cycleCount;
			Duration = duration;
		}
	}
	/**
	 * 获取当前时间
	 * 2018-04-07T16:24:27.8129704+08:00
	 * */
	@SuppressLint("SimpleDateFormat") 
	public static String getCurrentDate() {
		Date date = new Date();//2018-04-10T15:44:55.811+0800
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
		String str = df.format(date);
		return str;
	}

	//【方法】
	public static void request (final Context context,String method,StringEntity se,final AsyncHttp Asyn){
		client.post(context, "http://47.97.223.76/api/timecount/"
				+method,se,"application/json", 

				new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONArray arg0) {
				super.onSuccess(arg0);
				Asyn.onResult(true,arg0.toString());
			}

			@Override
			public void onSuccess(JSONObject arg0) {
				super.onSuccess(arg0);
				Asyn.onResult(true,arg0.toString());
			}

			public void onSuccess(String arg0) {
				super.onSuccess(arg0);
				Asyn.onResult(true,arg0.toString());
			} 

			@Override
			@SuppressWarnings("deprecation")
			public void onFailure(Throwable th) {
				super.onFailure(th);

				if(th!=null&&th.getMessage().equals("Unauthorized")){
					//401 未经授权的
					Toast.makeText(context, "未经授权的操作！", 0).show();
					//					context.startActivity(new Intent(context,LoginActivity.class));
					//					FridApplication.killActivity();
				}

				Toast.makeText(context, "Server Error："+th.toString(), 0).show();
				Asyn.onResult(false,th.toString());


			}








		});
	}

	public static abstract class AsyncHttp{
		public void onResult(boolean b,String msg){};
	}


}
