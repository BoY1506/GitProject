package com.zhou.designmodetest.StrategyMode;

import android.util.Log;

/**
 * 铁布衫实现类
 * Created by zhou on 2016/7/21.
 */
public class DefendTBS implements IDefendBehavior {

    @Override
    public void defend() {
        Log.e("策略模式", "铁布衫");
    }

}
