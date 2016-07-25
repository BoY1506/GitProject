package com.zhou.designmodetest.FacadeMode;

import android.util.Log;

/**
 * 投影仪
 * Created by zhou on 2016/7/20.
 */
public class Projector {

    public void move() {
        Log.e("外观模式", "放置投影仪");
    }

    public void on() {
        Log.e("外观模式", "打开投影仪");
    }

    public void off() {
        Log.e("外观模式", "关闭播投影仪");
    }

}
