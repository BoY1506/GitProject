package com.zhou.mvpapp.contract;

import android.support.v4.app.FragmentActivity;

import com.zhou.mvpapp.base.IBasePresenter;
import com.zhou.mvpapp.base.IBaseView;
import com.zhou.mvpapp.bean.User;

/**
 * 用户模块契约类
 * Created by zhou on 2016/9/12.
 */
public class UserContract {

    /**
     * 注册view
     */
    public interface IRegisterView extends IBaseView {
        //获取手机号
        String getPhone();

        //获取验证码
        String getCode();

        //获取密码
        String getPassword();

        //设置验证码
        void setSmsCode(String code);

        //获取父Activity
        FragmentActivity getMActivity();
    }

    /**
     * 注册presenter
     */
    public interface IRegisterPresenter extends IBasePresenter<IRegisterView> {
        //获取验证码
        void getSmscode();

        //注册
        void go2Register();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 登录view
     */
    public interface ILoginView extends IBaseView {
        //获取手机号
        String getPhone();

        //获取密码
        String getPassword();
    }

    /**
     * 登录presenter
     */
    public interface ILoginPresenter extends IBasePresenter<ILoginView> {
        //登录
        void go2Login();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 用户逻辑处理接口
     * Created by zhou on 2016/9/2.
     */
    public interface IUserModel {
        //获取验证码
        void getSmsCode(String phone, HandleListener<String> listener);

        //用户注册
        void userRegister(String phone, int code, String password, HandleListener<Object> listener);

        //用户登录
        void userLogin(String phone, String password, String device, String special, HandleListener<User> listener);
    }

}
