package com.zhou.designmodetest.ObserverMode;

import java.util.Observable;

/**
 * 利用系统Observable实主题
 * Created by zhou on 2016/7/12.
 */
public class ObjectCaseSys extends Observable {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        //设置更新
        setChanged();
        //通知观察者
        notifyObservers();
    }

}
