package com.zhou.gitproject.popfilter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.popfilter.view.FilterPopWindow;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * popupWindow筛选框练习
 * Created by zhou on 2016/7/19.
 */
public class MyPopFilter extends BaseActivity {

    @InjectView(R.id.filter_bar_tv1)
    TextView filterBarTv1;
    @InjectView(R.id.filter_bar_rl1)
    RelativeLayout filterBarRl1;
    @InjectView(R.id.filter_bar_tv2)
    TextView filterBarTv2;
    @InjectView(R.id.filter_bar_rl2)
    RelativeLayout filterBarRl2;
    @InjectView(R.id.filter_bar_tv3)
    TextView filterBarTv3;
    @InjectView(R.id.filter_bar_rl3)
    RelativeLayout filterBarRl3;
    @InjectView(R.id.filter_line)
    View filterLine;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.shadow_view)
    View shadowView;

    //popWindow
    private FilterPopWindow filterPop;
    //popWindow数据
    private List<String> popTypeFirstData, popRegionFirstData, popSortData;
    private Map<String, List<String>> popTypeSecondData, popRegionSecondData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_popfilter);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    /**
     * 初始化
     */
    private void initView() {
        filterBarTv1.setText("类型");
        filterBarTv2.setText("地点");
        filterBarTv3.setText("排序");
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("第" + (i + 1) + "条项目");
        }
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        //pop相关
        initPopData();
        //向上的箭头
        Drawable trigonUp = UIUtils.getDrawable(this, R.mipmap.filter_trigon_up);
        trigonUp.setBounds(0, 0, trigonUp.getMinimumWidth(), trigonUp.getMinimumHeight());
        //向下的箭头
        Drawable trigonDown = UIUtils.getDrawable(this, R.mipmap.filter_trigon_down);
        trigonDown.setBounds(0, 0, trigonDown.getMinimumWidth(), trigonDown.getMinimumHeight());
        filterPop = new FilterPopWindow(this, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        filterPop.setFilterTv(filterBarTv1, filterBarTv2, filterBarTv3, "类型", "地点", "排序");
        filterPop.setFilterTvDrawable(trigonUp, trigonDown);
        filterPop.setShadowView(shadowView);
        filterPop.setFilterData(popTypeFirstData, popRegionFirstData, popSortData, popTypeSecondData, popRegionSecondData);
        filterPop.setOnFilterItemSelected(new FilterPopWindow.OnFilterItemSelected() {
            @Override
            public void itemSelected(String selText) {
                showToast("选择了：" + selText);
            }
        });
    }

    /**
     * 初始化popup数据
     */
    private void initPopData() {
        popTypeFirstData = Arrays.asList("全部","美食", "电影", "娱乐", "购物", "旅游", "酒店", "吃饭", "洗浴");
        popRegionFirstData = Arrays.asList("全部","平江区", "高新区", "工业园区", "吴中区", "虎丘区", "吴江区");
        popSortData = Arrays.asList("综合排序","人气最高", "距离最近", "评分最高", "价格最低");
        popTypeSecondData = new HashMap<>();
        popTypeSecondData.put("全部", Collections.singletonList("全部"));
        popTypeSecondData.put("美食", Arrays.asList("烤肉", "自助餐", "西餐", "日韩料理", "蛋糕", "烤肉", "自助餐", "西餐", "日韩料理", "蛋糕", "烤肉", "自助餐", "西餐"));
        popTypeSecondData.put("电影", Arrays.asList("爱情剧", "动作剧", "恐怖剧", "喜剧", "科幻类", "爱情剧", "动作剧", "恐怖剧", "喜剧", "科幻类", "爱情剧", "动作剧", "恐怖剧", "喜剧"));
        popTypeSecondData.put("娱乐", Arrays.asList("棋牌", "球类", "KTV", "乐园", "棋牌", "球类", "KTV", "乐园", "棋牌", "球类", "KTV", "乐园"));
        popTypeSecondData.put("购物", Arrays.asList("服装", "饰品", "家具", "玩具", "服装", "饰品", "家具", "玩具", "家具", "玩具", "服装"));
        popTypeSecondData.put("旅游", Arrays.asList("经济型", "豪华型", "主题型", "经济型", "豪华型", "主题型", "经济型", "豪华型", "主题型"));
        popTypeSecondData.put("酒店", Arrays.asList("烤肉", "自助餐", "西餐", "日韩料理", "蛋糕", "烤肉", "自助餐", "西餐", "日韩料理", "蛋糕", "烤肉", "自助餐", "西餐", "日韩料理"));
        popTypeSecondData.put("吃饭", Arrays.asList("爱情剧", "动作剧", "恐怖剧", "喜剧", "科幻类", "爱情剧", "动作剧", "恐怖剧", "喜剧", "科幻类", "动作剧", "恐怖剧", "喜剧"));
        popTypeSecondData.put("洗浴", Arrays.asList("经济型", "豪华型", "主题型", "经济型", "豪华型", "主题型", "经济型", "豪华型", "主题型"));
        popRegionSecondData = new HashMap<>();
        popRegionSecondData.put("全部", Collections.singletonList("全部"));
        popRegionSecondData.put("平江区", Arrays.asList("观前街", "乐桥", "白马路", "干将路", "莫邪路", "临顿路", "项门", "金鸡湖大道", "独墅湖"));
        popRegionSecondData.put("高新区", Arrays.asList("上海路", "云南路", "高新路", "北京路", "航海路", "新平路", "留园", "虎丘", "淞泽家园", "体育馆"));
        popRegionSecondData.put("工业园区", Arrays.asList("崇文路", "华信路", "普惠路", "留园", "虎丘", "淞泽家园", "体育馆", "仁爱路", "星湖街", "东方大道"));
        popRegionSecondData.put("吴中区", Arrays.asList("吴中路", "胡秀路", "留园", "虎丘", "淞泽家园", "体育馆", "项门", "金鸡湖大道", "独墅湖", "文汇广场"));
        popRegionSecondData.put("虎丘区", Arrays.asList("留园", "虎丘", "淞泽家园", "体育馆", "公园", "文萃路", "白马路", "干将路", "莫邪路", "淞泽家园", "体育馆"));
        popRegionSecondData.put("吴江区", Arrays.asList("平江路", "吴江", "观前", "留园", "虎丘", "淞泽家园", "体育馆", "东方之门", "星海广场", "游乐园"));
    }

    @OnClick({R.id.filter_bar_rl1, R.id.filter_bar_rl2, R.id.filter_bar_rl3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter_bar_rl1:
                filterPop.showPop(FilterPopWindow.POP_FLITER_TYPE, filterLine);
                break;
            case R.id.filter_bar_rl2:
                filterPop.showPop(FilterPopWindow.POP_FLITER_REGION, filterLine);
                break;
            case R.id.filter_bar_rl3:
                filterPop.showPop(FilterPopWindow.POP_FLITER_SORT, filterLine);
                break;
        }
    }


}
