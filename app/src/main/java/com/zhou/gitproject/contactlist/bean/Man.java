package com.zhou.gitproject.contactlist.bean;

import com.zhou.gitproject.contactlist.utils.PinYinUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体
 * Created by zhou on 2016/8/13.
 */
public class Man implements Comparable<Man> {

    private int id;
    private String name;

    public static List<Man> manList;

    static {
        manList = new ArrayList<>();
        manList.add(new Man(0, "周博"));
        manList.add(new Man(1, "季旭"));
        manList.add(new Man(2, "山贼"));
        manList.add(new Man(3, "陶晶"));
        manList.add(new Man(4, "吴琼"));
        manList.add(new Man(5, "童哥"));
        manList.add(new Man(6, "王师傅"));
        manList.add(new Man(7, "大熊"));
        manList.add(new Man(8, "金开云"));
        manList.add(new Man(9, "房云丽"));
        manList.add(new Man(10, "李晓晓"));
        manList.add(new Man(11, "吴澄瑶"));
        manList.add(new Man(12, "刘世斌"));
        manList.add(new Man(13, "袁波"));
        manList.add(new Man(14, "冯杰"));
        manList.add(new Man(15, "刘春霞"));
        manList.add(new Man(16, "周良宏"));
        manList.add(new Man(17, "程硕"));
        manList.add(new Man(18, "任慧龙"));
        manList.add(new Man(19, "邹成伟"));
        manList.add(new Man(20, "白盼盼"));
        manList.add(new Man(21, "贠玉晶"));
        manList.add(new Man(22, "刘泽"));
        manList.add(new Man(23, "杨战旗"));
        manList.add(new Man(24, "高雪童"));
        manList.add(new Man(25, "赵菲菲"));
        manList.add(new Man(26, "孟江龙"));
        manList.add(new Man(27, "关文飞"));
        manList.add(new Man(28, "谭淼"));
        manList.add(new Man(29, "刘正"));
        manList.add(new Man(30, "黄鹏洪"));
        manList.add(new Man(31, "马昊"));
        manList.add(new Man(32, "武俊吉"));
        manList.add(new Man(33, "杨宝"));
        manList.add(new Man(34, "周青"));
        manList.add(new Man(35, "阿三"));
    }

    public Man(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Man another) {
        String lname = PinYinUtils.trans2PinYin(getName()).toUpperCase();
        String rname = PinYinUtils.trans2PinYin(another.getName()).toUpperCase();
        return lname.compareTo(rname);
    }

}
