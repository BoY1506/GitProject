package com.zhou.rongcloudtest.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zhou.rongcloudtest.R;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.Conversation;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * 会话界面
 * Created by zhou on 2016/8/25.
 */
public class ConversationActivity extends FragmentActivity implements RongIMClient.TypingStatusListener {

    @InjectView(R.id.title_tv)
    TextView titleTv;

    private static final int SET_TARGETID_TITLE = 0;//当前无输入
    private static final int SET_TEXT_TYPING_TITLE = 1;//正在文字输入
    private static final int SET_VOICE_TYPING_TITLE = 2;//正在语音输入

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_TARGETID_TITLE:
                    //无输入
                    titleTv.setText(mTitle);
                    break;
                case SET_TEXT_TYPING_TITLE:
                    //正在文字输入
                    titleTv.setText("对方正在输入...");
                    break;
                case SET_VOICE_TYPING_TITLE:
                    //正在语音输入
                    titleTv.setText("对方正在讲话...");
                    break;
            }
        }
    };

    /**
     * 目标 Id
     */
    private String mTargetId;

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    /**
     * 标题 title
     */
    private String mTitle;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        ButterKnife.inject(this);
        //初始化数据
        Intent intent = getIntent();
        getIntentDate(intent);
        //设置输入状态监听
        RongIMClient.setTypingStatusListener(this);
    }

    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {
        //获取当前会话id
        mTargetId = intent.getData().getQueryParameter("targetId");
        mTargetIds = intent.getData().getQueryParameter("targetIds");
        //intent.getData().getLastPathSegment();//获得当前会话类型
        mTitle = intent.getData().getQueryParameter("title");
        titleTv.setText(mTitle);
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
    }

    @Override
    public void onTypingStatusChanged(Conversation.ConversationType type, String targetId,
                                      Collection<TypingStatus> typingStatusSet) {
        //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
        if (type.equals(mConversationType) && targetId.equals(mTargetId)) {
            //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
            int count = typingStatusSet.size();
            if (count > 0) {
                Iterator iterator = typingStatusSet.iterator();
                TypingStatus status = (TypingStatus) iterator.next();
                String objectName = status.getTypingContentType();

                MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
                MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);

                //匹配对方正在输入的是文本消息还是语音消息
                if (objectName.equals(textTag.value())) {
                    //显示“对方正在输入”
                    mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
                } else if (objectName.equals(voiceTag.value())) {
                    //显示"对方正在讲话"
                    mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
                }
            } else {
                //当前会话没有用户正在输入，标题栏仍显示原来标题
                mHandler.sendEmptyMessage(SET_TARGETID_TITLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mConversationType == Conversation.ConversationType.CUSTOMER_SERVICE) {
            new AlertDialog.Builder(this)
                    .setMessage("确定退出客服界面？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ConversationFragment fragment = (ConversationFragment)
                                    getSupportFragmentManager().findFragmentById(R.id.conversation);
                            if (!fragment.onBackPressed()) {
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create().show();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

}