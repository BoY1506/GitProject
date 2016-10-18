package com.zhou.test.unreadmsg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.test.R;
import com.zhou.test.unreadmsg.view.BottomBarView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 未读消息数练习
 * Created by zhou on 2016/10/13.
 */
public class UnReadMsgTest extends Activity {

    @InjectView(R.id.add_msg_btn)
    Button addMsgBtn;
    @InjectView(R.id.read_msg_btn)
    Button readMsgBtn;
    @InjectView(R.id.bottombar_view)
    BottomBarView bottombarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unreadmsg_test);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.add_msg_btn, R.id.read_msg_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_msg_btn:
                bottombarView.addMsg();
                break;
            case R.id.read_msg_btn:
                bottombarView.setMessageCount(0);
                break;
        }
    }

}
