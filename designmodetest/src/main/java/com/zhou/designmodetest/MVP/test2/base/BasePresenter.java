package com.zhou.designmodetest.MVP.test2.base;

/**
 * Presenter父类
 * 处理presenter公用逻辑
 * Created by zhou on 2016/8/3.
 */
public abstract class BasePresenter<T> {

    public T mView;//view

    /**
     * 添加View
     *
     * @param view
     */
    public void attach(T view) {
        this.mView = view;
    }

    /**
     * 移除View
     */
    public void detach() {
        mView = null;
    }

}
