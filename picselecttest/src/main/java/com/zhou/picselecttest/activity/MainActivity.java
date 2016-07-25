package com.zhou.picselecttest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.picselecttest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 多种方式实现拍照、相册选择+大图浏览
 */
public class MainActivity extends Activity {

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
                intent = new Intent(MainActivity.this, FirstTypePicSelect.class);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, SecondTypePicSelect.class);
                break;
            case R.id.btn3:
                break;
        }
        startActivity(intent);
    }
}
