package com.zhou.gitproject.utils;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * 文件工具类
 * Created by zhou on 2016/8/29.
 */
public class FileUtils {

    private static final String DefaultDirsFileName = "huabanDownoad";


    @NonNull
    public static File getDirsFile() {
        return getDirsFile(DefaultDirsFileName);
    }

    @NonNull
    public static File getDirsFile(String dirsFileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), dirsFileName);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static File writeResponseBodyToDisk(File file, ResponseBody body, String name) {

        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(file.getPath(), name);
//            Logger.d("isExternalStorageWritable=" + Utils.isExternalStorageWritable());
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    if (fileSizeDownloaded == 0) {
                        LogUtils.show("file download: " + fileSizeDownloaded + " of " + fileSize);
                    }

                    if (fileSizeDownloaded == fileSize) {
                        LogUtils.show("file download: " + fileSizeDownloaded + " of " + fileSize);
                    }
                }
                outputStream.flush();
                //所有流程操作完成 返回true
                return futureStudioIconFile;
            } catch (IOException e) {
                //捕捉到写入异常 返回false
                return null;
            } finally {
                //finally修饰的代码块 一定执行 关闭流
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            //捕捉到 文件的异常 返回false
            return null;
        }

    }
}
