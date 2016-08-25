package com.zhou.gitproject.cityselect2.view;

import android.content.Context;
import android.widget.GridView;

/**
 * 自适应高度的gridView
 * Created by zhou on 2016/8/21.
 */
public class MyGridView extends GridView {

    public MyGridView(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
