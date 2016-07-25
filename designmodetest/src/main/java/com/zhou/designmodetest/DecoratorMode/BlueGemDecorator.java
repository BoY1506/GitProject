package com.zhou.designmodetest.DecoratorMode;

/**
 * 蓝宝石装饰品
 * 每颗攻击力+5
 * Created by zhou on 2016/7/19.
 */
public class BlueGemDecorator implements IEquipDecorator {

    //每个装饰品维护一个装备
    private IEquip equip;

    public BlueGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return equip.caculateAttack() + 5;
    }

    @Override
    public String description() {
        return equip.description() + "+ 蓝宝石";
    }

}
