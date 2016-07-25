package com.zhou.designmodetest.ObserverMode;

/**
 * 所有观察者需要实现此接口
 * Created by zhou on 2016/7/12.
 */
public interface Observer {

    /**
     * 更新数据
     *
     * @param msg
     */
    void update(String msg);

}
