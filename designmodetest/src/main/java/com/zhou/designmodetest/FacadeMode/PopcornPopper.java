package com.zhou.designmodetest.FacadeMode;

import android.util.Log;

/**
 * 爆米花机
 * Created by zhou on 2016/7/20.
 */
public class PopcornPopper {

    public void on() {
        Log.e("外观模式", "打开爆米花机");
    }

    public void make() {
        Log.e("外观模式", "制作爆米花");
    }

    public void off() {
        Log.e("外观模式", "关闭爆米花机");
    }

}
