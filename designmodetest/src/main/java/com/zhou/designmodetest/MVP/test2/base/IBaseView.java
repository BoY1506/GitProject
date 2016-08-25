package com.zhou.designmodetest.MVP.test2.base;

/**
 * View父类
 * 处理view公用逻辑
 * Created by zhou on 2016/8/3.
 */
public interface IBaseView {

    /**
     * 显示加载条
     */
    void showLoading();

    /**
     * 隐藏加载条
     */
    void hideLoading();

}
