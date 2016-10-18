package com.zhou.gitproject.pictest.picselect;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.pictest.picselect.adapter.PicPagerGalleryAdapter;
import com.zhou.gitproject.pictest.picselect.view.HackyViewPager;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.UIUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 显示图片浏览的界面(file类型，传图片uri)
 * Created by zhou on 2016/7/18.
 */
public class FilePicGallery extends Activity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.gallery_pager)
    HackyViewPager galleryPager;
    @InjectView(R.id.gallery_bottom_fl)
    FrameLayout galleryBottomFl;
    @InjectView(R.id.gallery_index_point)
    LinearLayout galleryIndexPoint;
    @InjectView(R.id.gallery_index_tv)
    TextView galleryIndexTv;

    private PicPagerGalleryAdapter adapter;
    private List<Uri> picUriList;

    private int indexType;
    private int lastSelectedIndex;
    private ImageView[] pointArray;

    public static final int INDEX_TYPE_POINT = 1;//显示点下标
    public static final int INDEX_TYPE_TEXT = 2;//显示文字下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_gallery);
        ButterKnife.inject(this);
        //透明化状态栏
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
        indexType = getIntent().getIntExtra("picIndexType", INDEX_TYPE_POINT);
        lastSelectedIndex = getIntent().getIntExtra("picIndex", 0);
        //设置下标
        if (picUriList.size() == 1) {
            //只有一张图片
            galleryBottomFl.setVisibility(View.GONE);
        } else {
            //多张图片
            switch (indexType) {
                case INDEX_TYPE_POINT:
                    //点下标
                    galleryIndexPoint.setVisibility(View.VISIBLE);
                    //生成点
                    createImageViewPoint(lastSelectedIndex);
                    break;
                case INDEX_TYPE_TEXT:
                    //文字下标
                    galleryIndexTv.setVisibility(View.VISIBLE);
                    galleryIndexTv.setText((lastSelectedIndex + 1) + " / " + picUriList.size());
                    break;
            }
        }
        //设置数据
        adapter = new PicPagerGalleryAdapter(this, picUriList);
        galleryPager.setAdapter(adapter);
        galleryPager.setCurrentItem(lastSelectedIndex);
        galleryPager.setOnPageChangeListener(this);
    }

    /**
     * 根据图片数量生成相应的点
     */
    private void createImageViewPoint(int position) {
        pointArray = new ImageView[picUriList.size()];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int pointPadding = UIUtils.dp2px(this, 3);
        params.setMargins(pointPadding, 0, pointPadding, 0);
        for (int i = 0; i < pointArray.length; i++) {
            ImageView point = new ImageView(this);
            point.setLayoutParams(params);
            point.setImageResource(R.drawable.icon_point_sel);
            if (i == position) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointArray[i] = point;
            galleryIndexPoint.addView(point);
        }
    }

    /**
     * 图片改变监听
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (indexType) {
            case INDEX_TYPE_POINT:
                //改变下标点
                pointArray[lastSelectedIndex].setEnabled(false);
                pointArray[position].setEnabled(true);
                lastSelectedIndex = position;
                break;
            case INDEX_TYPE_TEXT:
                //改变下标文字
                galleryIndexTv.setText((position + 1) + " / " + picUriList.size());
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
