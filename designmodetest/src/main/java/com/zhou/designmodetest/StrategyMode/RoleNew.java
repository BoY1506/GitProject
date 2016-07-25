package com.zhou.designmodetest.StrategyMode;

/**
 * 角色基类
 * 将各种技能已经单独封装和实现，将角色和技能分离开
 * 技能实现实现好，角色只是使用技能
 * Created by zhou on 2016/7/21.
 */
public abstract class RoleNew {

    protected String name;

    protected IDisplayBehavior iDisplayBehavior;
    protected IAttackBehavior iAttackBehavior;
    protected IDefendBehavior iDefendBehavior;
    protected IRunBehavior iRunBehavior;

    /**
     * 设置样子
     *
     * @param behavior
     * @return
     */
    public RoleNew setDisplayBehavior(IDisplayBehavior behavior) {
        this.iDisplayBehavior = behavior;
        return this;
    }

    /**
     * 设置攻击
     *
     * @param behavior
     * @return
     */
    public RoleNew setAttackBehavior(IAttackBehavior behavior) {
        this.iAttackBehavior = behavior;
        return this;
    }

    /**
     * 设置防御
     *
     * @param behavior
     * @return
     */
    public RoleNew setDefendBehavior(IDefendBehavior behavior) {
        this.iDefendBehavior = behavior;
        return this;
    }

    /**
     * 设置逃跑
     *
     * @param behavior
     * @return
     */
    public RoleNew setRunBehavior(IRunBehavior behavior) {
        this.iRunBehavior = behavior;
        return this;
    }

    /**
     * 样子
     */
    public void display() {
        iDisplayBehavior.display();
    }

    /**
     * 攻击
     */
    public void attack() {
        iAttackBehavior.attack();
    }

    /**
     * 防御
     */
    public void defend() {
        iDefendBehavior.defend();
    }

    /**
     * 逃跑
     */
    public void run() {
        iRunBehavior.run();
    }

}
