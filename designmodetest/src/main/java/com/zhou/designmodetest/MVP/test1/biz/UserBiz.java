package com.zhou.designmodetest.MVP.test1.biz;

import com.zhou.designmodetest.MVP.test1.bean.User;

/**
 * 用户登录业务类
 * 业务处理实现类，根据View层提供的参数，用于处理Model层的增删改查
 * Created by zhou on 2016/7/25.
 */
public class UserBiz implements IUserBiz {

    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if (username.equals("admin") && password.equals("123456")) {
                    User user = new User();
                    user.setUserName(username);
                    user.setUserPwd(password);
                    onLoginListener.loginSuccess(user);
                } else {
                    onLoginListener.loginFailed();
                }
            }
        }).start();
    }

}
