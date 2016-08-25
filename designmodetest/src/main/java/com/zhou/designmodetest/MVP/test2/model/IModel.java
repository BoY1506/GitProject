package com.zhou.designmodetest.MVP.test2.model;

/**
 * Model层数据处理接口
 * Created by zhou on 2016/7/25.
 */
public interface IModel {

    /**
     * 内置接口，结果处理callback
     */
    interface ICallback {
        /**
         * 结果处理
         *
         * @param data
         */
        void onResult(String data);
    }

    /**
     * 获取数据接口
     *
     * @param callback
     */
    void getData(ICallback callback);

    /**
     * 改进：model中直接处理数据，不处理线程操作
     * 获取数据接口
     */
    String getData();

}
