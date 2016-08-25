package com.zhou.gitproject.contactlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.contactlist.bean.ManModel;

import java.util.List;

/**
 * 联系人排序适配器
 * Created by zhou on 2016/8/15.
 */
public class ContactAdapter2 extends BaseAdapter implements SectionIndexer {

    private List<ManModel> list = null;
    private Context mContext;

    public ContactAdapter2(Context mContext, List<ManModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contact2_list, null);
            viewHolder.letterTv = (TextView) view.findViewById(R.id.letter_tv);
            viewHolder.avatarIv = (ImageView) view.findViewById(R.id.avatar_iv);
            viewHolder.nameTv = (TextView) view.findViewById(R.id.name_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.letterTv.setVisibility(View.VISIBLE);
            viewHolder.letterTv.setText(list.get(position).getSortLetters());
        } else {
            viewHolder.letterTv.setVisibility(View.GONE);
        }
        viewHolder.nameTv.setText(list.get(position).getName());
        return view;

    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<ManModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView letterTv;
        ImageView avatarIv;
        TextView nameTv;
    }

}
