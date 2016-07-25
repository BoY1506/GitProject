package com.zhou.gitproject.multipicker.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 自定义星座选择器dialog
 * Created by zhou on 2016/7/15.
 */
public class MyConsPickerDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    //滑动控件
    private ScrollerNumberPicker consPicker;

    //星座数据
    private String[] consArray = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座 ", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    //选中的信息
    private String curCons = "";

    public MyConsPickerDialog(Context context) {
        super(context, R.style.DialogStyle);
        mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        // 设置我们的布局到dialog中
        setContentView(R.layout.dialog_my_conspicker);
        //设置动画
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.DialogAnim);
        //设置控件
        findViewById(R.id.cancle_btn).setOnClickListener(this);
        findViewById(R.id.confirm_btn).setOnClickListener(this);
        consPicker = (ScrollerNumberPicker) findViewById(R.id.conspicker);
        //初始化数据和监听器
        setDataAndListener();
    }

    /**
     * 设置数据和监听器
     */
    private void setDataAndListener() {
        //设置数据
        ArrayList<String> consList = new ArrayList<>();
        Collections.addAll(consList, consArray);
        consPicker.setData(consList);
        consPicker.setDefault(0);
        //设置监听器
        consPicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                //经测试，该方法会打印两次，所以做一下不等判断
                if (!curCons.equals(text)) {
                    curCons = text;
                }
            }

            @Override
            public void selecting(int id, String text) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn:
                ToastUtils.showToast(mContext, getSelectedText(), Toast.LENGTH_SHORT);
                dismiss();
                break;
            case R.id.cancle_btn:
                dismiss();
                break;
        }
    }

    /**
     * 获取选中省市县信息
     *
     * @return
     */
    public String getSelectedText() {
        return curCons;
    }

}
