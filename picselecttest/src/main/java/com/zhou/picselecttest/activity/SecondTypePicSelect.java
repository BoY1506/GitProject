package com.zhou.picselecttest.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.zhou.picselecttest.R;
import com.zhou.picselecttest.adapter.PicGridViewAdapter;
import com.zhou.picselecttest.utils.Constants;
import com.zhou.picselecttest.utils.ImageUtils;
import com.zhou.picselecttest.utils.PhotoUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 第二种方式
 * 将得到的图片存储于系统相册中
 * Created by zhou on 2016/7/10.
 */
public class SecondTypePicSelect extends Activity {

    @InjectView(R.id.pics_gv)
    GridView picsGv;

    //图片数据list
    private ArrayList<Uri> picUriList;
    //图片gridView的adapter
    private PicGridViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pics_list);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        picUriList = new ArrayList<>();
        adapter = new PicGridViewAdapter(this, picUriList, picsGv);
        //设置适配器
        picsGv.setAdapter(adapter);
        //添加点击事件
        picsGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == picUriList.size()) {
                    //点击了添加照片按钮
                    ImageUtils.showImagePickDialog(SecondTypePicSelect.this);
                } else {
                    //点击了某张图
                    Toast.makeText(SecondTypePicSelect.this, "点击了第" + (position + 1) + "张图", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 处理inetnt回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_CANCELED) {
                    ImageUtils.deleteImageUri(this, ImageUtils.imgUri);
                } else {
                    Uri imageUriCamera = ImageUtils.imgUri;
                    Bitmap bm = PhotoUtils.getSmallBitmap(this, imageUriCamera);
                    adapter.addPic(PhotoUtils.saveBitmap(bm, "testPhoto2.jpeg"));
                }
                break;
            case Constants.GALLERY_REQUEST_CODE:
                if (resultCode == RESULT_CANCELED) {
                    return;
                }
                Uri imageUri = data.getData();
                Bitmap bm = PhotoUtils.getSmallBitmap(this, imageUri);
                adapter.addPic(PhotoUtils.saveBitmap(bm, "testPhoto2.jpeg"));
                break;

        }
    }

}
