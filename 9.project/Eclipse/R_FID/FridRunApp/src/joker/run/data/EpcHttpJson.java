package joker.run.data;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import joker.run.sqliteorm.Epc;

public class EpcHttpJson extends GsonState {
	
	@SerializedName(value = "epcList")  
	private List<Epc> list;

	public List<Epc> getList() {
		return list;
	}

	public void setList(List<Epc> list) {
		this.list = list;
	}



}
