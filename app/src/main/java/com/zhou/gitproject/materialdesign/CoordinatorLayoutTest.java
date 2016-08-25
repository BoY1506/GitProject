package com.zhou.gitproject.materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * CoordinatorLayout练习
 * 强大的FrameLayout，可让floatingActionBtn和SnackerBar自适应布局
 * 给子控件添加Flag来控制滑动过度动画：scroll、enterAlways、enterAlwaysCollapsed、exitUntilCollapsed
 * 后两者只能在CollapsingToolbarLayout中使用
 * Created by zhou on 2016/8/22.
 */
public class CoordinatorLayoutTest extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @InjectView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_test);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initView();
    }

    private void initView() {
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        collapsingToolbarLayout.setTitle("个人中心");
        //通过CollapsingToolbarLayout修改字体颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("这是第" + (i + 1) + "项");
        }
        RvAdapter adapter = new RvAdapter(this, list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new RvAdapter.OnItemClickeListener() {
            @Override
            public void onItemClick(View veiw, int position) {
                ToastUtils.showToast(getApplicationContext(), list.get(position), Toast.LENGTH_SHORT);
            }
        });
    }

}
