package joker.run.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="RunRecord")  
public class RunRecord {
	// 用注解标示字段和表中的字段来对应，id=true表示该字段为主键。
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "epc")
    private String epc;

    @DatabaseField(columnName = "name")
    private String name;
    
    @DatabaseField(columnName = "sumTurn")
    private String sumTurn;//总圈数
    @DatabaseField(columnName = "sumDistance")
    private String sumDistance;//总距离
    @DatabaseField(columnName = "time")
    private String time;//用时
    @DatabaseField(columnName = "pace")
    private String pace;//配速   总距离/用时
    
    //当前圈用时、上一圈用时
    private int current;
    private int last;
    private int tempTime;//记录秒针
    
    public RunRecord() {
    	
    	
	}
    
    
    
	public RunRecord(String epc, String name) {
		super();
		this.epc = epc;
		this.name = name;
		this.sumTurn = "0";
		this.sumDistance = "0";
		this.time = "0";
		this.pace = "0";
		this.current = 0;
		this.last = 0;
		this.tempTime = 0;
	}



	public int getTempTime() {
		return tempTime;
	}



	public void setTempTime(int tempTime) {
		this.tempTime = tempTime;
	}



	public int getCurrent() {
		return current;
	}



	public void setCurrent(int current) {
		this.current = current;
	}



	public int getLast() {
		return last;
	}



	public void setLast(int last) {
		this.last = last;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEpc() {
		return epc;
	}
	public void setEpc(String epc) {
		this.epc = epc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSumTurn() {
		return sumTurn;
	}
	public void setSumTurn(String sumTurn) {
		this.sumTurn = sumTurn;
	}
	public String getSumDistance() {
		return sumDistance;
	}
	public void setSumDistance(String sumDistance) {
		this.sumDistance = sumDistance;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPace() {
		return pace;
	}
	public void setPace(String pace) {
		this.pace = pace;
	}
	
    

}
