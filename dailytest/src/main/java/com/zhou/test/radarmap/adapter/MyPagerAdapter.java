package com.zhou.test.radarmap.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhou.test.R;
import com.zhou.test.radarmap.been.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager的适配器
 * Created by zhou on 2016/10/2.
 */
public class MyPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Info> data;
    private ViewPager pager;

    public MyPagerAdapter(Context context, ViewPager pager) {
        this.context = context;
        this.pager = pager;
        data = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final Info info = data.get(position);
        //设置一大堆演示用的数据，麻里麻烦~~
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_layout, null);
        ImageView ivPortrait = (ImageView) view.findViewById(R.id.iv);
        ImageView ivSex = (ImageView) view.findViewById(R.id.iv_sex);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvDistance = (TextView) view.findViewById(R.id.tv_distance);
        tvName.setText(info.getName());
        tvDistance.setText(info.getDistance() + "km");
        ivPortrait.setImageResource(info.getPortraitId());
        if (info.getSex()) {
            ivSex.setImageResource(R.drawable.girl);
        } else {
            ivSex.setImageResource(R.drawable.boy);
        }
        ivPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "这是 " + info.getName() + " >.<", Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    public ViewPager getPager() {
        return pager;
    }

    public void addDate(Info info) {
        if (data != null) {
            data.add(info);
            notifyDataSetChanged();
        }
    }

}
