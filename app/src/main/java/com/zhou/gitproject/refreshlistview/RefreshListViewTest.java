package com.zhou.gitproject.refreshlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.refreshlistview.mylv.MyUltraLvTest;
import com.zhou.gitproject.refreshlistview.swipelv.SwipeLvTest;
import com.zhou.gitproject.refreshlistview.ultralv.UltraLvTest;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 下拉刷新上拉加载的实现
 * Created by zhou on 2016/7/20.
 */
public class RefreshListViewTest extends BaseActivity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshlv_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt1:
                intent = new Intent(RefreshListViewTest.this, UltraLvTest.class);
                break;
            case R.id.bt2:
                intent = new Intent(RefreshListViewTest.this, MyUltraLvTest.class);
                break;
            case R.id.bt3:
                intent = new Intent(RefreshListViewTest.this, SwipeLvTest.class);
                break;
        }
        startActivity(intent);
    }
}
