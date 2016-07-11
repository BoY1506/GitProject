package com.zhou.gitproject.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhou.gitproject.R;
import com.zhou.gitproject.reciver.SmsObserver;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 短信练习，监听短信接收，发送短信
 * Created by zhou on 2016/7/8.
 */
public class MsgTest extends Activity {

    @InjectView(R.id.content_et)
    EditText contentEt;
    @InjectView(R.id.send_to__et)
    EditText sendToEt;
    @InjectView(R.id.send_btn)
    Button sendBtn;

    //短信接收的reciverFilter
    private IntentFilter reciverFilter;
    //短信接收的reciver
    private MyMsgReciver myMsgReciver;

    //短信发送得sendFilter
    private IntentFilter sendFilter;
    //短信发送状态的reciver
    private SendStatusReciver sendStatusReciver;

    //短信接收监听器observer
    private SmsObserver smsObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_test);
        ButterKnife.inject(this);
        initViewAndEvent();
    }

    /**
     * 初始化view和事件
     */
    private void initViewAndEvent() {
        //短信发送filter
        sendFilter = new IntentFilter("SENT_SMS_ACTION");
        //短信发送reciver
        sendStatusReciver = new SendStatusReciver();
        registerReceiver(sendStatusReciver, sendFilter);

        //短信接收filter
        reciverFilter = new IntentFilter("android.provider.Telephony.SMS_RECIVED");
        myMsgReciver = new MyMsgReciver();
        //注册短信接收reciver
        registerReceiver(myMsgReciver, reciverFilter);

//        //短信接收oberver
//        smsObserver = new SmsObserver(this, null);
//        Uri uri = Uri.parse("content://sms");
//        //注册监听器
//        getContentResolver().registerContentObserver(uri, true, smsObserver);
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.send_btn)
    public void onClick() {
        //利用SmsManger发送短信
        SmsManager manager = SmsManager.getDefault();
        //创建一个intent
        Intent intent = new Intent("SENT_SMS_ACTION");
        //以broadCast的形式发出
        PendingIntent pi = PendingIntent.getBroadcast(MsgTest.this, 0, intent, 0);
        manager.sendTextMessage(sendToEt.getText().toString().trim(), null, contentEt.getText().toString().trim(), pi, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销监听
        unregisterReceiver(sendStatusReciver);
        unregisterReceiver(myMsgReciver);
//        getContentResolver().unregisterContentObserver(smsObserver);
    }

    /**
     * 用于监听短信发送状态的reciver
     */
    private class SendStatusReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK) {
                //发送成功
                Toast.makeText(MsgTest.this, "短信发送成功", Toast.LENGTH_SHORT).show();
            } else {
                //发送失败
                Toast.makeText(MsgTest.this, "短信发送失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 用于监听短信接收的Reciver
     */
    private class MyMsgReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //获取数据bundle
            Bundle bundle = intent.getExtras();
            //提取短信信息
            Object[] pdus = (Object[]) bundle.get("pdus");
            //构建message数组
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                //利用SmsMessage.createFromPdu()方法
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            //获取发送方号码
            String address = messages[0].getOriginatingAddress();
            StringBuilder fullMsg = new StringBuilder();
            for (SmsMessage msg : messages) {
                //获取短息内容
                fullMsg.append(msg.getMessageBody());
            }
            Log.e("短息发送方", address);
            Log.e("短息内容", fullMsg.toString());
        }
    }

}
