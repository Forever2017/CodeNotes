package github.example.db_ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBHelper {/* extends OrmLiteSqliteOpenHelper {

    // 数据库名称，会在程序的目录中生成sqlite-test.db数据库文件
    private static final String TABLE_NAME = "sqlite-test.db";
    // 数据库版本 更新用
    private static final int DATABASE_VERSION = 1;

    private static DBHelper instance;
    // 用来存放Dao的地图
    private Map<String, Dao> daos = new HashMap<String, Dao>();


    private DBHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }



    // 初次运行程序会执行该onCreate方法,如果不是初次运行程序则不会执行该方法,防止重复建表。
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //建立 User 表
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //如果不是初次运行并且DATABASE_VERSION数值增加的时候，则会执行该方法，可以在该方法中删除原来的表并建立新表，在要修改数据表结构的时候使用。
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
       *//* try {
            //删除表..
			TableUtils.dropTable(connectionSource, User.class, true);
			onCreate(sqLiteDatabase, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}*//*
    }

    *//**
     * 获取单例
     *
     * @param context
     * @return
     *//*
    public static synchronized DBHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }


    *//**
     * 通过类来获得指定的Dao
     *//*
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao;
        String className = clazz.getSimpleName();
        if (!daos.containsKey(className)) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }else {
            dao = daos.get(className);
        }
        return dao;
    }

    *//**
     * 释放资源
     *//*
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
*/
}
