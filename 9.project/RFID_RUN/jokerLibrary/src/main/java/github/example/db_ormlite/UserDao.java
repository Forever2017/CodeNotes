package github.example.db_ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DBHelper helper;

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = DBHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param user
     */
    public void add(User user) {
        try {
            userDaoOpe.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //删除一条记录
    public void del(User user) {
        try {
            userDaoOpe.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新一条记录
    public void update(User user) {
        try {
            userDaoOpe.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新一条记录
    public List<User> queryList() {
        List<User> userList = null;
        try {
            userList = userDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    //...other operations

}
