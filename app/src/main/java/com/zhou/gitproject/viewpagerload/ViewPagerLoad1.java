package com.zhou.gitproject.viewpagerload;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.zhou.gitproject.R;
import com.zhou.gitproject.viewpagerload.adapter.MyFragmentPagerAdapter;
import com.zhou.gitproject.viewpagerload.fragment.Load1Fragment1;
import com.zhou.gitproject.viewpagerload.fragment.Load1Fragment2;
import com.zhou.gitproject.viewpagerload.fragment.Load1Fragment3;
import com.zhou.gitproject.viewpagerload.view.MyPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * activity控制viewpager加载
 * 每个fragment里由一个标志位控制第一次进入时加载数据
 * Created by zhou on 2016/8/16.
 */
public class ViewPagerLoad1 extends FragmentActivity {

    @InjectView(R.id.my_pager_indicaotr)
    MyPagerIndicator myPagerIndicaotr;
    @InjectView(R.id.pager)
    ViewPager pager;

    private List<Fragment> fragmentList;//fragmentList
    private MyFragmentPagerAdapter pagerAdapter;//pager适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_load1);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        // 添加导航栏
        fragmentList = new ArrayList<>();
        fragmentList.add(new Load1Fragment1());
        fragmentList.add(new Load1Fragment2());
        fragmentList.add(new Load1Fragment3());
        fragmentList.add(new Load1Fragment3());
        fragmentList.add(new Load1Fragment3());
        //设置指示器
        myPagerIndicaotr.setTvNum(5);
        myPagerIndicaotr.setTvText("待付款", "待发货", "待收货", "待评价", "全部");
        myPagerIndicaotr.setTvUnSeLColor(Color.GRAY);
        myPagerIndicaotr.setTvSeLColor(Color.RED);
        myPagerIndicaotr.setTvTextSize(16);
        myPagerIndicaotr.setViewPager(pager);
        // 设置适配器
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, null);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
    }

}
