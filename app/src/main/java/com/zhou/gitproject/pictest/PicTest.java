package com.zhou.gitproject.pictest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.pictest.picselect.PicSelectTest;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 图片处理练习
 * Created by zhou on 2016/9/10.
 */
public class PicTest extends BaseActivity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                break;
            case R.id.bt2:
                intent2Activity(PicSelectTest.class);
                break;
            case R.id.bt3:
                break;
        }
    }

}
