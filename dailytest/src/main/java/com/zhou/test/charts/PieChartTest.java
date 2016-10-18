package com.zhou.test.charts;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.zhou.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 饼图练习
 * Created by zhou on 2016/8/29.
 */
public class PieChartTest extends Activity {

    @InjectView(R.id.pie_chart)
    PieChartView pieChart;

    //数据
    int[] pieData = new int[]{50, 42, 90, 33, 66};
    int dataCount = 0;//总量

    List<SliceValue> values = new ArrayList<>();
    PieChartData chartData = new PieChartData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart_test);
        ButterKnife.inject(this);
        initData();
        initPieChart();//初始化
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int num : pieData) {
            dataCount = dataCount + num;
        }
    }

    /**
     * 初始化图表
     */
    private void initPieChart() {
        for (int i = 0; i < pieData.length; ++i) {
            if (i == 0) {
                SliceValue value1 = new SliceValue();
                value1.setValue(pieData[i] * 100 / dataCount);
                value1.setColor(ChartUtils.pickColor());
                value1.setLabel("第一季" + (pieData[i] * 100 / dataCount) + "%");
                values.add(value1);
            } else if (i == 1) {
                SliceValue value2 = new SliceValue();
                value2.setValue(pieData[i] * 100 / dataCount);
                value2.setColor(Color.GREEN);
                value2.setLabel("第二季" + (pieData[i] * 100 / dataCount) + "%");
                values.add(value2);
            } else if (i == 2) {
                SliceValue value3 = new SliceValue();
                value3.setValue(pieData[i] * 100 / dataCount);
                value3.setColor(Color.BLUE);
                value3.setLabel("第三季" + (pieData[i] * 100 / dataCount) + "%");
                values.add(value3);
            } else if (i == 3) {
                SliceValue value4 = new SliceValue();
                value4.setValue(pieData[i] * 100 / dataCount);
                value4.setColor(Color.CYAN);
                value4.setLabel("第四季" + (pieData[i] * 100 / dataCount) + "%");
                values.add(value4);
            } else {
                SliceValue value4 = new SliceValue();
                value4.setValue(pieData[i] * 100 / dataCount);
                value4.setColor(Color.CYAN);
                value4.setLabel("第五季" + (pieData[i] * 100 / dataCount) + "%");
                values.add(value4);
            }
        }

        chartData.setHasLabels(true);
        chartData.setValues(values);
        chartData.setSlicesSpacing(5);
        chartData.setHasCenterCircle(true);
        chartData.setCenterText1("年度销量");
        chartData.setCenterText1FontSize(30);
        chartData.setCenterText1Typeface(Typeface.SERIF);
        chartData.setCenterText2("单位(万件)");
        chartData.setCenterText2Typeface(Typeface.SERIF);

        pieChart.setPieChartData(chartData);
        pieChart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {

                Toast.makeText(getApplicationContext(), Math.round(sliceValue.getValue() * dataCount / 100) + "万件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

}
