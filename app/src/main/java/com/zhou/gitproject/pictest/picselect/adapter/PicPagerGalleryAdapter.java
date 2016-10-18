package com.zhou.gitproject.pictest.picselect.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 本地图片大图浏览适配器
 * Created by zhou on 2015/12/28.
 */
public class PicPagerGalleryAdapter extends PagerAdapter {

    //上下文
    private Context context;
    //数据
    private List<Uri> data;

    public PicPagerGalleryAdapter(Context context, List<Uri> uriList) {
        this.context = context;
        this.data = uriList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = UIUtils.inflate2(context, R.layout.item_pics_pager, container);
        //设置图片，利用photoView库实现手势放大缩小
        PhotoView photoView = (PhotoView) view.findViewById(R.id.pic_pv);
        photoView.setImageURI(data.get(position));
        //单击退出
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPhotoTap(View view, float x, float y) {
                ((Activity) context).finish();
            }
        });
        // Now just add PhotoView to ViewPager and return it
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
