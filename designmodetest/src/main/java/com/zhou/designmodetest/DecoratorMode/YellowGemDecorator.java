package com.zhou.designmodetest.DecoratorMode;

/**
 * 黄宝石装饰品
 * 每颗攻击力+10
 * Created by zhou on 2016/7/19.
 */
public class YellowGemDecorator implements IEquipDecorator {

    //每个装饰品维护一个装备
    private IEquip equip;

    public YellowGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return equip.caculateAttack() + 10;
    }

    @Override
    public String description() {
        return equip.description() + "+ 黄宝石";
    }
}
