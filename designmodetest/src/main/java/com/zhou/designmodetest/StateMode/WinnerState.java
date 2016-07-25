package com.zhou.designmodetest.StateMode;

import android.util.Log;

/**
 * 中奖的状态，该状态下不会有任何用户的操作
 * Created by zhou on 2016/7/15.
 */
public class WinnerState implements Status {

    private VendingMachine2 machine;

    public WinnerState(VendingMachine2 machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        throw new IllegalStateException("非法状态！");
    }

    @Override
    public void backMoney() {
        throw new IllegalStateException("非法状态！");
    }

    @Override
    public void turnCrank() {
        throw new IllegalStateException("非法状态！");

    }

    @Override
    public void dispense() {
        Log.e("状态模式ʽ", "你中奖了，恭喜你，将得到2件商品");
        machine.dispense();

        if (machine.getCount() == 0) {
            Log.e("状态模式", "商品已经售罄");
            machine.setState(machine.getSoldOutState());
        } else {
            machine.dispense();
            if (machine.getCount() > 0) {
                machine.setState(machine.getNoMoneyState());
            } else {
                Log.e("状态模式", "商品已经售罄");
                machine.setState(machine.getSoldOutState());
            }
        }
    }
}
