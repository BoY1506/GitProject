package com.zhou.mvpapp.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * SDcard相关工具类
 * Created by zhou on 2015/11/26.
 */
public class SDCardUtils {

    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardExisting() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡根路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的总容量
     *
     * @return
     */
    public static String getSDCardAllSize(Context context) {
        StatFs stat = new StatFs(getSDCardPath());
        long blockSize;
        long blockCount;
        //API18以下做适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // 获取单个数据块的大小（byte）
            blockSize = stat.getBlockSizeLong();
            //获取数据块的总数量
            blockCount = stat.getBlockCountLong();
        } else {
            blockSize = stat.getBlockSize();
            blockCount = stat.getBlockCount();
        }
        return Formatter.formatFileSize(context, blockSize * blockCount);
    }

    /**
     * 获取SD卡的剩余可用容量
     *
     * @return
     */
    public static String getSDCardAFreeSize(Context context) {
        StatFs stat = new StatFs(getSDCardPath());
        long blockSize;
        long freeBlockCount;
        //API18以下做适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // 获取单个数据块的大小（byte）
            blockSize = stat.getBlockSizeLong();
            //获取可用数据块的总数量
            freeBlockCount = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            freeBlockCount = stat.getAvailableBlocks();
        }
        return Formatter.formatFileSize(context, blockSize * freeBlockCount);
    }

}
