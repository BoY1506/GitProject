package com.zhou.gitproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.appsplash.SplashActivity;
import com.zhou.gitproject.baidumap.BaiduMapTest;
import com.zhou.gitproject.barcode.QRcodeTest;
import com.zhou.gitproject.blurbitmap.BlurbitmapTest;
import com.zhou.gitproject.circleimageview.CircleIvTest;
import com.zhou.gitproject.cityselect2.CitySelectTest;
import com.zhou.gitproject.contactlist.ContactListTest;
import com.zhou.gitproject.gesturelock.GestureLockTest;
import com.zhou.gitproject.listviewselall.ListViewSelAllTest;
import com.zhou.gitproject.materialdesign.MaterialDesignTest;
import com.zhou.gitproject.multipicker.MultiPickerTest;
import com.zhou.gitproject.mydialog.FragmentDialogTest;
import com.zhou.gitproject.pdfreader.PDFViewReader;
import com.zhou.gitproject.pictest.PicTest;
import com.zhou.gitproject.popfilter.PopFilterTest;
import com.zhou.gitproject.refreshlistview.RefreshListViewTest;
import com.zhou.gitproject.retrofit2.RetrofitTest;
import com.zhou.gitproject.shufbanner.ShufBannerTest;
import com.zhou.gitproject.smscode.SmsCodeTest;
import com.zhou.gitproject.statusbar.StatusBarTest;
import com.zhou.gitproject.videoplay.VideoPlayTest;
import com.zhou.gitproject.viewpagerload.ViewPagerLoadTest;
import com.zhou.gitproject.xmlparse.XMLParseTest;

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
    @InjectView(R.id.bt10)
    Button bt10;
    @InjectView(R.id.bt11)
    Button bt11;
    @InjectView(R.id.bt12)
    Button bt12;
    @InjectView(R.id.bt13)
    Button bt13;
    @InjectView(R.id.bt14)
    Button bt14;
    @InjectView(R.id.bt15)
    Button bt15;
    @InjectView(R.id.bt16)
    Button bt16;
    @InjectView(R.id.bt17)
    Button bt17;
    @InjectView(R.id.bt18)
    Button bt18;
    @InjectView(R.id.bt19)
    Button bt19;
    @InjectView(R.id.bt20)
    Button bt20;
    @InjectView(R.id.bt21)
    Button bt21;
    @InjectView(R.id.bt22)
    Button bt22;
    @InjectView(R.id.bt23)
    Button bt23;

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
    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9,
            R.id.bt10, R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15, R.id.bt16, R.id.bt17,
            R.id.bt18, R.id.bt19, R.id.bt20, R.id.bt21, R.id.bt22, R.id.bt23})
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
                intent = new Intent(MainActivity.this, PicTest.class);
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
            case R.id.bt10:
                intent = new Intent(MainActivity.this, SplashActivity.class);
                break;
            case R.id.bt11:
                intent = new Intent(MainActivity.this, QRcodeTest.class);
                break;
            case R.id.bt12:
                intent = new Intent(MainActivity.this, FragmentDialogTest.class);
                break;
            case R.id.bt13:
                intent = new Intent(MainActivity.this, ContactListTest.class);
                break;
            case R.id.bt14:
                intent = new Intent(MainActivity.this, ViewPagerLoadTest.class);
                break;
            case R.id.bt15:
                intent = new Intent(MainActivity.this, ListViewSelAllTest.class);
                break;
            case R.id.bt16:
                intent = new Intent(MainActivity.this, XMLParseTest.class);
                break;
            case R.id.bt17:
                intent = new Intent(MainActivity.this, BlurbitmapTest.class);
                break;
            case R.id.bt18:
                intent = new Intent(MainActivity.this, MaterialDesignTest.class);
                break;
            case R.id.bt19:
                intent = new Intent(MainActivity.this, CitySelectTest.class);
                break;
            case R.id.bt20:
                intent = new Intent(MainActivity.this, BaiduMapTest.class);
                break;
            case R.id.bt21:
                intent = new Intent(MainActivity.this, GestureLockTest.class);
                break;
            case R.id.bt22:
                intent = new Intent(MainActivity.this, VideoPlayTest.class);
                break;
            case R.id.bt23:
                intent = new Intent(MainActivity.this, PDFViewReader.class);
                break;
        }
        startActivity(intent);
    }

}