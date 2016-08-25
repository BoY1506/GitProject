package com.zhou.gitproject.contactlist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.contactlist.bean.Man;
import com.zhou.gitproject.contactlist.utils.PinYinUtils;
import com.zhou.gitproject.contactlist.view.PinnedSectionListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通讯录适配器
 * Created by zhou on 2016/8/13.
 */
public class ContactAdapter1 extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    private static final int VIEW_TYPE_NAME = 0;
    private static final int VIEW_TYPE_LETTER = 1;

    private Context context;
    //名字列表
    private List<Man> manList;

    //总列表
    private List<Object> datas;
    //右侧栏字母索引
    private Map<String, Integer> letterPosition;

    public ContactAdapter1(Context context, List<Man> manList) {
        this.context = context;
        this.manList = manList;
        initList();
    }

    /**
     * 初始化数据
     */
    private void initList() {
        datas = new ArrayList<>();
        letterPosition = new HashMap<>();
        //通讯录按排序
        Collections.sort(manList);
        //加入首字母到总列表
        for (int i = 0; i < manList.size(); i++) {
            Man man = manList.get(i);
            String firstLetter = getFirstLetter(man.getName());
            //填充右侧栏导航数据
            if (!letterPosition.containsKey(firstLetter)) {
                letterPosition.put(firstLetter, datas.size());
                datas.add(firstLetter);
            }
            //填充总数据
            datas.add(man);
        }
    }

    /**
     * 获取名称首字母
     */
    private String getFirstLetter(String name) {
        String firstLetter = "";
        char c = PinYinUtils.trans2PinYin(name).toUpperCase().charAt(0);
        if (c >= 'A' && c <= 'Z') {
            firstLetter = String.valueOf(c);
        }
        return firstLetter;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position) instanceof Man ? VIEW_TYPE_NAME : VIEW_TYPE_LETTER;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == VIEW_TYPE_LETTER;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        return itemViewType == VIEW_TYPE_NAME ? getNameView(position, convertView) : getLetterView(position, convertView);
    }

    /**
     * 获取名称视图
     */
    private View getNameView(int position, View convertView) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_contacts_list, null);
            viewHolder.name_tv = (TextView) convertView.findViewById(R.id.contact_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name_tv.setText(((Man) getItem(position)).getName());
        return convertView;
    }

    /**
     * 获取首字母视图
     */
    private View getLetterView(int position, View convertView) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_contacts_letter, null);
            viewHolder.letter_tv = (TextView) convertView.findViewById(R.id.contact_letter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.letter_tv.setText((String) getItem(position));
        return convertView;
    }

    /**
     * 返回右侧栏索引位置
     */
    public int getLetterPosition(String letter) {
        Integer positoin = letterPosition.get(letter);
        return positoin == null ? -1 : positoin;
    }

    /**
     * 更新数据
     */
    public void updateList() {
        initList();
        notifyDataSetChanged();
    }

    /**
     * 缓存视图viewHolder
     */
    static class ViewHolder {
        TextView name_tv;
        TextView letter_tv;
    }

}
