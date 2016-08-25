package com.zhou.gitproject.dagger2.bean;

import javax.inject.Inject;

/**
 * 香蕉
 * Created by zhou on 2016/7/27.
 */
public class Banana extends Fruit {

    private String size;
    private String color;

    @Inject
    public Banana() {
    }

    public Banana(String name, String size, String color) {
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
