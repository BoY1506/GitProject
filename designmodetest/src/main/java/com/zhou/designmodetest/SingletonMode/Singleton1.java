package com.zhou.designmodetest.SingletonMode;

/**
 * 恶汉式
 * 即使未调用getInstance()，仍会创建对象。资源比较浪费。
 * Created by zhou on 2016/7/11.
 */
public class Singleton1 {

    /**
     * 直接建立静态实例
     */
    private static Singleton1 instance = new Singleton1();

    /**
     * 构造方法私有化
     */
    private Singleton1() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    private static Singleton1 getInstance() {
        return instance;
    }

}
