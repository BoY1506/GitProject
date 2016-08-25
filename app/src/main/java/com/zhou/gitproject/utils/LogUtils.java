package com.zhou.gitproject.utils;

import android.util.Log;

/**
 * Log工具类
 * Created by zhou on 2015/7/29.
 */
public class LogUtils {

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //修改此值可控制是否打印log
    private static boolean isShowLog = true;

    //默认TAG名称
    private static final String TAG = "APP";

    /**
     * 显示LOG(默认error级别)
     *
     * @param msg
     */
    public static void show(String msg) {
        if (isShowLog) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 显示LOG(自定义TAG)
     *
     * @param tag
     * @param msg
     */
    public static void show(String tag, String msg) {
        if (isShowLog) {
            Log.e(tag, msg);
        }
    }

    /**
     * 显示LOG(自定义级别)
     *
     * @param msg
     * @param level 1-info; 2-debug; 3-verbose
     */
    public static void show(String msg, int level) {
        if (isShowLog) {
            switch (level) {
                case Log.VERBOSE:
                    Log.v(TAG, msg);
                    break;
                case Log.DEBUG:
                    Log.d(TAG, msg);
                    break;
                case Log.INFO:
                    Log.i(TAG, msg);
                    break;
                case Log.WARN:
                    Log.w(TAG, msg);
                    break;
                case Log.ERROR:
                    Log.e(TAG, msg);
                    break;
                default:
                    Log.e(TAG, msg);
                    break;
            }
        }
    }

    /**
     * 显示LOG(自定义TAG和级别)
     *
     * @param tag
     * @param msg
     * @param level
     */
    public static void show(String tag, String msg, int level) {
        if (isShowLog) {
            switch (level) {
                case Log.VERBOSE:
                    Log.v(tag, msg);
                    break;
                case Log.DEBUG:
                    Log.d(tag, msg);
                    break;
                case Log.INFO:
                    Log.i(tag, msg);
                    break;
                case Log.WARN:
                    Log.w(tag, msg);
                    break;
                case Log.ERROR:
                    Log.e(tag, msg);
                    break;
                default:
                    Log.e(tag, msg);
                    break;
            }
        }
    }

}
