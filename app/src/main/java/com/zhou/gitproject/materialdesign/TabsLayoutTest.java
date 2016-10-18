package com.zhou.gitproject.materialdesign;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.utils.UIUtils;
import com.zhou.gitproject.viewpagerload.adapter.MyFragmentPagerAdapter;
import com.zhou.gitproject.viewpagerload.base.MyLoadFragment;
import com.zhou.gitproject.viewpagerload.fragment.Load1Fragment1;
import com.zhou.gitproject.viewpagerload.fragment.Load1Fragment2;
import com.zhou.gitproject.viewpagerload.fragment.Load1Fragment3;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * TabLayout练习
 * Created by zhou on 2016/8/19.
 */
public class TabsLayoutTest extends BaseActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.pager)
    ViewPager pager;

    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabslayout_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor(this, Color.parseColor("#ff6600"));
        initView();
    }

    private void initView() {
        //初始化标题
        List<String> titleList = new ArrayList<>();
        titleList.add("待付款");
        titleList.add("待发货");
        titleList.add("待收货");
//        titleList.add("待评价");
//        titleList.add("全部");
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
//        tabs.addTab(tabs.newTab());
//        tabs.addTab(tabs.newTab());
        //初始化fragment
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Load1Fragment1());
        fragmentList.add(new Load1Fragment2());
        fragmentList.add(new Load1Fragment3());
//        fragmentList.add(new Load1Fragment3());
//        fragmentList.add(new Load1Fragment3());
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(this);
        pager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(pager);
        tabs.setTabsFromPagerAdapter(pagerAdapter);

        UIUtils.dp2px(this, 50f);

    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("订单");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((MyLoadFragment) pagerAdapter.getItem(position)).firstRequest();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
