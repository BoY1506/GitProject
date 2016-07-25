package com.zhou.designmodetest.DecoratorMode;

/**
 * 鞋子
 * 攻击力 5
 * Created by zhou on 2016/7/19.
 */
public class ShoeEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战靴子";
    }

}
