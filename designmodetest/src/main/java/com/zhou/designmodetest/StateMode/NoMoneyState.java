package com.zhou.designmodetest.StateMode;

import android.util.Log;

/**
 * 没钱的状态
 * Created by zhou on 2016/7/15.
 */
public class NoMoneyState implements Status {

    private VendingMachine2 machine;

    public NoMoneyState(VendingMachine2 machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.e("状态模式", "投币成功");
        machine.setState(machine.getHasMoneyState());
    }

    @Override
    public void backMoney() {
        Log.e("状态模式", "您未投币，想退钱？...");
    }

    @Override
    public void turnCrank() {
        Log.e("状态模式", "您未投币，想拿东西么？...");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态！");
    }

}
