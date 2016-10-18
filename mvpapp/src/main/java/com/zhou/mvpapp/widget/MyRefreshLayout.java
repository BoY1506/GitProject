package com.zhou.mvpapp.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.utils.UIUtils;

/**
 * 自定义封装刷新加载控件
 * Created by zhou on 2016/7/22.
 */
public class MyRefreshLayout extends RelativeLayout implements SwipeRefreshLayout.OnRefreshListener,
        AbsListView.OnScrollListener {

    /**
     * 刷新接口
     */
    private LayoutRefreshListener refreshListener;
    /**
     * 刷新布局
     */
    private SwipeRefreshLayout refreshLayout;
    /**
     * 数据listView
     */
    private ListView listView;
    /**
     * 空布局
     */
    private View emptyView;
    /**
     * 空布局图片
     */
    private ImageView emptyHintIv;
    /**
     * 空布局提示语
     */
    private TextView emptyHintTv;
    /**
     * 尾视图
     */
    private View footerView;
    /**
     * 尾视图提示语
     */
    private TextView footerHintTv;
    /**
     * 最后一条可见条目的索引
     */
    private int lastVisibalIndexl;
    /**
     * 当前页数
     */
    private int page;
    /**
     * 当前listView的适配器
     */
    private BaseAdapter listAdapter;
    /**
     * 是否正在加载数据
     */
    private boolean isDataLoading = false;

    public MyRefreshLayout(Context context) {
        this(context, null);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        //初始化刷新布局
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //初始化刷新控件
        refreshLayout = new SwipeRefreshLayout(getContext());
        refreshLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        refreshLayout.setColorSchemeColors(Color.parseColor("#FF6600"));
        refreshLayout.setOnRefreshListener(this);
        //初始化listView（用户在xml文件中自定义空布局）
        listView = (ListView) UIUtils.inflate(getContext(), R.layout.view_common_custom_list);
        listView.setOnScrollListener(this);
        //初始化空布局
        emptyView = new LinearLayout(getContext());
        emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ((LinearLayout) emptyView).setOrientation(LinearLayout.VERTICAL);
        ((LinearLayout) emptyView).setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        //空布局图片
        emptyHintIv = new ImageView(getContext());
        emptyHintIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        emptyHintIv.setImageResource(R.mipmap.ic_launcher);
        //空布局文字
        emptyHintTv = new TextView(getContext());
        LinearLayout.LayoutParams hintTvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        hintTvParams.topMargin = UIUtils.dp2px(getContext(), 10);
        emptyHintTv.setLayoutParams(hintTvParams);
        emptyHintTv.setText("暂时没有相关内容");
        emptyHintTv.setTextSize(16f);
        emptyHintTv.setTextColor(Color.GRAY);
        //将各个控件加入到主布局中
        refreshLayout.addView(listView);
        ((LinearLayout) emptyView).addView(emptyHintIv);
        ((LinearLayout) emptyView).addView(emptyHintTv);
        addView(refreshLayout);
        addView(emptyView);
        //尾视图
        footerView = new LinearLayout(getContext());
        footerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ((LinearLayout) footerView).setGravity(Gravity.CENTER);
        footerView.setPadding(0, UIUtils.dp2px(getContext(), 5), 0, UIUtils.dp2px(getContext(), 5));
        ProgressBar loadProgressBar = new ProgressBar(getContext());
        loadProgressBar.setLayoutParams(new LinearLayout.LayoutParams(UIUtils.dp2px(getContext(), 20),
                UIUtils.dp2px(getContext(), 20)));
        footerHintTv = new TextView(getContext());
        LinearLayout.LayoutParams footerTvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        footerTvParams.leftMargin = UIUtils.dp2px(getContext(), 10);
        footerHintTv.setLayoutParams(footerTvParams);
        footerHintTv.setText("正在加载更多...");
        footerHintTv.setTextSize(14f);
        footerHintTv.setTextColor(Color.GRAY);
        ((LinearLayout) footerView).addView(loadProgressBar);
        ((LinearLayout) footerView).addView(footerHintTv);
    }

    /**
     * 设置adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        this.listAdapter = adapter;
        this.listView.setAdapter(listAdapter);
        //初始化空数据(依赖于是否设置了adapter)
        refreshComplete(0, true);
    }

    /**
     * 设置lsitView点击事件
     */
    public void setOnLvItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listView.setOnItemClickListener(listener);
    }

    /**
     * 设置refreshLayout颜色
     */
    public void setRefreshLayoutColor(int... colors) {
        refreshLayout.setColorSchemeColors(colors);
    }

    /**
     * 设置空视图（有bug）
     * 设置此方法后，无法控制空视图的显示或隐藏
     */
    public void setLvEmptyView(View view) {
        this.emptyView = view;
    }

    /**
     * 设置空视图图片
     */
    public void setEvHintIvResource(int resourceId) {
        this.emptyHintIv.setImageResource(resourceId);
    }

    /**
     * 设置空视图文字
     */
    public void setEvHintTvText(String des) {
        this.emptyHintTv.setText(des);
    }

    /**
     * 设置尾视图
     */
    public void setLvFooterView(View view) {
        this.footerView = view;
    }

    /**
     * 设置尾视图文字
     */
    public void setFvHintTvText(String des) {
        this.footerHintTv.setText(des);
    }

    /**
     * 获取refreshLayout
     */
    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    /**
     * 刷新接口
     */
    @Override
    public void onRefresh() {
        //当前没有加载任务才刷新
        if (!isDataLoading && refreshListener != null) {
            page = 1;
            //开始请求数据
            isDataLoading = true;
            //设置refreshLayout刷新
            refreshLayout.setRefreshing(true);
            refreshListener.onRefresh(page);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //有尾视图的情况下才会进入该方法，说明一定还有数据，直接加载更多（松开手才加载）
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastVisibalIndexl == listAdapter.getCount()) {
            if (!isDataLoading && refreshListener != null) {
                //当前没有加载任务才加载
                page++;//页码+1
                //开始请求数据
                isDataLoading = true;
                refreshListener.loadMore(page);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滑动过程中不断计算最后一条可见的索引
        lastVisibalIndexl = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 更新结束
     * 检查是否需要显示空视图（这里如果使用setEmptyView方法，会导致SwipeRefreshLayout的滑块显示不全）
     * 检查是否需要更新尾视图
     */
    public void refreshComplete(int countAll, boolean isRefresh) {
        //空视图判断
        if (isRefresh) {
            //如果是刷新且adapter数据为0，则显示空视图
            if (listAdapter.getCount() == 0) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
                //如果是刷新且有数据，则让listView滑动到顶部
                //避免正在刷新中滑动到底部，这时不会执行加载，但是尾视图显示出来了
                listView.smoothScrollToPosition(0);
            }
            //刷新结束
            refreshLayout.setRefreshing(false);
        }
        //尾视图判断
        if (listAdapter.getCount() < countAll && listView.getFooterViewsCount() == 0) {
            listView.addFooterView(footerView);
        } else if (listAdapter.getCount() == countAll && listView.getFooterViewsCount() > 0) {
            listView.removeFooterView(footerView);
        }
        //更新完成
        isDataLoading = false;
    }

    /**
     * 设置ListView的监听回调
     */
    public void setLayoutRefreshListener(LayoutRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 刷新和加载的接口
     */
    public interface LayoutRefreshListener {
        //下拉刷新
        void onRefresh(int page);

        //上拉加载
        void loadMore(int page);
    }

}
