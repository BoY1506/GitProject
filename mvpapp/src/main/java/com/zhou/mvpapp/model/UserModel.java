package com.zhou.mvpapp.model;

import com.zhou.mvpapp.bean.SmsCode;
import com.zhou.mvpapp.bean.User;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.UserContract;
import com.zhou.mvpapp.db.impl.TUserDAOImpl;
import com.zhou.mvpapp.net.BaseResponse;
import com.zhou.mvpapp.net.HttpCall;
import com.zhou.mvpapp.net.SimpleCallback;
import com.zhou.mvpapp.utils.CallManager;

import retrofit2.Call;

/**
 * 用户逻辑处理类
 * Created by zhou on 2016/9/2.
 */
public class UserModel implements UserContract.IUserModel {

    private static UserContract.IUserModel instance;

    /**
     * 单一实例
     */
    public static UserContract.IUserModel getInstance() {
        if (instance == null) {
            synchronized (UserContract.IUserModel.class) {
                if (instance == null) {
                    instance = new UserModel();
                }
            }
        }
        return instance;
    }

    /**
     * 获取验证码
     */
    @Override
    public void getSmsCode(String phone, final HandleListener<String> listener) {
        Call<BaseResponse<SmsCode>> call = HttpCall.getApi().getRegisterSmsCode(phone);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<SmsCode>() {
            @Override
            public void convert(String msg, SmsCode data) {
                listener.onSuccess(msg, data.getCode() + "");
            }
        });
    }

    /**
     * 用户注册
     */
    @Override
    public void userRegister(String phone, int code, String password, final HandleListener<Object> listener) {
        Call<BaseResponse<Object>> call = HttpCall.getApi().userRegister(phone, code, password);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<Object>() {
            @Override
            public void convert(String msg, Object data) {
                listener.onSuccess(msg, data);
            }
        });
    }

    /**
     * 用户登录
     */
    @Override
    public void userLogin(String phone, String password, String device, String special, final HandleListener<User> listener) {
        Call<BaseResponse<User>> call = HttpCall.getApi().userLogin(phone, password, device, special);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<User>() {
            @Override
            public void convert(String msg, User data) {
                //保存用户到本地
                TUserDAOImpl.getInstance().saveUser(data);
                listener.onSuccess(msg, data);
            }
        });
    }

}
