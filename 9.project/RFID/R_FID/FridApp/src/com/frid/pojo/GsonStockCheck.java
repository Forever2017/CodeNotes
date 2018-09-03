package com.frid.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GsonStockCheck extends GsonState {

	@SerializedName(value = "stockCountTasks",alternate = {"productList", "stockTransferInfoList", "stockTransferDetail"})  
	private List<GsonItemCheck> list;

	public List<GsonItemCheck> getList() {
		return list;
	}

	public void setList(List<GsonItemCheck> list) {
		this.list = list;
	}
}
