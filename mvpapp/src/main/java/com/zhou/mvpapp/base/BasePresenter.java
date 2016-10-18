package com.zhou.mvpapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zhou.mvpapp.utils.CallManager;

/**
 * Presenter层基类
 * Created by zhou on 2016/8/31.
 */
public abstract class BasePresenter<V, M> implements IBasePresenter<V> {

    //上下文
    protected Context context;
    //view视图层接口
    protected V view;
    //实体类Model
    protected M model;

    public BasePresenter() {
        //创建model
        createModel();
    }

    /**
     * 绑定视图
     */
    @Override
    public void attachView(Context context, V view) {
        //建立关系
        this.context = context;
        this.view = view;
    }

    /**
     * 获取视图
     */
    @Override
    public V getView() {
        return view;
    }

    /**
     * 视图是否绑定
     */
    @Override
    public boolean isViewAttached() {
        return context != null && view != null;
    }

    /**
     * 解绑视图
     */
    @Override
    public void detachView() {
        //取消当前页面的所有call
        CallManager.getInstance().cancleCall();
        //解除视图
        if (isViewAttached()) {
            context = null;
            view = null;
        }
    }

    /**
     * 封装Intent跳转（不含参数）
     */
    protected void intent2Activity(Class tarActivity) {
        Intent intent = new Intent(context, tarActivity);
        context.startActivity(intent);
    }

    /**
     * 封装Intent跳转（含参数）
     */
    protected void intent2Activity(Class tarActivity, Bundle bundle) {
        Intent intent = new Intent(context, tarActivity);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    /**
     * 封装Intent带返回值的跳转（不含参数）
     */
    protected void intent2Activity4Result(Class tarActivity, int requestCode) {
        Intent intent = new Intent(context, tarActivity);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 封装Intent带返回值的跳转（含参数）
     */
    protected void intent2Activity(Class tarActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, tarActivity);
        intent.putExtra("bundle", bundle);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 创建model
     */
    protected abstract void createModel();

}
