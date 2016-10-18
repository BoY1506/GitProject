package com.zhou.mvpapp.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.activeandroid.ActiveAndroid;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.zhou.mvpapp.utils.Constants;
import com.zhou.mvpapp.utils.FileUtils;
import com.zhou.mvpapp.utils.SDCardUtils;

import java.io.File;

/**
 * Application基类
 * Created by zhou on 2016/8/31.
 */
public class BaseApplication extends Application {

    //全局context
    public static Context appContext;
    //App文件夹缓存路径
    public File picCacheDir;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        //初始化文件夹
        initAppFile();
        //初始化屏幕适配
        initAutoLayout();
        //初始化Fresco配置
        initFresco();
        //初始化ActiveAndorid数据库
        initDatabase();
    }

    /**
     * 获取全局Context
     *
     * @return
     */
    public static Context getAppContext() {
        return appContext;
    }

    /**
     * 初始化文件夹
     */
    private void initAppFile() {
        //创建外存缓存路径
        if (SDCardUtils.isSDCardExisting()) {
            //有内存卡且可用
            if (FileUtils.getFileDir(Constants.APP_CACHE_DIR).exists()) {
                //创建App图片缓存路径
                picCacheDir = new File(Constants.APP_CACHE_DIR);
            }
        } else {
            //无内存卡，存入系统文件夹
            picCacheDir = getFilesDir();
        }
    }

    /**
     * 初始化屏幕适配
     * 默认使用的高度是设备的可用高度，也就是不包括状态栏和底部的操作栏的
     * 如果你希望拿设备的物理高度进行百分比化，进行此设置
     */
    private void initAutoLayout() {
        //AutoLayoutConifg.getInstance().useDeviceSize();
    }

    /**
     * 初始化Fresco配置
     */
    private void initFresco() {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(appContext)
                .setBaseDirectoryPath(picCacheDir)
                .setMaxCacheSize(50 * 1024 * 1024)
                .build();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(appContext)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setDownsampleEnabled(true)//Downsampling，它处理图片的速度比常规的裁剪更快，
                        // 并且同时支持PNG，JPG以及WEP格式的图片，非常强大,与ResizeOptions配合使用
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .build();
        Fresco.initialize(appContext, config);
    }

    /**
     * 初始化数据库
     */
    private void initDatabase() {
        ActiveAndroid.initialize(appContext);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //结束数据库
        ActiveAndroid.dispose();
    }

}
