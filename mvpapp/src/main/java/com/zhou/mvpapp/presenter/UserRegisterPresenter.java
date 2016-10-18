package com.zhou.mvpapp.presenter;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.UserContract;
import com.zhou.mvpapp.model.UserModel;

/**
 * 用户注册presenter
 * Created by zhou on 2016/9/2.
 */
public class UserRegisterPresenter extends BasePresenter<UserContract.IRegisterView, UserContract.IUserModel>
        implements UserContract.IRegisterPresenter {

    @Override
    protected void createModel() {
        model = UserModel.getInstance();
    }

    /**
     * 获取验证码
     */
    @Override
    public void getSmscode() {
        if (checkCodeParams(view.getPhone())) {
            view.showLoadingViews();
            model.getSmsCode(view.getPhone(), new HandleListener<String>() {
                @Override
                public void onSuccess(String msg, String data) {
                    view.hideLoadingViews();
                    view.setSmsCode(data);
                }
            });
        }
    }

    /**
     * 注册
     */
    @Override
    public void go2Register() {
        if (checkRegisterParams(view.getPhone(), view.getCode(), view.getPassword())) {
            view.showLoadingViews();
            model.userRegister(view.getPhone(), Integer.parseInt(view.getCode()),
                    view.getPassword(), new HandleListener<Object>() {
                        @Override
                        public void onSuccess(String msg, Object data) {
                            view.hideLoadingViews();
                            registerSuccess();
                        }
                    });
        }
    }

    /**
     * 注册成功
     */
    private void registerSuccess() {
        ((ViewPager) view.getMActivity().findViewById(R.id.pager)).setCurrentItem(0);
    }

    /**
     * 检查参数
     */
    private boolean checkCodeParams(String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() < 11) {
            view.showToast(TextUtils.isEmpty(phone) ? "请输入手机号" : "手机号格式有误");
            return false;
        }
        return true;
    }

    /**
     * 检查参数
     */
    private boolean checkRegisterParams(String phone, String code, String pwd) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            view.showToast(TextUtils.isEmpty(phone) ? "请输入手机号" : "请输入验证码");
            return false;
        } else if (TextUtils.isEmpty(pwd) || phone.length() < 11) {
            view.showToast(TextUtils.isEmpty(pwd) ? "请输入密码" : "手机号格式有误");
            return false;
        } else if ((code).length() < 4 || pwd.length() < 6) {
            view.showToast((code).length() < 4 ? "验证码错误" : "请输入6-16位数字或字母组合");
            return false;
        }
        return true;
    }

}
