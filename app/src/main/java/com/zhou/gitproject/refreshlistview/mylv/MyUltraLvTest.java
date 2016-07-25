package com.zhou.gitproject.refreshlistview.mylv;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.refreshlistview.mylv.view.MyUltraRefreshHeader;
import com.zhou.gitproject.refreshlistview.mylv.view.MyUltraRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 自定义头部的ultralv练习
 * Created by zhou on 2016/7/22.
 */
public class MyUltraLvTest extends BaseActivity implements MyUltraRefreshListView.UltraRefreshListener {

    @InjectView(R.id.my_tltra_lv)
    MyUltraRefreshListView myTltraLv;
    @InjectView(R.id.ultra_ptr_framelayout)
    PtrClassicFrameLayout ultraPtrFramelayout;

    private List<String> data = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myultralv_test);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        //创建我们的自定义头部视图
        MyUltraRefreshHeader header = new MyUltraRefreshHeader(this);
        //设置头部视图
        ultraPtrFramelayout.setHeaderView(header);
        //设置视图修改的回调，因为我们的CustomUltraRefreshHeader实现了PtrUIHandler
        ultraPtrFramelayout.addPtrUIHandler(header);
        //设置数据刷新的会回调，因为UltraRefreshListView实现了PtrHandler
        ultraPtrFramelayout.setPtrHandler(myTltraLv);
        //设置适配器
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        myTltraLv.setAdapter(adapter);
        //设置数据刷新回调接口
        myTltraLv.setUltraRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        ultraPtrFramelayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.clear();
                for (int i = 0; i < 20; i++) {
                    data.add("添加了数据~~" + i);
                }
                //刷新完成
                myTltraLv.refreshComplete();
                adapter.notifyDataSetChanged();
            }
        }, 1500);
    }

    @Override
    public void addMore() {
        ultraPtrFramelayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                int count = adapter.getCount();
                for (int i = count; i < count + 10; i++) {
                    data.add("添加了数据~~" + i);
                }
                adapter.notifyDataSetChanged();
                //刷新完成
                myTltraLv.refreshComplete();
            }
        }, 1500);
    }

}
