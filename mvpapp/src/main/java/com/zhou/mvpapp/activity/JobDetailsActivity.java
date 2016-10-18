package com.zhou.mvpapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.base.BaseActivity;
import com.zhou.mvpapp.bean.JobDetails;
import com.zhou.mvpapp.contract.JobContract;
import com.zhou.mvpapp.presenter.JobDetailsPresenter;
import com.zhou.mvpapp.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 工作详情列表
 * Created by zhou on 2016/9/7.
 */
public class JobDetailsActivity extends BaseActivity<JobContract.IJobDetailsPresenter> implements
        JobContract.IJobDetailsView {

    @InjectView(R.id.view_loading)
    RelativeLayout viewLoading;
    @InjectView(R.id.station_name)
    TextView stationName;
    @InjectView(R.id.station_people_num)
    TextView stationPeopleNum;
    @InjectView(R.id.station_pro_name)
    TextView stationProName;
    @InjectView(R.id.station_pro_time)
    TextView stationProTime;
    @InjectView(R.id.station_pro_starttime)
    TextView stationProStarttime;
    @InjectView(R.id.station_pro_place)
    TextView stationProPlace;
    @InjectView(R.id.station_linkman)
    TextView stationLinkman;
    @InjectView(R.id.station_linkman_phone)
    TextView stationLinkmanPhone;
    @InjectView(R.id.station_day_pay)
    TextView stationDayPay;
    @InjectView(R.id.released_time)
    TextView releasedTime;
    @InjectView(R.id.station_description)
    TextView stationDescription;
    @InjectView(R.id.company_name)
    TextView companyName;
    @InjectView(R.id.company_address)
    TextView companyAddress;
    @InjectView(R.id.company_grade)
    TextView companyGrade;
    @InjectView(R.id.company_bargain_num)
    TextView companyBargainNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        ButterKnife.inject(this);
        //请求数据
        requestHttp();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("工作详情");
    }

    @Override
    public JobContract.IJobDetailsPresenter initPresenter() {
        return new JobDetailsPresenter();
    }

    @Override
    public void initView() {
        initLoadingView();
    }

    @Override
    public void initLoadingView() {
        loadingRelativeLayout = viewLoading;
    }

    private void requestHttp() {
        presenter.getJobDetailsData();
    }

    @Override
    public void setJobDetailsData(JobDetails jd) {
        //岗位信息
        stationName.setText(jd.getC_name());
        stationPeopleNum.setText("目标" + jd.getI_need_people() + "人(已招" + jd.getI_joined_people() + "人)");
        stationProName.setText(jd.getC_project_name());
        stationProTime.setText(jd.getI_project_days() + "天");
        stationProStarttime.setText(jd.getI_project_start());
        stationProPlace.setText(jd.getC_region_name() + jd.getC_region_detail());
        stationLinkman.setText(jd.getC_contract_name());
        stationLinkmanPhone.setText(jd.getC_contract_phone());
        stationDayPay.setText(jd.getD_day_salary() + "元/日");
        releasedTime.setText(jd.getI_add_time());
        if (TextUtils.isEmpty(jd.getC_desc())) {
            stationDescription.setText("该企业没有留下岗位描述...");
        } else {
            stationDescription.setText(jd.getC_desc());
        }
        //公司信息
        companyName.setText(jd.getCompany().getC_name());
        companyAddress.setText(jd.getCompany().getC_addr_detail());
        companyGrade.setText(jd.getCompany().getI_star() + "分");
        companyBargainNum.setText("(" + jd.getCompany().getPublish_job_nums() + "次招工)");
    }

}
