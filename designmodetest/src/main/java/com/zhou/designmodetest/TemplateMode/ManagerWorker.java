package com.zhou.designmodetest.TemplateMode;

import android.util.Log;

/**
 * HR人员
 * Created by zhou on 2016/7/13.
 */
public class ManagerWorker extends Worker {

    public ManagerWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.e("模板模式", name + "打游戏。。。");
    }

}
