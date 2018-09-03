package com.frid.data;

import android.widget.EditText;

public class TestMsg {
	/**是否使用测试数据*/
	public static boolean Switch = true;
	
//	public static String TestToken = "D71DBD9608E94C4C8FFC5C99E4146DA1";

	public static String updateMSG(String type,String msg){
		if(Switch == false) return msg;

		switch (type) { 
		case "QueryList"://核对单列表 querylist(选择列表)
			msg = "{\"stockTransferInfoList\":[{\"externalId\":\"0000001\",\"deliveryMan\":\"测试：Bat Man\",\"outWarehouseName\":\"DefaultWarehouse\",\"inWarehouseName\":\"Android Test Warehouse\",\"status\":0,\"createdOn\":\"2018-03-25T19:32:38.54\",\"updatedOn\":\"2018-03-25T19:32:38.573\"}],\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;

		case "querydetail"://核对单 商品列表 详情
			msg = "{\"fromWarehouseCode\":\"W001\",\"toWarehouseCode\":\"W002\",\"deliveryMan\":\"Bat Man\",\"stockTransferDetail\":[{\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X153\",\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productName\":\"测试：老干妈豆腐乳\",\"requestNumber\":5},{\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X154\",\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productName\":\"测试：用不坏电水壶\",\"requestNumber\":1},{\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X155\",\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productName\":\"测试：飞利浦剃须刀\",\"requestNumber\":3},{\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X156\",\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productName\":\"测试：北京面便面\",\"requestNumber\":9}],\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;

		case "GetStockCountTasks"://获取盘点任务
			msg = "{\"stockCountTasks\":[{\"stockCounttype\": 0,\"stockCountCode\":\"DE04E9B858SAGASFWEFCD58A3B995A68\",\"comment\":\"测试：入库盘点7E075BAAAAAAAAAE472D5CDE1BCF\"},{\"stockCounttype\": 1,\"stockCountCode\":\"DE04ESADFASGWFWEF58A3B995A68\",\"comment\":\"测试：入库盘点7E075BDDRRRRRR2D5CDE1BCF\"},{\"stockCounttype\": 0,\"stockCountCode\":\"DE04E9B8GDSFGASFSADFB995A68\",\"comment\":\"测试：入库盘点7E075BFFFFFFFF72D5CDE1BCF\"},{\"stockCounttype\": 1,\"stockCountCode\":\"DE04E9B8581EASDFASDFASDF3B995A68\",\"comment\":\"测试：入库盘点7E075BDWWWWWWWW72D5CDE1BCF\"},{\"stockCounttype\": 2,\"stockCountCode\":\"DE04E9B8581EASDFASDFASDF3B995A68\",\"comment\":\"测试：入库盘点7E075BDD22SSSSSSSSSBCF\"},{\"stockCounttype\": 2,\"stockCountCode\":\"DE04E9B8581EASDFASDFASDF3B995A68\",\"comment\":\"测试：入库盘点7E075BDD2204XXXXXX5CDE1BCF\"}],\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;

		case "QueryMyList"://待待接收列表
			msg = "{\"stockTransferInfoList\":[{\"externalId\":\"测试：0000001\",\"deliveryMan\":\"Bat Man\",\"outWarehouseName\":\"DefaultWarehouse\",\"inWarehouseName\":\"Android Test Warehouse\",\"status\":2,\"createdOn\":\"2018-03-25T19:32:38.54\",\"updatedOn\":\"2018-03-25T19:32:38.573\"},{\"externalId\":\"测试：0000066\",\"deliveryMan\":\"Bat Man\",\"outWarehouseName\":\"DefaultWarehouse\",\"inWarehouseName\":\"Android Test Warehouse\",\"status\":1,\"createdOn\":\"2018-03-25T19:32:38.54\",\"updatedOn\":\"2018-03-25T19:32:38.573\"},{\"externalId\":\"测试：0000099\",\"deliveryMan\":\"Bat Man\",\"outWarehouseName\":\"DefaultWarehouse\",\"inWarehouseName\":\"Android Test Warehouse\",\"status\":0,\"createdOn\":\"2018-03-25T19:32:38.54\",\"updatedOn\":\"2018-03-25T19:32:38.573\"}],\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;

		case "GetStockCountRfidList"://获取盘点RFID 详情列表  GetStockCountRfidList
			msg = "{\"productList\":[{\"productName\":\"测试：商品 1\",\"epc\":\"E28068100000003C344F5BD1\"},{\"productName\":\"测试：商品 2\",\"epc\":\"E28068100000003C344F5E0A\"},{\"productName\":\"测试：商品 3\",\"epc\":\"E28068100000003C344F5BE5\"},{\"productName\":\"测试：商品 4\",\"epc\":\"E28068100000003C344F5BBE\"},{\"productName\":\"测试：商品 5\",\"epc\":\"E28068100000003C344F5E32\"},{\"productName\":\"测试：商品 6\",\"epc\":\"E28068100000003C344F6358\"},{\"productName\":\"测试：商品 7\",\"epc\":\"E28068100000003C344F5E1E\"}],\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;

		case "UploadEpc":///** 上传核实订单 （提交核实，传一堆EPC上去） UploadEpcOfStockTransfer*/
			msg = "{\"DeviceCode\":\"A001\",\"Epcs\":[\"E0000088\",\"E0000099\"],\"StockTransferExternalId\":\"0000099\",\"Token\":\""+FridApplication.token+"\"}";
			break;

		case "Success":// 返回成功！
			msg = "{\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";	
			break;

		case "QueryProductItemList":// 保险箱 列表
			msg = "{\"productItemList\":[{\"productCode\":\"P0001\",\"subProductCode\":\"SP001\",\"productExternalId\":\"333333039794d4a1bb4df2d15e3bd01d2\",\"productName\":\"测试：小米手机NO1 Product\",\"price\":10,\"epc\":\"E28068100000003C344F5BD1\",\"rfidCode\":\"a30de80143224324b49d142ca19c22c0\",\"status\":2},{\"productCode\":\"P0001\",\"subProductCode\":\"SP001\",\"productExternalId\":\"344444d4a1bb4df2d15e3bd01d2\",\"productName\":\"测试：马桶吸NO1 Product\",\"price\":10,\"epc\":\"E28068100000003C344F5E0A\",\"rfidCode\":\"2e09dfc5b8a6471b82fe68c1913dcb05\",\"status\":2},{\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productExternalId\":\"ad66666e164cad8d413387993a966d\",\"productName\":\"测试：笔记本NO8 Product\",\"price\":10,\"epc\":\"E28068100000003C344F5BE5\",\"rfidCode\":\"cd0060b14be54d83923a2ea4efc64517\",\"status\":2},{\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productExternalId\":\"a77777164cad8d413387993a966d\",\"productName\":\"测试：电子烟NO8 Product\",\"price\":10,\"epc\":\"E28068100000003C344F5BBE\",\"rfidCode\":\"cd0060b14be54d83923a2ea4efc64517\",\"status\":2},{\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productExternalId\":\"ad8888864cad8d413387993a966d\",\"productName\":\"测试：鼠标NO8 Product\",\"price\":10,\"epc\":\"E28068100000003C344F5E32\",\"rfidCode\":\"cd0060b14be54d83923a2ea4efc64517\",\"status\":2},{\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productExternalId\":\"ad99999cad8d413387993a966d\",\"productName\":\"测试：耐克鞋NO8 Product\",\"price\":10,\"epc\":\"E28068100000003C344F6358\",\"rfidCode\":\"cd0060b14be54d83923a2ea4efc64517\",\"status\":7},{\"productCode\":\"P0008\",\"subProductCode\":\"SP003\",\"productExternalId\":\"ad000004cad8d413387993a966d\",\"productName\":\"测试：电饭锅NO8 Product\",\"price\":10,\"epc\":\"E28068100000003C344F5E1E\",\"rfidCode\":\"cd0060b14be54d83923a2ea4efc64517\",\"status\":7}],\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";	
			break;

		case "GetWaybillList":// GetWaybillList	运单号列表
			msg = "{\"responseMessage\":\"Success\",\"responseCode\":\"0000\",\"wayBillList\":[\"789\",\"WB00009\",\"WB00008\"]}";	
			break;	

			/*getProduct   仓库同步，上传数据
			getLog       上传日志，上传数据*/


		default:
			break;
		}


		return msg;

	}
	
