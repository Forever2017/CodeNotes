package com.frid.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GsonStock extends GsonState {

	@SerializedName(value = "stockCountTasks",alternate = {"deliverymanList","productList", "stockTransferInfoList", "stockTransferDetail"})  
	private List<GsonItem> list;

	public List<GsonItem> getList() {
		return list;
	}

	public void setList(List<GsonItem> list) {
		this.list = list;
	}
}
