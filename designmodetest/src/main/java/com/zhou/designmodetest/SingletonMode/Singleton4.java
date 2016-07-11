package com.zhou.designmodetest.SingletonMode;

/**
 * 内部类模式
 * 不调用getInstance()方法时，SingletonHolder不会被加载
 * 当我们调用时，才会使sintance实例化
 * 不仅能够确保线程安全，也能够保证单例对象的唯一性，同时也延迟了单例的实例化。
 * Created by zhou on 2016/7/11.
 */
public class Singleton4 {

    /**
     * 静态内部类
     */
    private static class Singleton4Holder {
        private static final Singleton4 instance = new Singleton4();
    }

    /**
     * 构造方法私有化
     */
    private Singleton4() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    private static Singleton4 getInstance() {
        return Singleton4Holder.instance;
    }

}
