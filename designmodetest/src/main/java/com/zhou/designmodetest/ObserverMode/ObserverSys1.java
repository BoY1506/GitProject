package com.zhou.designmodetest.ObserverMode;

import android.util.Log;

import java.util.Observable;

/**
 * 利用系统实现观察者1
 * Created by zhou on 2016/7/12.
 */
public class ObserverSys1 implements java.util.Observer {

    public ObserverSys1(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof ObjectCaseSys) {
            Log.e("观察者1被更新了", ((ObjectCaseSys) observable).getMsg());
        }
    }

}
