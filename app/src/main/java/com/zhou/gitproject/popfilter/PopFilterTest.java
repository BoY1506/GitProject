package com.zhou.gitproject.popfilter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.popfilter.my.MyPopFilter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * popwindow筛选框练习
 * Created by zhou on 2016/7/18.
 */
public class PopFilterTest extends BaseActivity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popfilter_test);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.bt1, R.id.bt2})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt1:
                intent = new Intent(PopFilterTest.this, MyPopFilter.class);
                break;
            case R.id.bt2:
                break;
        }
        startActivity(intent);
    }

}
