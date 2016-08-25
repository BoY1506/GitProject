package com.zhou.gitproject.viewpagerload.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.zhou.gitproject.R;
import com.zhou.gitproject.refreshlistview.swipelv.view.MyRefreshLayout;
import com.zhou.gitproject.utils.ToastUtils;
import com.zhou.gitproject.viewpagerload.base.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 第二个fragment
 * Created by zhou on 2016/8/16.
 */
public class Load2Fragment2 extends LazyLoadFragment implements MyRefreshLayout.LayoutRefreshListener {

    @InjectView(R.id.my_refresh_layout)
    MyRefreshLayout myRefreshLayout;

    private List<String> data = new ArrayList<>();
    private ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment1_load1, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data);
        myRefreshLayout.setRefreshLayoutColor(Color.parseColor("#ff6600"));
        myRefreshLayout.setAdapter(adapter);
        myRefreshLayout.setLayoutRefreshListener(this);
        myRefreshLayout.setOnLvItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(getActivity(), "点击了第" + (position + 1) + "项", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void loadData() {
        myRefreshLayout.onRefresh();
    }

    @Override
    public void onRefresh(int page) {
        myRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.clear();
                for (int i = 0; i < 20; i++) {
                    data.add("添加了数据~~" + i);
                }
                adapter.notifyDataSetChanged();
                //刷新完成
                myRefreshLayout.refreshComplete(50, true);
            }
        }, 2000);
    }

    @Override
    public void loadMore(int page) {
        myRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int count = adapter.getCount();
                for (int i = count; i < count + 10; i++) {
                    data.add("添加了数据~~" + i);
                }
                adapter.notifyDataSetChanged();
                //刷新完成
                myRefreshLayout.refreshComplete(50, false);
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
