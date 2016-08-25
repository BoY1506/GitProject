package com.zhou.gitproject.cityselect2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库helper类
 * Created by zhou on 2015/12/25.
 */
public class MySqliteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "search_db.db";
    private static int DB_VERSION = 1;

    public MySqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 当数据库第一次创建的时候调用需要设置数据库的表结构
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建最近访问城市表：id、内容
        db.execSQL("create table his_city(_id integer primary key autoincrement,city_id integer unique,city_name text)");
    }

    /**
     * 系统首先判断newVersion是否大于oldVersion，如果大于就执行此方法，否则执行,数据库版本升级不会重新执行onCreate()
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists his_city");
//        db.execSQL("create table his_city(_id integer primary key autoincrement,city_id integer unique,city_name text)");
    }

}
