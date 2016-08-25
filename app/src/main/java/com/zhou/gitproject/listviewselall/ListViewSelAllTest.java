package com.zhou.gitproject.listviewselall;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.listviewselall.adapter.ListSelAdapter;
import com.zhou.gitproject.listviewselall.bean.Friend;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * listView全选删除练习
 * Created by zhou on 2016/8/17.
 */
public class ListViewSelAllTest extends BaseActivity {


    @InjectView(R.id.list_sel_lv)
    ListView listSelLv;
    @InjectView(R.id.cancle_btn)
    Button cancleBtn;
    @InjectView(R.id.delete_btn)
    Button deleteBtn;
    @InjectView(R.id.option_ll)
    LinearLayout optionLl;

    private List<Friend> friendList;
    private ListSelAdapter adapter;

    private ActionBarBuilder barBuilder;

    //是否编辑模式
    private boolean isEditMode = false;
    //是否全选
    private boolean isSelAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewselall_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor(this, Color.parseColor("#FF6600"));
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        friendList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            friendList.add(new Friend("张三 " + (i + 1), false));
        }
    }

    /**
     * 初始化view
     */
    private void initView() {
        adapter = new ListSelAdapter(this, friendList);
        listSelLv.setAdapter(adapter);
        listSelLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(friendList.get(position).getName());
            }
        });
        listSelLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                friendList.get(position).setIsChecked(true);
                updateIsEditMode(true);
                return true;
            }
        });
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        barBuilder = builder;
        setActionBarBuilder();
    }

    /**
     * 设置actionbar
     */
    private void setActionBarBuilder() {
        if (isEditMode) {
            //编辑模式
            if (isSelAll) {
                //全选模式
                barBuilder.setAcBarLeftImg().setAcBarBigText("好友").setAcBarRightText("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateIsSelAll(false);
                    }
                });
            } else {
                //非全选模式
                barBuilder.setAcBarLeftImg().setAcBarBigText("好友").setAcBarRightText("全选", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateIsSelAll(true);
                    }
                });
            }
        } else {
            //非编辑模式
            barBuilder.setAcBarLeftImg().setAcBarBigText("好友").setAcBarRightText("", null);
        }
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.cancle_btn, R.id.delete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancle_btn:
                updateIsEditMode(false);
                break;
            case R.id.delete_btn:
                deleteSelList();
                break;
        }
    }

    /**
     * 更新编辑模式
     */
    private void updateIsEditMode(boolean b) {
        isEditMode = b;
        //更新actionBar
        setActionBarBuilder();
        //更新操作按钮
        optionLl.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
        //更新数据
        adapter.setIsEditMode(isEditMode);
        if (!isEditMode) {
            //如果取消，初始化全选标志
            isSelAll = false;
        }
    }

    /**
     * 更新全选模式
     */
    private void updateIsSelAll(boolean b) {
        isSelAll = b;
        //更新actionbar
        setActionBarBuilder();
        //更新数据
        if (isSelAll) {
            //全部选中
            adapter.setSelAll();
        } else {
            //取消全选
            adapter.setUnSelAll();
        }
    }

    /**
     * 删除选中数据
     */
    private void deleteSelList() {
        adapter.deleteData();
        //初始化全选标志
        isSelAll = false;
        setActionBarBuilder();
    }

}
