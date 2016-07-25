package com.zhou.gitproject.shufbanner.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhou.gitproject.R;
import com.zhou.gitproject.shufbanner.adapter.ShufBannerAdapter;
import com.zhou.gitproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片轮播器
 * Created by zhou on 2016/7/23.
 */
public class ShufBanner extends RelativeLayout implements ViewPager.OnPageChangeListener, View.OnClickListener {

    /**
     * 点击事件的接口
     */
    private ShufBannerClickListener bannerClickListener;
    /**
     * 图片加载imageLoader
     */
    private ImageLoader imageLoader;
    /**
     * 需要加载的图片地址
     */
    private List<String> shufImgUrls;
    /**
     * 显示的ViewPager
     */
    private ViewPager shufVp;
    /**
     * ImageView
     */
    private List<ImageView> shufIvs;
    /**
     * 轮播适配器
     */
    private ShufBannerAdapter shufAdapter;
    /**
     * 导航点的位置
     */
    private LinearLayout shufNaviLl;
    /**
     * 是否开启轮播
     */
    private boolean isStartShuf = false;
    /**
     * 点击事件的位置
     */
    private int position = -1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //获得的是正常位置
            shufVp.setCurrentItem(shufVp.getCurrentItem() + 1);
            if (isStartShuf) {
                sendEmptyMessageDelayed(1, 3000);
            }
        }
    };

    public ShufBanner(Context context) {
        this(context, null);
    }

    public ShufBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShufBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.view_shufbanner, this);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        imageLoader = ImageLoader.getInstance();
        shufVp = ((ViewPager) findViewById(R.id.shuf_vp));
        shufNaviLl = (LinearLayout) findViewById(R.id.shuf_navi_ll);
        shufImgUrls = new ArrayList<>();
        shufIvs = new ArrayList<>();
        shufAdapter = new ShufBannerAdapter(getContext(), shufIvs);
        shufVp.setAdapter(shufAdapter);
        shufVp.addOnPageChangeListener(this);
    }

    /**
     * 重新保存图片
     * 给原本的数据前后各加一条
     */
    private void reSaveUrl() {
        String startImgUrl = shufImgUrls.get(0);
        String endImgUrl = shufImgUrls.get(shufImgUrls.size() - 1);
        shufImgUrls.add(0, endImgUrl);
        shufImgUrls.add(shufImgUrls.size(), startImgUrl);
    }

    /**
     * 开启轮播
     *
     * @param urls
     */
    public void startShufBanner(List<String> urls) {
        if (urls.size() == 0) {
            return;
        }
        //清空数据
        clearAllData();
        shufImgUrls.addAll(urls);
        //重新保存图片地址
        reSaveUrl();
        //根据图片url创建imgView
        createImageView();
        //生成导航点
        addPoint2Navigation();
        //开始刷新
        shufAdapter.notifyDataSetChanged();
        shufVp.setCurrentItem(0);
        //发送循环请求
        isStartShuf = true;
        if (shufIvs.size() > 3) {
            handler.sendEmptyMessageAtTime(1, 3000);
        }
    }

    /**
     * 清除图片数据，停止轮播
     */
    public void clearAllData() {
        shufImgUrls.clear();
        shufIvs.clear();
        shufNaviLl.removeAllViews();
        handler.removeMessages(1);
        shufAdapter.notifyDataSetChanged();
        position = -1;
        isStartShuf = false;
    }

    /**
     * 创建布局
     */
    private void createImageView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        for (String url : shufImgUrls) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageLoader.displayImage(url, imageView);
            imageView.setOnClickListener(this);
            shufIvs.add(imageView);
        }
    }

    /**
     * 添加导航点到布局中
     */
    private void addPoint2Navigation() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int pointPadding = UIUtils.dp2px(getContext(), 5);
        params.setMargins(pointPadding, 0, pointPadding, 0);
        for (int i = 0; i < shufIvs.size() - 2; i++) {
            ImageView point = new ImageView(getContext());
            point.setLayoutParams(params);
            point.setImageResource(R.drawable.icon_point_sel);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            shufNaviLl.addView(point);
        }
    }

    @Override
    public void onClick(View v) {
        if (bannerClickListener != null) {
            bannerClickListener.onClick(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0) {
            //是否滚动过渡完结，滚动结束才跳动
            if (position == 0) {
                //滑到第0个点，跳到倒数第二个点
                shufVp.setCurrentItem(shufIvs.size() - 2, false);
            }
            if (position == shufIvs.size() - 1) {
                //滑到最后一个点，跳到第二个点
                shufVp.setCurrentItem(1, false);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        //修改当前点击点的位置和状态
        changePointPositionAndState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 改变当前点击的点
     *
     * @param position
     */
    private void changePointPositionAndState(int position) {
        if (position == 0) {
            position = shufIvs.size() - 2;
        } else if (position == shufIvs.size() - 1) {
            position = 1;
        }
        //图片对应的位置始-1得到点数的位置
        position--;
        for (int i = 0; i < shufNaviLl.getChildCount(); i++) {
            if (i == position) {
                shufNaviLl.getChildAt(i).setEnabled(true);
            } else {
                shufNaviLl.getChildAt(i).setEnabled(false);
            }
        }
    }

    /**
     * 设置接口
     *
     * @param listener
     */
    public void setOnBannerClcikListener(ShufBannerClickListener listener) {
        this.bannerClickListener = listener;
    }

    /**
     * 图片点击接口
     */
    public interface ShufBannerClickListener {
        /**
         * @param position -1 未设置任何点击
         */
        void onClick(int position);
    }

}
