package com.zhou.gitproject.pictest.picselect.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.zhou.gitproject.utils.SDCardUtils;
import com.zhou.gitproject.utils.ToastUtils;
import com.zhou.gitproject.utils.UIUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 照片处理工具类
 * 还需修改（太卡，大图会内存溢出）
 * Created by zhou on 2015/11/26.
 */
public class PhotoUtils {

    //请求码
    public static final int CAMERA_REQUEST_CODE = 101;//拍照
    public static final int GALLERY_REQUEST_CODE = 102;//相册
    public static final int CROP_REQUEST_CODE = 103;//裁剪
    //测试路径
    public static final String TEST_DIR = SDCardUtils.getSDCardPath() + "pic_select";

    //临时图片存储uri
    private static Uri imgUri = null;

    /**
     * 显示图片选择框
     *
     * @param context
     */
    public static void showImgSelDialog(final Activity context) {
        String title = "选择获取图片方式";
        String[] items = new String[]{"拍照", "相册"};
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        switch (which) {
                            case 0:
                                if (SDCardUtils.isSDCardExisting()) {
                                    //SD卡可用
                                    File tmpDir = new File(TEST_DIR);
                                    if (!tmpDir.exists()) {
                                        tmpDir.mkdir();
                                    }
                                    //照片的临时存储路径uri
                                    imgUri = Uri.fromFile(new File(tmpDir, "temphoto.jpg"));
                                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                                    context.startActivityForResult(intent, CAMERA_REQUEST_CODE);
                                } else {
                                    ToastUtils.showToast(context, "请插入SD卡", Toast.LENGTH_SHORT);
                                }
                                break;
                            case 1:
                                intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                context.startActivityForResult(intent, GALLERY_REQUEST_CODE);
                                break;
                        }
                    }
                }).create().show();
    }

    /**
     * 将Bitmap转化为文件类型的Uri并保存(针对普通照片)
     * 质量压缩，便于上传服务器
     *
     * @param bm
     * @return
     */
    public static Uri saveBitmap(Bitmap bm, String fileName) {
        File tmpDir = new File(TEST_DIR);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir, fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//从80开始
        bm.compress(Bitmap.CompressFormat.JPEG, options, baos);
        //将图片质量压缩到100k一下
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bm.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            //将输出流boas中的内容写入到文件中
            FileOutputStream fos = new FileOutputStream(img);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Bitmap转化为文件类型的Uri并保存(针对头像)
     * 因为头像是裁剪过了的，已指定了一个较小的像素值
     * 因此不需要再二次压缩
     *
     * @param bm
     * @return
     */
    public static Uri saveBitmap4Avatar(Bitmap bm, String fileName) {
        File tmpDir = new File(TEST_DIR);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 压缩图片
     * 像素压缩，便于加载到内存中进行展示
     */
    public static Bitmap getFixSmallBitmap(Activity context, Uri uri) {
        try {
            if (uri == null) {
                //拍照
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;//将照片像素缩小1/16
                return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imgUri), null, options);
            } else {
                //相册
                File file = new File(uri.getPath());
                Log.e("app看看file？", file.getAbsolutePath());
                if (file.length() / 1024 > 100) {
                    //如果文件大于100KB，就压缩
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;//将照片像素缩小1/16
                    return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
                } else {
                    return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 压缩图片
     * 像素压缩，便于加载到内存中进行展示
     */
    public static Bitmap getSmallBitmap(Activity context, Uri uri) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds设为true
            options.inJustDecodeBounds = true;
            //此时得到的bitmap为空，但是可以获取其宽和高
            Bitmap bm = null;
            if (uri == null) {
                //拍照
                bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imgUri), null, options);
            } else {
                //相册
                bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            }
            //-----------------------测试一下--------------------------
            Log.e("true下的bitmap是否为空？", bm == null ? "是的！！" : "不是！！");
            Log.e("options是否获取到宽高？", "宽度：" + options.outWidth + "，高度：" + options.outHeight);
            //----------------------------------------------------------
            //获取bm的实际宽
            int bmWidth = options.outWidth;
            //获取bm的实际高
            int bmHeight = options.outHeight;
            //获取手机的分辨率
            int screenWidth = UIUtils.getScreenWidthPixels(context);
            int screenHeight = UIUtils.getScreenHeightPixels(context);
            //计算缩放比例
            options.inSampleSize = calculateSamSize(bmWidth, bmHeight, screenWidth, screenHeight);
            //将inJustDecodeBounds设回false
            options.inJustDecodeBounds = false;
            if (uri == null) {
                //拍照
                bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imgUri), null, options);
            } else {
                //相册
                bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            }
            Log.e("false下的bitmap是否为空？", bm == null ? "是的！！" : "不是！！");
            return bm;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算压缩比
     *
     * @return
     */
    private static int calculateSamSize(int bmWidth, int bmHeight, int screenWidth, int screenHeight) {
        //-----------------------------------
        Log.e("app四个值？", bmWidth + ", " + bmHeight + ", " + screenWidth + ", " + screenHeight);
        //-----------------------------------
        //be=1表示不缩放
        int inSample = 1;
        //先缩小25%再比较，避免过度压缩
        int scaleBmWidth = (int) (bmWidth * 0.75);
        int scaleBmHeight = (int) (bmHeight * 0.75);
        while ((scaleBmWidth / inSample) >= screenWidth || (scaleBmHeight / inSample) >= screenHeight) {
            inSample *= 4;
        }
        Log.e("计算完了？be=", inSample + "");
        return inSample;
    }

    /**
     * 获取临时照片名称(当前时间)
     */
    public static String getPicName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date) + ".jpg";
    }

}
