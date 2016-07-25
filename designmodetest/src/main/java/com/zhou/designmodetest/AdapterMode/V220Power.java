package com.zhou.designmodetest.AdapterMode;

import android.util.Log;

/**
 * 提供220V的电压源
 * Created by zhou on 2016/7/14.
 */
public class V220Power {

    /**
     * 提供220V电压
     *
     * @return
     */
    public int provideV220Power() {
        Log.e("适配器模式", "电压源，提供220V电压");
        return 220;
    }

}
