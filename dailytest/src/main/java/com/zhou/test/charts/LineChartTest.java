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
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 折线图练习
 * Created by zhou on 2016/8/29.
 */
public class LineChartTest extends Activity {

    @InjectView(R.id.line_chart)
    LineChartView lineChart;

    public final static String[] months = new String[]{"一月", "二月", "三月",
            "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    List<int[]> lineData = new ArrayList<>();

    LineChartData lineChartData;
    List<PointValue> pointValues;
    List<Line> lines = new ArrayList<>();
    List<AxisValue> axisValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart_test);
        ButterKnife.inject(this);
        initData();
        initLineChart();//初始化
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //第一组数据（与坐标对应）
        lineData.add(new int[]{50, 42, 90, 33, 10, 74, 22, 18, 79, 20, 58, 66});
        //第二组数据
        lineData.add(new int[]{88, 16, 36, 46, 50, 22, 22, 64, 99, 43, 30, 30});
    }

    /**
     * 初始化图表
     */
    private void initLineChart() {
        Line line;
        //根据数据初始化线
        for (int i = 0; i < lineData.size(); i++) {
            line = new Line();
            pointValues = new ArrayList<>();
            for (int j = 0; j < lineData.get(i).length; j++) {
                pointValues.add(new PointValue(j, lineData.get(i)[j]));
                if (axisValues.size() < 12) {
                    axisValues.add(new AxisValue(j).setLabel(months[j]));
                }
            }
            line.setColor(ChartUtils.pickColor());
            line.setCubic(true);
            line.setFilled(true);
            line.setHasLabels(true);
            line.setHasLabelsOnlyForSelected(true);
            line.setValues(pointValues);
            lines.add(line);
        }

        //设置图表data
        lineChartData = new LineChartData(lines);
        lineChartData.setAxisXBottom(new Axis(axisValues).setName("月份").setTextColor(Color.BLACK));//下方添加X轴
        lineChartData.setAxisYLeft(new Axis().setName("销量(万件)").setTextColor(Color.BLACK).setHasLines(true));//左方添加Y轴
        lineChartData.setBaseValue(Float.NEGATIVE_INFINITY);

        //设置图标行为属性，支持缩放、滑动以及平移
        lineChart.setZoomType(ZoomType.HORIZONTAL);//可缩放
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(lineChartData);
        lineChart.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i1, PointValue pointValue) {
                Toast.makeText(getApplicationContext(), pointValue.getY() + "万件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

}
