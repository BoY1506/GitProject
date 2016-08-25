package com.zhou.test.win8imitation.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 点击缩放的imageView
 * Created by zhou on 2016/7/27.
 */
public class ScaleImageView extends ImageView {

    private static final String TAG = "ScaleImageView";

    private static final int SCALE_REDUCE_INIT = 0;//开始缩小
    private static final int SCALING = 1;//缩放中
    private static final int SCALE_ADD_INIT = 2;//开始放大

    /**
     * 控件的宽
     */
    private int mWidth;
    /**
     * 控件的高
     */
    private int mHeight;
    /**
     * 控件宽的1/2
     */
    private int mCenterWidth;
    /**
     * 控件高的1/2
     */
    private int mCenterHeight;
    /**
     * 缩放比，越接近1，缩放幅度越小
     */
    private float mMinScale = 0.9f;
    /**
     * 是否缩放结束
     */
    private boolean isFinish = true;

    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 布局改变时调用
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

            mCenterWidth = mWidth / 2;
            mCenterHeight = mHeight / 2;

            Drawable drawable = getDrawable();
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bd.setAntiAlias(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //开始缩小
                mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                break;
            case MotionEvent.ACTION_UP:
                //开始放大
                mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                break;
        }
        return true;
    }

    /**
     * 缩放控制Handler
     */
    private Handler mScaleHandler = new Handler() {
        private Matrix matrix = new Matrix();
        private int count = 0;
        private float s;
        /**
         * 是否可以处理点击事件
         */
        private boolean canClicked;

        public void handleMessage(android.os.Message msg) {
            matrix.set(getImageMatrix());
            switch (msg.what) {
                case SCALE_REDUCE_INIT:
                    if (!isFinish) {
                        //如果缩放没有结束，继续检测
                        mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                    } else {
                        //开始新的缩小
                        isFinish = false;
                        count = 0;
                        //计算s
                        s = (float) Math.sqrt(Math.sqrt(mMinScale));
                        beginScale(matrix, s);
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }
                    break;
                case SCALING:
                    beginScale(matrix, s);
                    if (count < 4) {
                        mScaleHandler.sendEmptyMessage(SCALING);
                    } else {
                        isFinish = true;
                        //是否可处理点击事件，只有第二次放大结束才调用点击事件
                        if (ScaleImageView.this.mOnViewClickListener != null && canClicked) {
                            canClicked = false;
                            ScaleImageView.this.mOnViewClickListener.onViewClick(ScaleImageView.this);
                        } else {
                            canClicked = true;
                        }
                    }
                    count++;
                    break;
                case SCALE_ADD_INIT:
                    if (!isFinish) {
                        //如果缩放没有结束，继续检测
                        mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                    } else {
                        //开始新的放大
                        isFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(1.0f / mMinScale));
                        beginScale(matrix, s);
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }
                    break;
            }
        }
    };

    /**
     * 开始缩放
     *
     * @param matrix
     * @param scale
     */
    private synchronized void beginScale(Matrix matrix, float scale) {
        matrix.postScale(scale, scale, mCenterWidth, mCenterHeight);
        setImageMatrix(matrix);
    }

    /**
     * 点击事件
     */
    private OnViewClickListener mOnViewClickListener;

    public void setOnClickIntent(OnViewClickListener onViewClickListener) {
        this.mOnViewClickListener = onViewClickListener;
    }

    public interface OnViewClickListener {
        void onViewClick(ScaleImageView view);
    }

}
