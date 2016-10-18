package com.zhou.mvpapp.presenter;

import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.bean.JobDetails;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.JobContract;
import com.zhou.mvpapp.model.JobModel;

/**
 * 工作详情Presenter
 * Created by zhou on 2016/9/7.
 */
public class JobDetailsPresenter extends BasePresenter<JobContract.IJobDetailsView, JobContract.IJobModel>
        implements JobContract.IJobDetailsPresenter {

    @Override
    protected void createModel() {
        model = JobModel.getInstance();
    }

    @Override
    public void getJobDetailsData() {
        model.JobDetails(view.getComeBundle().getString("id"),
                new HandleListener<JobDetails>() {
                    @Override
                    public void onSuccess(String msg, JobDetails data) {
                        view.setJobDetailsData(data);
                    }
                });
    }

}
