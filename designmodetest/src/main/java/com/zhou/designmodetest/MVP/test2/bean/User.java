package com.zhou.designmodetest.MVP.test2.bean;

/**
 * 用户实体类
 * 自身也可进行一下简单的数据操作
 * Created by zhou on 2016/7/25.
 */
public class User {

    String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
