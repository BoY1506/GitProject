package com.zhou.gitproject.picselect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.picselect.adapter.PicAddAdapter;
import com.zhou.gitproject.picselect.utils.PhotoUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 图片处理
 * Created by zhou on 2016/7/18.
 */
public class PicSelectTest extends BaseActivity {

    @InjectView(R.id.pic_gv)
    GridView picGv;

    //画廊适配器
    private PicAddAdapter adapter;
    //用于传递照片list
    private ArrayList<Uri> picUriList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picselect_test);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        adapter = new PicAddAdapter(this, picUriList, picGv);
        picGv.setAdapter(adapter);
        picGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == picUriList.size()) {
                    //如果点击加号，弹出选择照片对话框
                    PhotoUtils.showImgSelDialog(PicSelectTest.this);
                } else {
                    Intent intent = new Intent(PicSelectTest.this, FilePicGallery.class);
                    intent.putParcelableArrayListExtra("picUriList", picUriList);
                    intent.putExtra("picIndex", position + 1);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * activity返回处理
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            return;
        } else {
            switch (requestCode) {
                case PhotoUtils.CAMERA_REQUEST_CODE:
                    //拍照
                    adapter.addPic(PhotoUtils.saveBitmap(PhotoUtils.getFixSmallBitmap(this, null), PhotoUtils.getPicName()));
                    break;
                case PhotoUtils.GALLERY_REQUEST_CODE:
                    //相册
                    if (data == null) {
                        return;
                    } else {
                        Uri uri = data.getData();
                        adapter.addPic(PhotoUtils.saveBitmap(PhotoUtils.getFixSmallBitmap(this, uri), PhotoUtils.getPicName()));
                    }
                    break;
            }
        }
    }

}
