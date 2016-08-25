package com.zhou.gitproject.statusbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 沉浸式状态栏
 * 需要设置主题为noTitle
 * Created by zhou on 2016/7/8.
 */
public class StatusBarTest extends BaseActivity {

    @InjectView(R.id.mainView)
    TextView mainView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.main_layout)
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor4Drawlayout(this, mainLayout, Color.parseColor("#ff4c3b"));
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

}
