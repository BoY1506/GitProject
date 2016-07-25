package com.zhou.designmodetest.StateMode;


import android.util.Log;

/**
 * 自动售货机2
 * Created by zhou on 2016/7/15.
 */
public class VendingMachine2 {

    private Status noMoneyState;
    private Status hasMoneyState;
    private Status soldState;
    private Status soldOutState;
    private Status winnerState;

    private int count = 0;
    private Status currentStatus = noMoneyState;

    public VendingMachine2(int count) {
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        winnerState = new WinnerState(this);

        if (count > 0) {
            this.count = count;
            currentStatus = noMoneyState;
        }
    }

    /**
     * 放钱
     */
    public void insertMoney() {
        currentStatus.insertMoney();
    }

    /**
     * 退钱
     */
    public void backMoney() {
        currentStatus.backMoney();
    }

    /**
     * 转动手柄
     */
    public void turnCrank() {
        currentStatus.turnCrank();
        if (currentStatus == soldState || currentStatus == winnerState)
            currentStatus.dispense();
    }

    /**
     * 发放商品
     */
    public void dispense() {
        Log.e("状态模式", "发出一件商品...");
        if (count != 0) {
            count -= 1;
        }
    }

    /**
     * 设置状态
     */
    public void setState(Status status) {
        this.currentStatus = status;
    }

    public Status getNoMoneyState() {
        return noMoneyState;
    }

    public Status getHasMoneyState() {
        return hasMoneyState;
    }

    public Status getSoldState() {
        return soldState;
    }

    public Status getSoldOutState() {
        return soldOutState;
    }

    public Status getWinnerState() {
        return winnerState;
    }

    public int getCount() {
        return count;
    }
}
