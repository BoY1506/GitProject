package com.zhou.gitproject.dagger2;

import android.app.Activity;

import com.zhou.gitproject.dagger2.bean.Apple;
import com.zhou.gitproject.dagger2.bean.Fruit;

/**
 * Dagger2依赖注入框架练习
 * 编译时注入，效率更高，复杂项目中可以使用此框架，简化依赖
 * Created by zhou on 2016/7/27.
 */
public class DraggerTest extends Activity {

    /**
     * 传统写法
     * Container是调用者，在它自身创建他所需要的对象
     * 但是如果要改变对象，就要改变Container
     * 实际上Container只是一个使用者，无需关心他所使用的对象如何生成和改变
     * 因此需要多加几个类来直接提供对象给它，它只负责使用，类似工厂模式
     */
    public class Container {
        Fruit f = new Apple("apple", "big", "red");
    }

}
