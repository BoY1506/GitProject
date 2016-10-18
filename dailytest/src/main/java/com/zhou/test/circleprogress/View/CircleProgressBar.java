package com.zhou.test.circleprogress.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.zhou.test.R;

/**
 * 自定义圆形进度条
 * Created by zhou on 2016/10/13.
 */
public class CircleProgressBar extends View {

    private Paint mCirclePaint;//外圆画笔
    private Paint mCircleInnerPaint;//内圆画笔
    private Paint mTextPaint;//文字画笔

    private float mProgress;//进度
    private String mProgressText = "0%";//文字
    private boolean mStartProgress;//是否开启
    private RectF mCircleRectF;//扇形区域

    private static final int CIRCLE_LINE_WIDTH = 10;//外圆宽度
    private static final int CIRCLE_RADIUS = 150;//内圆半径
    private static final int TEXT_SIZE = 54;//文字大小

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化工作
     */
    private void init() {
        //外圆画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(CIRCLE_LINE_WIDTH);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.circle_color));
        //内圆画笔
        mCircleInnerPaint = new Paint();
        mCircleInnerPaint.setAntiAlias(true);
        mCircleInnerPaint.setStyle(Paint.Style.FILL);
        mCircleInnerPaint.setColor(ContextCompat.getColor(getContext(), R.color.circle_inner_color));
        //文字画笔
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.circle_text_color));
        mTextPaint.setTextSize(TEXT_SIZE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取中心点
        float halfWidth = getMeasuredWidth() / 2;
        float halfHeight = getMeasuredHeight() / 2;
        //绘制内圆
        canvas.drawCircle(halfWidth, halfHeight, CIRCLE_RADIUS - CIRCLE_LINE_WIDTH / 2, mCircleInnerPaint);
        //绘制文字
        mProgressText = mProgress + "%";
        canvas.drawText(mProgressText, halfWidth - mTextPaint.measureText(mProgressText) / 2,
                halfHeight - (mTextPaint.ascent() + mTextPaint.descent()) / 2, mTextPaint);
        //绘制外圆
        if (null == mCircleRectF) {
            mCircleRectF = new RectF(halfWidth - CIRCLE_RADIUS, halfHeight - CIRCLE_RADIUS,
                    halfWidth + CIRCLE_RADIUS, halfHeight + CIRCLE_RADIUS);
        }
        if (mStartProgress) {
            float swipeProgress = mProgress / 100f * 360;
            canvas.drawArc(mCircleRectF, -90, swipeProgress, false, mCirclePaint);
        } else {
            canvas.drawCircle(halfWidth, halfHeight, CIRCLE_RADIUS, mCirclePaint);
        }
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        if (progress > 100) {
            progress = 100;
        }
        if (progress < 0) {
            progress = 0;
        }
        mProgress = progress;
        mProgressText = "Pause";
        mStartProgress = true;
        postInvalidate();
    }

    /**
     * 获取进度
     *
     * @return
     */
    public float getCurrentProgress() {
        return mProgress;
    }

    /**
     * 完成绘制
     */
    public void progressFinished() {
        mStartProgress = false;
        postInvalidate();
    }

}
