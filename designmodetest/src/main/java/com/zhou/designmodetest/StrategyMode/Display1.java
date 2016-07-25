package com.zhou.designmodetest.StrategyMode;

import android.util.Log;

/**
 * 样子1实现类
 * Created by zhou on 2016/7/21.
 */
public class Display1 implements IDisplayBehavior {

    @Override
    public void display() {
        Log.e("策略模式", "样子1");
    }

}
