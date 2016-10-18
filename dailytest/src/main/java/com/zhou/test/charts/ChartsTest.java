package com.zhou.test.charts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.test.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 图表练习
 * Created by zhou on 2016/8/29.
 */
public class ChartsTest extends Activity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartstest);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(ChartsTest.this, LineChartTest.class);
                break;
            case R.id.btn2:
                intent = new Intent(ChartsTest.this, ColumnChartTest.class);
                break;
            case R.id.btn3:
                intent = new Intent(ChartsTest.this, PieChartTest.class);
                break;
        }
        startActivity(intent);
    }

}
