package com.zhou.gitproject.shufbanner.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 图片轮播适配器
 * Created by zhou on 2016/4/23.
 */
public class ShufBannerAdapter extends PagerAdapter {

    private Context context;
    private List<ImageView> imageViews;

    public ShufBannerAdapter(Context context, List<ImageView> mImages) {
        this.context = context;
        this.imageViews = mImages;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}


