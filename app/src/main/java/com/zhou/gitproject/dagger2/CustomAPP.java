package com.zhou.gitproject.dagger2;

import android.app.Application;

/**
 * application类
 * Created by zhou on 2016/7/28.
 */
public class CustomAPP extends Application {

//    //单例的有效范围随着其依附的Component，为了使得@PerApp的作用范围是整个Application
//    //标记静态全局component
//    //注意两个有依赖关系的Component不能使用相同的Scope
//    private static FruitComponent component;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        component = DaggerFruitComponent.create();
//    }
//
//    /**
//     * 返回Component
//     *
//     * @return
//     */
//    public static FruitComponent getComponent() {
//        return component;
//    }

}
