package com.zhou.gitproject.listviewselall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.listviewselall.bean.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * 全选list适配器
 * Created by zhou on 2016/8/17.
 */
public class ListSelAdapter extends BaseAdapter {

    private Context context;
    private List<Friend> friendList;

    private boolean isEditMode = false;//是否处于编辑模式

    public ListSelAdapter(Context context, List<Friend> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Friend getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listsel_lv, parent, false);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
            viewHolder.checkCb = (CheckBox) convertView.findViewById(R.id.check_cb);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //这里会报错，状态被改变
        viewHolder.nameTv.setText(getItem(position).getName());
        viewHolder.checkCb.setChecked(getItem(position).getIsChecked());
        viewHolder.checkCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新用户选中状态
                getItem(position).setIsChecked(!getItem(position).getIsChecked());
                notifyDataSetChanged();
            }
        });
        if (isEditMode) {
            //处于编辑模式，显示checkBox
            viewHolder.checkCb.setVisibility(View.VISIBLE);
        } else {
            //非编辑模式隐藏
            viewHolder.checkCb.setVisibility(View.GONE);
        }
        return convertView;
    }

    /**
     * 设置是否是编辑模式
     */
    public void setIsEditMode(boolean b) {
        this.isEditMode = b;
        if (isEditMode) {
            //编辑模式
            notifyDataSetChanged();
        } else {
            //取消编辑，全部取消选中
            setUnSelAll();
        }
    }

    /**
     * 设置全选
     */
    public void setSelAll() {
        for (Friend friend : friendList) {
            friend.setIsChecked(true);
        }
        notifyDataSetChanged();
    }

    /**
     * 取消全选
     */
    public void setUnSelAll() {
        for (Friend friend : friendList) {
            friend.setIsChecked(false);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     */
    public void deleteData() {
        //用一个新的list来保存要删除的数据，解决顺序问题
        List<Friend> deleteList = new ArrayList<>();
        for (Friend friend : friendList) {
            if (friend.getIsChecked()) {
                deleteList.add(friend);
            }
        }
        friendList.removeAll(deleteList);
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView nameTv;
        CheckBox checkCb;
    }

}
