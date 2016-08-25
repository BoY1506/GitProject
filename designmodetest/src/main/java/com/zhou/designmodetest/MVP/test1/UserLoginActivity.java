package com.zhou.designmodetest.MVP.test1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zhou.designmodetest.MVP.test1.bean.User;
import com.zhou.designmodetest.MVP.test1.presenter.UserLoginPresenter;
import com.zhou.designmodetest.MVP.test1.view.IUserLoginView;
import com.zhou.designmodetest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * mvp模式小练习：用户登录
 * Activity只属于View，用于处理用户的时事件和提供界面数据，业务处理交给presenter来做
 * Created by zhou on 2016/7/25.
 */
public class UserLoginActivity extends Activity implements IUserLoginView {

    @InjectView(R.id.user_name_et)
    EditText userNameEt;
    @InjectView(R.id.user_pwd_et)
    EditText userPwdEt;
    @InjectView(R.id.login_btn)
    Button loginBtn;
    @InjectView(R.id.clear_btn)
    Button clearBtn;
    @InjectView(R.id.loading_pb)
    ProgressBar loadingPb;

    private UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.inject(this);
        presenter = new UserLoginPresenter(this);
    }

    @Override
    public String getUserName() {
        return userNameEt.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return userPwdEt.getText().toString().trim();
    }

    @Override
    public void clearInput() {
        userNameEt.setText("");
        userPwdEt.setText("");
    }

    @Override
    public void showLoading() {
        loadingPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingPb.setVisibility(View.GONE);
    }

    @Override
    public void showLoginSuggess(User user) {
        Toast.makeText(this, "登录成功：username=" + user.getUserName() + "，password=" + user.getUserPwd(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.login_btn, R.id.clear_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                presenter.login();
                break;
            case R.id.clear_btn:
                presenter.clear();
                break;
        }
    }

}
