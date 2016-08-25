package com.zhou.gitproject.viewpagerload.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.gitproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义viewpagerTitle+指示器(配合懒加载)
 * Created by zhou on 2016/8/16.
 */
public class MyLazyPagerIndicator extends LinearLayout implements ViewPager.OnPageChangeListener, View.OnClickListener {

    /**
     * 放置textView的ll
     */
    private LinearLayout tvLl;

    /**
     * 选项textView数量
     */
    private int tvNum = 0;

    /**
     * 选项textView集合
     */
    private List<TextView> tvList;

    /**
     * 文字大小
     */
    private int tvTextSize;

    /**
     * 文字未选中颜色
     */
    private int tvUnSelColor;

    /**
     * 文字选中颜色
     */
    private int tvSelColor;

    /**
     * 下划线
     */
    private View underLine;

    /**
     * 下划线宽度
     */
    private int underLineWidth = 0;

    /**
     * viewPager
     */
    private ViewPager pager;

    /**
     * 上一个pager下标
     */
    private int lastPagerIndex = 0;

    public MyLazyPagerIndicator(Context context) {
        this(context, null);
    }

    public MyLazyPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLazyPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        //初始化布局
        setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setOrientation(LinearLayout.VERTICAL);
        //文字布局ll
        tvLl = new LinearLayout(getContext());
        tvLl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
        tvLl.setOrientation(LinearLayout.HORIZONTAL);
        //文字集合
        tvList = new ArrayList<>();
        tvTextSize = 18;
        tvUnSelColor = Color.parseColor("#646464");
        tvSelColor = Color.parseColor("#ff6600");
        //下划线
        underLine = new View(getContext());
        underLine.setBackgroundColor(tvSelColor);
        //将控件加入布局
        addView(tvLl);
        addView(underLine);
    }

    /**
     * 设置tv个数
     *
     * @param num
     */
    public void setTvNum(int num) {
        this.tvNum = num;
        for (int i = 0; i < tvNum; i++) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(tvTextSize);
            if (i == 0) {
                tv.setTextColor(tvSelColor);
            } else {
                tv.setTextColor(tvUnSelColor);
            }
            tv.setOnClickListener(this);
            tvLl.addView(tv);
            tvList.add(tv);
        }
        //更新下划线宽度
        underLineWidth = UIUtils.getScreenWidthPixels((Activity) getContext()) / tvNum;
        underLine.setLayoutParams(new LayoutParams(underLineWidth, UIUtils.dp2px(getContext(), 3f)));
    }

    /**
     * 设置tv标题
     */
    public void setTvText(String... titles) {
        if (titles.length == tvNum) {
            //只有标题数量等于tvNum才进行设置
            for (int i = 0; i < tvList.size(); i++) {
                tvList.get(i).setText(titles[i]);
            }
        }
    }

    /**
     * 设置tv文字大小
     *
     * @param size
     */
    public void setTvTextSize(int size) {
        for (TextView tv : tvList) {
            tv.setTextSize(size);
        }
    }

    /**
     * 设置tv文字未选中颜色
     *
     * @param color
     */
    public void setTvUnSeLColor(int color) {
        tvUnSelColor = color;
        for (int i = 1; i < tvList.size(); i++) {
            tvList.get(i).setTextColor(tvUnSelColor);
        }
    }

    /**
     * 设置tv文字选中颜色
     *
     * @param color
     */
    public void setTvSeLColor(int color) {
        tvSelColor = color;
        tvList.get(0).setTextColor(tvSelColor);
        underLine.setBackgroundColor(color);
    }

    /**
     * 设置viewPagwer
     *
     * @param vierPager
     */
    public void setViewPager(ViewPager vierPager) {
        this.pager = vierPager;
        pager.setOnPageChangeListener(this);
        pager.setOffscreenPageLimit(tvNum - 1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LayoutParams params = (LayoutParams) underLine.getLayoutParams();
        params.leftMargin = (int) ((position + positionOffset) * underLineWidth);
        underLine.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        //设置字体颜色
        tvList.get(lastPagerIndex).setTextColor(tvUnSelColor);
        tvList.get(position).setTextColor(tvSelColor);
        lastPagerIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        if (pager != null) {
            pager.setCurrentItem(tvList.indexOf(v));
        }
    }

}
