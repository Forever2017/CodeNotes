package joker.run.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Epc")  
public class Epc {

    // 用注解标示字段和表中的字段来对应，id=true表示该字段为主键。
    @DatabaseField(generatedId = true)
    private int id;

    //普通列名
    @DatabaseField(columnName = "epc")
    private String epc;

    //普通列名
    @DatabaseField(columnName = "name")
    private String name;

    public Epc() {
    }
    
    

    public Epc(String epc, String name) {
		super();
		this.epc = epc;
		this.name = name;
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
}
