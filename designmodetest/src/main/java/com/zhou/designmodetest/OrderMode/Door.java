package com.zhou.designmodetest.OrderMode;

import android.util.Log;

/**
 * 们
 * Created by zhou on 2016/7/18.
 */
public class Door {

    public void open() {
        Log.e("命令模式", "打开门");
    }

    public void close() {
        Log.e("命令模式", "关闭门");
    }

}
