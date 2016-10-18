package com.zhou.mvpapp.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhou.mvpapp.R;
import com.zhou.mvpapp.bean.WorkerResult;

import java.util.List;

/**
 * 工人列表Rv适配器
 * Created by zhou on 2016/10/9.
 */
public class WorkerListAdapter4Rv extends BaseQuickAdapter<WorkerResult.Worker> {

//public class WorkerListAdapter4Rv extends CommonAdapter<WorkerResult.Worker> {

//    public WorkerListAdapter4Rv(Context context, List<WorkerResult.Worker> datas) {
//        super(context, R.layout.item_worker_list, datas);
//    }
//
//    @Override
//    protected void convert(ViewHolder holder, WorkerResult.Worker worker, int position) {
//        holder.setImgPic(R.id.worker_avatar, worker.getC_avatar())
//                .setText(R.id.worker_no, "编号：" + worker.getC_no())
//                .setText(R.id.worker_name, worker.getC_name())
//                .setText(R.id.worker_location, worker.getC_addr())
//                .setText(R.id.worker_good_job, worker.getC_good_job().size() == 1 ?
//                        worker.getC_good_job().get(0) : worker.getC_good_job().get(0) + "," + worker.getC_good_job().get(1));
//    }

    public WorkerListAdapter4Rv(List<WorkerResult.Worker> data) {
        super(R.layout.item_worker_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkerResult.Worker worker) {
        baseViewHolder.setText(R.id.worker_no, "编号：" + worker.getC_no())
                .setText(R.id.worker_name, worker.getC_name())
                .setText(R.id.worker_location, worker.getC_addr())
                .setText(R.id.worker_good_job, worker.getC_good_job().size() == 1 ?
                        worker.getC_good_job().get(0) : worker.getC_good_job().get(0) + "," + worker.getC_good_job().get(1));
        ((SimpleDraweeView) baseViewHolder.getView(R.id.worker_avatar)).setImageURI(Uri.parse(worker.getC_avatar()));
    }

}
