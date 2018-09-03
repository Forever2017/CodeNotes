package com.frid.pojo;

import java.util.List;

/*运单号list*/
public class BillList extends GsonState{
	private List<String> wayBillList;
	
	public List<String> getWayBillList() {//Contain
		return wayBillList;
	}
	public void setWayBillList(List<String> wayBillList) {
		this.wayBillList = wayBillList;
	}
	
}
