package com.zhou.gitproject.pdfreader;

import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Intent管理类
 * Created by zhou on 2016/9/23.
 */
public class IntentManager {

    /**
     * 获取打开一个HTML文件的Intent
     *
     * @param path
     * @return
     */
    public static Intent getHtmlFileIntent(String path) {
        Uri uri = Uri.parse(path).buildUpon().encodedAuthority("com.android.htmlfileprovider")
                .scheme("content").encodedPath(path).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    /**
     * 获取打开一个图片文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getImageFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    /**
     * 获取打开一个文本文件的intent
     *
     * @param path
     * @param isStrPath
     * @return
     */
    public static Intent getTextFileIntent(String path, boolean isStrPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isStrPath) {
            Uri uri1 = Uri.parse(path);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = Uri.fromFile(new File(path));
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    /**
     * 获取打开一个PDF文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getPdfFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    /**
     * 获取打开一个Word文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getWordFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    /**
     * 获取打开一个Excel文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getExcelFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    /**
     * 获取打开一个PPT文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getPptFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    /**
     * 获取打开一个音频文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getAudioFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    /**
     * 获取打开一个视频文件的intent
     *
     * @param path
     * @return
     */
    public static Intent getVideoFileIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

}
