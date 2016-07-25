package com.zhou.designmodetest.FacadeMode;

import android.util.Log;

/**
 * 电脑
 * Created by zhou on 2016/7/20.
 */
public class Computer {

    public void on() {
        Log.e("外观模式", "打开电脑");
    }

    public void off() {
        Log.e("外观模式", "关闭电脑");
    }

}
