package com.zhou.mvpapp.activity;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhou.mvpapp.R;
import com.zhou.mvpapp.adapter.WorkerListAdapter4Rv;
import com.zhou.mvpapp.base.BaseActivity;
import com.zhou.mvpapp.bean.WorkerResult;
import com.zhou.mvpapp.contract.TeamContract;
import com.zhou.mvpapp.presenter.FindWorkerPresenter;
import com.zhou.mvpapp.utils.ActionBarBuilder;
import com.zhou.mvpapp.widget.MyRefreshLayout4Rv;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 寻找工人
 * Created by zhou on 2016/9/29.
 */
public class FindWorkerActivity extends BaseActivity<TeamContract.IFindWorkerPresenter> implements
        TeamContract.IFindWorkerView, MyRefreshLayout4Rv.LayoutRefreshListener {

    @InjectView(R.id.worker_refreshLayout)
    MyRefreshLayout4Rv workerRefreshLayout;

    private List<WorkerResult.Worker> data = new ArrayList<>();//列表数据项
    private WorkerListAdapter4Rv adapter;//列表适配器

    private boolean isRefresh;//是否刷新

    //----------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_worker);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("寻找工人");
    }

    @Override
    public TeamContract.IFindWorkerPresenter initPresenter() {
        return new FindWorkerPresenter();
    }

    @Override
    public void initView() {
        adapter = new WorkerListAdapter4Rv(data);
        workerRefreshLayout.setAdapter(adapter);
        workerRefreshLayout.setLayoutRefreshListener(this);
        workerRefreshLayout.setOnRvItemClickListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
        workerRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                workerRefreshLayout.onRefresh();
            }
        });
    }

    /**
     * 设置数据
     */
    @Override
    public void setWorkerData(WorkerResult result) {
        if (isRefresh) {
            adapter.setNewData(result.getList());
            workerRefreshLayout.refreshComplete(result.getCountAll(), true);
        } else {
            adapter.addData(result.getList());
            workerRefreshLayout.refreshComplete(result.getCountAll(), false);
        }
    }


    @Override
    public void onRefresh(int page) {
        showLog("刷新被调用了");
        isRefresh = true;
        presenter.getWorkerListData(page);
    }

    @Override
    public void loadMore(int page) {
        showLog("加载被调用了");
        isRefresh = false;
        presenter.getWorkerListData(page);
    }

}


////public class FindWorkerActivity extends BaseActivity<TeamContract.IFindWorkerPresenter> implements
////        TeamContract.IFindWorkerView, MyRefreshLayout4Rv.LayoutRefreshListener {
//public class FindWorkerActivity extends BaseActivity<TeamContract.IFindWorkerPresenter> implements
//        TeamContract.IFindWorkerView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
//
//    @InjectView(R.id.worker_list)
//    RecyclerView workerList;
//    @InjectView(R.id.refreshLayout)
//    SwipeRefreshLayout refreshLayout;
//
//    private List<WorkerResult.Worker> data = new ArrayList<>();//列表数据项
//    private WorkerListAdapter4Rv adapter;//列表适配器
//
//    private int page;
//
//    private boolean isRefresh;//是否刷新
//
//    private boolean isLoadingData = false;
//
//    //----------------------------------------
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_find_worker);
//        ButterKnife.inject(this);
//        initView();
//    }
//
//    @Override
//    public void initActionBar(ActionBarBuilder builder) {
//        builder.setAcBarLeftImg().setAcBarBigText("寻找工人");
//    }
//
//    @Override
//    public TeamContract.IFindWorkerPresenter initPresenter() {
//        return new FindWorkerPresenter();
//    }
//
//    @Override
//    public void initView() {
//        refreshLayout.setColorSchemeColors(Color.parseColor("#ff6600"));
//        refreshLayout.setOnRefreshListener(this);
//        adapter = new WorkerListAdapter4Rv(data);
//        adapter.setEmptyView(UIUtils.inflate2(mContext, R.layout.view_empty, refreshLayout));
//        adapter.setLoadingView(UIUtils.inflate(mContext, R.layout.footerview_list_pro_text));
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        adapter.openLoadMore(10);
//        adapter.setOnLoadMoreListener(this);
//        workerList.setLayoutManager(new LinearLayoutManager(mContext));
//        workerList.setAdapter(adapter);
//        workerList.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
////                presenter.onWorkerItemClick(adapter.getData().get(i));
//                workerList.smoothScrollToPosition(0);
//            }
//        });
//    }
//
//    /**
//     * 设置数据
//     */
//    @Override
//    public void setWorkerData(WorkerResult result) {
//        if (isRefresh) {
//            adapter.setNewData(result.getList());
//            refreshLayout.setRefreshing(false);
//        } else {
//            adapter.addData(result.getList());
//        }
//        if (adapter.getData().size() == result.getCountAll()) {
//            adapter.loadComplete();
//        }
//        showLog("已有数据大小：" + adapter.getData().size());
//        showLog("总的数据大小：" + result.getCountAll());
//        isLoadingData = false;
//    }
//
//    @Override
//    public void onRefresh() {
//        showLog("调用刷新了");
//        isRefresh = true;
//        page = 1;
//        presenter.getWorkerListData(page);
//    }
//
//    @Override
//    public void onLoadMoreRequested() {
//        showLog("为啥调用加载了");
//        isRefresh = false;
//        page++;
//        presenter.getWorkerListData(page);
//    }
//
//}
