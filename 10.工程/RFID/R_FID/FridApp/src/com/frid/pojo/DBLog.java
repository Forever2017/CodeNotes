package com.frid.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="box_log")  
public class DBLog {
	/**打开保险箱，用不着*/		
	public final static int OPENSAFE = 0;
	/**移库单 确认移库 */		
	public final static int INCONFIRMED = 1;
	/**保险箱 展示产品 */		
	public final static int SHOWPRODUCT2CUSTOMER = 2;
	/**保险箱 顾客购买 */		
	public final static int CUSTOMERBUY = 3;
	/**保险箱 顾客不买 */		
	public final static int CUSTOMERNOTBUY = 4;
	/*{
	　　　　　　"RFIDCode":"a30de80143224324b49d142ca19c22c0",
	　　　　　　"Operation":2,    操作
	　　　　　　"OperationTime":"2018-04-07T16:24:27.8129704+08:00",
	　　　　　　"Comment":null
	}*/
	@DatabaseField(generatedId=true)//自增长的主键 
	private int id;
	@DatabaseField
	private String RFIDCode;
	@DatabaseField
	private int Operation;
	@DatabaseField
	private String OperationTime;
	@DatabaseField
	private String Comment;

	public DBLog() {}
	/**
		EPC的ID   private String RFIDCode; 
		操作类型       int Operation;
		时间      String OperationTime;
		其他       String Comment;
	 * */
	public DBLog(String rFIDCode, int operation, String operationTime,
			String comment) {
		super();
		RFIDCode = rFIDCode;
		Operation = operation;
		OperationTime = operationTime;
		Comment = comment;
	}
	public String getRFIDCode() {
		return RFIDCode;
	}
	public void setRFIDCode(String rFIDCode) {
		RFIDCode = rFIDCode;
	}
	public int getOperation() {
		return Operation;
	}
	public void setOperation(int operation) {
		Operation = operation;
	}
	public String getOperationTime() {
		return OperationTime;
	}
	public void setOperationTime(String operationTime) {
		OperationTime = operationTime;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
}
