package com.zhou.mvpapp.base;

import android.content.Context;

/**
 * Presenter层基类接口
 * Created by zhou on 2016/9/12.
 */
public interface IBasePresenter<V> {

    //绑定视图
    void attachView(Context context, V view);

    //获取视图
    V getView();

    //视图是否绑定
    boolean isViewAttached();

    //解除视图
    void detachView();

}
