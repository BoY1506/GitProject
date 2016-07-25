package com.zhou.gitproject.refreshlistview.mylv.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 自定义滑动头部
 * Created by zhou on 2016/7/22.
 */
public class MyUltraRefreshHeader extends RelativeLayout implements PtrUIHandler {

    //头部刷新滚动控件
    private CircleView mcirCircleView;
    //提示文字
    private TextView mDescText;
    //滚动动画
    private ObjectAnimator animator;

    public MyUltraRefreshHeader(Context context) {
        this(context, null);
    }

    public MyUltraRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyUltraRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        //初始化圆形
        int circleWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                getResources().getDisplayMetrics());
        mcirCircleView = new CircleView(getContext());
        LinearLayout.LayoutParams circleParams = new LinearLayout.LayoutParams(circleWidth, circleWidth);
        mcirCircleView.setLayoutParams(circleParams);
        //初始化文字
        mDescText = new TextView(getContext());
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(circleWidth * 3,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        descParams.gravity = Gravity.CENTER;
        descParams.setMargins(circleWidth, 0, 0, 0);
        mDescText.setLayoutParams(descParams);
        mDescText.setTextSize(14);
        mDescText.setTextColor(Color.DKGRAY);
        mDescText.setText("下拉刷新");
        //添加线性的父布局
        LinearLayout ll = new LinearLayout(getContext());
        RelativeLayout.LayoutParams llParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParams.addRule(CENTER_IN_PARENT);
        ll.setLayoutParams(llParams);
        ll.setPadding(20, 20, 20, 20);
        ll.addView(mcirCircleView);
        ll.addView(mDescText);
        addView(ll);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, 120);
    }

    /**
     * 重置时调用
     *
     * @param ptrFrameLayout
     */
    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        mcirCircleView.setRotation(0f);
    }

    /**
     * 准备下拉时调用
     *
     * @param ptrFrameLayout
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        mDescText.setText("下来刷新");
    }

    /**
     * 开始熟刷新时调用，启动动画
     *
     * @param ptrFrameLayout
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        animator = ObjectAnimator.ofFloat(mcirCircleView, "rotation", mcirCircleView.getRotation(),
                mcirCircleView.getRotation() + 360f).setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    /**
     * 刷新完成时调用
     *
     * @param ptrFrameLayout
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        animator.cancel();
        mDescText.setText("刷新完成");
    }

    /**
     * 下拉过程中调用
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //头部的高度
        int headerHeight = ptrIndicator.getHeaderHeight();
        //刷新需要滑动的偏移量
        int offsetToRefresh = ptrIndicator.getOffsetToRefresh();
        //上一次滑动的Y偏移值
        int lastPosY = ptrIndicator.getLastPosY();
        //当前系统偏移值
        int currentPosY = ptrIndicator.getCurrentPosY();
        //当前与上一次滑动处理的偏移值
        float offsetY = ptrIndicator.getOffsetY();
        if (isUnderTouch && status == ptrFrameLayout.PTR_STATUS_PREPARE) {
            mcirCircleView.setRotation(currentPosY);
            if (currentPosY < offsetToRefresh && lastPosY >= offsetToRefresh) {
                //从下往上滑
                mDescText.setText("下拉刷新");
            } else if (currentPosY > offsetToRefresh && lastPosY <= offsetToRefresh) {
                //从上往下滑
                mDescText.setText("松开刷新");
            }
        }
    }
}
