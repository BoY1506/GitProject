package com.zhou.rongcloudtest.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.zhou.rongcloudtest.App;
import com.zhou.rongcloudtest.R;
import com.zhou.rongcloudtest.utils.AppConifg;
import com.zhou.rongcloudtest.utils.ContactsProvider;
import com.zhou.rongcloudtest.utils.MyTextMessageItemProvider;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.FileInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.LocationInputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.message.FileMessage;
import io.rong.message.LocationMessage;

/**
 * 登录界面
 * 连接成功前后进行一些列初始化配置
 * Created by zhou on 2016/8/25.
 */
public class LoginActivity extends AppCompatActivity implements RongIM.UserInfoProvider, RongIM.LocationProvider {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);
        initRongIMConfig();
    }

    /**
     * 一系列初始化配置
     */
    private void initRongIMConfig() {
        //方法一：
        //设置用户信息提供者
        RongIM.setUserInfoProvider(this, true);
        //设置会话消息item模板
        RongIM.getInstance().registerMessageTemplate(new MyTextMessageItemProvider());
        //设置位置消息提供者
        RongIM.setLocationProvider(this);
        //设置会话括展privoder
        RongIM.registerMessageType(FileMessage.class);
        RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, getContactsProvider());
    }

    /**
     * 设置拓展功能
     */
    private InputProvider.ExtendProvider[] getContactsProvider() {
        //扩展功能自定义
        InputProvider.ExtendProvider[] providers = {
                //语音和视频通话要收费
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//相机
                new FileInputProvider(RongContext.getInstance()),// 文件消息
                new LocationInputProvider(RongContext.getInstance()),//地理位置
                new ContactsProvider(RongContext.getInstance())//自定义通讯录
        };
        return providers;
    }

    @OnClick(R.id.login_btn)
    public void onClick() {
        switch (AppConifg.USER_TYPE) {
            case 1:
                connect(AppConifg.BOB_TOKEN);
                break;
            case 2:
                connect(AppConifg.KANG_TOKEN);
                break;
            case 3:
                connect(AppConifg.XIAO_TOKEN);
                break;
        }
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }

//        //方法二：
//        //设置当前用户信息
//        if (RongIM.getInstance() != null) {
//            switch (AppConifg.USER_TYPE) {
//                case 1:
//                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(AppConifg.BOB_USER_ID,
//                            AppConifg.BOB_USER_NAME, Uri.parse(AppConifg.BOB_USER_ORTRAIT_URI)));
//                    break;
//                case 2:
//                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(AppConifg.KANG_USER_ID,
//                            AppConifg.KANG_USER_NAME, Uri.parse(AppConifg.KANG_USER_ORTRAIT_URI)));
//                    break;
//                case 3:
//                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(AppConifg.XIAO_USER_ID,
//                            AppConifg.XIAO_USER_NAME, Uri.parse(AppConifg.XIAO_USER_ORTRAIT_URI)));
//                    break;
//            }
//            //设置消息体内是否携带用户信息。
//            RongIM.getInstance().setMessageAttachedUserInfo(true);
//        }
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        //在这里获取用户信息（可从Server端获取，然后刷新）
//        RongIM.getInstance().refreshUserInfoCache(new UserInfo("userId", "userName", Uri.parse("avatar.png")));
        switch (userId) {
            case AppConifg.BOB_USER_ID:
                return new UserInfo(AppConifg.BOB_USER_ID, AppConifg.BOB_USER_NAME, Uri.parse(AppConifg.BOB_USER_ORTRAIT_URI));
            case AppConifg.KANG_USER_ID:
                return new UserInfo(AppConifg.KANG_USER_ID, AppConifg.KANG_USER_NAME, Uri.parse(AppConifg.KANG_USER_ORTRAIT_URI));
            case AppConifg.XIAO_USER_ID:
                return new UserInfo(AppConifg.XIAO_USER_ID, AppConifg.XIAO_USER_NAME, Uri.parse(AppConifg.XIAO_USER_ORTRAIT_URI));
        }
        return null;
    }

    @Override
    public void onStartLocation(Context context, LocationCallback locationCallback) {
        //在这里打开你的地图页面,保存 locationCallback 对象
        Uri uri = Uri.parse("http://api.map.baidu.com/staticimage?center=116.403874,39.914889&width=400&height=300&zoom=11&markers=116.383177,39.923743&markerStyles=m,A");
        LocationMessage locationMessage = LocationMessage.obtain(116.403874, 39.914888, "中国北京", uri);
        //如果地图地位成功，那么调用
        locationCallback.onSuccess(locationMessage);
//        //如果地图地位失败，那么调用
//        locationCallback.onFailure("定位失败!");
    }

}
