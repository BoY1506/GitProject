package com.zhou.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.zhou.test.barcode.QRcodeTest;
import com.zhou.test.ormlite.ORMLiteTest;
import com.zhou.test.win8imitation.Win8Test;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 日常小练习
 */
public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
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
        }
        startActivity(intent);
    }
}