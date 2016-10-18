package com.zhou.mvpapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义虚线
 * Created by zhou on 2015/12/11.
 */
public class DashedLine extends View {

    public DashedLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        //虚线高度
        paint.setStrokeWidth(getMeasuredHeight());
        paint.setColor(Color.parseColor("#C8C8C8"));
        Path path = new Path();
        path.moveTo(0, 0);
        //虚线宽度
        path.lineTo(getMeasuredWidth(), 0);
        //虚线间距
        PathEffect effects = new DashPathEffect(new float[]{5, 5}, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }

}
