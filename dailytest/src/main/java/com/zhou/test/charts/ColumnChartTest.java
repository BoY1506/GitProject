package com.zhou.test.charts;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.zhou.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * 柱状图测试
 * Created by zhou on 2016/8/29.
 */
public class ColumnChartTest extends Activity {

    @InjectView(R.id.column_chart)
    ColumnChartView columnChart;

    public final static String[] months = new String[]{"Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    ColumnChartData columnData;
    List<SubcolumnValue> lsValue;
    List<Column> lsColumn = new ArrayList<>();
    List<AxisValue> axisValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_columnchart_test);
        ButterKnife.inject(this);
        initColumnChart();//初始化
    }

    /**
     * 初始化图表
     */
    private void initColumnChart() {

        int numSubcolumns = 1;
        int numColumns = months.length;

        for (int i = 0; i < numColumns; i++) {
            lsValue = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; j++) {
                lsValue.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }
            // 点击柱状图就展示数据量
            lsColumn.add(new Column(lsValue).setHasLabelsOnlyForSelected(true));
            axisValues.add(new AxisValue(i).setLabel(months[i]));
        }

        columnData = new ColumnChartData(lsColumn);
        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true).setTextColor(Color.BLACK));
        columnData.setAxisYLeft(new Axis().setHasLines(true).setTextColor(Color.BLACK).setMaxLabelChars(2));

        columnChart.setColumnChartData(columnData);
        columnChart.setValueSelectionEnabled(true);
        columnChart.setZoomType(ZoomType.HORIZONTAL);
//        Viewport v = new Viewport(columnChart.getMaximumViewport());
//        v.left = 0;
//        v.right = 5;
//        columnChart.setCurrentViewport(v);
        columnChart.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                Toast.makeText(getApplicationContext(), subcolumnValue.getValue() + "万件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

}
