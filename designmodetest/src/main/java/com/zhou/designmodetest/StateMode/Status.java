package com.zhou.designmodetest.StateMode;

/**
 * 状态的接口
 * Created by zhou on 2016/7/15.
 */
public interface Status {

    /**
     * 放钱
     */
    void insertMoney();

    /**
     * 退钱
     */
    void backMoney();

    /**
     * 转动曲柄
     */
    void turnCrank();

    /**
     * 出商品
     */
    void dispense();

}
