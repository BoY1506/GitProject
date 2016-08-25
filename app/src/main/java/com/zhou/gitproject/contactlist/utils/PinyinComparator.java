package com.zhou.gitproject.contactlist.utils;

import com.zhou.gitproject.contactlist.bean.ManModel;

import java.util.Comparator;

/**
 * 信息比较
 * Created by zhou on 2016/8/15.
 */
public class PinyinComparator implements Comparator<ManModel> {

    public int compare(ManModel o1, ManModel o2) {
        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#") || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}