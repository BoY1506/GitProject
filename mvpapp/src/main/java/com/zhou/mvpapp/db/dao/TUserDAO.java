package com.zhou.mvpapp.db.dao;

import com.zhou.mvpapp.bean.User;

/**
 * 本地用户处理接口
 * Created by zhou on 2016/9/5.
 */
public interface TUserDAO {

    //保存用户
    void saveUser(User user);

    //删除用户
    void deleteUser();

    //判断用户是否登录
    boolean isUserLogined();

    //获取用户id
    int getUserId();

    //获取用户班组id
    int getUserBanzuId();

    //获取用户状态
    int getUserStatus();

    //获取用户token
    String getUserToken();

    //更新用户状态
    void updateUserStatus(int status);

    //更新用户班组id
    void updateUserBanzuId(int banzuId);

    //删除用户
    void deleteUserBanzuId();

}
