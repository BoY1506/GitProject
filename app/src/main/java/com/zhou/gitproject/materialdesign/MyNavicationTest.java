package com.zhou.gitproject.materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhou.gitproject.R;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 使用Toolbar + DrawerLayout快速实现高大上菜单侧滑
 * Created by zhou on 2016/8/22.
 */
public class MyNavicationTest extends ActionBarActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
    @InjectView(R.id.mainView)
    TextView mainView;
    @InjectView(R.id.main_layout)
    LinearLayout mainLayout;
    @InjectView(R.id.navigation_view)
    NavigationView navigationView;
    @InjectView(R.id.drawer_ly)
    DrawerLayout drawerLy;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationview_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor(this, Color.parseColor("#77db93"));
        titleBar.setVisibility(View.GONE);
        initView();
    }

    private void initView() {
        tlCustom.setTitle("Toolbar");//设置Toolbar标题
        tlCustom.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(tlCustom);
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLy, tlCustom, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        //此句不可少
        mDrawerToggle.syncState();
        drawerLy.setDrawerListener(mDrawerToggle);
        //菜单点击
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_1:
                        ToastUtils.showToast(getApplicationContext(), "CC Talk", Toast.LENGTH_SHORT);
                        break;
                    case R.id.item_2:
                        ToastUtils.showToast(getApplicationContext(), "HJ Class", Toast.LENGTH_SHORT);
                        break;
                    case R.id.item_3:
                        ToastUtils.showToast(getApplicationContext(), "Words", Toast.LENGTH_SHORT);
                        break;
                    case R.id.item_4:
                        ToastUtils.showToast(getApplicationContext(), "Big HJ", Toast.LENGTH_SHORT);
                        break;
                    case R.id.item_5:
                        ToastUtils.showToast(getApplicationContext(), "Android", Toast.LENGTH_SHORT);
                        break;
                    case R.id.item_6:
                        ToastUtils.showToast(getApplicationContext(), "IOS", Toast.LENGTH_SHORT);
                        break;
                }
                return false;
            }
        });
    }

}
