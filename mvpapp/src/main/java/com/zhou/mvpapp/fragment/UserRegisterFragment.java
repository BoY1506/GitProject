package com.zhou.mvpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.base.BaseFragment;
import com.zhou.mvpapp.contract.UserContract;
import com.zhou.mvpapp.presenter.UserRegisterPresenter;
import com.zhou.mvpapp.utils.DialogUtils;
import com.zhou.mvpapp.utils.EditTextUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 用户注册fragment
 * Created by zhou on 2016/9/2.
 */
public class UserRegisterFragment extends BaseFragment<UserContract.IRegisterPresenter> implements
        UserContract.IRegisterView {

    @InjectView(R.id.phone_et)
    EditText phoneEt;
    @InjectView(R.id.smscode_et)
    EditText smscodeEt;
    @InjectView(R.id.get_smscode_btn)
    Button getSmscodeBtn;
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.register_btn)
    Button registerBtn;

    View userRegisterView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userRegisterView = inflater.inflate(R.layout.fragment_user_register, container, false);
        ButterKnife.inject(this, userRegisterView);
        initView();
        return userRegisterView;
    }

    @Override
    public void initView() {

    }

    @Override
    public UserContract.IRegisterPresenter initPresenter() {
        return new UserRegisterPresenter();
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
    public String getCode() {
        return EditTextUtils.getEtText(smscodeEt);
    }

    @Override
    public String getPassword() {
        return EditTextUtils.getEtText(passwordEt);
    }

    @Override
    public void setSmsCode(String code) {
        smscodeEt.setText(code);
    }

    @Override
    public FragmentActivity getMActivity() {
        return getActivity();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.get_smscode_btn, R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_smscode_btn:
                presenter.getSmscode();
                break;
            case R.id.register_btn:
                presenter.go2Register();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
