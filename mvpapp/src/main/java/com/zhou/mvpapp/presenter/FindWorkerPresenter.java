package com.zhou.mvpapp.presenter;

import android.os.Bundle;

import com.zhou.mvpapp.activity.WorkerDetailsActivity;
import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.bean.WorkerResult;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.TeamContract;
import com.zhou.mvpapp.model.TeamModel;

/**
 * 寻找工人presenter
 * Created by zhou on 2016/9/29.
 */
public class FindWorkerPresenter extends BasePresenter<TeamContract.IFindWorkerView, TeamContract.ITeamModel>
        implements TeamContract.IFindWorkerPresenter {

    @Override
    protected void createModel() {
        model = TeamModel.getInstance();
    }

    @Override
    public void getWorkerListData(int page) {
        model.findWorkers("", 10, page, new HandleListener<WorkerResult>() {
            @Override
            public void onSuccess(String msg, WorkerResult data) {
                view.setWorkerData(data);
            }
        });
    }

    @Override
    public void onWorkerItemClick(WorkerResult.Worker worker) {
        Bundle bundle = new Bundle();
        bundle.putInt("workerId", Integer.parseInt(worker.getI_user_id()));
        intent2Activity(WorkerDetailsActivity.class, bundle);
    }

}
