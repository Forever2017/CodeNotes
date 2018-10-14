package com.frid.tool;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.frid.data.AppData;
import com.frid.data.FridApplication;
import com.frid.pojo.DBGsonProduct;
import com.frid.pojo.DBLog;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonItemCheck;
import com.google.gson.Gson;

public class JsonTool {

	
	public static StringEntity createJson(String[]...strings) {
		StringEntity strEntity = null;
		JSONObject jsonbject = new JSONObject();

		for (String[] string : strings) {
			try { 
				jsonbject.put(string[0], string[1]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}		
		try {
			strEntity = new StringEntity(jsonbject.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try { strEntity = new StringEntity("{\"state\", \"json error\"}");} catch (UnsupportedEncodingException e1) {}
		}

		return strEntity;
	}
	
	/**获取 上传的【盘点】结果 
	 * String StockCountCode  盘点单ID */
	public String getSCR(String StockCountCode,List<GsonItem> list) {
		return new Gson().toJson(new SCR(StockCountCode,list));
	}
	/**获取 上传的【核对】结果 */
	public String getEpcCheck(String StockTransferExternalId,List<GsonItemCheck> qlist) {
		return new Gson().toJson(new EPC(StockTransferExternalId,qlist));
	}
	/**获取 上传的【仓库同步】数据*/
	public String getProduct(List<DBGsonProduct> list) {
		return new Gson().toJson(new Product(list));
	}
	/**获取 上传的【操作日志】数据*/
	public String getLog(List<DBLog> list) {
		return new Gson().toJson(new TLog(list));
	}
	
	//
	class SCR{
		private String StockCountCode;
		private String Token;
		private String DeviceCode;
		private List<SCRItem> StockCountResult = new ArrayList<SCRItem>();
		
		public SCR(String StockCountCode, List<GsonItem> list) {
			this.StockCountCode = StockCountCode;
			this.Token = FridApplication.token;
			this.DeviceCode = FridApplication.DeviceNumber;
			for (GsonItem gsonItem : list) 
				StockCountResult.add(new SCRItem(gsonItem.getId(), gsonItem.getState()+""));
		}
	}
	
	class EPC{
		private String StockTransferExternalId;
		private String Token;
		private String DeviceCode;
		private List<String> Epcs = new ArrayList<String>();
		
		public EPC(String StockTransferExternalId, List<GsonItemCheck> qlist) {
			this.StockTransferExternalId = StockTransferExternalId;
			this.Token = FridApplication.token;
			this.DeviceCode = FridApplication.DeviceNumber;
			for (GsonItemCheck gsonItem : qlist) 
				Epcs.add(gsonItem.getEpc());
		}

	}
	
	class Product{
		private String Token;
		private String DeviceCode;
		private List<ProductItem> ProductItemList = new ArrayList<ProductItem>();
		
		public Product(List<DBGsonProduct> list) {
			this.Token = FridApplication.token;
			this.DeviceCode = FridApplication.DeviceNumber;
			for (DBGsonProduct dbp : list) 
				ProductItemList.add(new ProductItem(dbp.getEpc(), dbp.getInputWaybillNumber(), dbp.getState()));
		}
	}
	
	class TLog{
		private String Token;
		private String DeviceCode;
		private List<DBLog> TerminalOperationList = new ArrayList<DBLog>();
		
		public TLog(List<DBLog> list) {
			this.Token = FridApplication.token;
			this.DeviceCode = FridApplication.DeviceNumber;
			TerminalOperationList.addAll(list);
		}
	}
	
	
	//子pojo
	class SCRItem{
		private String EPC;
		private String Result;
		public SCRItem(String ePC, String result) {
			super();
			EPC = ePC;
			Result = result;
		}
		public String getEPC() {
			return EPC;
		}
		public void setEPC(String ePC) {
			EPC = ePC;
		}
		public String getResult() {
			return Result;
		}
		public void setResult(String result) {
			Result = result;
		}
	}
	
	class ProductItem{
		private String RFIDCode;
		private String InputWaybillNumber;
		private int Status;
		public ProductItem(String rFIDCode, String inputWaybillNumber,
				int status) {
			super();
			RFIDCode = rFIDCode;
			InputWaybillNumber = inputWaybillNumber;
			Status = status;
		}
		public String getRFIDCode() {
			return RFIDCode;
		}
		public void setRFIDCode(String rFIDCode) {
			RFIDCode = rFIDCode;
		}
		public String getInputWaybillNumber() {
			return InputWaybillNumber;
		}
		public void setInputWaybillNumber(String inputWaybillNumber) {
			InputWaybillNumber = inputWaybillNumber;
		}
		public int getStatus() {
			return Status;
		}
		public void setStatus(int status) {
			Status = status;
		}
		
	}
}
