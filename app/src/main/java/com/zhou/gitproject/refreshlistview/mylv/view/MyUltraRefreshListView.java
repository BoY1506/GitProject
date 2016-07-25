package com.zhou.gitproject.refreshlistview.mylv.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 自定义刷新和加载的listView
 * Created by zhou on 2016/7/22.
 */
public class MyUltraRefreshListView extends ListView implements PtrHandler, AbsListView.OnScrollListener {

    //刷新接口
    private UltraRefreshListener mUltraRefreshListener;
    //尾布局
    private View footerView;
    //是否正在加载数据
    private boolean isLoadData = false;
    //是否是下拉刷新，主要在处理结果时使用
    private boolean isRefresh = false;

    public MyUltraRefreshListView(Context context) {
        this(context, null);
    }

    public MyUltraRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyUltraRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        footerView = UIUtils.inflate(getContext(), R.layout.view_refreshlv_footer);
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //加载更多的判断（滑到最后一个条目自动加载下一页）
        if (totalItemCount > 1 && !isLoadData && totalItemCount == firstVisibleItem + visibleItemCount) {
            isRefresh = false;
            isLoadData = true;
            addFooterView(footerView);
            if (mUltraRefreshListener != null) {
                mUltraRefreshListener.addMore();
            }
        }
    }

    /**
     * PtrHandler 的接口回调，是否能够加载数据的判断
     */
    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View content, View header) {
//        return !isLoadData && PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, content, header);
        return !isLoadData && checkContentCanBePulledDown(ptrFrameLayout, content, header);
    }

    /**
     * 从PtrHandler的默认实现类 PtrDefaultHandler中找到的，用以判断是否可以下拉刷新
     */
    public static boolean checkContentCanBePulledDown(PtrFrameLayout frame, View content, View header) {
        return !canChildScrollUp(content);
    }

    /**
     * 从PtrHandler的默认实现类 PtrDefaultHandler中找到的，用以判断是否可以下拉刷新
     */
    public static boolean canChildScrollUp(View view) {
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0
                        || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else {
            return view.canScrollVertically(-1);
        }
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        isLoadData = true;
        isRefresh = true;
        if (mUltraRefreshListener != null) {
            mUltraRefreshListener.onRefresh();
        }
    }

    /**
     * 刷新完成的后调用此方法还原布局
     */
    public void refreshComplete() {
        isLoadData = false;
        if (isRefresh) {
            //获取其父控件，刷新
            ViewParent parent = getParent();
            if (parent instanceof PtrClassicFrameLayout) {
                ((PtrClassicFrameLayout) parent).refreshComplete();
            }
        } else {
            removeFooterView(footerView);
        }
    }

    /**
     * 设置ListView的监听回调
     */
    public void setUltraRefreshListener(UltraRefreshListener mUltraRefreshListener) {
        this.mUltraRefreshListener = mUltraRefreshListener;
    }

    /**
     * 刷新和加载的接口
     */
    public interface UltraRefreshListener {
        //下拉刷新
        void onRefresh();

        //上拉加载
        void addMore();
    }

}
