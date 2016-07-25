package com.zhou.designmodetest.ObserverMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个服务号
 * Created by zhou on 2016/7/12.
 */
public class ObjectCase implements Subject {

    //消息
    private String msg;

    //存放Oberver的集合
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        //注册观察者
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        //通知素有观察者
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    /**
     * 更新消息并通知观察者
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers();
    }

}
