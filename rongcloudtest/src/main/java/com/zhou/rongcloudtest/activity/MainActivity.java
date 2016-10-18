package com.zhou.rongcloudtest.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.zhou.rongcloudtest.R;
import com.zhou.rongcloudtest.utils.AppConifg;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;

/**
 * 主界面
 * Created by zhou on 2016/8/25.
 */
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.start_conversation_btn)
    Button startConversationBtn;
    @InjectView(R.id.start_conversation_list_btn)
    Button startConversationListBtn;
    @InjectView(R.id.start_subconversation_list_btn)
    Button startSubconversationListBtn;
    @InjectView(R.id.start_custom_service_btn)
    Button startCustomServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.start_conversation_btn, R.id.start_conversation_list_btn, R.id.start_subconversation_list_btn,
            R.id.start_custom_service_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_conversation_btn:
                //启动会话界面
                if (RongIM.getInstance() != null) {
                    switch (AppConifg.USER_TYPE) {
                        case 1:
                            RongIM.getInstance().startPrivateChat(this, AppConifg.XIAO_USER_ID, AppConifg.XIAO_USER_NAME);
                            break;
                        case 2:
                            RongIM.getInstance().startPrivateChat(this, AppConifg.BOB_USER_ID, AppConifg.BOB_USER_NAME);
                            break;
                        case 3:
                            RongIM.getInstance().startPrivateChat(this, AppConifg.BOB_USER_ID, AppConifg.BOB_USER_NAME);
                            break;
                    }
                }
                break;
            case R.id.start_conversation_list_btn:
                //启动会话列表界面
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startConversationList(this);
                }
                break;
            case R.id.start_subconversation_list_btn:
                //启动聚合会话列表界面
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startSubConversationList(this, Conversation.ConversationType.GROUP);
                }
                break;
            case R.id.start_custom_service_btn:
                //打开客服界面
                if (RongIM.getInstance() != null) {
                    //首先需要构造使用客服者的用户信息
                    CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                    CSCustomServiceInfo csInfo = csBuilder.nickName(AppConifg.BOB_USER_NAME).build();
                    RongIM.getInstance().startCustomerServiceChat(this, "KEFU147218342199725", "优工客服", csInfo);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            new AlertDialog.Builder(this)
                    .setMessage("确定退出应用？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (RongIM.getInstance() != null) {
                                RongIM.getInstance().disconnect(true);
                                android.os.Process.killProcess(Process.myPid());
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create().show();
        }
        return false;
    }
}
