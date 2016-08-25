package com.zhou.gitproject.listviewselall.bean;

/**
 * 好友实体类
 * 通过一个标志位来标志是否被选中
 * Created by zhou on 2016/8/17.
 */
public class Friend {

    private String name;
    private boolean isChecked;

    public Friend(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

}
