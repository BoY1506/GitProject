package com.zhou.test.ormlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zhou.test.ormlite.bean.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * dbHelper类，单例
 * Created by zhou on 2016/7/28.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //数据库名
    private static final String TABLE_NAME = "user_test.db";
    //单例
    private static DatabaseHelper instance;
    //每张表对应一个DAO，ORM会帮我们自动创建各种增删改查方法，在helper里存放各个DAO的map集合
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }

    /**
     * 创建数据库
     *
     * @param sqLiteDatabase
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //创建表
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据库
     *
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得userDao
     *
     * @param clazz
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            //已经有此DAO
            dao = daos.get(className);
        } else {
            //还没创建此DAO
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
