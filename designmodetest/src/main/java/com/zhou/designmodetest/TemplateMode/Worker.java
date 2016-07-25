package com.zhou.designmodetest.TemplateMode;

import android.util.Log;

import java.util.Date;

/**
 * 工人超类
 * 公用的方法写死，不同的方法用接口或者抽象方法
 * Created by zhou on 2016/7/13.
 */
public abstract class Worker {

    protected String name;

    public Worker(String name) {
        this.name = name;
    }

    /**
     * 记录一天的工作
     */
    public final void workOneDay() {
        Log.e("模板模式", "- - - - - - - - - - 开始 - - - - - - - - - -");
        enterCompany();
        openComputer();
        work();
        closeComputer();
        exitCompany();
        Log.e("模板模式", "- - - - - - - - - - 结束 - - - - - - - - - -");
        Log.e("", "");
    }

    /**
     * 工作
     * 每个子类不同实现
     */
    public abstract void work();

    /**
     * 返回时间标志位，默认为flase
     * 子类可重写该方法
     *
     * @return
     */
    public boolean isNeedTime() {
        return false;
    }

    /**
     * 进入公司
     */
    public void enterCompany() {
        Log.e("模板模式", name + "进入公司。");
    }

    /**
     * 打开电脑
     */
    public void openComputer() {
        Log.e("模板模式", name + "打开电脑。");
    }

    /**
     * 关闭电脑
     */
    public void closeComputer() {
        Log.e("模板模式", name + "关闭电脑。");
    }

    /**
     * 离开公司
     */
    public void exitCompany() {
        Log.e("模板模式", name + "离开公司。");
        if (isNeedTime()) {
            Log.e("模板模式", "离开公司时间" + new Date().toLocaleString());
        }
    }

}
