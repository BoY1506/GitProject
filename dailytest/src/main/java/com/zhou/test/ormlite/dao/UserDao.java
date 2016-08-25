package com.zhou.test.ormlite.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.zhou.test.ormlite.bean.User;
import com.zhou.test.ormlite.db.DatabaseHelper;

import java.sql.SQLException;

/**
 * 虽然ORM帮我们封装了DAO，但我们可以再次封装一下，使用自定义风格的DAO
 * Created by zhou on 2016/7/29.
 */
public class UserDao {

    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
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

    /**
     * 删除一个用户
     *
     * @param id
     */
    public void delete(int id) {
        try {
            userDaoOpe.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新一个用户
     *
     * @param user
     */
    public void update(User user) {
        try {
            userDaoOpe.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //。。。各种查询操作
}
