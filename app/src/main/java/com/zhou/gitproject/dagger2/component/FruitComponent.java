package com.zhou.gitproject.dagger2.component;

import com.zhou.gitproject.dagger2.container.Container;
import com.zhou.gitproject.dagger2.module.FruitModule;

import dagger.Component;

/**
 * 水果对象中转器，用于查找和生成对象，提供给容器
 * 必须是接口类型
 * Created by zhou on 2016/7/27.
 */
//@PerActivity//自定义标注单例模式
//@Singleton//单例模式
@Component(modules = {FruitModule.class}) //指明Component在哪些Module中查找依赖
//module可添加多个：modules = {FruitModule1.class,FruitModule2.class}
//可添加依赖：dependencies = {ComponentB.class},添加后A中没有的对象，B中必须显示提供
public interface FruitComponent { //接口，自动生成实现

    //注入方法，在Container中调用，把对象提供给谁
    void inject(Container container);

    /**
     * 要么有参数，无返回值
     * 要么无参数，有返回值
     * Component先从Module中找@Provides，如果没有，则寻找该对象带@Inject的构造方法
     * 构造方法中的参数递归寻找
     */
//    Apple getApple();

    /**
     * SunComponent的使用
     * Component可以依赖，也可使用SunComponent
     * 只需在父类中添加返回字Component的方法即可
     * 当注入的元素来自父Component的Module，则这些元素会缓存在父Component中
     * 当注入的元素来自子Component的Module，则这些元素会缓存在子Component中
     */
//    @PerApp
//    @Component(modules=xxx.class)
//    public AppComponent{
//        //只需要在父Component添加返回子Component的方法即可
//        SubComponent subcomponent();
//    }
//
//    //子Component：
//    @PerAcitivity//注意子Component的Scope范围小于父Component
//    @Subcomponent(modules=xxx.class)//使用@Subcomponent
//    public SubComponent{
//        void inject(SomeActivity activity);
//    }
//
//    //使用
//    public class SomeActivity extends Activity {
//        public void onCreate(Bundle savedInstanceState){
//            //调用subComponent方法创建出子Component
//            App.getComponent().subCpmponent().inject(this);
//        }
//    }

}
