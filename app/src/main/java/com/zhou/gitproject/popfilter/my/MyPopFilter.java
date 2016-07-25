package com.zhou.gitproject.popfilter.my;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.popfilter.my.adapter.FilterPopAdapter;
import com.zhou.gitproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private final static int TYPE_POP_FLITER = 1;//类型筛选项
    private final static int DIFF_POP_FLITER = 2;//难度选项
    private final static int SORT_POP_FLITER = 3;//排序筛选项

    private int popIndex = -1;//当前显示的pop下标

    private PopupWindow filterPop;//筛选popWindow
    private ListView filterPopLv;//popWindow的listView
    private FilterPopAdapter filterPopAdapter1, filterPopAdapter2, filterPopAdapter3;//popWindow的适配器
    private List<String> filterData1, filterData2, filterData3;//popWindow数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_popfilter);
        ButterKnife.inject(this);
        initView();
        initPop();
    }

    /**
     * 初始化
     */
    private void initView() {
        filterBarTv1.setText("类型");
        filterBarTv2.setText("难度");
        filterBarTv3.setText("排序");
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("第" + (i + 1) + "条项目");
        }
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
    }

    /**
     * 初始化popupWindow
     */
    private void initPop() {
        //初始化popWindow数据
        initPopData();
        View filterPopView = UIUtils.inflate(this, R.layout.pop_filter_view);
        //实例化listView
        filterPopLv = (ListView) filterPopView.findViewById(R.id.filter_lv);
        filterPop = new PopupWindow(filterPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置popWindow弹出窗体可点击获取焦点
        filterPop.setFocusable(true);
        //必须加此方法，点击其余部分popWindow可消失
        filterPop.setBackgroundDrawable(new ColorDrawable());
        filterPop.setOnDismissListener(new MyPopDissmissListener());
        filterPop.setAnimationStyle(R.style.PopWindowAnim);
        //先不设置adapter
        filterPopAdapter1 = new FilterPopAdapter(this, filterData1, 0);
        filterPopAdapter2 = new FilterPopAdapter(this, filterData2, 0);
        filterPopAdapter3 = new FilterPopAdapter(this, filterData3, 0);
        filterPopLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (popIndex) {
                    case 1:
                        //类型
                        filterPopAdapter1.setSelIndex(position);
                        showToast("选择了" + filterData1.get(position));
                        break;
                    case 2:
                        //难度
                        filterPopAdapter2.setSelIndex(position);
                        showToast("选择了" + filterData2.get(position));
                        break;
                    case 3:
                        //排序
                        filterPopAdapter3.setSelIndex(position);
                        showToast("选择了" + filterData3.get(position));
                        break;
                }
                filterPop.dismiss();
            }
        });
    }

    /**
     * 初始化popup数据
     */
    private void initPopData() {
        filterData1 = Arrays.asList("全部", "知识精讲", "项目实战");
        filterData2 = Arrays.asList("全部", "初级", "中级", "高级");
        filterData3 = Arrays.asList("全部", "最新", "最热");
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.filter_bar_rl1, R.id.filter_bar_rl2, R.id.filter_bar_rl3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter_bar_rl1:
                showPop(TYPE_POP_FLITER);
                break;
            case R.id.filter_bar_rl2:
                showPop(DIFF_POP_FLITER);
                break;
            case R.id.filter_bar_rl3:
                showPop(SORT_POP_FLITER);
                break;
        }
    }

    /**
     * 显示popWindow
     */
    private void showPop(int index) {
        //当前在操作的popWindow下标
        popIndex = index;
        //向上的箭头
        Drawable filter_trigon_up = UIUtils.getDrawable(this, R.mipmap.filter_trigon_up);
        //必须加不然不显示
        filter_trigon_up.setBounds(0, 0, filter_trigon_up.getMinimumWidth(), filter_trigon_up.getMinimumHeight());
        //判断
        switch (popIndex) {
            case TYPE_POP_FLITER:
                //类型
                filterBarTv1.setCompoundDrawables(null, null, filter_trigon_up, null);
                filterPopLv.setAdapter(filterPopAdapter1);
                break;
            case DIFF_POP_FLITER:
                //难度
                filterBarTv2.setCompoundDrawables(null, null, filter_trigon_up, null);
                filterPopLv.setAdapter(filterPopAdapter2);
                break;
            case SORT_POP_FLITER:
                //排序
                filterBarTv3.setCompoundDrawables(null, null, filter_trigon_up, null);
                filterPopLv.setAdapter(filterPopAdapter3);
                break;
        }
        //显示蒙层
        shadowView.setVisibility(View.VISIBLE);
        shadowView.startAnimation(AnimationUtils.loadAnimation(MyPopFilter.this, R.anim.anim_shadow_show));
        //显示popWindow
        filterPop.showAsDropDown(filterLine);
    }

    /**
     * popWindow消失监听
     */
    private class MyPopDissmissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            //向下的箭头
            Drawable filter_trigon_down = UIUtils.getDrawable(MyPopFilter.this, R.mipmap.filter_trigon_down);
            //必须加不然不显示
            filter_trigon_down.setBounds(0, 0, filter_trigon_down.getMinimumWidth(), filter_trigon_down.getMinimumHeight());
            //判断
            switch (popIndex) {
                case TYPE_POP_FLITER:
                    //类型
                    filterBarTv1.setCompoundDrawables(null, null, filter_trigon_down, null);
                    break;
                case DIFF_POP_FLITER:
                    //难度
                    filterBarTv2.setCompoundDrawables(null, null, filter_trigon_down, null);
                    break;
                case SORT_POP_FLITER:
                    //排序
                    filterBarTv3.setCompoundDrawables(null, null, filter_trigon_down, null);
                    break;
            }
            //取消蒙层
            shadowView.startAnimation(AnimationUtils.loadAnimation(MyPopFilter.this, R.anim.anim_shadow_dismiss));
            shadowView.setVisibility(View.GONE);
        }
    }

}
