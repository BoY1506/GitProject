package com.zhou.mvpapp.bean;

/**
 * 验证码返回实体类
 * Created by zhou on 2016/9/2.
 */
public class SmsCode {

    private int code;//验证码
    private boolean sms_state;//发送状态

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSms_state() {
        return sms_state;
    }

    public void setSms_state(boolean sms_state) {
        this.sms_state = sms_state;
    }

}
