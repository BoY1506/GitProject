package com.zhou.mvpapp.utils;

import java.io.File;

/**
 * 文件工具类
 * Created by zhou on 2016/8/29.
 */
public class FileUtils {

    /**
     * 检查文件夹是否存在
     *
     * @param filePath
     * @return
     */
    public static File getFileDir(String filePath) {
        //App根文件夹
        File appRootDir = new File(Constants.APP_FILEDIR_DIR);
        if (!appRootDir.exists()) {
            appRootDir.mkdir();
        }
        //目标文件夹
        File aimFile = new File(filePath);
        if (!aimFile.exists()) {
            //检查目标文件夹
            aimFile.mkdir();
        }
        return aimFile;
    }

}
