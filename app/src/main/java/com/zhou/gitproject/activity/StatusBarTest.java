package com.zhou.gitproject.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 沉浸式状态栏练习
 * Created by zhou on 2016/7/8.
 */
public class StatusBarTest extends Activity {

    @InjectView(R.id.mainView)
    TextView mainView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.main_layout)
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor4Drawlayout(this, mainLayout, Color.parseColor("#ff4c3b"));
    }

}
