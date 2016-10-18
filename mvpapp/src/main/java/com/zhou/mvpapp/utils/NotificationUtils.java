package com.zhou.mvpapp.utils;

import android.app.Application;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;

import com.zhou.mvpapp.R;

/**
 * 通知处理类
 * Created by zhou on 2016/8/30.
 */
public class NotificationUtils {

    //默认的图标
    private static final int resId = R.mipmap.ic_launcher;

    /**
     * 默认使用应用图标的通知栏
     */
    public static Notification getNotification(Application application, String title, String text) {
        return getNotification(application, resId, title, text);
    }

    /**
     * 获取普通Notification
     */
    public static Notification getNotification(Application application, int resIdIcon, String title, String text) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(application)
                .setSmallIcon(resIdIcon)//图标
                .setContentTitle(title)//标题
                .setContentText(text)//正文描述
                .setTicker(text);//滚动文字
        return mBuilder.build();
    }

    /**
     * 默认使用应用图标的通知栏
     */
    public static Notification getProcessNotification(Application application, String title, int process) {
        return getProcessNotification(application, resId, title, process);
    }

    /**
     * 获取进度Notification
     */
    public static Notification getProcessNotification(Application application, int resIdIcon, String title, int process) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(application)
                .setSmallIcon(resIdIcon)//图标
                .setContentTitle(title)//标题
                .setProgress(100, process, false);
        return mBuilder.build();
    }

}
