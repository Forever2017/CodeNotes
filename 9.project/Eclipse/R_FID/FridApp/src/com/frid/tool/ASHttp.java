package com.frid.tool;
import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.frid.data.AppData;
import com.frid.data.FridApplication;
import com.frid.data.TestMsg;
import com.frid.ui.LoginActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


public class ASHttp {
	private static AsyncHttpClient client = new AsyncHttpClient();  
	static {  
		/**设置链接超时，如果不设置，默认为10s*/  
		client.setTimeout(5000); 
	}  

	//【登录】
	/** 登录 Login */
	public static void Login(Context context,String UserName,String Password,final AsyncHttp Asyn){
		/*{
		    "UserName":"Yanfeng",
		    "Password":"123456"
		}*/
		StringEntity se = JsonTool.createJson(new String[]{"UserName",UserName},new String[]{"Password",Password});
		request(context,AppData.Login,se,Asyn);
	}

	/** 注销 Logout */
	public static void Logout(Context context,String Token,final AsyncHttp Asyn){
		/*{
		    "Token":"D71DBD9608E94C4C8FFC5C99E4146DA1",
		    "DeviceCode":"T001"
		}*/
		StringEntity se = JsonTool.createJson(new String[]{"Token",Token},new String[]{"DeviceCode",AppData.deviceCode});
		request(context,AppData.Logout,se,Asyn);
	}

	/** 获取新Token GetNewToken */
	public static void GetNewToken(Context context,String RefreshToken,final AsyncHttp Asyn){
		/*{
		    "RefreshToken":"0539209CBAC94C52A945CC6E72B3C41F"
		}*/
		StringEntity se = JsonTool.createJson(new String[]{"RefreshToken",RefreshToken});
		request(context,AppData.GetNewToken,se,Asyn);
	}

