package com.zhou.mvpapp.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.zhou.mvpapp.base.BaseApplication;

/**
 * Dialog工具类
 * Created by zhou on 2016/5/9.
 */
public class DialogUtils {

    /**
     * 普通文字提示框（无标题）
     *
     * @param context
     * @param content
     * @param clickListener
     */
    public static void commonDialog(Context context, String content, DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setMessage(content)
                .setPositiveButton("确认", clickListener)
                .setNegativeButton("取消", null)
                .create().show();
    }

    /**
     * 普通文字提示框（有标题）
     *
     * @param context
     * @param title
     * @param content
     * @param clickListener
     */
    public static void commonDialog(Context context, String title, String content, DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确认", clickListener)
                .setNegativeButton("取消", null)
                .create().show();
    }

    /**
     * 普通文字提示框（无标题）
     *
     * @param context
     * @param content
     * @param clickListener
     */
    public static void commonDialog2(Context context, String content, String proBtnTv,
                                     DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setMessage(content)
                .setPositiveButton(proBtnTv, clickListener)
                .setNegativeButton("取消", null)
                .create().show();
    }

    /**
     * 普通文字提示框（有标题）
     *
     * @param context
     * @param title
     * @param content
     * @param clickListener
     */
    public static void commonDialog2(Context context, String title, String content, String proBtnTv,
                                     DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(proBtnTv, clickListener)
                .setNegativeButton("取消", null)
                .create().show();
    }

    /**
     * 获取数据加载dialog
     *
     * @param context
     * @return
     */
    public static ProgressDialog loadingDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("请稍后...");
        dialog.setCancelable(true);
        return dialog;
    }

    /**
     * 重新登录dialog
     *
     * @param clickListener
     */
    public static void loginDialog(DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(BaseApplication.getAppContext())
                .setMessage("您的登录信息已过期，请重新登录。")
                .setPositiveButton("确认", clickListener)
                .setNegativeButton("取消", null)
                .create().show();
    }

    /**
     * 显示dialog
     */
    public static void showDialog(Dialog progressDialog) {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    /**
     * 取消dialog
     */
    public static void hideDialog(Dialog progressDialog) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
