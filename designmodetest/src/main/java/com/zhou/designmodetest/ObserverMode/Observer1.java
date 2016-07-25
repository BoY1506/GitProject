package com.zhou.designmodetest.ObserverMode;

import android.util.Log;

/**
 * 一个观察者1
 * Created by zhou on 2016/7/12.
 */
public class Observer1 implements Observer {

    //主题
    private Subject subject;

    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        Log.e("观察者1被更新了", msg);
    }

}
