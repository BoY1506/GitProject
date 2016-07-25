package com.zhou.designmodetest.ObserverMode;

import android.util.Log;

/**
 * 一个观察者2
 * Created by zhou on 2016/7/12.
 */
public class Observer2 implements Observer {

    //主题
    private Subject subject;

    public Observer2(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        Log.e("观察者2被更新了", msg);
    }

}
