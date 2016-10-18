package com.zhou.mvpapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.adapter.JobListAdapter;
import com.zhou.mvpapp.base.BaseActivity;
import com.zhou.mvpapp.bean.JobResult;
import com.zhou.mvpapp.contract.JobContract;
import com.zhou.mvpapp.presenter.HomePresenter;
import com.zhou.mvpapp.utils.ActionBarBuilder;
import com.zhou.mvpapp.widget.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 首页
 * Created by zhou on 2016/9/6.
 */
public class HomeActivity extends BaseActivity<JobContract.IHomePresenter> implements JobContract.IHomeView,
        MyRefreshLayout.LayoutRefreshListener {

    @InjectView(R.id.jobs_mrl)
    MyRefreshLayout jobsMrl;

    private List<JobResult.Job> data = new ArrayList<>();//列表数据项
    private JobListAdapter adapter;//列表适配器

    private boolean isRefresh;//是否刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        initView();
        //请求数据
        requestHttp();
    }


    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("工作列表");
    }

    @Override
    public JobContract.IHomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initView() {
        adapter = new JobListAdapter(mContext, R.layout.item_job_list, data);
        jobsMrl.setAdapter(adapter);
        jobsMrl.setLayoutRefreshListener(this);
        jobsMrl.setOnLvItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onJobItemClick(data.get(position));
            }
        });
    }

    private void requestHttp() {
        jobsMrl.getRefreshLayout().post(new Runnable() {
            @Override
            public void run() {
                //首次进入刷新
                jobsMrl.onRefresh();
            }
        });
    }

    /**
     * 设置数据
     */
    @Override
    public void setJobData(JobResult result) {
        if (isRefresh) {
            //刷新
            adapter.setRefresh(result.getList());
            jobsMrl.refreshComplete(result.getCountAll(), true);
        } else {
            //加载
            adapter.addAll(result.getList());
            jobsMrl.refreshComplete(result.getCountAll(), false);
        }
    }

    /**
     * 刷新
     *
     * @param page
     */
    @Override
    public void onRefresh(int page) {
        isRefresh = true;
        presenter.getJobData(page);
    }

    /**
     * 加载
     *
     * @param page
     */
    @Override
    public void loadMore(int page) {
        isRefresh = false;
        presenter.getJobData(page);
    }

}
