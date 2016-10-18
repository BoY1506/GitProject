package com.zhou.gitproject.utils;

import java.text.DecimalFormat;

/**
 * 文本格式化工具类
 * Created by zhou on 2016/8/29.
 */
public class TextFormatUtils {

    private static DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance();

    /**
     * 保留小数位数
     *
     * @param number
     * @param digits
     * @return
     */
    public static String getDecimals(float number, int digits) {
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
        numberFormat.applyPattern("00.00%");
        return numberFormat.format(number);
    }

    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size
     * @return
     */
    public static String getFileSize(long size) {
        numberFormat.applyPattern("####.00");
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return numberFormat.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return numberFormat.format(mbsize) + "MB";
        } else {
            return "size: error";
        }
    }

}
