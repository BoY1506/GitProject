package com.zhou.gitproject.dagger2.myannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 可以自定义单例模式
 * 利用@Scope标注单例模式，等于起了个见明知意的别名
 * Created by zhou on 2016/7/28.
 */
@Scope//注明是Scope
@Documented//标记在文档
@Retention(RetentionPolicy.RUNTIME)//运行时级别
public @interface PerActivity {
}
