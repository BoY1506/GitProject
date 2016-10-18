package com.zhou.mvpapp.adapter.abslistview;

import android.content.Context;

import com.zhou.mvpapp.adapter.abslistview.base.ItemViewDelegate;

import java.util.List;

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    /**
     * 公布一个放法传入viewHolder和t让用户自己操作
     */
    protected abstract void convert(ViewHolder viewHolder, T item, int position);

    /**
     * 更新数据
     */
    public void setData(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void setRefresh(List<T> list) {
        this.mDatas.clear();
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     */
    public void addAll(List<T> list) {
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

}
