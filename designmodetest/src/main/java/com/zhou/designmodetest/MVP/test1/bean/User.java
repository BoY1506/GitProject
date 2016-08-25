package com.zhou.designmodetest.MVP.test1.bean;

/**
 * Model类：用户
 * bean属于用户数据，自身可实现增删改查等基本操作
 * Created by zhou on 2016/7/25.
 */
public class User {

    private String userName;
    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

}
