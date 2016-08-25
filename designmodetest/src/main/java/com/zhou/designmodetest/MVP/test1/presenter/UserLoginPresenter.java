package com.zhou.designmodetest.MVP.test1.presenter;

import android.os.Handler;

import com.zhou.designmodetest.MVP.test1.bean.User;
import com.zhou.designmodetest.MVP.test1.biz.IUserBiz;
import com.zhou.designmodetest.MVP.test1.biz.OnLoginListener;
import com.zhou.designmodetest.MVP.test1.biz.UserBiz;
import com.zhou.designmodetest.MVP.test1.view.IUserLoginView;

/**
 * 控制器presenter
 * 控制层，持有业务类和View，来控制业务的操作
 * Created by zhou on 2016/7/25.
 */
public class UserLoginPresenter {

    //持有业务逻辑处理类
    private IUserBiz userBiz;
    //持有View类
    private IUserLoginView userLoginView;
    private Handler handler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userBiz = new UserBiz();
        this.userLoginView = userLoginView;
    }

    /**
     * 控制用户登录
     */
    public void login() {
        //显示进度条
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showLoginSuggess(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showLoginFailed();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    /**
     * 控制清除
     */
    public void clear() {
        userLoginView.clearInput();
    }

}
