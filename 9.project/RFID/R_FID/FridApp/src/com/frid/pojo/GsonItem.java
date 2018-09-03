package com.frid.pojo;

import com.google.gson.annotations.SerializedName;


public class GsonItem implements Cloneable {
	/*{
         "stockCountCode": "DE04E9B8581E412E98FCD58A3B995A68",
         "comment": "入库盘点7E075BDD22044A38A60E472D5CDE1BCF"
     }*/
	@SerializedName(value = "stockCountCode",alternate = {"epc", "externalId", "productExternalId","id4"})  
	private String id;

	@SerializedName(value = "comment",alternate = {"productName", "deliveryMan","name4"})  
	private String name;

	@SerializedName(value = "requestNumber",alternate = {"number222"})  
	private String number;//用来充当存储Epc号码 

	/*盘点：1=盘点找到，0=盘点未找到   用来做当前扫描状态   
	 *核实列表：1=已核实，0=未核实   用来做当前扫描状态
	 *保险箱状态：1正常 2展示中 3已售 4异常 5丢失 6标签坏了*/   
	@SerializedName(value = "status",alternate = {"stockCounttype","XXXXXXX"})  
	private int state = 0;
	
	private int current = 0;

	/* item文字颜色, 0 为不赋颜色(即正常)  */
	//private int colour = 0;

	public GsonItem(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	//基本上只有测试用到~
	public GsonItem(String id, String name,int state) {
		super();
		this.state = state;
		this.id = id;
		this.name = name;
	}
	//基本上只有测试用到~
	public GsonItem(String id, String name,int state,String number) {
		super();
		this.state = state;
		this.id = id;
		this.name = name;
		this.number = number;
	}
	//实现对象克隆
	@Override  
	public Object clone() {  
		GsonItem stu = null;  
		try{  
			stu = (GsonItem)super.clone();  
		}catch(CloneNotSupportedException e) {  
			e.printStackTrace();  
		}  
		return stu;  
	}  
	
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	/*public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}*/
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
