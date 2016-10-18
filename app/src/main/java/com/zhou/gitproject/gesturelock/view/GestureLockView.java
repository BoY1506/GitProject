package com.zhou.gitproject.gesturelock.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * 圆点View
 * Created by zhou on 2016/9/14.
 */
public class GestureLockView extends View {

    private static final String TAG = "GestureLockView";

    /**
     * GestureLockView的三种状态
     */
    public enum Mode {
        //无状态、手指按下、手指抬起
        STATUS_NO_FINGER, STATUS_FINGER_ON, STATUS_FINGER_UP
    }

    /**
     * GestureLockView的当前状态
     */
    private Mode mCurrentStatus = Mode.STATUS_NO_FINGER;

    /**
     * 宽度
     */
    private int mWidth;
    /**
     * 高度
     */
    private int mHeight;
    /**
     * 外圆半径
     */
    private int mRadiusThin;
    /**
     * 外圆半径
     */
    private int mRadiusRude;
    /**
     * 画笔的宽度
     */
    private int mStrokeWidth = 2;

    /**
     * 圆心坐标
     */
    private int mCenterX;
    private int mCenterY;
    private Paint mPaint;

    /**
     * 箭头（小三角最长边的一半长度 = mArrawRate * mWidth / 2 ）
     */
    private float mArrowRate = 0.15F;
    private int mArrowDegree = -1;
    private Path mArrowPath;
    /**
     * 内圆的半径 = mInnerCircleRadiusRate * mRadius
     */
    private float mInnerCircleRadiusRate = 0.3F;

    /**
     * 四个颜色，可由用户自定义，初始化时由GestureLockViewGroup传入
     */
    private int mColorNoFingerInner;//内圆
    private int mColorNoFingerOutter;//外圆
    private int mColorFingerOn;//手指按下
    private int mColorFingerUp;//手指抬起

    public GestureLockView(Context context, int colorNoFingerInner, int colorNoFingerOutter, int colorFingerOn, int colorFingerUp) {
        super(context);
        this.mColorNoFingerInner = colorNoFingerInner;
        this.mColorNoFingerOutter = colorNoFingerOutter;
        this.mColorFingerOn = colorFingerOn;
        this.mColorFingerUp = colorFingerUp;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArrowPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 取长和宽中的小值
        mWidth = mWidth < mHeight ? mWidth : mHeight;
        mRadiusThin = mRadiusRude = mCenterX = mCenterY = mWidth / 2;
        //获取半径
        mRadiusThin -= mStrokeWidth / 2;
        mRadiusRude -= mStrokeWidth;

        //绘制三角形路径，初始时是个默认箭头朝上的一个等腰三角形，用户绘制结束后，根据由两个GestureLockView决定需要旋转多少度
        float mArrowLength = mWidth / 2 * mArrowRate;
//        mArrowPath.moveTo(mWidth / 2, mStrokeWidth + 2);
//        mArrowPath.lineTo(mWidth / 2 - mArrowLength, mStrokeWidth + 2 + mArrowLength);
//        mArrowPath.lineTo(mWidth / 2 + mArrowLength, mStrokeWidth + 2 + mArrowLength);
        mArrowPath.moveTo(mWidth / 2, (float) (mWidth / 2 / 2 - (mArrowLength * Math.sqrt(3))));
        mArrowPath.lineTo(mWidth / 2 - mArrowLength, mWidth / 2 / 2);
        mArrowPath.lineTo(mWidth / 2 + mArrowLength, mWidth / 2 / 2);
        mArrowPath.close();
        mArrowPath.setFillType(Path.FillType.WINDING);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentStatus) {
            case STATUS_FINGER_ON:
                // 按下
                // 绘制外圆
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(mColorFingerOn);
                mPaint.setStrokeWidth(mStrokeWidth * 2);
                canvas.drawCircle(mCenterX, mCenterY, mRadiusRude, mPaint);
                // 绘制内圆
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(mCenterX, mCenterY, mRadiusRude * mInnerCircleRadiusRate, mPaint);
                break;
            case STATUS_FINGER_UP:
                // 抬起
                // 绘制外圆
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(mColorFingerUp);
                mPaint.setStrokeWidth(mStrokeWidth * 2);
                canvas.drawCircle(mCenterX, mCenterY, mRadiusRude, mPaint);
                // 绘制内圆
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(mCenterX, mCenterY, mRadiusRude * mInnerCircleRadiusRate, mPaint);
                //绘制箭头
                drawArrow(canvas);
                break;
            case STATUS_NO_FINGER:
                // 无状态
                // 绘制外圆
//                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(mStrokeWidth);
                mPaint.setColor(mColorNoFingerOutter);
                canvas.drawCircle(mCenterX, mCenterY, mRadiusThin, mPaint);
//                // 绘制内圆
//                mPaint.setColor(mColorNoFingerInner);
//                canvas.drawCircle(mCenterX, mCenterY, mRadius * mInnerCircleRadiusRate, mPaint);
                break;
        }
    }

    /**
     * 绘制箭头
     *
     * @param canvas
     */
    private void drawArrow(Canvas canvas) {
        if (mArrowDegree != -1) {
            mPaint.setStyle(Paint.Style.FILL);
            canvas.save();
            //旋转画布
            canvas.rotate(mArrowDegree, mCenterX, mCenterY);
            //画三角形
            canvas.drawPath(mArrowPath, mPaint);
            canvas.restore();
        }
    }

    /**
     * 设置当前模式并重绘界面
     *
     * @param mode
     */
    public void setMode(Mode mode) {
        this.mCurrentStatus = mode;
        invalidate();
    }

    /**
     * 设置箭头旋转角度
     *
     * @param degree
     */
    public void setArrowDegree(int degree) {
        this.mArrowDegree = degree;
    }

    /**
     * 获取箭头旋转角度
     *
     * @return
     */
    public int getArrowDegree() {
        return this.mArrowDegree;
    }

}
