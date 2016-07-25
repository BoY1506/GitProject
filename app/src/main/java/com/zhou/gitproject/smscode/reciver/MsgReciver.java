package com.zhou.gitproject.smscode.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.zhou.gitproject.utils.LogUtils;

/**
 * 自定义短信接收器reciver
 * Created by zhou on 2016/7/12.
 */
public class MsgReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.show("收到了短息没啊！！", "sss!!!");
        //获取数据
        Bundle bundle = intent.getExtras();
        //提取pdus数组
        Object[] pdus = (Object[]) bundle.get("pdus");
        //构建message数组
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < messages.length; i++) {
            //利用SmsMessage.createFromPdu()方法
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        //获取对方发送号码
        String from = messages[0].getOriginatingAddress();
        StringBuilder builder = new StringBuilder();
        for (SmsMessage msg : messages) {
            //短信可能由多条组成
            builder.append(msg.getMessageBody());
        }
        LogUtils.show("短息发送方", from);
        LogUtils.show("短信内容", builder.toString());
    }

}
