package com.zhou.gitproject.cityselect2.bean;

import com.zhou.gitproject.cityselect2.utils.PingYinUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市实体类
 * Created by zhou on 2016/8/21.
 */
public class City implements Comparable<City> {

    private int id;
    private String name;

    public static List<City> cityList;
    public static List<City> hotList;

    static {
        cityList = new ArrayList<>();
        hotList = new ArrayList<>();
        cityList.add(new City(0, "安庆"));
        cityList.add(new City(1, "安阳"));
        cityList.add(new City(2, "安徽"));
        cityList.add(new City(3, "北京"));
        cityList.add(new City(4, "保定"));
        cityList.add(new City(5, "亳州"));
        cityList.add(new City(6, "重庆"));
        cityList.add(new City(7, "长沙"));
        cityList.add(new City(8, "常熟"));
        cityList.add(new City(9, "大连"));
        cityList.add(new City(10, "德州"));
        cityList.add(new City(11, "鄂尔多斯"));
        cityList.add(new City(12, "福州"));
        cityList.add(new City(13, "桂林"));
        cityList.add(new City(14, "广东"));
        cityList.add(new City(15, "杭州"));
        cityList.add(new City(16, "合肥"));
        cityList.add(new City(17, "济南"));
        cityList.add(new City(18, "连云港"));
        cityList.add(new City(19, "洛阳"));
        cityList.add(new City(20, "眉山"));
        cityList.add(new City(21, "宁波"));
        cityList.add(new City(22, "南昌"));
        cityList.add(new City(23, "平顶山"));
        cityList.add(new City(24, "青岛"));
        cityList.add(new City(25, "齐齐哈尔"));
        cityList.add(new City(26, "日照"));
        cityList.add(new City(27, "上海"));
        cityList.add(new City(28, "沈阳"));
        cityList.add(new City(29, "三亚"));
        cityList.add(new City(30, "武汉"));
        cityList.add(new City(31, "西安"));
        cityList.add(new City(32, "烟台"));
        cityList.add(new City(33, "扬州"));
        cityList.add(new City(34, "张家界"));
        cityList.add(new City(35, "驻马店"));
        hotList.add(new City(6, "重庆"));
        hotList.add(new City(7, "长沙"));
        hotList.add(new City(8, "常熟"));
        hotList.add(new City(9, "大连"));
        hotList.add(new City(10, "德州"));
        hotList.add(new City(11, "鄂尔多斯"));
        hotList.add(new City(12, "福州"));
        hotList.add(new City(13, "桂林"));
        hotList.add(new City(14, "广东"));
        hotList.add(new City(15, "杭州"));
        hotList.add(new City(16, "合肥"));
    }

    public City(int id, String name) {
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
    public int compareTo(City another) {
        String lname = PingYinUtil.getPingYin(getName()).toUpperCase();
        String rname = PingYinUtil.getPingYin(another.getName()).toUpperCase();
        return lname.compareTo(rname);
    }

}
