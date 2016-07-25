package com.zhou.gitproject.refreshlistview.mylv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 头布局圆形刷新滚动图片
 * Created by zhou on 2016/7/22.
 */
public class CircleView extends View {

    //控件半径
    private int mRadius;
    //绘制弧形的画笔
    private Paint mArcPaint;
    //绘制弧形的区域
    private RectF mRange;

    private int[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);
        mArcPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 测量大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics());
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics());
        }

        //获取半径
        mRadius = Math.min(width, height) / 2;
        //设置宽高为固定值
        setMeasuredDimension(mRadius * 2, mRadius * 2);
        mRange = new RectF(0, 0, mRadius * 2, mRadius * 2);
    }

    /**
     * 绘制图形
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        float degree = 360 / colors.length / 2f;
        for (int i = 0; i < 8; i++) {
            mArcPaint.setColor(colors[i % 4]);
            canvas.drawArc(mRange, -90f + degree * i, degree, true, mArcPaint);
        }
    }

}
