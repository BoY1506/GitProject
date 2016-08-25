package com.zhou.gitproject.popfilter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

/**
 * pop筛选种类二级列表适配器
 * Created by zhou on 2015/11/18.
 */
public class PopMultiSecondAdapter extends BaseAdapter {

    private Context context;
    private List<String> data;

    //被选中下标
    private int secondSelIndex = -1;

    public PopMultiSecondAdapter(Context context, List<String> data, int secondSelIndex) {
        this.context = context;
        this.data = data;
        this.secondSelIndex = secondSelIndex;
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
            convertView = UIUtils.inflate2(context, R.layout.item_filter_second_list4pop,parent);
            viewHolder = new ViewHolder();
            viewHolder.filterTv = (TextView) convertView.findViewById(R.id.filter_tv);
            viewHolder.filterIv = (ImageView) convertView.findViewById(R.id.filter_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //判断状态
        if (position == secondSelIndex) {
            //选中状态
            viewHolder.filterTv.setTextColor(Color.parseColor("#FF6400"));
            viewHolder.filterIv.setVisibility(View.VISIBLE);
        } else {
            //未选中状态
            viewHolder.filterTv.setTextColor(Color.parseColor("#646464"));
            viewHolder.filterIv.setVisibility(View.INVISIBLE);
        }
        viewHolder.filterTv.setText(data.get(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView filterTv;
        ImageView filterIv;
    }

    /**
     * 重置选中下标
     *
     * @param index
     */
    public void setSecondSelIndex(int index) {
        this.secondSelIndex = index;
        notifyDataSetChanged();
    }

    /**
     * 重置选中下标和数据
     *
     * @param index
     * @param list
     */
    public void setSecondSelIndexAndData(int index, List<String> list) {
        this.secondSelIndex = index;
        this.data = list;
        notifyDataSetChanged();
    }

}
