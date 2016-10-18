package com.zhou.mvpapp.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;

import com.zhou.mvpapp.adapter.recyclerview.base.ItemViewDelegate;
import com.zhou.mvpapp.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

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
    protected abstract void convert(ViewHolder holder, T t, int position);

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
