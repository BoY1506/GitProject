package com.zhou.designmodetest.AdapterMode;

import android.util.Log;

/**
 * 电压转换适配器
 * 将220V电压转化为5V
 * Created by zhou on 2016/7/14.
 */
public class PowerAdapter implements V5Power {

    private V220Power v220Power;

    public PowerAdapter(V220Power v220Power) {
        this.v220Power = v220Power;
    }

    /**
     * 转化
     *
     * @return
     */
    @Override
    public int provideV5Power() {
        int oldU = v220Power.provideV220Power();
        //经过一系列操作，220V -> 5V
        int newU = 5;
        Log.e("适配器模式", "转化器已成功转化电压为5V");
        return newU;
    }

}
