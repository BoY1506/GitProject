package com.zhou.gitproject.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘控制工具类
 * Created by zhou on 2016/7/28.
 */
public class KeyBoardUtils {

    /**
     * 打卡软键盘
     *
     * @param editText 输入框
     * @param context  上下文
     */
    public static void openKeybord(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param editText 输入框
     * @param context  上下文
     */
    public static void closeKeybord(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
