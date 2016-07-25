package com.zhou.designmodetest.ObserverMode;

/**
 * 主题接口，所有服务者必须实现此接口
 * Created by zhou on 2016/7/12.
 */
public interface Subject {

    /**
     * 注册一个观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();

}
