package com.zhou.mvpapp.db.impl;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.zhou.mvpapp.bean.User;
import com.zhou.mvpapp.db.dao.TUserDAO;
import com.zhou.mvpapp.db.tables.TUser;
import com.zhou.mvpapp.utils.Logger;

/**
 * 本地用户表操作类
 * Created by zhou on 2016/9/5.
 */
public class TUserDAOImpl implements TUserDAO {

    private static TUserDAO instance;

    /**
     * 单一实例
     */
    public static TUserDAO getInstance() {
        if (instance == null) {
            synchronized (TUserDAO.class) {
                if (instance == null) {
                    instance = new TUserDAOImpl();
                }
            }
        }
        return instance;
    }

    /**
     * 保存用户
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        //先删除原数据
        deleteUser();
        //插入新数据
        TUser tUser = new TUser();
        tUser.uId = user.getI_user_id();
        tUser.name = user.getC_name();
        tUser.status = user.getI_status();
        tUser.type = user.getI_type();
        tUser.banzuId = user.getI_banzu_id();
        tUser.cidentify = user.getCidentify();
        tUser.save();
    }

    /**
     * 删除用户
     */
    @Override
    public void deleteUser() {
        new Delete().from(TUser.class).execute();
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    @Override
    public boolean isUserLogined() {
        return new Select().from(TUser.class).exists();
    }

    /**
     * 获取用户id
     *
     * @return
     */
    @Override
    public int getUserId() {
        return ((TUser) new Select().from(TUser.class).executeSingle()).uId;
    }

    /**
     * 获取用户班组id
     *
     * @return
     */
    @Override
    public int getUserBanzuId() {
        return ((TUser) new Select().from(TUser.class).executeSingle()).banzuId;
    }

    /**
     * 获取用户状态
     *
     * @return
     */
    @Override
    public int getUserStatus() {
        return ((TUser) new Select().from(TUser.class).executeSingle()).status;
    }

    /**
     * 获取用户token
     *
     * @return
     */
    @Override
    public String getUserToken() {
        return ((TUser) new Select().from(TUser.class).executeSingle()).cidentify;
    }

    /**
     * 更新用户状态
     *
     * @param status
     */
    @Override
    public void updateUserStatus(int status) {
        new Update(TUser.class).set("status", status).execute();
    }

    /**
     * 更新用户班组id
     *
     * @param banzuId
     */
    @Override
    public void updateUserBanzuId(int banzuId) {
        new Update(TUser.class).set("banzuId", banzuId).execute();
    }

    /**
     * 删除用户班组id
     */
    @Override
    public void deleteUserBanzuId() {
        new Update(TUser.class).set("banzuId", 0).execute();
    }

    public void getUser() {
        Logger.show(new Select().from(TUser.class).execute().toString());
    }

}
