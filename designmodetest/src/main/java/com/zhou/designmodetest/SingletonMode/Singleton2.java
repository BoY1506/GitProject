package com.zhou.designmodetest.SingletonMode;

/**
 * 懒汉式
 * 当调用getInstance()方法时，当instance不为null时，
 * 仍会因为线程同步而导致资源被浪费，这是最大的问题。
 * Created by zhou on 2016/7/11.
 */
public class Singleton2 {

    /**
     * 静态实例
     */
    private static Singleton2 instance;

    /**
     * 构造方法私有化
     */
    private Singleton2() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    private static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

}
