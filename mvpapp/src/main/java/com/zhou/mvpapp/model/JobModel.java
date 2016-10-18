package com.zhou.mvpapp.model;

import com.zhou.mvpapp.bean.JobDetails;
import com.zhou.mvpapp.bean.JobResult;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.JobContract;
import com.zhou.mvpapp.net.BaseResponse;
import com.zhou.mvpapp.net.HttpCall;
import com.zhou.mvpapp.net.SimpleCallback;
import com.zhou.mvpapp.utils.CallManager;

import retrofit2.Call;

/**
 * 职位逻辑处理类
 * Created by zhou on 2016/9/6.
 */
public class JobModel implements JobContract.IJobModel {

    private static JobContract.IJobModel instance;

    /**
     * 单一实例
     */
    public static JobContract.IJobModel getInstance() {
        if (instance == null) {
            synchronized (JobContract.IJobModel.class) {
                if (instance == null) {
                    instance = new JobModel();
                }
            }
        }
        return instance;
    }

    /**
     * 职位搜索
     */
    @Override
    public void findJobs(String searchAttr, int eachNum, int page, final HandleListener<JobResult> listener) {
        Call<BaseResponse<JobResult>> call = HttpCall.getApi().findJobs(searchAttr, eachNum, page);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<JobResult>() {
            @Override
            public void convert(String msg, JobResult data) {
                listener.onSuccess(msg, data);
            }
        });
    }

    /**
     * 职位详情
     */
    @Override
    public void JobDetails(String iPobId, final HandleListener<JobDetails> listener) {
        Call<BaseResponse<JobDetails>> call = HttpCall.getApi().jobDetails(iPobId);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<JobDetails>() {
            @Override
            public void convert(String msg, JobDetails data) {
                listener.onSuccess(msg, data);
            }
        });
    }

}
