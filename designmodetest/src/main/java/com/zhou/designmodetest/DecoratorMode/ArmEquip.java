package com.zhou.designmodetest.DecoratorMode;

/**
 * 武器
 * 攻击力20
 * Created by zhou on 2016/7/19.
 */
public class ArmEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "屠龙刀";
    }

}
