package com.zhou.gitproject.refreshlistview.swipelv.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

/**
 * 封装刷新加载listView
 * Created by zhou on 2016/7/22.
 */
public class MySwipeRefreshListView extends ListView implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    //刷新接口
    private MyRefreshListener myRefreshListener;
    //刷新布局
    private SwipeRefreshLayout swipeRefreshLayout;
    //空布局
    private ViewGroup emptyView;
    //尾视图
    private View footerView;
    //最后一条可见条目的索引
    private int lastVisibalIndexl;
    //当前页数
    private int page;
    //当前lsitView的适配器
    private BaseAdapter listAdapter;
    //是否正在加载数据
    private boolean isLoadData = false;

    public MySwipeRefreshListView(Context context) {
        this(context, null);
    }

    public MySwipeRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySwipeRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //尾视图
        footerView = UIUtils.inflate(getContext(), R.layout.view_refreshlv_footer);
        //滑动监听
        setOnScrollListener(this);
    }

    /**
     * 设置必要的成员
     */
    public void setNecessMember(SwipeRefreshLayout refreshLayout, BaseAdapter adapter, ViewGroup emptyView) {
        this.swipeRefreshLayout = refreshLayout;
        this.listAdapter = adapter;
        this.emptyView = emptyView;
        setAdapter(listAdapter);
        swipeRefreshLayout.setColorSchemeColors(Color.RED);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 刷新接口
     */
    @Override
    public void onRefresh() {
        //当前没有加载任务才刷新
        if (!isLoadData && myRefreshListener != null) {
            page = 1;
            //开始请求数据
            isLoadData = true;
            myRefreshListener.onRefresh(page);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //有尾视图的情况下才会进入该方法，说明一定还有数据，直接加载更多（松开手才加载）
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastVisibalIndexl == listAdapter.getCount()) {
            if (!isLoadData && myRefreshListener != null) {
                //当前没有加载任务才加载
                page++;//页码+1
                //开始请求数据
                isLoadData = true;
                myRefreshListener.loadMore(page);
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
        if (isRefresh) {
            //如果是刷新且adapter数据为0，则显示空视图
            if (listAdapter.getCount() == 0) {
                emptyView.setVisibility(VISIBLE);
            } else {
                emptyView.setVisibility(GONE);
                //如果是刷新且有数据，则让listView滑动到顶部
                //避免正在刷新中滑动到底部，这时不会执行加载，但是尾视图显示出来了
                smoothScrollToPosition(0);
            }
        }
        //判断是否删除尾视图
        if (listAdapter.getCount() < countAll && getFooterViewsCount() == 0) {
            addFooterView(footerView);
        } else if (listAdapter.getCount() == countAll && getFooterViewsCount() > 0) {
            removeFooterView(footerView);
        }
        //更新完成
        isLoadData = false;
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 设置ListView的监听回调
     */
    public void setMyRefreshListener(MyRefreshListener myRefreshListener) {
        this.myRefreshListener = myRefreshListener;
    }

    /**
     * 刷新和加载的接口
     */
    public interface MyRefreshListener {
        //下拉刷新
        void onRefresh(int page);

        //上拉加载
        void loadMore(int page);
    }

}
