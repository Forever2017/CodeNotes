package joker.run.sqliteorm;

import com.j256.ormlite.field.DatabaseField;

public class Epc {

    // 用注解标示字段和表中的字段来对应，id=true表示该字段为主键。
    @DatabaseField(generatedId = true)
    private String id;

    //普通列名
    @DatabaseField(columnName = "epc")
    private String epc;

    //普通列名
    @DatabaseField(columnName = "name")
    private String name;

    public Epc() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
