package com.zhou.gitproject.viewpagerload.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 懒加载fragment
 * 通过视图标志位 + 数据标志位 +userVisiable控制加载
 * 稍微有点延迟
 * Created by zhou on 2016/8/16.
 */
public abstract class LazyLoadFragment extends Fragment {

    /**
     * 控件是否加载完毕
     */
    private boolean isViewCreate;

    /**
     * 数据是否加载完毕
     */
    private boolean isLoadDataCompleted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isViewCreate = true;
        return null;
    }

    /**
     * 第一个fragment以外懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreate && !isLoadDataCompleted) {
            loadData();
            isLoadDataCompleted = true;
        }
    }

    /**
     * 第一个fragment自动请求数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            loadData();
            isLoadDataCompleted = true;
        }
    }

    /**
     * 用于让子类重写
     */
    public abstract void loadData();

}
