package com.zhou.mvpapp.base;

import android.os.Bundle;

/**
 * View层基类接口
 * Created by zhou on 2016/8/31.
 */
public interface IBaseView {

    //初始化view
    void initView();

    //初始化loading控件
    void initLoadingView();

    //显示加载控件
    void showLoadingViews();

    //显示加载控件
    void showLoadingViews(int type);

    //隐藏加载控件
    void hideLoadingViews();

    //显示吐司
    void showToast(String msg);

    //打印Log
    void showLog(String msg);

    //获取bundle
    Bundle getComeBundle();

}
