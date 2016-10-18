package com.zhou.test.unreadmsg.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.test.R;

/**
 * 未读消息控件
 * Created by zhou on 2016/10/13.
 */
public class BottomBarView extends RelativeLayout {

    private int msgCount;
    private TextView bar_num;

    public BottomBarView(Context context) {
        this(context, null);
    }

    public BottomBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_unread_layout, this, true);
        inflate(getContext(), R.layout.view_unread_layout, this);
        bar_num = (TextView) findViewById(R.id.bar_num);
    }

    /**
     * 设置消息数目
     *
     * @param count
     */
    public void setMessageCount(int count) {
        msgCount = count;
        if (count == 0) {
            bar_num.setVisibility(View.GONE);
        } else {
            bar_num.setVisibility(View.VISIBLE);
            if (count < 100) {
                bar_num.setText(count + "");
            } else {
                bar_num.setText("99+");
            }
        }
        invalidate();
    }

    /**
     * 添加一条消息
     */
    public void addMsg() {
        setMessageCount(msgCount + 1);
    }

}