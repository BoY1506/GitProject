package com.zhou.gitproject.contactlist.bean;

/**
 * 联系人实体类
 */
public class ManModel {

    private String name;   //姓名
    private String sortLetters;  //首字母

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

}
