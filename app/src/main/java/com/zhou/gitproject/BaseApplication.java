package com.zhou.gitproject;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Application基类
 * Created by zhou on 2016/7/23.
 */
public class BaseApplication extends Application {

    //全局context
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化imageLoader配置
        initImageLoader();
    }

    /**
     * 初始化imageLoader配置
     */
    private void initImageLoader() {

        //图片参数默认配置
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)//设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在外存中
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片的缩放方式,按比例缩放
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();

        //imageloader参数配置
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)//图片默认参数配置
                .threadPoolSize(3) //线程池数量
                .threadPriority(Thread.NORM_PRIORITY - 2) //线程优先级
                .memoryCache(new WeakMemoryCache())//缓存使用弱引用
                .tasksProcessingOrder(QueueProcessingType.LIFO)// default
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))// connectTimeout (5 s), readTimeout (30 s)超时时间
                .build();

        //初始化imageLoader
        ImageLoader.getInstance().init(configuration);//初始化
    }

}
