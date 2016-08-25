package com.zhou.gitproject.cityselect2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.cityselect2.bean.City;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

/**
 * 搜索城市列表适配器
 * Created by zhou on 2016/8/23.
 */
public class SearchCityResultAdapter extends BaseAdapter {

    private Context context;
    private List<City> cityList;

    public SearchCityResultAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
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
            convertView = UIUtils.inflate2(context, R.layout.item_citylist_c_name, parent);
            holder.textView = (TextView) convertView.findViewById(R.id.city_name_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(getItem(position).getName());
        return convertView;
    }

    /**
     * 更新数据
     */
    public void setData(List<City> list) {
        this.cityList = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView textView;
    }

}
