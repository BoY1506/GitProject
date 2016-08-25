package com.zhou.gitproject.multipicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.multipicker.utils.AssetsUtils;
import com.zhou.gitproject.multipicker.view.MyCityPickerDialog;
import com.zhou.gitproject.multipicker.view.MyConsPickerDialog;
import com.zhou.gitproject.utils.ActionBarBuilder;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.ColorPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.FilePicker;
import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.util.StorageUtils;

/**
 * 两种省市县级联选择器
 * 1.NumberPicker
 * 2.github控件--AndroidPicker
 * Created by zhou on 2016/7/14.
 */
public class MultiPickerTest extends BaseActivity {

    @InjectView(R.id.select_city_btn1)
    Button selectCityBtn1;
    @InjectView(R.id.select_cons_btn1)
    Button selectConsBtn1;
    @InjectView(R.id.sel_btn1)
    Button selBtn1;
    @InjectView(R.id.sel_btn2)
    Button selBtn2;
    @InjectView(R.id.sel_btn3)
    Button selBtn3;
    @InjectView(R.id.sel_btn4)
    Button selBtn4;
    @InjectView(R.id.sel_btn5)
    Button selBtn5;
    @InjectView(R.id.sel_btn6)
    Button selBtn6;
    @InjectView(R.id.sel_btn7)
    Button selBtn7;
    @InjectView(R.id.sel_btn8)
    Button selBtn8;
    @InjectView(R.id.sel_btn9)
    Button selBtn9;
    @InjectView(R.id.sel_btn10)
    Button selBtn10;
    @InjectView(R.id.sel_btn11)
    Button selBtn11;
    @InjectView(R.id.sel_btn12)
    Button selBtn12;

    private MyCityPickerDialog cityPickerDialog;
    private MyConsPickerDialog consPickerDialog;

    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multipicker_test);
        ButterKnife.inject(this);
        initPickerDialog();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    /**
     * 初始化pickerDialog
     */
    private void initPickerDialog() {
        cityPickerDialog = new MyCityPickerDialog(this);
        consPickerDialog = new MyConsPickerDialog(this);
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.select_city_btn1, R.id.select_cons_btn1, R.id.sel_btn1, R.id.sel_btn2, R.id.sel_btn3,
            R.id.sel_btn4, R.id.sel_btn5, R.id.sel_btn6, R.id.sel_btn7, R.id.sel_btn8, R.id.sel_btn9,
            R.id.sel_btn10, R.id.sel_btn11, R.id.sel_btn12})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_city_btn1:
                cityPickerDialog.show();
                break;
            case R.id.select_cons_btn1:
                consPickerDialog.show();
                break;
            case R.id.sel_btn1:
                //年月日选择
                DatePicker ymdPicker = new DatePicker(this);
                ymdPicker.setRange(1970, 2030);
                ymdPicker.setSelectedItem(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                ymdPicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        showToast(year + month + day);
                    }
                });
                ymdPicker.show();
                break;
            case R.id.sel_btn2:
                //年月选择
                DatePicker ymPicker = new DatePicker(this);
                ymPicker.setRange(2000, 2050);
                ymPicker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
                    @Override
                    public void onDatePicked(String year, String month) {
                        showToast(year + month);
                    }
                });
                ymPicker.show();
                break;
            case R.id.sel_btn3:
                //月日选择
                DatePicker mdPicker = new DatePicker(this, DatePicker.MONTH_DAY);
                mdPicker.setOnDatePickListener(new DatePicker.OnMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String month, String day) {
                        showToast(month + "-" + day);
                    }
                });
                mdPicker.show();
                break;
            case R.id.sel_btn4:
                TimePicker timePicker = new TimePicker(this);
                timePicker.setTopLineVisible(false);
                timePicker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
                    @Override
                    public void onTimePicked(String hour, String minute) {
                        showToast(hour + ":" + minute);
                    }
                });
                timePicker.show();
                break;
            case R.id.sel_btn5:
                //单项选择
                OptionPicker optionPicker = new OptionPicker(this, new String[]{
                        "第一项", "第二项", "这是一个很长很长很长很长很长很长很长很长很长的很长很长的很长很长的项"
                });
