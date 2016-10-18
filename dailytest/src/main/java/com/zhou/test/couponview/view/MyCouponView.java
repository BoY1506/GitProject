package com.zhou.test.couponview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 优惠券View
 * Created by zhou on 2016/8/30.
 */
public class MyCouponView extends LinearLayout {

    private Paint mPaint;

    private float gap = 20;//圆间距

    private float radius = 20;//圆半径

    private int circleNum;//圆数量
    private float remain;//剩下的距离

    public MyCouponView(Context context) {
        super(context);
        init();
    }

    public MyCouponView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCouponView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);//数量可能除不尽
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            //把剩余除不尽的长度两端各留一半
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }

}
