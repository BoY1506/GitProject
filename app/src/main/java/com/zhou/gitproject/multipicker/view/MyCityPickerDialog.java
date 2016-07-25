package com.zhou.gitproject.multipicker.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.zhou.gitproject.R;
import com.zhou.gitproject.multipicker.utils.CityConvert;
import com.zhou.gitproject.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 自定义城市选择器dialog
 * Created by zhou on 2016/7/15.
 */
public class MyCityPickerDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    //滑动控件
    private ScrollerNumberPicker provincePicker;
    private ScrollerNumberPicker cityPicker;
    private ScrollerNumberPicker areaPicker;

    //选中的信息
    private String curProvince = "";
    private String curCity = "";
    private String curArea = "";

    //刷新界面
    private static final int REFRESH_VIEW = 0x001;
    //选择监听
    private OnSelectingListener onSelectingListener;

    /**
     * 必须要加此handler，才能实现数据刷新
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_VIEW:
                    if (onSelectingListener != null)
                        onSelectingListener.selected(true);
                    break;
                default:
                    break;
            }
        }
    };

    public MyCityPickerDialog(Context context) {
        super(context, R.style.DialogStyle);
        mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        // 设置我们的布局到dialog中
        setContentView(R.layout.dialog_my_citypicker);
        //设置动画
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.DialogAnim);
        //设置控件
        findViewById(R.id.cancle_btn).setOnClickListener(this);
        findViewById(R.id.confirm_btn).setOnClickListener(this);
        provincePicker = (ScrollerNumberPicker) findViewById(R.id.citypicker_province);
        cityPicker = (ScrollerNumberPicker) findViewById(R.id.citypicker_city);
        areaPicker = (ScrollerNumberPicker) findViewById(R.id.citypicker_area);
        //初始化数据和监听器
        setDataAndListener();
    }

    /**
     * 设置数据和监听器
     */
    private void setDataAndListener() {
        //设置数据
        CityConvert.getInstance(mContext);
        provincePicker.setData(CityConvert.mProvinceDatas);
        provincePicker.setDefault(0);
        updateCities();
        //设置监听器
        provincePicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                //更新市（经测试，该方法会打印两次，所以做一下不等判断）
                if (!curProvince.equals(text)) {
                    curProvince = text;
                    updateCities();
                    handler.obtainMessage(REFRESH_VIEW).sendToTarget();
                }
            }

            @Override
            public void selecting(int id, String text) {
            }
        });
        cityPicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                //更新县
                if (!curCity.equals(text)) {
                    curCity = text;
                    updateAreas();
                    handler.obtainMessage(REFRESH_VIEW).sendToTarget();
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        areaPicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                //记录选中县
                if (!curArea.equals(text)) {
                    curArea = text;
                    handler.obtainMessage(REFRESH_VIEW).sendToTarget();
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        String provinceName = provincePicker.getSelectedText();
        ArrayList<String> cityList = CityConvert.mCitisDatasMap.get(provinceName);
        if (cityList == null) {
            cityList = new ArrayList<>();
            cityList.add("");
        }
        cityPicker.setData(cityList);
        cityPicker.setDefault(0);
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        String cityName = cityPicker.getSelectedText();
        ArrayList<String> areaList = CityConvert.mAreaDatasMap.get(cityName);
        if (areaList == null) {
            areaList = new ArrayList<>();
            areaList.add("");
        }
        areaPicker.setData(areaList);
        areaPicker.setDefault(0);
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
        return curProvince + curCity + curArea;
    }

    public interface OnSelectingListener {
        void selected(boolean selected);
    }

}
