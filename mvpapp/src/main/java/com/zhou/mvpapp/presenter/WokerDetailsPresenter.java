package com.zhou.mvpapp.presenter;

import com.zhou.mvpapp.base.BasePresenter;
import com.zhou.mvpapp.bean.WorkerDetails;
import com.zhou.mvpapp.contract.HandleListener;
import com.zhou.mvpapp.contract.TeamContract;
import com.zhou.mvpapp.model.TeamModel;

/**
 * 工人详情presenter
 * Created by zhou on 2016/9/29.
 */
public class WokerDetailsPresenter extends BasePresenter<TeamContract.IWorkerDetailsView, TeamContract.ITeamModel>
        implements TeamContract.IWorkerDetailsPresenter {

    @Override
    protected void createModel() {
        model = TeamModel.getInstance();
    }

    @Override
    public void getWorkerDetailsData() {
        model.workerDetails(view.getComeBundle().getInt("workerId"), "v9tv40nq71c96kv91anhfb3hj4",
                new HandleListener<WorkerDetails>() {
                    @Override
                    public void onSuccess(String msg, WorkerDetails data) {
                        view.setWorkerDetailsData(data);
                    }
                });
    }

}
