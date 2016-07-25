package com.zhou.gitproject.picselect;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.picselect.adapter.FilePicGalleryAdapter;
import com.zhou.gitproject.picselect.view.HackyViewPager;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 显示图片浏览的界面(file类型，传图片uri)
 * Created by zhou on 2016/7/18.
 */
public class FilePicGallery extends BaseActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.gallery_pager)
    HackyViewPager galleryPager;
    @InjectView(R.id.gallery_tv)
    TextView galleryTv;

    private FilePicGalleryAdapter adapter;
    private List<Uri> picUriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_gallery);
        ButterKnife.inject(this);
        StatusBarUtils.setImageBackground(this);
        //初始化控件
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        //拿到图片和下标
        picUriList = getIntent().getParcelableArrayListExtra("picUriList");
        int index = getIntent().getIntExtra("picIndex", 1);
        //设置下标
        galleryTv.setText(index + " / " + picUriList.size());
        //设置数据
        adapter = new FilePicGalleryAdapter(this, picUriList);
        galleryPager.setAdapter(adapter);
        galleryPager.setCurrentItem(index - 1);
        galleryPager.setOnPageChangeListener(this);
    }

    /**
     * 图片改变监听
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        galleryTv.setText((position + 1) + " / " + picUriList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
