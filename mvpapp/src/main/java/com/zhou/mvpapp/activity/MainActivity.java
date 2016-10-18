package com.zhou.mvpapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.adapter.MyFragmentPagerAdapter;
import com.zhou.mvpapp.base.BaseActivity;
import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.fragment.UserLoginFragment;
import com.zhou.mvpapp.fragment.UserRegisterFragment;
import com.zhou.mvpapp.utils.ActionBarBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * MVP架构项目实践
 * 登录注册+查看工作列表
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarBigText("登录注册");
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        //初始化标题
        List<String> titleList = new ArrayList<>();
        titleList.add("登录");
        titleList.add("注册");
        //添加tabs
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        //初始化fragment
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new UserLoginFragment());
        fragmentList.add(new UserRegisterFragment());
        //设置适配器
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragmentList, titleList);
        pager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(pager);
        tabs.setTabsFromPagerAdapter(pagerAdapter);
    }

}