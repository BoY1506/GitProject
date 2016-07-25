package com.zhou.designmodetest.StrategyMode;

/**
 * 角色的超类
 * 需求：每个角色对应一个名字，每个角色有四个技能，不同角色样子不同
 * Created by zhou on 2016/7/21.
 */
public abstract class Role {

    protected String name;

    protected abstract void display();

    protected abstract void run();

    protected abstract void attack();

    protected abstract void defend();

}
