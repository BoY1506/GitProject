package com.zhou.gitproject.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

/**
 * Created by zhou on 2016/8/22.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    /**
     * ItemClick的单击事件回调接口
     */
    public interface OnItemClickeListener {
        void onItemClick(View veiw, int position);
    }

    private OnItemClickeListener mOnItemClickListener;

    /**
     * 为接口提供入口
     *
     * @param mOnItemClickListener
     */
    public void setOnItemClickListener(OnItemClickeListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public RvAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    /**
     * viewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        TextView textView;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = UIUtils.inflate2(context, R.layout.item_listsel_lv, parent);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.textView = (TextView) view.findViewById(R.id.name_tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(data.get(position));
        //如果设置了回调，则添加点击事件
        if (mOnItemClickListener != null) {
            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.textView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
