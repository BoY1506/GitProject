package com.zhou.designmodetest.StrategyMode;

/**
 * 角色A
 * 这时候角色基类已经将同样的技能封装好，不同技能也已经实现
 * 角色只需要做的就是动态设置技能和使用技能即可,并且可以设置自己独立的其他特点
 * Created by zhou on 2016/7/21.
 */
public class RoleAnew extends RoleNew {

    public RoleAnew(String name) {
        this.name = name;
    }

}
