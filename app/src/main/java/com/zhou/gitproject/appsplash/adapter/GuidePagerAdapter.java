package com.zhou.gitproject.appsplash.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 引导页viewPager适配器
 * Created by zhou on 2016/1/18.
 */
public class GuidePagerAdapter extends PagerAdapter {

    private List<View> views;

    public GuidePagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
