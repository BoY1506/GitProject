package com.zhou.gitproject.popfilter.my.adapter;

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
public class FilterPopAdapter extends BaseAdapter {

    private Context context;
    private List<String> data;

    //选中index
    private int selIndex;

    public FilterPopAdapter(Context context, List<String> data, int selIndex) {
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
            convertView = UIUtils.inflate2(context, R.layout.item_filter_list, parent);
            viewHolder = new ViewHolder();
            viewHolder.itemTv = (TextView) convertView.findViewById(R.id.filter_item_tv);
            viewHolder.itemIv = (ImageView) convertView.findViewById(R.id.filter_item_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemTv.setText(data.get(position));
        if (position == selIndex) {
            viewHolder.itemIv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.itemIv.setVisibility(View.GONE);
        }
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
        TextView itemTv;
        ImageView itemIv;
    }

}
