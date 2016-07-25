package com.zhou.gitproject.smscode.view;

import android.widget.Button;

/**
 * 第二种方式：利用属性动画valueAnimator
 * 按钮包装类，实现set和get方法
 * Created by zhou on 2016/7/12.
 */
public class BtnWrapper {

    private Button getCodeBtn;

    public BtnWrapper(Button getCodeBtn) {
        this.getCodeBtn = getCodeBtn;
    }

    /**
     * 设置时间
     *
     * @param time
     */
    public void setTime(int time) {
        if (getCodeBtn != null) {
            getCodeBtn.setText(time + "s后重发");
        }
    }

    /**
     * 设置计时结束后文字
     */
    public void setTimeEndText(String str) {
        if (getCodeBtn != null) {
            getCodeBtn.setText(str);
        }
    }

    /**
     * 设置按钮是否可点击
     *
     * @param clickable
     */
    public void setClickable(boolean clickable) {
        if (getCodeBtn != null) {
            getCodeBtn.setClickable(clickable);
        }
    }

}
