package com.zhou.gitproject.smscode.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * 第一种方法：利用CountDownTimer类实现
 * Created by zhou on 2016/7/12.
 */
public class TimeCounter extends CountDownTimer {

    //获取验证码按钮
    private Button getCodeBtn;

    public TimeCounter(long millisInFuture, long countDownInterval, Button button) {
        //参数依次为总时长,和计时的时间间隔
        super(millisInFuture, countDownInterval);
        this.getCodeBtn = button;
    }

    /**
     * 计时过程中调用
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        //按钮不可点击
        getCodeBtn.setClickable(false);
        getCodeBtn.setText(millisUntilFinished / 1000 + "s后重发");
    }

    /**
     * 计时结束时调用
     */
    @Override
    public void onFinish() {
        getCodeBtn.setClickable(true);
        getCodeBtn.setText("获取验证码");
    }

}
