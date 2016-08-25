package com.zhou.gitproject.dagger2.myannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 自定义注解标注区分
 * Created by zhou on 2016/7/27.
 */
@Qualifier//必须，表示IntNamed是用来做区分用途
@Documented//规范要求是Documented，当然不写也问题不大，但是建议写，做提示作用
@Retention(RetentionPolicy.RUNTIME)//规范要求是Runtime级别
public @interface IntNamed {

    int value();

}
