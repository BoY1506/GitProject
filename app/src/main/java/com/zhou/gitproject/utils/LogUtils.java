package com.zhou.gitproject.utils;

import android.util.Log;

/**
 * Log工具类
 * Created by zhou on 2015/7/29.
 */
public class LogUtils {

    private static final boolean isShowLog = true;

    /**
     * 显示LOG(默认info级别)
     *
     * @param TAG
     * @param msg
     */
    public static void show(String TAG, String msg) {
        if (isShowLog) {
            show(TAG, msg, Log.ERROR);
        }
    }

    /**
     * 显示LOG
     *
     * @param TAG
     * @param msg
     * @param level 1-info; 2-debug; 3-verbose
     */
    public static void show(String TAG, String msg, int level) {
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
                    Log.i(TAG, msg);
                    break;
            }
        }
    }

}
