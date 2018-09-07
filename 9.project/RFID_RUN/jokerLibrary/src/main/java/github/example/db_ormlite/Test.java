package github.example.db_ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Test {


    public void test(Context context) throws SQLException {

       /**  jar下载地址   http://ormlite.com/releases/       */


        Dao userDao = DBHelper.getHelper(context).getDao(User.class);

        User user = new User();

        //添加一条记录
        userDao.create(user);
        //删除一条记录
        userDao.delete(user);
        //更新一条记录
        userDao.update(user);

        //查询所有记录
        List<User> users = userDao.queryForAll();


        // .... 等等 方法

        /** 或者用Dao封装来用..*/

        UserDao uDao = new UserDao(context);
        //添加一条记录
        uDao.add(user);
        //删除一条记录
        uDao.del(user);
        //更新一条记录
        uDao.update(user);

        //查询所有记录
        List<User> userList = uDao.queryList();

    }

}
