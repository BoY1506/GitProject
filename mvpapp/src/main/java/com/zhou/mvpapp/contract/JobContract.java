package com.zhou.mvpapp.contract;

import com.zhou.mvpapp.base.IBasePresenter;
import com.zhou.mvpapp.base.IBaseView;
import com.zhou.mvpapp.bean.JobDetails;
import com.zhou.mvpapp.bean.JobResult;

/**
 * 工作模块契约类
 * Created by zhou on 2016/9/12.
 */
public class JobContract {

    /**
     * 工作列表view
     */
    public interface IHomeView extends IBaseView {
        //设置数据
        void setJobData(JobResult result);
    }

    /**
     * 工作列表presenter
     */
    public interface IHomePresenter extends IBasePresenter<IHomeView> {
        //获取数据
        void getJobData(int page);

        //item点击
        void onJobItemClick(JobResult.Job job);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 工作详情view
     */
    public interface IJobDetailsView extends IBaseView {
        //设置数据
        void setJobDetailsData(JobDetails details);
    }

    /**
     * 工作详情presenter
     */
    public interface IJobDetailsPresenter extends IBasePresenter<IJobDetailsView> {
        //获取数据
        void getJobDetailsData();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 工作逻辑处理接口
     * Created by zhou on 2016/9/6.
     */
    public interface IJobModel {
        //职位搜索
        void findJobs(String searchAttr, int eachNum, int page, HandleListener<JobResult> listener);

        //职位详情
        void JobDetails(String iPobId, HandleListener<JobDetails> listener);
    }

}
