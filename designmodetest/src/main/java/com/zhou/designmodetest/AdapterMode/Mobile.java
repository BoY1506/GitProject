package com.zhou.designmodetest.AdapterMode;

import android.util.Log;

/**
 * 手机类 需要5V电压
 * Created by zhou on 2016/7/14.
 */
public class Mobile {

    /**
     * 充电
     *
     * @param power
     */
    public void inputPower(V5Power power) {
        int provideV5Power = power.provideV5Power();
        Log.e("适配器模式", "手机接收到了电压，为" + provideV5Power + "V");
    }

}
