package com.zhou.picselecttest.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片压缩工具类
 * Created by zhou on 2016/7/9.
 */
public class PhotoUtils {

    /**
     * 将Bitmap转化为文件类型的Uri并保存(针对普通照片)
     * 质量压缩，用于压缩文件大小，便于用户上传
     *
     * @param bm
     * @param fileName
     * @return
     */
    public static Uri saveBitmap(Bitmap bm, String fileName) {
        File img = new File(SDCardUtils.getSDCardPath(), fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //从80%开始
        int options = 80;
        //将bitmap写入boas
        bm.compress(Bitmap.CompressFormat.JPEG, options, baos);
        //将图片质量压缩到100k一下
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bm.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        //将输出流baos中的内容写入到文件中
        try {
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
     * 将Bitmap转化为文件类型的Uri并保存(针对头像，不压缩)
     * 由于裁剪时已经指定图片宽高的像素数（300*300）因此不必在压缩
     *
     * @param bm
     * @return
     */
    public static Uri saveBitmap4Avatar(Bitmap bm, String fileName) {
        File img = new File(SDCardUtils.getSDCardPath(), fileName);
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
     * 指定压缩到原图的1/16
     */
    public static Bitmap getSmallBitmap(Context context, Uri uri) {
        InputStream is = null;
        try {
            Log.e("uri？", uri.toString());
            Log.e("uri路径？", uri.getPath());
            is = context.getContentResolver().openInputStream(uri);
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;//将照片像素缩小1/16
            options.inJustDecodeBounds = false;
            Bitmap bm = BitmapFactory.decodeStream(is, null, options);
            Log.e("压缩过的bm是否为空？", bm == null ? "是" : "否");
            if (bm == null) {
                return null;
            }
            return bm;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 根据屏幕分辨率,压缩图片
     * 像素压缩，用于图片加载到内存并显示
     *
     * @param context
     * @param is
     * @return
     */
    public static Bitmap getSmallBitmap(Activity context, InputStream is) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds设为true
        options.inJustDecodeBounds = true;
        //此时得到的bitmap为空，但是可以获取其宽和高
        Bitmap bm = BitmapFactory.decodeStream(is, null, options);
        //-----------------------测试一下--------------------------
        Log.e("是否为空", bm == null ? "是的" : "不是");
        Log.e("是否获取到宽高", "宽度：" + options.outWidth + "，高度：" + options.outHeight);
        //----------------------------------------------------------
        //获取bm的实际宽
        int w = options.outWidth;
        //获取bm的实际高
        int h = options.outHeight;
        //获取手机的分辨率
        int ww = UIUtils.getScreenWidthPixels(context);
        int hh = UIUtils.getScreenHeightPixels(context);
        //设置缩放比例
        options.inSampleSize = calculateSamSize(w, h, ww, hh);
        //将inJustDecodeBounds设回false
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * 计算压缩比
     *
     * @return
     */
    private static int calculateSamSize(int w, int h, int ww, int hh) {
        //-----------------------------------
        Log.e("四个值", w + ", " + h + ", " + ww + ", " + hh);
        //-----------------------------------
        //be=1表示不缩放
        int be = 1;
        if (w > h && w > ww) {
            //如果宽度大，则根据宽度缩放
            be = w / ww;
        } else if (w < h && h > hh) {
            //如果高度大，则根据高度缩放
            be = h / hh;
        }
        if (be <= 0) {
            be = 1;
        }
        Log.e("计算完了？be=", be + "");
        return be;
    }

    /**
     * 计算压缩比2
     *
     * @return
     */
    private static int calculateSamSize2(int w, int h, int ww, int hh) {

        //-----------------------------------
        Log.e("四个值", w + ", " + h + ", " + ww + ", " + hh);
        //-----------------------------------

        //计算缩放比例
        int be = 1;
        int scaleWidth = w / ww;
        int scaleHeight = h / hh;
        if (scaleWidth >= scaleHeight && scaleWidth >= 1) {
            be = scaleWidth;
        } else if (scaleWidth < scaleHeight && scaleHeight >= 1) {
            be = scaleHeight;
        }
        Log.e("计算完了？be=", be + "");
        return be;
    }

}
