package com.zhou.mvpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.base.BaseFragment;
import com.zhou.mvpapp.contract.UserContract;
import com.zhou.mvpapp.presenter.UserLoginPresenter;
import com.zhou.mvpapp.utils.DialogUtils;
import com.zhou.mvpapp.utils.EditTextUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 用户登录fragment
 * Created by zhou on 2016/9/2.
 */
public class UserLoginFragment extends BaseFragment<UserContract.ILoginPresenter> implements UserContract.ILoginView {

    @InjectView(R.id.phone_et)
    EditText phoneEt;
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.login_btn)
    Button loginBtn;

    View userLoginView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userLoginView = inflater.inflate(R.layout.fragment_user_login, container, false);
        ButterKnife.inject(this, userLoginView);
        initView();
        return userLoginView;
    }

    @Override
    public UserContract.ILoginPresenter initPresenter() {
        return new UserLoginPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initLoadingView() {
        loadingDialog = DialogUtils.loadingDialog(mContext);
    }

    @Override
    public String getPhone() {
        return EditTextUtils.getEtText(phoneEt);
    }

    @Override
    public String getPassword() {
        return EditTextUtils.getEtText(passwordEt);
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.login_btn)
    public void onClick() {
        presenter.go2Login();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
