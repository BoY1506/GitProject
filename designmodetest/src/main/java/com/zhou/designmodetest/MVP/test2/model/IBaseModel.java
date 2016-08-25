package com.zhou.designmodetest.MVP.test2.model;

import java.util.Map;

/**
 * Model父类
 * 处理一些公用的业务逻辑
 * 如检测用户登录，get请求数据，post请求等
 * Created by zhou on 2016/8/3.
 */
public interface IBaseModel {

    /**
     * 检测用户登录
     */
    boolean isUserLogined();

    /**
     * 检测用户登录
     */
    String getRequest(String url);

    /**
     * 检测用户登录
     */
    String postRequest(String url, Map<String, String> params);

}