	/**登录测试*/
	public static void loginTest(EditText UserName,EditText PassWord){
		if(Switch == false) return;

		/*测试数据*/
		UserName.setText("Jinquan");
		PassWord.setText("123456");
	}
	
	/**模拟checkFrid**/
	public static String testCheckFrid(String epc,String msg) {
		if(Switch == false) return msg;
		
		switch (epc) {
		case "E28068100000003C344F5BD1":
			msg = "{\"productCode\":123456,\"subProductCode\":123456,\"productName\":\"测试：老干妈豆腐乳\",\"price\":2,\"reservationCode\":123456,\"supplierCode\":123456,\"supplierName\":123456,\"rfid\":123456,\"warehouse\":123456,\"status\":0,\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X153\",\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;
		case "E28068100000003C344F5E0A":
			msg = "{\"productCode\":123456,\"subProductCode\":123456,\"productName\":\"测试：用不坏电水壶\",\"price\":2,\"reservationCode\":123456,\"supplierCode\":123456,\"supplierName\":123456,\"rfid\":123456,\"warehouse\":123456,\"status\":0,\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X154\",\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;
		case "E28068100000003C344F5BE5":
			msg = "{\"productCode\":123456,\"subProductCode\":123456,\"productName\":\"测试：飞利浦剃须刀\",\"price\":2,\"reservationCode\":123456,\"supplierCode\":123456,\"supplierName\":123456,\"rfid\":123456,\"warehouse\":123456,\"status\":0,\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X155\",\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;
		case "E28068100000003C344F5BBE":
			msg = "{\"productCode\":123456,\"subProductCode\":123456,\"productName\":\"测试：北京面便面\",\"price\":2,\"reservationCode\":123456,\"supplierCode\":123456,\"supplierName\":123456,\"rfid\":123456,\"warehouse\":123456,\"status\":0,\"productExternalId\":\"7894561234ASDFGHJKLKL7898456X156\",\"responseCode\":\"0000\",\"responseMessage\":\"Success\"}";
			break;
		default:
			break;
		}

		return msg;
	}
}
