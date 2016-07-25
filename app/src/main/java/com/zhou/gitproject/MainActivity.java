package com.zhou.gitproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.circleimageview.CircleIvTest;
import com.zhou.gitproject.multipicker.MultiPickerTest;
import com.zhou.gitproject.picselect.PicSelectTest;
import com.zhou.gitproject.popfilter.PopFilterTest;
import com.zhou.gitproject.refreshlistview.RefreshListViewTest;
import com.zhou.gitproject.retrofit.RetrofitTest;
import com.zhou.gitproject.shufbanner.ShufBannerTest;
import com.zhou.gitproject.smscode.SmsCodeTest;
import com.zhou.gitproject.statusbar.StatusBarTest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 安卓常用功能整合
 * Created by zhou on 2016/7/8.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;
    @InjectView(R.id.bt4)
    Button bt4;
    @InjectView(R.id.bt5)
    Button bt5;
    @InjectView(R.id.bt6)
    Button bt6;
    @InjectView(R.id.bt7)
    Button bt7;
    @InjectView(R.id.bt7)
    Button bt8;
    @InjectView(R.id.bt9)
    Button bt9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7,R.id.bt8,R.id.bt9})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt1:
                intent = new Intent(MainActivity.this, StatusBarTest.class);
                break;
            case R.id.bt2:
                intent = new Intent(MainActivity.this, SmsCodeTest.class);
                break;
            case R.id.bt3:
                intent = new Intent(MainActivity.this, RetrofitTest.class);
                break;
            case R.id.bt4:
                intent = new Intent(MainActivity.this, MultiPickerTest.class);
                break;
            case R.id.bt5:
                intent = new Intent(MainActivity.this, PicSelectTest.class);
                break;
            case R.id.bt6:
                intent = new Intent(MainActivity.this, PopFilterTest.class);
                break;
            case R.id.bt7:
                intent = new Intent(MainActivity.this, RefreshListViewTest.class);
                break;
            case R.id.bt8:
                intent = new Intent(MainActivity.this, CircleIvTest.class);
                break;
            case R.id.bt9:
                intent = new Intent(MainActivity.this, ShufBannerTest.class);
                break;
        }
        startActivity(intent);
    }

}
