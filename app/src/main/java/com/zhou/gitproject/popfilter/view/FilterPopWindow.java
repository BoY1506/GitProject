package com.zhou.gitproject.popfilter.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.popfilter.adapter.PopMultiFirstAdapter;
import com.zhou.gitproject.popfilter.adapter.PopMultiSecondAdapter;
import com.zhou.gitproject.popfilter.adapter.PopSingleAdapter;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;
import java.util.Map;

/**
 * 筛选popWinodw（2个二级列表，1个一级列表）
 * Created by zhou on 2016/8/11.
 */
public class FilterPopWindow extends PopupWindow implements AdapterView.OnItemClickListener {

    //context
    private Context context;

    //2种pop视图
    private View popMultiView, popSingleView;
    //3个筛选listView
    private ListView popMultiFirstLv, popMultiSecondLv, popSingleLv;
    //5个筛选listView适配器
    private PopMultiFirstAdapter popTypeFirstAdapter, popRegionFirstAdapter;
    private PopMultiSecondAdapter popTypeSecondAdapter, popRegionSecondAdapter;
    private PopSingleAdapter popSortAdapter;
    //筛选条件数据
    private List<String> popTypeFirstData, popRegionFirstData, popSortData;
    private Map<String, List<String>> popTypeSecondData, popRegionSecondData;

    //被选中的一级目录导航栏的索引
    private int popTypeFirstSelIndex = 0;
    private int popRegionFirstSelIndex = 0;
    //被选中的二级目录导航栏的索引
    private int popTypeSecondSelIndex = 0;
    private int popRegionSecondSelIndex = 0;
    //当前控制的pop索引
    private int popCurIndex = -1;

    //popWindow蒙层
    private View shadowView;

    //文字tv
    private TextView filterTv1, filterTv2, filterTv3;
    //箭头drawable
    private Drawable trigonUp, trigonDown;
    //文字描述
    private String filterText1, filterText2, filterText3;

    public final static int POP_FLITER_TYPE = 1;//点开类别选框
    public final static int POP_FLITER_REGION = 2;//点开地点选框
    public final static int POP_FLITER_SORT = 3;//点开排序选框

    public FilterPopWindow(Context context, int width, int height) {
        super(width, height);
        this.context = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //pop布局
        popMultiView = UIUtils.inflate(context, R.layout.pop_multi_filter);
        popSingleView = UIUtils.inflate(context, R.layout.pop_single_filter);
        //pop控件
        popMultiFirstLv = (ListView) popMultiView.findViewById(R.id.filter_first_lv);
        popMultiSecondLv = (ListView) popMultiView.findViewById(R.id.filter_second_lv);
        popSingleLv = (ListView) popSingleView.findViewById(R.id.filter_lv);
        //设置popWindow弹出窗体可点击获取焦点
        setFocusable(true);
        //popWindow外部可点击
        setOutsideTouchable(true);
        //点击其余部分popWindow可消失且可监听dismiss事件
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置进出动画
        setAnimationStyle(R.style.PopWindowAnim);
        //设置listView点击监听
        popMultiFirstLv.setOnItemClickListener(this);
        popMultiSecondLv.setOnItemClickListener(this);
        popSingleLv.setOnItemClickListener(this);
    }

    /**
     * 设置filter数据
     */
    public void setFilterData(List<String> popTypeFirstData, List<String> popRegionFirstData, List<String> popSortData,
                              Map<String, List<String>> popTypeSecondData, Map<String, List<String>> popRegionSecondData) {
        this.popTypeFirstData = popTypeFirstData;
        this.popRegionFirstData = popRegionFirstData;
        this.popSortData = popSortData;
        this.popTypeSecondData = popTypeSecondData;
        this.popRegionSecondData = popRegionSecondData;
        //初始化adapter
        popTypeFirstAdapter = new PopMultiFirstAdapter(context, popTypeFirstData);
        popRegionFirstAdapter = new PopMultiFirstAdapter(context, popRegionFirstData);
        popTypeSecondAdapter = new PopMultiSecondAdapter(context, popTypeSecondData.get(popTypeFirstData.get(0)), 0);
        popRegionSecondAdapter = new PopMultiSecondAdapter(context, popRegionSecondData.get(popRegionFirstData.get(0)), 0);
        popSortAdapter = new PopSingleAdapter(context, popSortData, 0);
        //设置适配器(只设置单项的，双项不定)
        popSingleLv.setAdapter(popSortAdapter);
    }

