package github.example.db_ormlite;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


//用注解标示该类和表user来映射，如果不写tableName则会默认用该类的类名来当做表名。
@DatabaseTable(tableName = "tb_user")
public class User {

    // 用注解标示字段和表中的字段来对应，id=true表示该字段为主键。
    @DatabaseField(generatedId = true)
    private String id;

    //普通列名
    @DatabaseField(columnName = "name")
    private String name;

    //必须有空构造函数
    public User() { }






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
}
