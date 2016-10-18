package com.zhou.mvpapp.utils;

import android.widget.Toast;

import com.zhou.mvpapp.base.BaseApplication;

/**
 * Toast工具类
 * Created by zhou on 2016/7/12.
 */
public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //统一的Toast
    private static Toast mToast;

    /**
     * 显示Toast
     */
    public static void showShortToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getAppContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 显示Toast（带时长）
     */
    public static void showLongToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getAppContext(), text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

}
