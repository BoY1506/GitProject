package com.zhou.designmodetest.StateMode;

import android.util.Log;

import java.util.Random;

/**
 * 已投入钱的状态
 * Created by zhou on 2016/7/15.
 */
public class HasMoneyState implements Status {

    private VendingMachine2 machine;
    private Random random = new Random();

    public HasMoneyState(VendingMachine2 machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.e("状态模式ʽ", "您已经投过币了，无需再投....");
    }

    @Override
    public void backMoney() {
        Log.e("状态模式ʽ", "退币成功");
        machine.setState(machine.getNoMoneyState());
    }

    @Override
    public void turnCrank() {
        Log.e("状态模式ʽ", "你转动了手柄");
        int winner = random.nextInt(10);
        if (winner == 0 && machine.getCount() > 1) {
            machine.setState(machine.getWinnerState());
        } else {
            machine.setState(machine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态！");
    }

}
