package com.zhou.gitproject.viewpagerload.base;

import android.support.v4.app.Fragment;

/**
 * 懒加载fragment
 * 通过标志位实现加载
 * 无延迟，建议使用
 * Created by zhou on 2016/8/16.
 */
public abstract class MyLoadFragment extends Fragment {

    private boolean isFirstIn = true;//是否第一次进入

    /**
     * 第一次进入请求数据
     */
    public void firstRequest() {
        if (isFirstIn) {
            loadData();
        }
        isFirstIn = false;
    }

    public abstract void loadData();

}
