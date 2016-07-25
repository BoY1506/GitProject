package com.zhou.gitproject;

import android.app.Activity;
import android.widget.Toast;

import com.zhou.gitproject.utils.LogUtils;
import com.zhou.gitproject.utils.ToastUtils;

/**
 * activity基类
 * Created by zhou on 2016/7/12.
 */
public class BaseActivity extends Activity {

    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 打印Log
     *
     * @param msg
     */
    protected void showLog(String msg) {
        LogUtils.show("app", msg);
    }

}
