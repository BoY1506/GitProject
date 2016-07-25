package com.zhou.designmodetest.OrderMode;

import android.util.Log;

/**
 * 电灯
 * Created by zhou on 2016/7/18.
 */
public class Light {

    public void on() {
        Log.e("命令模式", "打开电灯");
    }

    public void off() {
        Log.e("命令模式", "关闭电灯");
    }

}