    /**
     * 设置筛选文字
     */
    public void setFilterTv(TextView tv1, TextView tv2, TextView tv3, String text1, String text2, String text3) {
        this.filterTv1 = tv1;
        this.filterTv2 = tv2;
        this.filterTv3 = tv3;
        this.filterText1 = text1;
        this.filterText2 = text2;
        this.filterText3 = text3;
    }

    /**
     * 设置文字drawable
     */
    public void setFilterTvDrawable(Drawable up, Drawable down) {
        this.trigonUp = up;
        this.trigonDown = down;
    }

    /**
     * 设置蒙层
     */
    public void setShadowView(View shadowView) {
        this.shadowView = shadowView;
    }

    /**
     * 显示popWindow
     */
    public void showPop(int index, View filterLine) {
        popCurIndex = index;
        //判断
        switch (popCurIndex) {
            case POP_FLITER_TYPE:
                //类型
                if (filterTv1 != null && trigonUp != null) {
                    filterTv1.setCompoundDrawables(null, null, trigonUp, null);
                }
                setHeight(UIUtils.getScreenHeightPixels((Activity) context) / 2);
                setContentView(popMultiView);
                popMultiFirstLv.setAdapter(popTypeFirstAdapter);
                popMultiSecondLv.setAdapter(popTypeSecondAdapter);
                break;
            case POP_FLITER_REGION:
                if (filterTv2 != null && trigonUp != null) {
                    filterTv2.setCompoundDrawables(null, null, trigonUp, null);
                }
                //地点
                setHeight(UIUtils.getScreenHeightPixels((Activity) context) / 2);
                setContentView(popMultiView);
                popMultiFirstLv.setAdapter(popRegionFirstAdapter);
                popMultiSecondLv.setAdapter(popRegionSecondAdapter);
                break;
            case POP_FLITER_SORT:
                //排序
                if (filterTv3 != null && trigonUp != null) {
                    filterTv3.setCompoundDrawables(null, null, trigonUp, null);
                }
                setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                setContentView(popSingleView);
                break;
        }
        if (shadowView != null) {
            //显示蒙层
            shadowView.setVisibility(View.VISIBLE);
            shadowView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_shadow_show));
        }
        //显示popWindow
        showAsDropDown(filterLine);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String condition = null;
        switch (popCurIndex) {
            case POP_FLITER_TYPE:
                //类型
                if (parent.getAdapter() == popTypeFirstAdapter) {
                    //种类一级列表
                    popTypeFirstAdapter.setFirstSelIndex(position);
                    if (position == popTypeFirstSelIndex) {
                        //上次选中的一级列表，内含已选二级下标
                        popTypeSecondAdapter.setSecondSelIndexAndData(popTypeSecondSelIndex,
                                popTypeSecondData.get(popTypeFirstData.get(position)));
                    } else {
                        popTypeSecondAdapter.setSecondSelIndexAndData(-1, popTypeSecondData.get(popTypeFirstData.get(position)));
                    }
                } else if (parent.getAdapter() == popTypeSecondAdapter) {
                    //种类二级列表
                    popTypeSecondAdapter.setSecondSelIndex(position);
                    //暂存1、2级下标
                    popTypeFirstSelIndex = popTypeFirstAdapter.getFirstSelIndex();
                    popTypeSecondSelIndex = position;
                    if (popTypeFirstSelIndex == 0 && popTypeSecondSelIndex == 0) {
                        //全部
                        condition = "全部";
                        if (filterTv1 != null && filterText1 != null) {
                            filterTv1.setText(filterText1);
                        }
                    } else {
                        condition = (String) popTypeSecondAdapter.getItem(position);
                        if (filterTv1 != null) {
                            filterTv1.setText(condition);
                        }
                    }
                    if (onFilterItemSelected != null) {
                        onFilterItemSelected.itemSelected(condition);
                    }
                    dismiss();
                }
                break;
            case POP_FLITER_REGION:
                //地点
                if (parent.getAdapter() == popRegionFirstAdapter) {
                    //地点一级列表
                    popRegionFirstAdapter.setFirstSelIndex(position);
                    if (position == popRegionFirstSelIndex) {
                        //上次选中的一级列表，内含已选二级下标
                        popRegionSecondAdapter.setSecondSelIndexAndData(popRegionSecondSelIndex,
                                popRegionSecondData.get(popRegionFirstData.get(position)));
                    } else {
                        popRegionSecondAdapter.setSecondSelIndexAndData(-1, popRegionSecondData.get(popRegionFirstData.get(position)));
                    }
                } else if (parent.getAdapter() == popRegionSecondAdapter) {
                    //地点二级列表
                    popRegionSecondAdapter.setSecondSelIndex(position);
                    //暂存1、2级下标
                    popRegionFirstSelIndex = popRegionFirstAdapter.getFirstSelIndex();
                    popRegionSecondSelIndex = position;
                    if (popRegionFirstSelIndex == 0 && popRegionSecondSelIndex == 0) {
                        //全部
                        condition = "全部";
                        if (filterTv2 != null && filterText2 != null) {
                            filterTv2.setText(filterText2);
                        }
                    } else {
                        condition = (String) popRegionSecondAdapter.getItem(position);
                        if (filterTv2 != null) {
                            filterTv2.setText(condition);
                        }
                    }
                    if (onFilterItemSelected != null) {
                        onFilterItemSelected.itemSelected(condition);
                    }
                    dismiss();
                }
                break;
            case POP_FLITER_SORT:
                //排序
                popSortAdapter.setSelIndex(position);
                if (position == 0) {
                    //综合排序
                    condition = "全部";
                    if (filterTv3 != null && filterText3 != null) {
                        filterTv3.setText(filterText3);
                    }
                } else {
                    condition = (String) popSortAdapter.getItem(position);
                    if (filterTv3 != null) {
                        filterTv3.setText(condition);
                    }
                }
                if (onFilterItemSelected != null) {
                    onFilterItemSelected.itemSelected(condition);
                }
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //判断
        switch (popCurIndex) {
            case FilterPopWindow.POP_FLITER_TYPE:
                if (filterTv1 != null && trigonDown != null) {
                    filterTv1.setCompoundDrawables(null, null, trigonDown, null);
                }
                break;
            case FilterPopWindow.POP_FLITER_REGION:
                if (filterTv2 != null && trigonDown != null) {
                    filterTv2.setCompoundDrawables(null, null, trigonDown, null);
                }
                break;
            case FilterPopWindow.POP_FLITER_SORT:
                if (filterTv3 != null && trigonDown != null) {
                    filterTv3.setCompoundDrawables(null, null, trigonDown, null);
                }
                break;
        }
        if (shadowView != null) {
            //取消蒙层
            shadowView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_shadow_dismiss));
            shadowView.setVisibility(View.GONE);
        }
    }

    //单项选择接口
    private OnFilterItemSelected onFilterItemSelected;

    public void setOnFilterItemSelected(OnFilterItemSelected filterItemSelected) {
        this.onFilterItemSelected = filterItemSelected;
    }

    /**
     * 单项选择接口
     */
    public interface OnFilterItemSelected {
        void itemSelected(String selText);
    }

}
