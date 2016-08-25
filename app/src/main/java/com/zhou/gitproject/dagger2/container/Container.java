package com.zhou.gitproject.dagger2.container;

import com.zhou.gitproject.dagger2.bean.Fruit;

import javax.inject.Inject;

/**
 * 调用者自身，即容器，包含所需对象
 * Created by zhou on 2016/7/27.
 */
public class Container {

    //添加@Inject，标记f可以被注入
    @Inject
    Fruit f;

//    //通过Named来标注区别
//    @Named("apple")
//    @Inject
//    Fruit apple;

//    @Named("banana")
//    @Inject
//    Fruit banana;
//
//    //使用自定义注解区分
//    @IntNamed(1)
//    @Inject
//    Fruit banana2;

//    @Inject
//    Lazy<Fruit> lazyFruit;//注入Lazy元素
//
//    @Inject
//    Provider<Fruit> providerFruit;//注入Provider元素
//
//    @Inject
//    Set<Fruit> fruits;//注入后集合中包含apple,banana

    public void init() {
//        //使用FruitComponent的实现类注入
//        DaggerFruitComponent.create().inject(this);

//        //在这时才创建f1,以后每次调用get会得到同一个f1对象
//        Fruit f1 = lazyFruit.get();
//        //在这时创建f2，以后每次调用get会再强制调用Module的Provides方法一次，根据Provides方法具体实现的不同
//        //可能返回跟f2是同一个对象，也可能不是
//        Fruit f2 = providerFruit.get();

//        //与如下结果相同
//        DaggerFruitComponent
//                .builder()
//                        //指定Module实例
//                .fruitModule(new FruitModule())
//                .build()
//                .inject(this);

        /**
         * 同一个Component，注入不同调用者，得到的对象不同
         * 可以加上@Singleton标注单例
         */
//        DaggerFruitComponent c1 = (DaggerFruitComponent) DaggerFruitComponent.create();
//        c1.inject(container1);
//        c1.inject(container2);
//        //由于制造machine的方法使用了@Singleton，所以先后注入container1,container2中的machine相同
//        System.out.println(conainter1.machine == conainter2.machine);//true

        /**
         * 单例的保存位置不是静态域。而是Component实例中
         * 不同Component提供出的对象不同
         */
        //c1,c2是不同对象，它们各自缓存machine
//        DaggerFruitComponent c1= (DaggerFruitComponent) DaggerFruitComponent.create();
//        DaggerFruitComponent c2= (DaggerFruitComponent) DaggerFruitComponent.create();
//        c1.inject(container1);
//        c1.inject(container2);
//        c2.inject(container3);
//        c2.inject(container4);
//        System.out.println(conainter1.machine==conainter2.machine);//true
//        System.out.println(conainter2.machine==conainter3.machine);//false
//        System.out.println(conainter3.machine==conainter4.machine);//true
//        System.out.println(conainter4.machine==conainter1.machine);//false
    }

}
