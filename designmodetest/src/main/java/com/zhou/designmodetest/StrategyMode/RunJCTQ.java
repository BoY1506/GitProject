package com.zhou.designmodetest.StrategyMode;

import android.util.Log;

/**
 * 金蝉脱壳实现类
 * Created by zhou on 2016/7/21.
 */
public class RunJCTQ implements IRunBehavior {

    @Override
    public void run() {
        Log.e("策略模式", "DefendTBS");
    }

}
