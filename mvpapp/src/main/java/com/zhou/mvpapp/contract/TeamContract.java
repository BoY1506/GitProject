package com.zhou.mvpapp.contract;

import com.zhou.mvpapp.base.IBasePresenter;
import com.zhou.mvpapp.base.IBaseView;
import com.zhou.mvpapp.bean.WorkerDetails;
import com.zhou.mvpapp.bean.WorkerResult;

/**
 * 班组模块契约类
 * Created by zhou on 2016/9/29.
 */
public class TeamContract {

    /**
     * 工人列表view
     */
    public interface IFindWorkerView extends IBaseView {
        //设置数据
        void setWorkerData(WorkerResult result);
    }

    /**
     * 工人列表presenter
     */
    public interface IFindWorkerPresenter extends IBasePresenter<IFindWorkerView> {
        //获取数据
        void getWorkerListData(int page);

        //item点击
        void onWorkerItemClick(WorkerResult.Worker worker);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 工人详情view
     */
    public interface IWorkerDetailsView extends IBaseView {
        //设置数据
        void setWorkerDetailsData(WorkerDetails details);
    }

    /**
     * 工人详情presenter
     */
    public interface IWorkerDetailsPresenter extends IBasePresenter<IWorkerDetailsView> {
        //获取数据
        void getWorkerDetailsData();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 工人逻辑处理接口
     * Created by zhou on 2016/9/6.
     */
    public interface ITeamModel {
        //工人搜索
        void findWorkers(String searchAttr, int eachNum, int page, HandleListener<WorkerResult> listener);

        //工人详情
        void workerDetails(int workerId, String cidentify, HandleListener<WorkerDetails> listener);
    }

}
