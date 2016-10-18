package com.zhou.mvpapp.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 文本格式化工具类
 * Created by zhou on 2016/8/29.
 */
public class TextFormatUtils {

    /**
     * 获取整数
     *
     * @param number
     * @return
     */
    public static String getNumber(double number) {
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        return numberFormat.format(number);
    }

    /**
     * 保留小数位数
     *
     * @param number
     * @param digits
     * @return
     */
    public static String getDecimals(float number, int digits) {
        DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance();
        numberFormat.setMaximumFractionDigits(digits);
        return numberFormat.format(number);
    }

    /**
     * 保留百分比格式
     *
     * @param number
     * @return
     */
    public static String getPercents(float number) {
        DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance();
        numberFormat.applyPattern("00.00%");
        return numberFormat.format(number);
    }

}
