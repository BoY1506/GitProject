package com.zhou.gitproject.utils;

import android.content.Context;
import android.widget.Toast;

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
    public static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

}
