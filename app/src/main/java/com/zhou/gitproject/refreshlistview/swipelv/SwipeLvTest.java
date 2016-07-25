package com.zhou.gitproject.refreshlistview.swipelv;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.refreshlistview.swipelv.view.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * SwipeRefreshLayout+自动加载实现刷新效果
 * Created by zhou on 2016/7/22.
 */
public class SwipeLvTest extends BaseActivity implements MyRefreshLayout.LayoutRefreshListener {

    @InjectView(R.id.my_refresh_layout)
    MyRefreshLayout myRefreshLayout;

//    @InjectView(R.id.refresh_ly)
//    SwipeRefreshLayout refreshLy;
//    @InjectView(R.id.swipe_refresh_lv)
//    MySwipeRefreshListView swipeRefreshLv;
//    @InjectView(R.id.empty_view)
//    LinearLayout emptyView;


    private List<String> data = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipelv_test);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
//        swipeRefreshLv.setNecessMember(refreshLy, adapter, emptyView);
//        swipeRefreshLv.setMyRefreshListener(this);
//        //测试空视图
//        swipeRefreshLv.refreshComplete(0, true);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        myRefreshLayout.setAdapter(adapter);
        myRefreshLayout.setLayoutRefreshListener(this);
        //测试空视图
        myRefreshLayout.refreshComplete(0, true);
        myRefreshLayout.setOnLvItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("点击了第" + (position + 1) + "项");
            }
        });
    }

    @Override
    public void onRefresh(int page) {
//        swipeRefreshLv.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                data.clear();
//                for (int i = 0; i < 20; i++) {
//                    data.add("添加了数据~~" + i);
//                }
//                adapter.notifyDataSetChanged();
//                //刷新完成
//                swipeRefreshLv.refreshComplete(50, true);
//            }
//        }, 2000);
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
//        swipeRefreshLv.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int count = adapter.getCount();
//                for (int i = count; i < count + 10; i++) {
//                    data.add("添加了数据~~" + i);
//                }
//                adapter.notifyDataSetChanged();
//                //刷新完成
//                swipeRefreshLv.refreshComplete(50, false);
//            }
//        }, 2000);
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

}
