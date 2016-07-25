package com.zhou.designmodetest.StrategyMode;

import android.util.Log;

/**
 * 子角色A
 * 缺点：每次都要继承，重写很多重复的方法，这样封装的是实现，每次都要多次实现
 * 改进：我们应该封装接口，使得一次定义，多次使用
 * Created by zhou on 2016/7/21.
 */
public class RoleA extends Role {

    public RoleA(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        Log.e("策略模式", "样子1");
    }

    @Override
    protected void run() {
        Log.e("策略模式", "金蝉脱壳");
    }

    @Override
    protected void attack() {
        Log.e("策略模式", "降龙十八掌");
    }

    @Override
    protected void defend() {
        Log.e("策略模式", "铁布衫");
    }

}
