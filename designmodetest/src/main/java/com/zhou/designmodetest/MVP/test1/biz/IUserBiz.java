package com.zhou.designmodetest.MVP.test1.biz;

/**
 * 用户登录接口
 * Created by zhou on 2016/7/25.
 */
public interface IUserBiz {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param lintener
     */
    void login(String username, String password, OnLoginListener lintener);

}
