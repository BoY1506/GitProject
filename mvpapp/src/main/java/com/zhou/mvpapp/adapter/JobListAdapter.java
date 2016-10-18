package com.zhou.mvpapp.adapter;

import android.content.Context;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.adapter.abslistview.CommonAdapter;
import com.zhou.mvpapp.adapter.abslistview.ViewHolder;
import com.zhou.mvpapp.bean.JobResult;
import com.zhou.mvpapp.utils.TextFormatUtils;

import java.util.List;

/**
 * 工作列表适配器
 * Created by zhou on 2016/9/6.
 */
public class JobListAdapter extends CommonAdapter<JobResult.Job> {

    public JobListAdapter(Context context, int layoutId, List<JobResult.Job> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, JobResult.Job item, int position) {
        viewHolder.setText(R.id.job_name, item.getC_name())
                .setText(R.id.job_pro_name, item.getC_project_name())
                .setText(R.id.job_pro_time, "(" + item.getI_project_days() + "天工期)")
                .setText(R.id.job_pro_starttime, item.getI_project_start())
                .setText(R.id.job_pro_place, item.getC_region_name() + item.getC_region_detail())
                .setText(R.id.company_grade, item.getCompany().getI_star() + "分")
                .setText(R.id.released_time, item.getI_add_time())
                .setText(R.id.job_need_num, "目标" + item.getI_need_people() + "人")
                .setText(R.id.job_joined_num, "(已招" + item.getI_joined_people() + "人)")
                .setText(R.id.job_day_pay, "¥" + TextFormatUtils.getNumber(Double.parseDouble(
                        item.getD_day_salary().equals("") ? "0" : item.getD_day_salary())));
    }

}
