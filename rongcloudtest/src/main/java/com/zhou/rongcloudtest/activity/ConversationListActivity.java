package com.zhou.rongcloudtest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zhou.rongcloudtest.R;

/**
 * 会话列表界面
 * Created by zhou on 2016/8/25.
 */
public class ConversationListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);
        init();
    }

    /**
     * 一些初始化设置
     */
    private void init() {
//        ConversationListFragment fragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);
//        //拼接Uri: sheme(rong://) + host(packageName) + path(converssationlist) + parameter
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                .appendPath("conversationlist")
//                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true")
//                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
//                .build();
//        fragment.setUri(uri);
    }

}
