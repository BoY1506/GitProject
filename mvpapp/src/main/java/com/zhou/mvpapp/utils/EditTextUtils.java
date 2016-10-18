package com.zhou.mvpapp.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * editText工具类
 * Created by zhou on 2015/12/15.
 */
public class EditTextUtils {

    /**
     * 添加剩余字数监听
     *
     * @param et
     * @param tv
     * @param num
     */
    public static void addSurplusNumWatcher(EditText et, final TextView tv, final int num) {

        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                tv.setText("剩余" + (num - s.toString().length()) + "字");
            }
        });
    }

    /**
     * 添加剩余字数监听
     *
     * @param et
     * @param tv
     * @param num
     */
    public static void addSurplusNumWatcher2(EditText et, final TextView tv, final int num) {

        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                tv.setText(s.toString().length() + " / " + num);
            }
        });
    }

    /**
     * 添加清除图标监听
     *
     * @param et
     * @param iv
     */
    public static void addClearIconWatcher(final EditText et, final ImageView iv) {

        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et.getText())) {
                    iv.setVisibility(View.VISIBLE);
                } else {
                    iv.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 获取ediText返回值
     *
     * @param et
     * @return
     */
    public static String getEtText(EditText et) {
        return et.getText().toString().trim();
    }

}
