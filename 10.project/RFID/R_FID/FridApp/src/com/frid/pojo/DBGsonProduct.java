package com.frid.pojo;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//用注解标示该类和表user来映射，如果不写tableName则会默认用该类的类名来当做表名。  
@DatabaseTable(tableName = "box_product")
public class DBGsonProduct {
	/** =====商品状态==== */
	/** 正常 */
	public final static int INSTOCK = 2;
	/** 展示中 */
	public final static int SHOWING = 5;
	/** 已售出 */
	public final static int SOLD = 6;
	/** 异常 */
	public final static int ABNORMAL = 7;
	/** 丢失 */
	public final static int LOST = 8;
	/** ID损坏 */
	public final static int LABLEBROKEN = 9;
	/*
	 * { 　　　　　　"productCode":"P0001", 　　　　　　"subProductCode":"SP001",
	 * 　　　　　　"productExternalId":"3608d039794d4a1bb4df2d15e3bd01d2",
	 * 　　　　　　"productName":"NO1 Product", 　　　　　　"price":10,
	 * 　　　　　　"epc":"E0000001",
	 * 　　　　　　"rfidCode":"a30de80143224324b49d142ca19c22c0", 　　　　　　"status":2 }
	 */

	// 用注解标示字段和表中的字段来对应，id=true表示该字段为主键。
	@DatabaseField(id = true)
	@SerializedName(value = "productExternalId", alternate = { "pppppp" })
	private String id;

	// 普通字段则不用写括号
	@DatabaseField
	@SerializedName(value = "productName", alternate = { "qqqqqq" })
	private String name;

	@DatabaseField
	@SerializedName(value = "epc", alternate = { "eeeeee" })
	private String epc;

	@DatabaseField
	@SerializedName(value = "status", alternate = { "ssssss" })
	private int state = 0;

	@DatabaseField
	private String InputWaybillNumber;// 运单号 有必要存储到数据库

	private int isExist = 0;// 扫描时，是否存在 0 不存在 1存在

	public DBGsonProduct() {
		/* ORMLite建表的类需要有一个无参数的构造函数 */
	}

	public DBGsonProduct(String id, String name, String epc, int state) {
		super();
		this.id = id;
		this.name = name;
		this.epc = epc;
		this.state = state;
		this.InputWaybillNumber = "";// 运单号默认为空
	}

	public int getIsExist() {
		return isExist;
	}

	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEpc() {
		return epc;
	}

	public void setEpc(String epc) {
		this.epc = epc;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getInputWaybillNumber() {
		return InputWaybillNumber;
	}

	public void setInputWaybillNumber(String inputWaybillNumber) {
		InputWaybillNumber = inputWaybillNumber;
	}
}
