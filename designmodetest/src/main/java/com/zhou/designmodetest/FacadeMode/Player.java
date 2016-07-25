package com.zhou.designmodetest.FacadeMode;

import android.util.Log;

/**
 * 播放器
 * Created by zhou on 2016/7/20.
 */
public class Player {

    public void on() {
        Log.e("外观模式", "打开播放器");
    }

    public void off() {
        Log.e("外观模式", "关闭播放器");
    }

}