	//【入库\盘点 单】
	/** 获取盘点任务   list
	 *  列表方法名：String MethodName
	 * */
	public static void GetStockCountTasks(Context context,final AsyncHttp Asyn){
		/*{
		    "Token":"D71DBD9608E94C4C8FFC5C99E4146DA1",
		    "DeviceCode":"T001"
		}*/
		//	StringEntity se = JsonTool.createJson(new String[]{"Token",TestMsg.TestToken},new String[]{"DeviceCode","T001"});
		StringEntity se = JsonTool.createJson(new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});
		request(context,AppData.GetStockCountTasks,se,Asyn);
	}

	/** 获取盘点RFID    info
	 * 详情列表  GetStockCountRfidList */
	public static void GetStockCountRfidList(Context context,String StockCountCode,final AsyncHttp Asyn){
		/*{
		    "StockCountCode":"DE04E9B8581E412E98FCD58A3B995A68",
		    "Token":"559231438C32494AB32C4F3895A7E10A",
		    "DeviceCode":"A001"
		}*/
		StringEntity se = JsonTool.createJson(new String[]{"StockCountCode",StockCountCode},new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});
		request(context,AppData.GetStockCountRfidList,se,Asyn);
	}

	/** 上传【盘点】结果    info
	 * 详情列表  UploadStockCountResult */
	public static void UploadStockCountResult(Context context,String uploadMsg,final AsyncHttp Asyn){
		StringEntity se = null;
		try {
			se = new StringEntity(uploadMsg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request(context,AppData.UploadStockCountResult,se,Asyn);
	}
	//【保险箱】
	/** 核对单列表 querylist
	 *  */
	public static void QueryMyList(Context context,final AsyncHttp Asyn){
		/*{
			　　"Status":2,
			　　"Token":"7ABB666574A94D48B6A7C55DF52F2FDB",
			　　"DeviceCode":"A001"
		  }*/
		StringEntity se = JsonTool.createJson(new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.queryMyMist,se,Asyn);
	}	
	/** =待移库 -》确认移库=== =InConfirmOfStockTransfer========*/
	public static void Inconfirm(Context context,String id,final AsyncHttp Asyn){
		/*{
			　　"StockTransferExternalId":"48094af9d795487089c88e392ea06307",
			　　"Token":"17471C97E06E44BD84C60848E52CA3A6",
			　　"DeviceCode":"A001"
			}*/

		StringEntity se = JsonTool.createJson(new String[]{"StockTransferExternalId",id},new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.inconfirm,se,Asyn);
	}
	/** 保险箱 列表=QueryProductItemList
	 *  */
	public static void QueryProductItemList(Context context,final AsyncHttp Asyn){
		/*{
			　　"Token":"BF2C1E181F65446C940A6A31327E6B84",
			　　"DeviceCode":"A001"
			}*/
		StringEntity se = JsonTool.createJson(new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.queryproductitemlist,se,Asyn);
	}		
	/** ===返库===  confirmstockback========直接返库，不用同步  */
	public static void Confirmstockback(Context context,final AsyncHttp Asyn){
		/*{
			　　"Token":"BF2C1E181F65446C940A6A31327E6B84",
			　　"DeviceCode":"A001"
			}*/
		StringEntity se = JsonTool.createJson(new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.confirmstockback,se,Asyn);
	}
	//【核对】
	/** 核对单列表 querylist
	 *  */
	public static void QueryList(Context context,final AsyncHttp Asyn){
		/*{
			"OutWarehouseCode":“”,
			"InWarehouseCode":“”,
			"Status":0,
			"Token":"7ABB666574A94D48B6A7C55DF52F2FDB",
			"DeviceCode":"A001"
		}*/
		StringEntity se = JsonTool.createJson(new String[]{"Status","0"},new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.QueryList,se,Asyn);
	}

	/** 核对单详情（商品列表） querydetail*/
	public static void QueryDetail(Context context,String id,final AsyncHttp Asyn){
		/*{
			"StockTransferExternalId":"48094af9d795487089c88e392ea06307",
			"Token":"056F7B00AE174C0FB394E0FF188A57A2",
			"DeviceCode":"A001"
		}*/

		//StringEntity se = JsonTool.createJson(new String[]{"StockTransferExternalId",id},new String[]{"Token",TestMsg.TestToken},new String[]{"DeviceCode",AppData.deviceCode});
		StringEntity se = JsonTool.createJson(new String[]{"StockTransferExternalId",id},new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.QueryDetail,se,Asyn);
	}

	/** 上传核实订单 （提交核实，传一堆EPC上去） UploadEpcOfStockTransfer*/
	public static void UploadEpc(Context context,String uploadMsg,final AsyncHttp Asyn){
		uploadMsg = TestMsg.updateMSG("UploadEpc", uploadMsg);
		StringEntity se = null;
		try {
			se = new StringEntity(uploadMsg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request(context,AppData.UploadEpc,se,Asyn);
	}
	//【其他】
	/** 检查RFID    CheckRfid========*/
	public static void CheckRfid(Context context,String EPC,final AsyncHttp Asyn){
		/*{
			　　"EPC":"E0000002",
			　　"Token":"4896028ADC5C477880E9F11292DF12EC",
			　　"DeviceCode":"A001"
			}*/

		StringEntity se = JsonTool.createJson(new String[]{"EPC",EPC},new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.CheckRfid,se,Asyn);
	}

	/** 获取运单号list（用来核对手输的运单号）*/
	public static void GetWaybillList(Context context,final AsyncHttp Asyn){
		/*{
			　　"Token":"BF2C1E181F65446C940A6A31327E6B84",
			　　"DeviceCode":"A001"
			}*/
		StringEntity se = JsonTool.createJson(new String[]{"Token",FridApplication.token},new String[]{"DeviceCode",AppData.deviceCode});

		request(context,AppData.GetWaybillList,se,Asyn);
	}

	/** =仓库同步====WarehouseSync====*/
	public static void WarehouseSync(Context context,String getProduct,final AsyncHttp Asyn){
		getProduct = TestMsg.updateMSG("getProduct", getProduct);
		StringEntity se = null;
		try {
			se = new StringEntity(getProduct);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request(context,AppData.WarehouseSync,se,Asyn);
	}

	/** =上载操作日志===UploadOperationLog===*/
	public static void UploadOperationLog(Context context,String getLog,final AsyncHttp Asyn){
		getLog = TestMsg.updateMSG("getLog", getLog);
		StringEntity se = null;
		try {
			se = new StringEntity(getLog);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request(context,AppData.UploadOperationLog,se,Asyn);
	}


	//【方法】
	public static void request (final Context context,String method,StringEntity se,final AsyncHttp Asyn){
		client.post(context, AppData.Server+method,se,"application/json", 
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
					context.startActivity(new Intent(context,LoginActivity.class));
					FridApplication.killActivity();
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
