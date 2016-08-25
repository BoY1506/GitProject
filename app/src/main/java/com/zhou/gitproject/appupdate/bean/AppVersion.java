package com.zhou.gitproject.appupdate.bean;

/**
 * AppVerson实体类
 * Created by zhou on 2016/8/19.
 */
public class AppVersion {

    private String mVersionCode;
    private String mVersionName;
    private String mVersionDesc;
    private String mVersionPath;

    public String getmVersionCode() {
        return mVersionCode;
    }

    public void setmVersionCode(String mVersionCode) {
        this.mVersionCode = mVersionCode;
    }

    public String getmVersionName() {
        return mVersionName;
    }

    public void setmVersionName(String mVersionName) {
        this.mVersionName = mVersionName;
    }

    public String getmVersionDesc() {
        return mVersionDesc;
    }

    public void setmVersionDesc(String mVersionDesc) {
        this.mVersionDesc = mVersionDesc;
    }

    public String getmVersionPath() {
        return mVersionPath;
    }

    public void setmVersionPath(String mVersionPath) {
        this.mVersionPath = mVersionPath;
    }

}
