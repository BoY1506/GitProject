package com.zhou.gitproject.smscode.reciver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.zhou.gitproject.smscode.SmsCodeTest;
import com.zhou.gitproject.utils.LogUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 监听短信变化的observer
 * Created by zhou on 2016/7/12.
 */
public class SmsObserver extends ContentObserver {

    private Context context;
    private Handler handler;


    public SmsObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
        this.handler = handler;
    }

    /**
     * 当系统收件箱发生变化会调用
     *
     * @param selfChange
     * @param uri
     */
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

        Log.e("xxxx", "!!!!!!!!!!");
        LogUtils.show("拿到了uri", uri.toString());

        Uri inboxUri = Uri.parse("content://sms/inbox");
        //查询收件箱，按时间倒序
        Cursor cursor = context.getContentResolver().query(inboxUri, null, null, null, "date desc");
        if (cursor.moveToFirst()) {
            String from = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndex("body"));

            LogUtils.show("短信内容", "发件人为：" + from + " " + "短信内容为：" + body);

            //正则匹配连续的6为数字
            Pattern pattern = Pattern.compile("(\\d{6})");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                String code = matcher.group(0);
                LogUtils.show("匹配到了数字", code);
                handler.obtainMessage(SmsCodeTest.MSG_RECEIVED_CODE, code).sendToTarget();
            }
        }
        cursor.close();
    }

}
