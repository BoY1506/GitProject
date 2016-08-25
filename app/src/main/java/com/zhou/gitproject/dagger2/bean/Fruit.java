package com.zhou.gitproject.dagger2.bean;

/**
 * 水果父类
 * Created by zhou on 2016/7/27.
 */
public class Fruit {

    private String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
