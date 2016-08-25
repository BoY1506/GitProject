package com.zhou.gitproject.popfilter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

/**
 * popupWindow筛选适配器
 * Created by zhou on 2016/4/25.
 */
public class PopSingleAdapter extends BaseAdapter {

    private Context context;
    private List<String> data;

    //选中index
    private int selIndex;

    public PopSingleAdapter(Context context, List<String> data, int selIndex) {
        this.context = context;
        this.data = data;
        this.selIndex = selIndex;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = UIUtils.inflate2(context, R.layout.item_filter_list4pop, parent);
            viewHolder = new ViewHolder();
            viewHolder.filterTv = (TextView) convertView.findViewById(R.id.filter_tv);
            viewHolder.filterIv = (ImageView) convertView.findViewById(R.id.filter_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //判断状态
        if (position == selIndex) {
            convertView.setBackgroundResource(R.color._white);
            viewHolder.filterIv.setVisibility(View.VISIBLE);
        } else {
            convertView.setBackgroundResource(R.color._background);
            viewHolder.filterIv.setVisibility(View.GONE);
        }
        viewHolder.filterTv.setText(data.get(position));
        return convertView;
    }

    /**
     * 重置选中下标
     *
     * @param selIndex
     */
    public void setSelIndex(int selIndex) {
        this.selIndex = selIndex;
        notifyDataSetChanged();
    }

    /**
     * 获取选中下标
     *
     * @return
     */
    public int getSelIndex() {
        return selIndex;
    }

    private static class ViewHolder {
        TextView filterTv;
        ImageView filterIv;
    }

}
