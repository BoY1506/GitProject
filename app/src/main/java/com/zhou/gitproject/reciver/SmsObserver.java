package com.zhou.gitproject.reciver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * 接收短信变化的observer
 * Created by zhou on 2016/7/8.
 */
public class SmsObserver extends ContentObserver {

    private Context mContext;
    private Handler mHandler;

    public SmsObserver(Context context, Handler handler) {
        super(handler);
        mContext = context;
        mHandler = handler;
    }

    /**
     * 当收件箱发生变化会回调这个方法
     *
     * @param selfChange
     * @param uri
     */
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        Log.e("URI", uri.toString());
        //获取收件箱Uri
        Uri inboxUri = Uri.parse("content://sms/inbox");
        Cursor c = mContext.getContentResolver().query(inboxUri, null, null, null, "date desc");
        if (c != null) {
            if (c.moveToNext()) {
                //发送方
                String address = c.getString(c.getColumnIndex("address"));
                //内容
                String body = c.getString(c.getColumnIndex("body"));
                Log.e("收到短信", "发件人为：" + address + " " + "短信内容为：" + body);
            }
            c.close();
        }
    }
}
