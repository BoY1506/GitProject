package com.zhou.test.ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * User表（id，name，sex，age）
 * Created by zhou on 2016/7/28.
 */
@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(columnName = "_id", generatedId = true)
    private int userId;
    @DatabaseField(columnName = "_name")
    private String userName;
    @DatabaseField(columnName = "_sex")
    private String userSex;
    @DatabaseField(columnName = "_age")
    private int userAge;
    @ForeignCollectionField(columnName = "_articles")
    //一对多关联，可级联查询到用户下的所有文章
    private Collection<Article> articles;

    public User() {

    }

    public User(String userName, String userSex, int userAge) {
        this.userName = userName;
        this.userSex = userSex;
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
