package com.zhou.gitproject.appsplash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * APP启动页和引导页
 * 启动页优化：
 * 1.尽量使用动画让用户视觉感更好
 * 2.网络请求不论完不完成，3s后进行跳转，若提前完成网络请求，则提前跳转也行
 * Created by zhou on 2016/7/27.
 */
public class SplashActivity extends BaseActivity {

    @InjectView(R.id.time_tv)
    TextView timeTv;
    @InjectView(R.id.skip_ll)
    LinearLayout skipLl;

    private static final int TIMING = 1000;//计时中

    private int seconds = 3;//默认计时3s

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIMING:
                    //计时中
                    timeTv.setText(--seconds + "S");
                    if (seconds > 0) {
                        //大于0s，继续计时
                        handler.sendEmptyMessageDelayed(TIMING, 1000);
                    } else {
                        //计时结束
                        checkIsFirstIn();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        StatusBarUtils.setImageBackground(this);
        handler.sendEmptyMessageDelayed(TIMING, 1000);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    /**
     * 是否第一次进入
     */
    private void checkIsFirstIn() {
//        //将状态存储到sharedPreferences里
//        boolean isFirstIn = (boolean) SPUtils.get(this, "isFirstIn", true);
//        if (isFirstIn) {
//            //第一次使用
//            SPUtils.put(this, "isFirstIn", false);
//            goGuide();
//        } else {
//            //不是第一次
//            goHome();
//        }
        goGuide();
    }

    /**
     * 去引导页
     */
    public void goGuide() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 去首页
     */
    public void goHome() {
        showToast("进入app");
        finish();
    }

    @OnClick(R.id.skip_ll)
    public void onClick() {
        handler.removeMessages(TIMING);
        checkIsFirstIn();
    }

    /**
     * 启动页时屏蔽物理返回按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
