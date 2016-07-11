package com.zhou.designmodetest.SingletonMode;

/**
 * 懒汉式进阶-双重锁
 * 第一层判断是为了避免不必要的同步问题。
 * 第二层判断是为了在null的时候创建单例实例。
 * 种方式一般能够满足需求。
 * Created by zhou on 2016/7/11.
 */
public class Singleton3 {

    /**
     * 静态实例
     */
    private static Singleton3 instance;

    /**
     * 构造方法私有化
     */
    private Singleton3() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    private static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

}
