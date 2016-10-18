package com.zhou.mvpapp.net;

import com.zhou.mvpapp.utils.Logger;
import com.zhou.mvpapp.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件下载Callback
 * Created by zhou on 2016/9/1.
 */
public abstract class FileCallback implements Callback<ResponseBody> {

    //目标文件存储的文件夹路径
    private String fileDir;
    //目标文件存储的文件名
    private String fileName;

    public FileCallback(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //返回成功
        //Http 状态码code:[200,300）
        if (response.isSuccessful() && response.errorBody() == null) {
            saveFile(response.body());
        } else {
            //返回失败
            Logger.show("error code" + response.code());
            Logger.show("error message" + response.message());
            ToastUtils.showShortToast("网络请求返回异常");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        //请求失败
        ToastUtils.showShortToast("请检查网络连接是否正常");
    }

    /**
     * 将文件写入磁盘
     *
     * @param body
     */
    private void saveFile(ResponseBody body) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            long total = body.contentLength();
            long sum = 0;
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                inProgress(sum * 1.0f / total, total);
            }
            fos.flush();
            //下载完毕
            onSuccess(file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            body.close();
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将下载进度进行回传
     * 此时不在主线程内
     *
     * @param progress
     * @param totle
     */
    public abstract void inProgress(float progress, long totle);

    /**
     * 下载成功
     *
     * @param file
     */
    public abstract void onSuccess(File file);

}
