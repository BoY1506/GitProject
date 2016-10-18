package com.zhou.mvpapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhou.mvpapp.R;
import com.zhou.mvpapp.base.BaseActivity;
import com.zhou.mvpapp.bean.WorkerDetails;
import com.zhou.mvpapp.contract.TeamContract;
import com.zhou.mvpapp.presenter.WokerDetailsPresenter;
import com.zhou.mvpapp.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 工人详情
 * Created by zhou on 2016/9/29.
 */
public class WorkerDetailsActivity extends BaseActivity<TeamContract.IWorkerDetailsPresenter>
        implements TeamContract.IWorkerDetailsView {

    @InjectView(R.id.view_loading)
    RelativeLayout viewLoading;
    @InjectView(R.id.worker_avatar)
    SimpleDraweeView workerAvatar;
    @InjectView(R.id.worker_name)
    TextView workerName;
    @InjectView(R.id.worker_no)
    TextView workerNo;
    @InjectView(R.id.worker_sex)
    TextView workerSex;
    @InjectView(R.id.worker_age)
    TextView workerAge;
    @InjectView(R.id.worker_location)
    TextView workerLocation;
    @InjectView(R.id.skill_experience_stars)
    RatingBar skillExperienceStars;
    @InjectView(R.id.devote2work_stars)
    RatingBar devote2workStars;
    @InjectView(R.id.safety_awareness_stars)
    RatingBar safetyAwarenessStars;
    @InjectView(R.id.performance_rate_stars)
    RatingBar performanceRateStars;
    @InjectView(R.id.composite_score)
    TextView compositeScore;
    @InjectView(R.id.worker_phone)
    TextView workerPhone;
    @InjectView(R.id.worker_goodjob)
    TextView workerGoodjob;
    @InjectView(R.id.worker_payrequired)
    TextView workerPayrequired;
    @InjectView(R.id.working_hours)
    TextView workingHours;
    @InjectView(R.id.worker_selfdes)
    TextView workerSelfdes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);
        ButterKnife.inject(this);
        initView();
        //请求数据
        requestHttp();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("工人详情");
    }

    @Override
    public TeamContract.IWorkerDetailsPresenter initPresenter() {
        return new WokerDetailsPresenter();
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
        presenter.getWorkerDetailsData();
    }

    @Override
    public void setWorkerDetailsData(WorkerDetails wd) {
        //工人文字信息
        workerNo.setText("编号：" + wd.getC_no());
        workerName.setText(wd.getC_name());
        workerSex.setText(wd.getC_gender_name());
        workerAge.setText(wd.getAge() + "岁");
        workerLocation.setText(wd.getC_addr());
        skillExperienceStars.setRating(Float.parseFloat(wd.getI_tec_rate()));
        devote2workStars.setRating(Float.parseFloat(wd.getI_work_rate()));
        safetyAwarenessStars.setRating(Float.parseFloat(wd.getI_safe_rate()));
        performanceRateStars.setRating(Float.parseFloat(wd.getI_ly_rate()));
        compositeScore.setText(wd.getI_star() + "分");
        workerPhone.setText(wd.getC_phone());
        workerGoodjob.setText(wd.getC_good_job().replaceAll(",", "/"));
        workerPayrequired.setText("¥" + wd.getC_day_price() + "/日");
        workingHours.setText(wd.getI_work_days() + "工日");
        if (TextUtils.isEmpty(wd.getC_other())) {
            workerSelfdes.setText("这个工人没有留下描述...");
        } else {
            workerSelfdes.setText(wd.getC_other());
        }
        //图片信息
        workerAvatar.setImageURI(Uri.parse(wd.getC_avatar()));
    }

}
