package com.zhou.mvpapp.presenter;

import android.os.Bundle;

import com.zhou.mvpapp.activity.JobDetailsActivity;
import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.bean.JobResult;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.JobContract;
import com.zhou.mvpapp.model.JobModel;
import com.zhou.mvpapp.utils.GsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页presenter
 * Created by zhou on 2016/9/6.
 */
public class HomePresenter extends BasePresenter<JobContract.IHomeView, JobContract.IJobModel>
        implements JobContract.IHomePresenter {

    @Override
    protected void createModel() {
        model = JobModel.getInstance();
    }

    /**
     * 请求数据
     */
    @Override
    public void getJobData(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("i_type", 1);
        model.findJobs(GsonUtils.map2Json(map), 5, page, new HandleListener<JobResult>() {
            @Override
            public void onSuccess(String msg, JobResult result) {
                view.setJobData(result);
            }
        });
    }

    /**
     * item点击
     */
    @Override
    public void onJobItemClick(JobResult.Job job) {
        Bundle bundle = new Bundle();
        bundle.putString("id", job.getI_pob_id());
        intent2Activity(JobDetailsActivity.class, bundle);
    }

}
