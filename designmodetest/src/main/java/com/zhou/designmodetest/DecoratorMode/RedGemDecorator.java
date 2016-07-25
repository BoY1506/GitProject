package com.zhou.designmodetest.DecoratorMode;

/**
 * 红宝石装饰品
 * 每颗攻击力+15
 * Created by zhou on 2016/7/19.
 */
public class RedGemDecorator implements IEquipDecorator {

    //每个装饰品维护一个装备
    private IEquip equip;

    public RedGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return equip.caculateAttack() + 15;
    }

    @Override
    public String description() {
        return equip.description() + "+ 红宝石";
    }
}
