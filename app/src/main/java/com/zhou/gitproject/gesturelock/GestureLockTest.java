package com.zhou.gitproject.gesturelock;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.gesturelock.view.GestureLockViewGroup;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 手势锁练习
 * Created by zhou on 2016/9/14.
 */
public class GestureLockTest extends BaseActivity {

    @InjectView(R.id.gesture_vg)
    GestureLockViewGroup gestureVg;

    String answer = "25879";

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            gestureVg.postReset();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesturelock_test);
        ButterKnife.inject(this);
        gestureVg.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {
            @Override
            public void onGetSelectNumbers(String numbers) {
                if (numbers.equals(answer)) {
                    showToast("答案正确");
                    handler.sendEmptyMessage(0);
                } else {
                    showToast("答案错误");
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }

            @Override
            public void onUnmatchedExceedBoundary() {
                showToast("次数用完！");
            }
        });
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

}
