package com.zhou.designmodetest.DecoratorMode;

/**
 * 装备的接口
 * Created by zhou on 2016/7/19.
 */
public interface IEquip {

    /**
     * 装备的接口
     *
     * @return
     */
    int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    String description();

}
