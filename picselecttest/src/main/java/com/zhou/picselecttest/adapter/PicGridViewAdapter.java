package com.zhou.picselecttest.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhou.picselecttest.R;

import java.util.List;

/**
 * 图片列表适配器，动态计算每个item的大小，适配大小不一的屏幕
 * Created by zhou on 2016/7/9.
 */
public class PicGridViewAdapter extends BaseAdapter {

    //上下文
    private Context context;
    //数据
    private List<Uri> data;
    //图片展示的gridView
    private GridView gridView;

    public PicGridViewAdapter(Context context, List<Uri> data, GridView gridView) {
        this.context = context;
        this.data = data;
        this.gridView = gridView;
    }

    @Override
    public int getCount() {
        //最后一张为添加按钮,如果图片满9张，添加按钮消失
        return data.size() == 9 ? data.size() : data.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        //点击已选图片才返回
        return position < data.size() ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 测量代码只有放在如下位置才会生效，经测试，在构造方法里或convertView=null时，获取不到itemWidth的值
     * getView方法会打印多次
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pics_gv, null);
            viewHolder = new ViewHolder();
            viewHolder.picIv = (ImageView) convertView.findViewById(R.id.pic_iv);
            viewHolder.picDeleteIv = (ImageView) convertView.findViewById(R.id.pic_delete_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //测量item的宽度
        int itemWidth = (gridView.getWidth() - gridView.getHorizontalSpacing() * 2 - gridView.getPaddingLeft() - gridView.getPaddingRight()) / 3;
        //为每个item设置大小
        viewHolder.picIv.setLayoutParams(new RelativeLayout.LayoutParams(itemWidth, itemWidth));
        //设置数据
        if (position < data.size()) {
            //已选图片赋值
            viewHolder.picIv.setImageURI(data.get(position));
            viewHolder.picDeleteIv.setVisibility(View.VISIBLE);
        } else {
            //添加图片按钮赋值
            viewHolder.picIv.setImageResource(R.mipmap.add_pic_icon);
            viewHolder.picDeleteIv.setVisibility(View.GONE);
        }
        viewHolder.picDeleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("确认删除图片？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                subPic(position);
                            }
                        }).create().show();
            }
        });
        return convertView;
    }

    /**
     * 添加图片
     */
    public void addPic(Uri uri) {
        data.add(uri);
        notifyDataSetChanged();
    }

    /**
     * 删除图片
     */
    public void subPic(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    /**
     * viewHolder
     */
    private static class ViewHolder {
        ImageView picIv;//图片
        ImageView picDeleteIv;//删除图标
    }

}
