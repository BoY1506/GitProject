package com.zhou.mvpapp.model;

import com.zhou.mvpapp.bean.WorkerDetails;
import com.zhou.mvpapp.bean.WorkerResult;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.TeamContract;
import com.zhou.mvpapp.net.BaseResponse;
import com.zhou.mvpapp.net.HttpCall;
import com.zhou.mvpapp.net.SimpleCallback;
import com.zhou.mvpapp.utils.CallManager;

import retrofit2.Call;

/**
 * 班组逻辑处理类
 * Created by zhou on 2016/9/6.
 */
public class TeamModel implements TeamContract.ITeamModel {

    private static TeamContract.ITeamModel instance;

    /**
     * 单一实例
     */
    public static TeamContract.ITeamModel getInstance() {
        if (instance == null) {
            synchronized (TeamContract.ITeamModel.class) {
                if (instance == null) {
                    instance = new TeamModel();
                }
            }
        }
        return instance;
    }

    /**
     * 工人搜索
     */
    @Override
    public void findWorkers(String searchAttr, int eachNum, int page, final HandleListener<WorkerResult> listener) {
        Call<BaseResponse<WorkerResult>> call = HttpCall.getApi().findWorkers(searchAttr, eachNum, page);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<WorkerResult>() {
            @Override
            public void convert(String msg, WorkerResult data) {
                listener.onSuccess(msg, data);
            }
        });
    }

    /**
     * 工人详情
     */
    @Override
    public void workerDetails(int workerId, String cidentify, final HandleListener<WorkerDetails> listener) {
        Call<BaseResponse<WorkerDetails>> call = HttpCall.getApi().workerDetails(workerId, cidentify);
        CallManager.getInstance().addCall(call);
        call.enqueue(new SimpleCallback<WorkerDetails>() {
            @Override
            public void convert(String msg, WorkerDetails data) {
                listener.onSuccess(msg, data);
            }
        });
    }

}
