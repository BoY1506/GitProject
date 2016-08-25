package com.zhou.gitproject.contactlist.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 右侧首字母侧栏
 * Created by zhou on 2016/8/13.
 */
public class LetterBar extends LinearLayout {

    public LetterBar(Context context) {
        this(context, null);
    }

    public LetterBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        setBackgroundColor(Color.parseColor("#60000000"));
        setOrientation(VERTICAL);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        for (int i = 0; i < 27; i++) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(params);
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            if (i == 0) {
                tv.setText("#");
            } else {
                tv.setText((char) ('A' + (i - 1)) + "");
            }
            addView(tv);
        }
    }

    /**
     * 触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //拿到触摸点y值
                float y = event.getY();
                //每个字母的高度
                int defSize = getHeight() / getChildCount();
                //计算手指落在哪个字母上
                int index = (int) (y / defSize);
                TextView tv = (TextView) getChildAt(index);
                if (tv != null && onLetterSelectedListener != null) {
                    //将选中字母输出
                    onLetterSelectedListener.onLetterSelected(tv.getText().toString());
                }
                break;
            case MotionEvent.ACTION_UP:
                if (onLetterSelectedListener != null) {
                    //输出空字母
                    onLetterSelectedListener.onLetterSelected("");
                }
                break;
        }
        //消费触摸事件
        return true;
    }

    private OnLetterSelectedListener onLetterSelectedListener;

    public void setOnLetterSelectedListener(OnLetterSelectedListener onLetterSelectedListener) {
        this.onLetterSelectedListener = onLetterSelectedListener;
    }

    /**
     * 获取所选字母
     */
    public interface OnLetterSelectedListener {
        void onLetterSelected(String letter);
    }

}
