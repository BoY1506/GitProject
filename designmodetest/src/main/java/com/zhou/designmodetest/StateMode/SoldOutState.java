package com.zhou.designmodetest.StateMode;

import android.util.Log;

/**
 * 售罄的状态
 * Created by zhou on 2016/7/15.
 */
public class SoldOutState implements Status{

    private VendingMachine2 machine;

    public SoldOutState(VendingMachine2 machine)
    {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.e("状态模式", "投币失败，商品已售罄");
    }

    @Override
    public void backMoney() {
        Log.e("状态模式", "您未投币，想退钱么？...");
    }

    @Override
    public void turnCrank() {
        Log.e("状态模式", "商品售罄，转动手柄也木有用");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态！");
    }

}
