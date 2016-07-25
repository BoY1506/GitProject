package com.zhou.gitproject.utils;

import android.os.Environment;

import java.io.File;

/**
 * SDcard相关工具类
 * Created by zhou on 2015/11/26.
 */
public class SDCardUtils {

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardExisting() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory() + File.separator;
    }

}
