package com.zhou.designmodetest.MVP.test1.biz;

import com.zhou.designmodetest.MVP.test1.bean.User;

/**
 * 登录结果接口
 * Created by zhou on 2016/7/25.
 */
public interface OnLoginListener {

    /**
     * 登录成功
     *
     * @param user
     */
    void loginSuccess(User user);

    /**
     * 登录失败
     */
    void loginFailed();

}
