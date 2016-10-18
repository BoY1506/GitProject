package com.zhou.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.test.charts.ChartsTest;
import com.zhou.test.circleprogress.CircleProgressTest;
import com.zhou.test.couponview.CouponViewTest;
import com.zhou.test.ormlite.ORMLiteTest;
import com.zhou.test.qrbar.QRcodeTest;
import com.zhou.test.radarmap.RadarMapTest;
import com.zhou.test.unreadmsg.UnReadMsgTest;
import com.zhou.test.win8imitation.Win8Test;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 日常小练习
 */
public class MainActivity extends Activity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn4)
    Button btn4;
    @InjectView(R.id.btn5)
    Button btn5;
    @InjectView(R.id.btn6)
    Button btn6;
    @InjectView(R.id.btn7)
    Button btn7;
    @InjectView(R.id.btn8)
    Button btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(MainActivity.this, Win8Test.class);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, ORMLiteTest.class);
                break;
            case R.id.btn3:
                intent = new Intent(MainActivity.this, QRcodeTest.class);
                break;
            case R.id.btn4:
                intent = new Intent(MainActivity.this, ChartsTest.class);
                break;
            case R.id.btn5:
                intent = new Intent(MainActivity.this, CouponViewTest.class);
                break;
            case R.id.btn6:
                intent = new Intent(MainActivity.this, RadarMapTest.class);
                break;
            case R.id.btn7:
                intent = new Intent(MainActivity.this, CircleProgressTest.class);
                break;
            case R.id.btn8:
                intent = new Intent(MainActivity.this, UnReadMsgTest.class);
                break;
        }
        startActivity(intent);
    }
}