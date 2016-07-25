package com.zhou.designmodetest.OrderMode;

import android.util.Log;

/**
 * 电脑
 * Created by zhou on 2016/7/18.
 */
public class Computer {

    public void on() {
        Log.e("命令模式", "打开电脑");
    }

    public void off() {
        Log.e("命令模式", "关闭电脑");
    }

}
