package com.zhou.mvpapp.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.zhou.mvpapp.activity.HomeActivity;
import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.bean.User;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.UserContract;
import com.zhou.mvpapp.model.UserModel;

/**
 * 用户登录presenter
 * Created by zhou on 2016/9/6.
 */
public class UserLoginPresenter extends BasePresenter<UserContract.ILoginView, UserContract.IUserModel>
        implements UserContract.ILoginPresenter {

    @Override
    protected void createModel() {
        model = UserModel.getInstance();
    }

    /**
     * 登录
     */
    @Override
    public void go2Login() {
        if (checkRegisterParams(view.getPhone(), view.getPassword())) {
            view.showLoadingViews();
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取手机型号和系统版本号;
            String device = android.os.Build.MODEL + "," + android.os.Build.VERSION.RELEASE;
            //获取设备标示imei;
            String special = tm.getDeviceId();
            model.userLogin(view.getPhone(), view.getPassword(), device, special, new HandleListener<User>() {
                @Override
                public void onSuccess(String msg, User data) {
                    view.hideLoadingViews();
                    intent2Activity(HomeActivity.class);
                }
            });
        }
    }

    /**
     * 检查参数
     */
    private boolean checkRegisterParams(String phone, String pwd) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            view.showToast(TextUtils.isEmpty(phone) ? "请输入手机号" : "请输入密码");
            return false;
        } else if (phone.length() < 11 || pwd.length() < 6) {
            view.showToast(TextUtils.isEmpty(pwd) ? "手机号格式有误" : "密码错误");
            return false;
        }
        return true;
    }

}
