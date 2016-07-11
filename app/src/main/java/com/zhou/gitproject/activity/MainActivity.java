package com.zhou.gitproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 章节练习：短信、拍照、xml解析
 * Created by zhou on 2016/7/8.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;

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
    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt1:
                intent = new Intent(MainActivity.this, MsgTest.class);
                break;
            case R.id.bt2:
                intent = new Intent(MainActivity.this, StatusBarTest.class);
                break;
            case R.id.bt3:
                break;
        }
        startActivity(intent);
    }

}
