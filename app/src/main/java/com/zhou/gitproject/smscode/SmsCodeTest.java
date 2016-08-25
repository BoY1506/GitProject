package com.zhou.gitproject.smscode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.smscode.reciver.SmsObserver;
import com.zhou.gitproject.smscode.utils.TimeCounter;
import com.zhou.gitproject.smscode.view.BtnWrapper;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 短信验证码倒计时+自动回填
 * 倒计时：
 * 方法一：CountDownTimer类实现
 * 方法二：属性动画valueAnimator实现
 * 短信监听：
 * 方法一：利用系统reciver实现
 * 方法二：利用ovserver实现
 * Created by zhou on 2016/7/12.
 */
public class SmsCodeTest extends BaseActivity {

    @InjectView(R.id.code_et)
    EditText codeEt;
    @InjectView(R.id.get_code_btn)
    Button getCodeBtn;

    public static final int MSG_RECEIVED_CODE = 1;

    private TimeCounter timer;//计时器

    private ObjectAnimator animator;//属性动画

//    private MsgReciver msgReciver;//短信接收器reciver

    private SmsObserver smsObserver;//短信接收observer

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_RECEIVED_CODE) {
                String code = (String) msg.obj;
                if (!TextUtils.isEmpty(code)) {
                    codeEt.setText(code);
                    codeEt.setSelection(code.length());
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smscode_test);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    /**
     * 初始化
     */
    private void initView() {
//        timer = new TimeCounter(10000, 1000, getCodeBtn);
        animator = createAnimator();

//        //注册短信接收监听器
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        filter.setPriority(Integer.MAX_VALUE);
//        msgReciver = new MsgReciver();
//        registerReceiver(msgReciver, filter);

        //注册短信接收observer
        smsObserver = new SmsObserver(this, handler);
        Uri uri = Uri.parse("content://sms/");
        getContentResolver().registerContentObserver(uri, true, smsObserver);
    }

    @OnClick(R.id.get_code_btn)
    public void onClick() {
//        //开启计时器
//        timer.start();
//        showToast("开始计时");
        //开启动画
        animator.start();
    }

    /**
     * 创建动画
     *
     * @return
     */
    private ObjectAnimator createAnimator() {
        ObjectAnimator anim = ObjectAnimator.ofInt(new BtnWrapper(getCodeBtn), "time", 10, 0).setDuration(10 * 1000);
        //匀速变化
        anim.setInterpolator(new LinearInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始时调用，拿到btn
                BtnWrapper wrapper = (BtnWrapper) ((ObjectAnimator) animation).getTarget();
                wrapper.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束时调用，拿到btn
                BtnWrapper wrapper = (BtnWrapper) ((ObjectAnimator) animation).getTarget();
                wrapper.setClickable(true);
                wrapper.setTimeEndText("获取验证码");
            }
        });
        return anim;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(msgReciver);
        getContentResolver().unregisterContentObserver(smsObserver);
    }

}
