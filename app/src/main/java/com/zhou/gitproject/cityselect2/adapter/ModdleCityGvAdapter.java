package com.zhou.gitproject.cityselect2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.cityselect2.bean.City;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

/**
 * 最近、热门城市列表适配器
 * Created by zhou on 2016/8/23.
 */
public class ModdleCityGvAdapter extends BaseAdapter {

    private Context context;
    private List<City> cityList;
    private GridView gridView;

    public ModdleCityGvAdapter(Context context, List<City> cityList, GridView gridView) {
        this.context = context;
        this.cityList = cityList;
        this.gridView = gridView;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public City getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = UIUtils.inflate2(context, R.layout.item_moddle_city_gv, parent);
            holder.textView = (TextView) convertView.findViewById(R.id.moddle_city_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int horizontalSpacing = gridView.getHorizontalSpacing();
        int width = (gridView.getWidth() - horizontalSpacing * 2 - gridView.getPaddingLeft() - gridView.getPaddingRight()) / 3;
        int height = UIUtils.dp2px(context, 45f);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        holder.textView.setLayoutParams(params);
        holder.textView.setText(getItem(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }

}
