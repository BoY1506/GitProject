package com.zhou.gitproject.appupdate.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.zhou.gitproject.appupdate.bean.AppVersion;
import com.zhou.gitproject.utils.GsonUtils;
import com.zhou.gitproject.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

/**
 * 软件更新工具类
 * Created by zhou on 2016/8/19.
 */
public class UpdateManager {

    private Context mContext;

    //新版本
    private AppVersion mAppVersion;
    //下载dialog
    private ProgressDialog updateDialog;
    //下载地址
    private File downloadDir;
    //下载的文件
    private File apk;

    //检测更新地址
    private static final String PATH = "http://xxx.xxx.xx/autoupdate/version.html";

    public UpdateManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 检测软件是否需要更新
     */
    public void checkUpdate() {
        OkHttpUtils
                .get()
                .url(PATH)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        ToastUtils.showToast(mContext, "网络连接失败", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        mAppVersion = GsonUtils.getBean(s, AppVersion.class);
                        if (isNeedUpdate()) {
                            //需要更新
                            showUpdateDialog();
                        } else {
                            ToastUtils.showToast(mContext, "当前已是最新版本", Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    /**
     * 检查是否需要更新
     */
    private boolean isNeedUpdate() {
        int serverVersion = Integer.parseInt(mAppVersion.getmVersionCode());
        int localVersion = 1;
        try {
            localVersion = mContext.getPackageManager().getPackageInfo("com.zhou.gitproject", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return serverVersion > localVersion;
    }

    /**
     * 显示更新dialog
     */
    private void showUpdateDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle("发现新版本")
                .setMessage(mAppVersion.getmVersionDesc())
                .setNegativeButton("下次再说", null)
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startUpdate();
                    }
                })
                .create().show();
    }

    /**
     * 开始更新
     */
    private void startUpdate() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            downloadDir = new File(Environment.getExternalStorageDirectory() + File.separator + "newapk");
            if (!downloadDir.exists()) {
                downloadDir.mkdir();
            }
            updateDialog = new ProgressDialog(mContext);
            updateDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            updateDialog.setMax(100);
            updateDialog.setProgress(0);
            updateDialog.show();
            downloadApk();
        } else {
            ToastUtils.showToast(mContext, "请插入内存卡", Toast.LENGTH_SHORT);
        }
    }


    /**
     * 下载apk
     */
    private void downloadApk() {
        OkHttpUtils
                .get()
                .url(mAppVersion.getmVersionPath())
                .build()
                .execute(new FileCallBack(downloadDir.getAbsolutePath(), mAppVersion.getmVersionName()) {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        ToastUtils.showToast(mContext, "网络连接失败", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onResponse(File file, int i) {
                        apk = file;
                        updateDialog.dismiss();
                        installAPK();
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        updateDialog.setProgress((int) progress);
                    }
                });
    }

    /**
     * 安装apk
     */
    private void installAPK() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + apk.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

}
