package com.zhou.designmodetest.TemplateMode;

import android.util.Log;

/**
 * IT人员
 * Created by zhou on 2016/7/13.
 */
public class ITWorker extends Worker {

    public ITWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.e("模板模式", name + "写程序-测bug-fix bug。");
    }

    /**
     * 重写该方法返回true
     *
     * @return
     */
    @Override
    public boolean isNeedTime() {
        return true;
    }

}