//                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setSubmitTextColor(Color.parseColor("#ff6600"));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int i, String s) {
                        showToast(s);
                    }
                });
                optionPicker.show();
                break;
            case R.id.sel_btn6:
                //生肖选择
                OptionPicker consPicker = new OptionPicker(this, new String[]{
                        "水瓶", "双鱼", "白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯",
                });
                consPicker.setLabel("座哦");
                consPicker.setTopBackgroundColor(0xFFEEEEEE);
                consPicker.setLineVisible(true);
                consPicker.setCancelTextColor(0xFFEE0000);
                consPicker.setSubmitTextColor(0xFF33B5E5);
                consPicker.setTextColor(0xFFFF0000, 0xFF999999);
                consPicker.setLineColor(0xFFEE0000);
                consPicker.setSelectedItem("射手");
                consPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int position, String option) {
                        showToast(option);
                    }
                });
                consPicker.show();
                break;
            case R.id.sel_btn7:
                //时间选择
                ArrayList<String> firstList = new ArrayList<String>();
                firstList.add("今天");
                firstList.add("明天");
                ArrayList<ArrayList<String>> secondList = new ArrayList<ArrayList<String>>();
                ArrayList<String> secondListItem = new ArrayList<String>();
                for (int i = 0; i < 24; i++) {
                    secondListItem.add(DateUtils.fillZero(i) + "点");
                }
                secondList.add(secondListItem);//对应今天
                secondList.add(secondListItem);//对应明天
                ArrayList<ArrayList<ArrayList<String>>> thirdList = new ArrayList<ArrayList<ArrayList<String>>>();
                ArrayList<ArrayList<String>> thirdListItem1 = new ArrayList<ArrayList<String>>();
                ArrayList<String> thirdListItem2 = new ArrayList<String>();
                for (int i = 0; i < 60; i++) {
                    thirdListItem2.add(DateUtils.fillZero(i) + "分");
                }
                for (int i = 0; i < 24; i++) {
                    thirdListItem1.add(thirdListItem2);//对应0-23点
                }
                thirdList.add(thirdListItem1);//对应今天
                thirdList.add(thirdListItem1);//对应明天
                LinkagePicker linkagePicker = new LinkagePicker(this, firstList, secondList);
                linkagePicker.setSelectedItem("明天", "9点");
                linkagePicker.setOnLinkageListener(new LinkagePicker.OnLinkageListener() {
                    @Override
                    public void onPicked(String first, String second, String third) {
                        showToast(first + "-" + second + "-" + third);
                    }
                });
                linkagePicker.show();
                break;
            case R.id.sel_btn8:
                //数字选择
                NumberPicker numberPicker = new NumberPicker(this);
                numberPicker.setAnimationStyle(R.style.DialogAnim);
                numberPicker.setOffset(4);//偏移量
                numberPicker.setRange(40, 100);//数字范围
                numberPicker.setSelectedItem(65);
                numberPicker.setLabel("Kg");
                numberPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int position, String option) {
                        showToast(option);
                    }
                });
                numberPicker.show();
                break;
            case R.id.sel_btn9:
                //颜色选择
                final ColorPicker colorPicker = new ColorPicker(this);
                colorPicker.setInitColor(Color.parseColor("#ff6600"));
                colorPicker.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
                    @Override
                    public void onColorPicked(int pickedColor) {
                        showToast(ConvertUtils.toColorString(pickedColor));
                    }
                });
                colorPicker.show();
                break;
            case R.id.sel_btn10:
                //文件选择
                FilePicker filePicker = new FilePicker(this, FilePicker.FILE);
                filePicker.setShowHideDir(true);
                //picker.setAllowExtensions(new String[]{".jpg",".png",".jpeg"});
                filePicker.setOnFilePickListener(new FilePicker.OnFilePickListener() {
                    @Override
                    public void onFilePicked(String currentPath) {
                        showToast(currentPath);
                    }
                });
                filePicker.show();
                break;
            case R.id.sel_btn11:
                //文件夹选择
                FilePicker dirPicker = new FilePicker(this, FilePicker.DIRECTORY);
                dirPicker.setRootPath(StorageUtils.getRootPath(this) + "vketang/");
                dirPicker.setOnFilePickListener(new FilePicker.OnFilePickListener() {
                    @Override
                    public void onFilePicked(String currentPath) {
                        showToast(currentPath);
                    }
                });
                dirPicker.show();
                break;
            case R.id.sel_btn12:
                //地区选择
                ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
                String json = AssetsUtils.readText(this, "city.json");
                data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
//                AddressPicker picker = new AddressPicker(this, data);
                CustomHeaderAndFooterPicker picker = new CustomHeaderAndFooterPicker(this, data);
                //picker.setHideProvince(true);//加上此句举将只显示地级及县级
                //picker.setHideCounty(true);//加上此句举将只显示省级及地级
                picker.setAnimationStyle(R.style.DialogAnim);
                picker.setTextColor(Color.parseColor("#000000"));
                picker.setTitleText("选择地区");
                picker.setLineColor(Color.parseColor("#ff6600"));
                picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {

                    }
                });
                picker.show();
                break;
        }
    }

    /**
     * 自定义视图的picker
     */
    public class CustomHeaderAndFooterPicker extends AddressPicker {

        public CustomHeaderAndFooterPicker(Activity activity, ArrayList<Province> data) {
            super(activity, data);
        }

        @Nullable
        @Override
        protected View makeHeaderView() {
            //顶部视图
            View headerView = LayoutInflater.from(MultiPickerTest.this).inflate(R.layout.view_dialog_header, null);
            headerView.findViewById(R.id.cancle_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCancel();
                    dismiss();
                }
            });
            headerView.findViewById(R.id.confirm_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSubmit();
                    dismiss();
                }
            });
            return headerView;
        }

    }
}
