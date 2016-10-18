package com.zhou.mvpapp.utils;

import java.io.File;

/**
 * App常量类
 * Created by zhou on 2016/9/1.
 */
public class Constants {

    public static final String APP_FILEDIR_NAME = "mvpapp";//App文件夹名称
    public static final String APP_FILEDIR_DIR = SDCardUtils.getSDCardPath() + APP_FILEDIR_NAME;//App文件夹路径
    public static final String APP_CACHE_DIR = APP_FILEDIR_DIR + File.separator + "cache";//app图片缓存目录

}
