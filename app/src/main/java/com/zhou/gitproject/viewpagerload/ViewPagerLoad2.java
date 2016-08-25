package com.zhou.gitproject.viewpagerload;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.zhou.gitproject.R;
import com.zhou.gitproject.viewpagerload.adapter.MyFragmentPagerAdapter;
import com.zhou.gitproject.viewpagerload.fragment.Load2Fragment1;
import com.zhou.gitproject.viewpagerload.fragment.Load2Fragment2;
import com.zhou.gitproject.viewpagerload.fragment.Load2Fragment3;
import com.zhou.gitproject.viewpagerload.view.MyLazyPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * activity控制viewpager加载
 * 每个fragment里由一个标志位 + userVisibleHint控制
 * Created by zhou on 2016/8/16.
 */
public class ViewPagerLoad2 extends FragmentActivity {

    @InjectView(R.id.my_pager_indicaotr)
    MyLazyPagerIndicator myLazyPagerIndicator;
    @InjectView(R.id.pager)
    ViewPager pager;

    private List<Fragment> fragmentList;//fragmentList
    private MyFragmentPagerAdapter pagerAdapter;//pager适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_load2);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        // 添加导航栏
        fragmentList = new ArrayList<>();
        fragmentList.add(new Load2Fragment1());
        fragmentList.add(new Load2Fragment2());
        fragmentList.add(new Load2Fragment3());
        //设置指示器
        myLazyPagerIndicator.setTvNum(3);
        myLazyPagerIndicator.setTvText("待付款", "待发货", "全部订单");
        myLazyPagerIndicator.setTvTextSize(18);
        myLazyPagerIndicator.setViewPager(pager);
        // 设置适配器
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, null);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
    }

}
