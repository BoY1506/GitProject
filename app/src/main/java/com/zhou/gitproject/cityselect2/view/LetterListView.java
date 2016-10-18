package com.zhou.gitproject.cityselect2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhou.gitproject.utils.UIUtils;

/**
 * 右侧字母导航栏
 * Created by zhou on 2016/8/21.
 */
public class LetterListView extends View {

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    private String[] b = {"定位", "最近", "热门", "全部", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //    private int choose = -1;
    private Paint paint;
    private boolean showBkg = false;
    private Rect textBounds;//测量文字区域

    public LetterListView(Context context) {
        this(context, null);
    }

    public LetterListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#000001"));
        paint.setTextSize(UIUtils.dp2px(getContext(), 12f));
        paint.setAntiAlias(true);
        textBounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBkg) {
            //显示背景点击变色
            canvas.drawColor(Color.parseColor("#40000000"));
        }
        //获取宽高
        int height = getHeight();
        int width = getWidth();
        //每个item的高度
        int singleHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            //文字绘制位置
            paint.getTextBounds(b[i], 0, b[i].length(), textBounds);
            float xPos = width / 2 - textBounds.width() / 2;
            float yPos = singleHeight / 2 + textBounds.height() / 2 + singleHeight * i;
            canvas.drawText(b[i], xPos, yPos, paint);
        }
    }

    /**
     * 触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        //触摸到的下标
        int c = (int) (y / getHeight() * b.length);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //按下
                showBkg = true;
                if (onTouchingLetterChangedListener != null) {
                    if (c >= 0 && c < b.length) {
                        onTouchingLetterChangedListener.onTouchingLetterChanged(b[c]);
                    }
                }
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                //还原
                showBkg = false;
                if (onTouchingLetterChangedListener != null) {
                    onTouchingLetterChangedListener.onTouchingLetterChanged("");
                }
                postInvalidate();
                break;
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }

}
