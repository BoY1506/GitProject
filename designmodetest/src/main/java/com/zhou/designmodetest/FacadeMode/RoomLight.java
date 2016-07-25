package com.zhou.designmodetest.FacadeMode;

import android.util.Log;

/**
 * 灯光
 * Created by zhou on 2016/7/20.
 */
public class RoomLight {

    public void up() {
        Log.e("外观模式", "调亮灯光");
    }

    public void down() {
        Log.e("外观模式", "调暗灯光");
    }

}
