package com.frid.data;

public class AppData {
	public static final  String deviceCode = "A001"; //设备ID 临时先放一个
	public static final  int permissions = 1; //permissions身份默认值（当返回为空的时候，赋值）

	
	public static final String SP = "com.frid.data";
	/**服务器地址*/
	//	public static String Server = "http://47.97.223.76:80/api/security/"+deviceCode;
	public static String Server = "http://47.97.223.76:80/api";
   
	//========Service方法========
	//【登录】
	/**登录*/   
	public static final String Login = "/security/"+deviceCode+"/login";

	/** 注销 Logout */
	public static final String Logout = "/security/"+deviceCode+"/logout";

	/** 获取新Token GetNewToken */
	public static final String GetNewToken = "/security/"+deviceCode+"/GetNewToken";
	//【入库\盘点 单】
	/** 获取盘点任务列表  GetStockCountTasks */
	public static final String GetStockCountTasks = "/warehouse/Getstockcounttasks";

	/** 获取盘点RFID 详情列表  GetStockCountRfidList */
	public static final String GetStockCountRfidList = "/warehouse/Getstockcountrfidlist";

	/** 上传【盘点】结果   详情列表  UploadStockCountResult */
	public static final String UploadStockCountResult = "/warehouse/uploadstockcountresult";
	//【保险箱】
	/** 待移库列表 =QueryMyListOfStockTransfer========*/
	public static final String queryMyMist = "/warehouse/stocktransferrequest/querymylist";
	/** =待移库 -》确认移库=== =InConfirmOfStockTransfer========*/
	public static final String inconfirm = "/warehouse/stocktransferrequest/inconfirm";
	/** ==保险箱 列表== QueryProductItemList== ========*/
	public static final String queryproductitemlist = "/warehouse/queryproductitemlist";
	/** ===返库===  confirmstockback========直接返库，不用同步  */
	public static final String confirmstockback = "/warehouse/confirmstockback";		
	//【核对】
	/** 核对单列表 querylist*/
	public static final String QueryList = "/warehouse/stocktransferrequest/querylist";

	/** 核对单详情（商品列表） querydetail*/
	public static final String QueryDetail = "/warehouse/stocktransferrequest/querydetail";

	/** 上传核实订单 （提交核实，传一堆EPC上去） UploadEpcOfStockTransfer*/
	public static final String UploadEpc = "/warehouse/stocktransferrequest/uploadepc";
	//【其他】
	/** 检查RFID 通过EPC的ID得到商品信息   CheckRfid*/
	public static final String CheckRfid = "/warehouse/checkrfid";
	/** 获取运单号list（用来核对手输的运单号）*/
	public static final String GetWaybillList = "/warehouse/getwaybilllist";
	/** =仓库同步====WarehouseSync====*/
	public static final String WarehouseSync = "/sync/productitems";
	/** =上载操作日志===UploadOperationLog===*/
	public static final String UploadOperationLog = "/sync/operationlogs";
}