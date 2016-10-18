package com.zhou.test.qrbar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhou.test.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 集成一片枫叶二维码练习
 * Created by zhou on 2016/9/23.
 */
public class QRcodeTest extends Activity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;
    @InjectView(R.id.bt4)
    Button bt4;
    @InjectView(R.id.bt5)
    Button bt5;
    @InjectView(R.id.result_iv)
    ImageView resultIv;

    public static final int REQUEST_CODE = 100;
    public static final int REQUEST_MYUI = 102;
    public static final int REQUEST_IMAGE = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_test);
        ButterKnife.inject(this);
        //初始化
        ZXingLibrary.initDisplayOpinion(this);
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Intent intent = new Intent(QRcodeTest.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.bt2:
                Intent intent2 = new Intent(QRcodeTest.this, MyLayoutUI.class);
                startActivityForResult(intent2, REQUEST_MYUI);
                break;
            case R.id.bt3:
                Intent intent3 = new Intent(Intent.ACTION_GET_CONTENT);
                intent3.addCategory(Intent.CATEGORY_OPENABLE);
                intent3.setType("image/*");
                startActivityForResult(intent3, REQUEST_IMAGE);
                break;
            case R.id.bt4:
                final EditText editText = new EditText(this);
                new AlertDialog.Builder(this)
                        .setView(editText)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = editText.getText().toString().toString();
                                if (TextUtils.isEmpty(str)) {
                                    Toast.makeText(QRcodeTest.this, "请输入内容", Toast.LENGTH_LONG).show();
                                } else {
                                    resultIv.setImageBitmap(CodeUtils.createImage(str, 400, 400, null));
                                }
                            }
                        }).create().show();
                break;
            case R.id.bt5:
                final EditText editText2 = new EditText(this);
                new AlertDialog.Builder(this)
                        .setView(editText2)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = editText2.getText().toString().toString();
                                if (TextUtils.isEmpty(str)) {
                                    Toast.makeText(QRcodeTest.this, "请输入内容", Toast.LENGTH_LONG).show();
                                } else {
                                    resultIv.setImageBitmap(CodeUtils.createImage(str, 400, 400,
                                            BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
                                }
                            }
                        }).create().show();
                break;
        }
    }

    /**
     * 处理二维码扫描结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(QRcodeTest.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        } else if (requestCode == REQUEST_MYUI) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(QRcodeTest.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        } else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                //ContentResolver cr = getContentResolver();
                try {
                    //显得到bitmap图片
                    // Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片
                    CodeUtils.analyzeBitmap(uri.getPath(), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(QRcodeTest.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(QRcodeTest.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                    //回收bitmap
                    //if (mBitmap != null) {
                    //    mBitmap.recycle();
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
