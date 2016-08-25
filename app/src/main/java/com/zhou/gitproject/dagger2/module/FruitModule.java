package com.zhou.gitproject.dagger2.module;

import com.zhou.gitproject.dagger2.bean.Apple;
import com.zhou.gitproject.dagger2.bean.Fruit;

import dagger.Module;
import dagger.Provides;

/**
 * 水果Module提供者，提供对象原材料
 * 一个完整的Module必须拥有@Module与@Provides注解
 * Created by zhou on 2016/7/27.
 */
@Module //注明本类属于Module
//添加多个module另一种方法：@Module(includes={ModuleA.class,ModuleB.class})
public class FruitModule {

//    @Singleton //添加@Singleton标明该方法产生只产生一个实例
    @Provides //注明该方法是用来提供依赖对象的特殊方法
    public Fruit provideFruit() {
        return new Apple("apple", "big", "red");
    }

//    /**
//     * 区分返回类型相同的不同子对象
//     * 使用@Named
//     */
//    @Named("apple")
//    @Provides
//    public Fruit provideApple() {  //提供Apple给对应的Fruit
//        return new Apple();
//    }
//
//    @Named("banana")
//    @Provides
//    public Fruit provideBanana() {  //提供Banana给对应的Fruit
//        return new Banana();
//    }
//
//    //使用自定义注解来区分
//    @IntNamed(1)
//    @Provides
//    public Fruit provideBanana2() {  //Banana
//        return new Banana();
//    }
//
//    @Provides(type = Provides.Type.SET)//type是SET时，改方法返回一个元素
//    public Fruit getFruit() {
//        return new Apple();
//    }
//
//    @Provides(type = Provides.Type.SET_VALUES)//type是SET时，改方法返回一个Set集合
//    public Set<Fruit> getFruits() {
//        Set<Fruit> fruits = new HashSet<>();
//        fruits.add(new Apple());
//        fruits.add(new Banana());
//        return fruits;
//    }

}
