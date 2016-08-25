package com.zhou.gitproject.contactlist;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.contactlist.adapter.ContactAdapter2;
import com.zhou.gitproject.contactlist.bean.ManModel;
import com.zhou.gitproject.contactlist.utils.CharacterParser;
import com.zhou.gitproject.contactlist.utils.PinyinComparator;
import com.zhou.gitproject.contactlist.view.ClearEditText;
import com.zhou.gitproject.contactlist.view.SideBar;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 仿微信通讯录
 * Created by zhou on 2016/8/15.
 */
public class ContactList2 extends BaseActivity {

    @InjectView(R.id.search_et)
    ClearEditText searchEt;
    @InjectView(R.id.contact2_lv)
    ListView contact2Lv;
    @InjectView(R.id.dialog_tv)
    TextView dialogTv;
    @InjectView(R.id.side_bar)
    SideBar sideBar;

    private ContactAdapter2 adapter;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<ManModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist_2);
        ButterKnife.inject(this);
        StatusBarUtils.setColor(this, Color.parseColor("#ff6600"));
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("通讯录");
    }

    /**
     * 初始化
     */
    private void initView() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar.setTextView(dialogTv);
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    contact2Lv.setSelection(position);
                }
            }
        });
        SourceDateList = filledData(getResources().getStringArray(R.array.date));
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new ContactAdapter2(this, SourceDateList);
        contact2Lv.setAdapter(adapter);
        contact2Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                showToast(((ManModel) adapter.getItem(position)).getName());
            }
        });
        //根据输入框输入值的改变来过滤搜索
        searchEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<ManModel> filledData(String[] date) {
        List<ManModel> mSortList = new ArrayList<ManModel>();

        for (int i = 0; i < date.length; i++) {
            ManModel man = new ManModel();
            man.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                man.setSortLetters(sortString.toUpperCase());
            } else {
                man.setSortLetters("#");
            }
            mSortList.add(man);
        }
        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<ManModel> filterDateList = new ArrayList<ManModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (ManModel man : SourceDateList) {
                String name = man.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(man);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
