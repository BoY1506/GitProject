package com.zhou.mvpapp.contract;

/**
 * 数据处理结果回调
 * Created by zhou on 2016/9/2.
 */
public interface HandleListener<T> {

    //成功
    void onSuccess(String msg, T data);

}
