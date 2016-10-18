package com.zhou.mvpapp.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhou.mvpapp.R;
import com.zhou.mvpapp.utils.UIUtils;

/**
 * 自定义封装刷新加载控件(针对recyclerView)
 * Created by zhou on 2016/7/22.
 */
public class MyRefreshLayout4Rv extends RelativeLayout implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * 刷新接口
     */
    private LayoutRefreshListener refreshListener;
    /**
     * 刷新控件
     */
    private SwipeRefreshLayout refreshLayout;
    /**
     * 数据listView
     */
    private RecyclerView recyclerView;
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
     * 是否可改变空布局属性
     */
    private boolean isEmptyViewCanChange = true;
    /**
     * 尾视图
     */
    private View footerView;
    /**
     * 尾视图提示语
     */
    private TextView footerViewHintTv;
    /**
     * 是否可改变尾布局属性
     */
    private boolean isFooterViewCanChange = true;
    /**
     * 当前页数
     */
    private int page;
    /**
     * 当前listView的适配器
     */
    private BaseQuickAdapter recyclerListAdapter;
    /**
     * 是否正在加载数据
     */
    private boolean isDataLoading = false;
    /**
     * 最后一个可见索引
     */
    private int lastVisibleItemPosition;
    /**
     * 最后一个位置索引
     */
    private int[] lastPostions;
    /**
     * RecycleView的布局管理器的类型
     */
    private LayoutManagerType layoutManagerType;

    /**
     * RecycleView的布局管理器的类型
     */
    private enum LayoutManagerType {
        LINEAR_LAYOUT,
        GRID_LAYOUT,
        STAGGERED_GRID_LAYOUT
    }

    public MyRefreshLayout4Rv(Context context) {
        this(context, null);
    }

    public MyRefreshLayout4Rv(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshLayout4Rv(Context context, AttributeSet attrs, int defStyleAttr) {
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
        recyclerView = (RecyclerView) UIUtils.inflate(getContext(), R.layout.view_common_custom_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnScrollListener(new LoadScrollListener());
        //初始化空布局
        emptyView = UIUtils.inflate2(getContext(), R.layout.view_empty, refreshLayout);
        //空布局图片
        emptyHintIv = (ImageView) emptyView.findViewById(R.id.empty_hint_iv);
        //空布局文字
        emptyHintTv = (TextView) emptyView.findViewById(R.id.empty_hint_tv);
        //将各个控件加入到主布局中
        refreshLayout.addView(recyclerView);
        addView(refreshLayout);
        //尾视图
        footerView = UIUtils.inflate(getContext(), R.layout.footerview_list_pro_text);
        footerViewHintTv = (TextView) footerView.findViewById(R.id.loading_hint_tv);
    }

    /**
     * 设置adapter
     */
    public void setAdapter(BaseQuickAdapter adapter) {
        this.recyclerListAdapter = adapter;
        this.recyclerView.setAdapter(recyclerListAdapter);
        //设置空视图和尾视图
        recyclerListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerListAdapter.setEmptyView(emptyView);
    }

    /**
     * 设置lsitView点击事件
     */
    public void setOnRvItemClickListener(OnItemClickListener listener) {
        this.recyclerView.addOnItemTouchListener(listener);
    }

    /**
     * 设置refreshLayout颜色
     */
    public void setRefreshLayoutColor(int... colors) {
        refreshLayout.setColorSchemeColors(colors);
    }

    /**
     * 设置layoutManager
     */
    public void setRecyclerViewLayoutManager(RecyclerView.LayoutManager manager) {
        recyclerView.setLayoutManager(manager);
    }

    /**
     * 设置空视图
     */
    public void setEmptyView(View view) {
        this.emptyView = view;
        recyclerListAdapter.setEmptyView(emptyView);
        isEmptyViewCanChange = false;
    }

    /**
     * 设置空视图图片
     */
    public void setEmptyViewHintHintIvResource(int resourceId) {
        if (isEmptyViewCanChange) {
            this.emptyHintIv.setImageResource(resourceId);
        }
    }

    /**
     * 设置空视图文字
     */
    public void setEmptyViewHintTvText(String des) {
        if (isEmptyViewCanChange) {
            this.emptyHintTv.setText(des);
        }
    }

    /**
     * 设置尾视图
     */
    public void setFooterView(View view) {
        this.footerView = view;
        isFooterViewCanChange = false;
    }

    /**
     * 设置尾视图文字
     */
    public void setLoadingViewHintTvText(String des) {
        if (isFooterViewCanChange) {
            this.footerViewHintTv.setText(des);
        }
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
            //开始刷新
            refreshLayout.setRefreshing(true);
            //调刷新接口
            refreshListener.onRefresh(page);
        }
    }

    /**
     * 更新结束
     * 检查是否需要显示空视图（这里如果使用setEmptyView方法，会导致SwipeRefreshLayout的滑块显示不全）
     * 检查是否需要更新尾视图
     */
    public void refreshComplete(int countAll, boolean isRefresh) {
        //现有的数据大小
        int dataSize = recyclerListAdapter.getData().size();
        //刷新
        if (isRefresh) {
            //如果是刷新且有数据，且尾视图漏出来了
            if (dataSize > 0) {
                //回滚到顶部使尾视图隐藏
                recyclerView.smoothScrollToPosition(0);
            }
        }
        //尾视图判断
        if (dataSize < countAll && recyclerListAdapter.getFooterLayoutCount() == 0) {
            recyclerListAdapter.addFooterView(footerView);
        } else if (dataSize == countAll && recyclerListAdapter.getFooterLayoutCount() > 0) {
            recyclerListAdapter.removeFooterView(footerView);
        }
        //结束刷新控件
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        //更新完成
        isDataLoading = false;
    }

    /**
     * 加载滑动监听
     */
    private class LoadScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            //RecycleView 显示的条目数
            int visibleCount = layoutManager.getChildCount();
            //显示数据总数
            int totalCount = layoutManager.getItemCount();
            // 四个条件，分别是是否有数据，状态是否是滑动停止状态，显示的最大条目是否大于整个数据（注意偏移量）
            if (visibleCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalCount - 1) {
                //由于recyclerView的smoothScroll原因，刚进入页面，手动下拉刷新，实际上调用的是此方法，因此page=0++=1
                //但是设置自动刷新可避免此情况
                if (!isDataLoading && refreshListener != null) {
                    //当前没有加载任务才加载
                    page++;//页码+1
                    //开始请求数据
                    isDataLoading = true;
                    //调加载接口
                    refreshListener.loadMore(page);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            /**
             * 获取布局参数
             */
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

            //如果为null，第一次运行，确定布局类型
            if (layoutManagerType == null) {
                if (layoutManager instanceof LinearLayoutManager) {
                    layoutManagerType = LayoutManagerType.LINEAR_LAYOUT;
                } else if (layoutManager instanceof GridLayoutManager) {
                    layoutManagerType = LayoutManagerType.GRID_LAYOUT;
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    layoutManagerType = LayoutManagerType.STAGGERED_GRID_LAYOUT;
                } else {
                    throw new RuntimeException("LayoutManager should be LinearLayoutManager,GridLayoutManager,StaggeredGridLayoutManager");
                }
            }

            //对于不太能够的布局参数，不同的方法获取到当前显示的最后一个条目数
            switch (layoutManagerType) {
                case LINEAR_LAYOUT:
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    break;
                case GRID_LAYOUT:
                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    break;
                case STAGGERED_GRID_LAYOUT:
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    if (lastPostions == null) {
                        lastPostions = new int[staggeredGridLayoutManager.getSpanCount()];
                    }
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPostions);
                    lastVisibleItemPosition = findMax(lastPostions);
                    break;
            }
        }
    }

    /**
     * 当是瀑布流时，获取到的是每一个瀑布最下方显示的条目，通过条目进行对比
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
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
