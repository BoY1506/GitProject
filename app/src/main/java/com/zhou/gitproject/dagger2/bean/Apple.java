package com.zhou.gitproject.dagger2.bean;

import javax.inject.Inject;

/**
 * 苹果
 * Created by zhou on 2016/7/27.
 */
public class Apple extends Fruit {

    private String size;
    private String color;

    //找不掉将会调用这个构造方法，因为他使用了@Inject标注
    @Inject
    public Apple() {
    }

    public Apple(String name, String size, String color) {
        super(name);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
