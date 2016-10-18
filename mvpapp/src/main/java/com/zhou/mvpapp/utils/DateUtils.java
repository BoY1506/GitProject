package com.zhou.mvpapp.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式化工具类
 * Created by zhou on 2016/8/29.
 */
public class DateUtils {

    /**
     * 获取当前年份(int)
     *
     * @return
     */
    public static int getCurYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份(int)
     *
     * @return
     */
    public static int getCurMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日(int)
     *
     * @return
     */
    public static int getCurDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     * 24小时制
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurTime() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurDate() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 获取当前格式化日期
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurFormatDate() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy年MM月dd日").format(date);
    }

    /**
     * 获取当前格式化日期(年月)
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurFormatDate2() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy年MM月").format(date);
    }

    /**
     * 获取当前日期+日期
     *
     * @return
     */
    public static Date getToDate(Date date, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, add);
        return calendar.getTime();
    }

}
