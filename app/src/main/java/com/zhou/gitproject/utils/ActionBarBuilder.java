package com.zhou.gitproject.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.gitproject.R;

/**
 * 封装actionBar样式工具类
 * Created by zhou on 2015/11/12.
 */
public class ActionBarBuilder {

    private Activity context;//上下文
    private View actionBarView;//actionbar视图

    /**
     * 用于Activity创建
     *
     * @param context
     * @param viewId
     */
    public ActionBarBuilder(Activity context, int viewId) {
        this.context = context;
        this.actionBarView = context.findViewById(viewId);
    }

    /**
     * 用于Fragment创建
     *
     * @param view
     * @param viewId
     */
    public ActionBarBuilder(View view, int viewId) {
        this.actionBarView = view.findViewById(viewId);
    }

    /**
     * 给ActionBar设置左图标（默认点击事件为finish）
     */
    public ActionBarBuilder setAcBarLeftImg() {
        ImageView leftImg = (ImageView) actionBarView.findViewById(R.id.mactionbar_left_iv);
        leftImg.setVisibility(View.VISIBLE);
        actionBarView.findViewById(R.id.mactionbar_left_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
        return this;
    }

    /**
     * 给ActionBar设置左文字+点击事件
     *
     * @param leftText
     * @param leftListener
     * @return
     */
    public ActionBarBuilder setAcBarLeftText(String leftText, View.OnClickListener leftListener) {
        TextView leftTv = (TextView) actionBarView.findViewById(R.id.mactionbar_left_tv);
        leftTv.setVisibility(View.VISIBLE);
        leftTv.setText(leftText);
        actionBarView.findViewById(R.id.mactionbar_left_rl).setOnClickListener(leftListener);
        return this;
    }

    /**
     * 给ActionBar设置大标题
     *
     * @param bigText
     * @return
     */
    public ActionBarBuilder setAcBarBigText(String bigText) {
        TextView bigTv = (TextView) actionBarView.findViewById(R.id.mactionbar_big_tv);
        bigTv.setText(bigText);
        return this;
    }

    /**
     * 给ActionBar设置小标题
     *
     * @param smallText
     * @return
     */
    public ActionBarBuilder setAcBarSmallText(String smallText) {
        TextView smallTv = (TextView) actionBarView.findViewById(R.id.mactionbar_small_tv);
        smallTv.setVisibility(View.VISIBLE);
        smallTv.setText(smallText);
        return this;
    }

    /**
     * 给ActionBar设置右文字+点击事件
     *
     * @param rightText
     * @param rightListener
     * @return
     */
    public ActionBarBuilder setAcBarRightText(String rightText, View.OnClickListener rightListener) {
        TextView rightTv = (TextView) actionBarView.findViewById(R.id.mactionbar_right_tv);
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText(rightText);
        actionBarView.findViewById(R.id.mactionbar_rigth_rl).setOnClickListener(rightListener);
        return this;
    }

    /**
     * 给ActionBar设置右图标+点击事件
     *
     * @param rightImgId
     * @param rightListener
     * @return
     */
    public ActionBarBuilder setAcBarRightImg(int rightImgId, View.OnClickListener rightListener) {
        ImageView rightImg = (ImageView) actionBarView.findViewById(R.id.mactionbar_right_iv);
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setImageResource(rightImgId);
        actionBarView.findViewById(R.id.mactionbar_rigth_rl).setOnClickListener(rightListener);
        return this;
    }

    /**
     * 隐藏actionBar
     */
    public void hideActionBar() {
        actionBarView.setVisibility(View.GONE);
    }

}
