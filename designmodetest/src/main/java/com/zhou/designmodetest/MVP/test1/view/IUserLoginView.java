package com.zhou.designmodetest.MVP.test1.view;

import com.zhou.designmodetest.MVP.test1.bean.User;

/**
 * view实现接口
 * Created by zhou on 2016/7/25.
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearInput();

    void showLoading();

    void hideLoading();

    void showLoginSuggess(User user);

    void showLoginFailed();

}
