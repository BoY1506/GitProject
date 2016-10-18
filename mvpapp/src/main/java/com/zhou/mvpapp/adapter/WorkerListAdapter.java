package com.zhou.mvpapp.adapter;

import android.content.Context;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.adapter.abslistview.CommonAdapter;
import com.zhou.mvpapp.adapter.abslistview.ViewHolder;
import com.zhou.mvpapp.bean.WorkerResult;

import java.util.List;

/**
 * 工人列表适配器
 * Created by zhou on 2016/9/6.
 */
public class WorkerListAdapter extends CommonAdapter<WorkerResult.Worker> {

    public WorkerListAdapter(Context context, int layoutId, List<WorkerResult.Worker> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, WorkerResult.Worker item, int position) {
        viewHolder.setImgPic(R.id.worker_avatar, item.getC_avatar())
                .setText(R.id.worker_no, "编号：" + item.getC_no())
                .setText(R.id.worker_name, item.getC_name())
                .setText(R.id.worker_location, item.getC_addr())
                .setText(R.id.worker_good_job, item.getC_good_job().size() == 1 ?
                        item.getC_good_job().get(0) : item.getC_good_job().get(0) + "," + item.getC_good_job().get(1));
    }

}
