package com.zhou.picselecttest.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.zhou.picselecttest.R;
import com.zhou.picselecttest.adapter.PicGridViewAdapter;
import com.zhou.picselecttest.utils.Constants;
import com.zhou.picselecttest.utils.PhotoUtils;
import com.zhou.picselecttest.utils.SDCardUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 图片选择第一种方式
 * 利用自己创建的文件来存储图片，格式为jpg，所获得的图片不在系统相册中
 * Created by zhou on 2016/7/9.
 */
public class FirstTypePicSelect extends Activity {

    @InjectView(R.id.pics_gv)
    GridView picsGv;

    //图片保存地址Uri
    private Uri imgUri;
    //接收照片的bitmap
    private Bitmap bm;

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
                    showAddPicDialog();
                } else {
                    //点击了某张图
                    Toast.makeText(FirstTypePicSelect.this, "点击了第" + (position + 1) + "张图", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 添加图片选择框
     */
    private void showAddPicDialog() {
        String title = "选择获取图片方式";
        String[] items = {"拍照", "相册"};
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = null;
                        switch (which) {
                            case 0:
                                //拍照
                                if (SDCardUtils.isSDCardExisting()) {
                                    try {
                                        //有SD卡
                                        //创建一个临时的file
                                        File tmpFile = new File(SDCardUtils.getSDCardPath(), "testPhoto.jpg");
                                        if (tmpFile.exists()) {
                                            tmpFile.delete();
                                        }
                                        tmpFile.createNewFile();
                                        Log.e("file路径？", tmpFile.getPath());
                                        Log.e("file全路径？", tmpFile.getAbsolutePath());
                                        //拿到文件的uri
                                        imgUri = Uri.fromFile(tmpFile);
                                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        //设置输出位置
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                                        startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(FirstTypePicSelect.this, "请插入SD卡", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 1:
                                //相册
                                intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE);
                                break;
                        }
                    }
                }).create().show();
    }

    /**
     * 处理inetnt回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //如果返回成功
            switch (requestCode) {
                case Constants.CAMERA_REQUEST_CODE:
                    //拍照
                    Uri picUri;
                    //拿到照片的is
                    //这里总是拿不到，暂停测试
                    Log.e("uri是否为空？", imgUri == null ? "是" : "否");
                    bm = PhotoUtils.getSmallBitmap(this, imgUri);
                    picUri = PhotoUtils.saveBitmap(bm, "testPhoto2.jpg");
                    adapter.addPic(picUri);
                    break;
                case Constants.GALLERY_REQUEST_CODE:
                    //相册
                    if (data != null) {
                        Uri uri = data.getData();
                        bm = PhotoUtils.getSmallBitmap(this, uri);
                        picUri = PhotoUtils.saveBitmap(bm, "testPhoto2.jpg");
                        adapter.addPic(picUri);
                    }
                    break;
            }
        }
    }

    //Activity销毁时回收bitmap
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bm != null) {
            if (!bm.isRecycled()) {
                bm.recycle();
                System.gc();
            }
        }
    }
}
