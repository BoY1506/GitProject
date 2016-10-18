package com.zhou.rongcloudtest.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.zhou.rongcloudtest.R;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * 自定义通讯录拓展功能提供者
 * Created by zhou on 2016/8/26.
 */
public class ContactsProvider extends InputProvider.ExtendProvider {

    HandlerThread mWorkThread;
    Handler mUploadHandler;
    private int REQUEST_CONTACT = 20;

    public ContactsProvider(RongContext context) {
        super(context);
        mWorkThread = new HandlerThread("RongCloudTest");
        mWorkThread.start();
        mUploadHandler = new Handler(mWorkThread.getLooper());
    }

    /**
     * 设置展示的图标
     *
     * @param context
     * @return
     */
    @Override
    public Drawable obtainPluginDrawable(Context context) {
        //R.drawable.de_contacts 通讯录图标
        return context.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    /**
     * 设置图标下的title
     *
     * @param context
     * @return
     */
    @Override
    public CharSequence obtainPluginTitle(Context context) {
        //R.string.add_contacts 通讯录
        return context.getString(R.string.add_contacts);
    }

    /**
     * click 事件
     *
     * @param view
     */
    @Override
    public void onPluginClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK)
            return;

        if (data.getData() != null && "content".equals(data.getData().getScheme())) {
            mUploadHandler.post(new MyRunnable(data.getData()));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class MyRunnable implements Runnable {

        Uri mUri;

        public MyRunnable(Uri uri) {
            mUri = uri;
        }

        @Override
        public void run() {
            String[] contact = getPhoneContacts(mUri);
            //获取姓名+手机号
            String showMessage = contact[0] + "\n" + contact[1];
            //构建消息
            TextMessage content = TextMessage.obtain(showMessage);
            Message myMessage = Message.obtain(getCurrentConversation().getTargetId(), getCurrentConversation().getConversationType(), content);
            //发送消息
            if (RongIM.getInstance() != null) {
                RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {
                        //消息本地数据库存储成功的回调
                        Log.e("xxx", "通讯录消息本地数据库存储成功的回调");
                    }

                    @Override
                    public void onSuccess(Message message) {
                        //消息通过网络发送成功的回调
                        Log.e("xxx", "通讯录消息网络发送成功");
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                        //消息发送失败的回调
                        Log.e("xxx", "通讯录消息网络发送失败");
                    }
                });
            }
        }
    }

    /**
     * 获取联系人信息
     *
     * @param uri
     * @return
     */
    private String[] getPhoneContacts(Uri uri) {

        String[] contact = new String[2];
        ContentResolver cr = getContext().getContentResolver();
        Cursor cursor = cr.query(uri, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);

            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);

            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        }
        return contact;
    }

}
