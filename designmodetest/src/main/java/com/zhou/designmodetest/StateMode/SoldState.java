package com.zhou.designmodetest.StateMode;

import android.util.Log;

/**
 * 准备出商品的状态,该状态下，不会有任何用户的操作
 * Created by zhou on 2016/7/15.
 */
public class SoldState implements Status {

    private VendingMachine2 machine;

    public SoldState(VendingMachine2 machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.e("状态模式", "正在出货，请勿投币");
    }

    @Override
    public void backMoney() {
        Log.e("状态模式", "正在出货，没有可退的钱");
    }

    @Override
    public void turnCrank() {
        Log.e("状态模式", "正在出货，请勿重复转动手柄");
    }

    @Override
    public void dispense() {
        machine.dispense();
        if (machine.getCount() > 0) {
            machine.setState(machine.getNoMoneyState());
        } else {
            Log.e("状态模式", "商品已经售罄");
            machine.setState(machine.getSoldOutState());
        }
    }
}
