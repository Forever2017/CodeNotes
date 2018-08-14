package com.frid.pojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GsonProductList extends GsonState{
	@SerializedName(value = "productItemList",alternate = {"list2222"})  
	private List<DBGsonProduct> list;
	
	public List<DBGsonProduct> getList() {
		return list;
	}

	public void setList(List<DBGsonProduct> list) {
		this.list = list;
	}
	
}
