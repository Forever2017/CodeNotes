package joker.run.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Epc")  
public class Epc {
	/** @DatabaseTable：表示定义了一个数据表，如果不指定名字，在Android中会以类名作为表名，如packageInfo就是SQLite数据库中的表名，我们也可以指定表名，
	 *  @DatabaseField：表示定义了数据中的一个字段
	 *  -> id （唯一）表示数据中的一个主键，Id字段唯一标识一行，如果要使用ID方法的查询，更新，刷新和删除，则需要这些字段。
	 *  -> generatedId（唯一）表示自动增长id，我们不需要给它赋值。其他字段，可以使用
	 *  
	 *  -> columnName 包含此字段的数据库中列的字符串名称。如果未设置，则使用带有规范化大小写的字段名称
	 *  -> canBeNull表示是否可为空
	 *  -> defaultValue = "0" 默认值
	 *  -> unique 在表中的所有行中必须是唯一的。
	 *  eg: （id = true, canBeNull = false）
	 *  eg: generatedId和id=true 不能共存
	 *  官网解释地址：http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Using
	 * */
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "epc")
    private String epc;

    //普通列名
    @DatabaseField(columnName = "name")
    private String name;
    
    @DatabaseField(columnName = "status")
    private String status;

    public Epc() {
    }   
    
    

    public Epc(String epc, String name) {
		super();
		this.epc = epc;
		this.name = name;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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
