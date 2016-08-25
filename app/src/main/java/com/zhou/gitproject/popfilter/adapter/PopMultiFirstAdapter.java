package com.zhou.gitproject.popfilter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

/**
 * pop筛选种类一级列表适配器
 * Created by zhou on 2015/11/18.
 */
public class PopMultiFirstAdapter extends BaseAdapter {

    private Context context;
    private List<String> data;

    //被选中下标
    private int firstSelIndex = 0;

    public PopMultiFirstAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
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
            convertView = UIUtils.inflate2(context, R.layout.item_filter_first_list4pop, parent);
            viewHolder = new ViewHolder();
            viewHolder.filterTv = (TextView) convertView.findViewById(R.id.filter_tv);
            viewHolder.filterLineRight = convertView.findViewById(R.id.filter_line_right);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //判断状态
        if (position == firstSelIndex) {
            //选中状态
            convertView.setBackgroundResource(R.color._white);
            viewHolder.filterTv.setTextColor(Color.parseColor("#646464"));
            viewHolder.filterLineRight.setVisibility(View.GONE);
        } else {
            //未选中状态
            convertView.setBackgroundResource(R.color._background);
            viewHolder.filterTv.setTextColor(Color.parseColor("#969696"));
            viewHolder.filterLineRight.setVisibility(View.VISIBLE);
        }
        viewHolder.filterTv.setText(data.get(position));
        return convertView;
    }

    /**
     * 重置选中下标
     *
     * @param index
     */
    public void setFirstSelIndex(int index) {
        this.firstSelIndex = index;
        notifyDataSetChanged();
    }

    /**
     * 获取选中的一级下标
     *
     * @return
     */
    public int getFirstSelIndex() {
        return firstSelIndex;
    }

    private static class ViewHolder {
        TextView filterTv;
        View filterLineRight;
    }

}
