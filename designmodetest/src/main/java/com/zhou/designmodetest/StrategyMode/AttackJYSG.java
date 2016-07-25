package com.zhou.designmodetest.StrategyMode;

import android.util.Log;

/**
 * 九阳神功实现类
 * Created by zhou on 2016/7/21.
 */
public class AttackJYSG implements IAttackBehavior {

    @Override
    public void attack() {
        Log.e("策略模式", "九阳神功");
    }

}
