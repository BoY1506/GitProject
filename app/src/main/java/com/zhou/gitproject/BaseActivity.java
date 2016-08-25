package com.zhou.gitproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.utils.AppManager;
import com.zhou.gitproject.utils.LogUtils;
import com.zhou.gitproject.utils.ToastUtils;

/**
 * activity基类
 * Created by zhou on 2016/7/12.
 */
public abstract class BaseActivity extends ActionBarActivity {

    //activity引用
    protected Activity mContext;
    //主布局容器
    protected LinearLayout baseLayoutLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //强制竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        //设置沉浸式状态栏
//        StatusBarUtils.setColor(mContext, Color.RED);
        // 添加Activity到管理栈
        AppManager.getAppManager().addActivity(mContext);
//        //注册EventBus
//        EventBus.getDefault.register(this);
    }

    /**
     * 设置公共布局
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        ViewGroup baseView = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_base_layout, null);
        baseLayoutLl = (LinearLayout) baseView.findViewById(R.id.base_layout_ll);
        super.setContentView(baseView);
        View childView = getLayoutInflater().inflate(layoutResID, baseView, false);
        if (baseLayoutLl == null) {
            showLog("父布局为空啊！！！");
        }
        baseLayoutLl.addView(childView);
        //初始化ActionBar
        initActionBar(getActionBarBuilder());
    }

    /**
     * 获取ActionBarBuilder
     *
     * @return view
     */
    protected ActionBarBuilder getActionBarBuilder() {
        return new ActionBarBuilder(mContext, R.id.mactionbar);
    }

    /**
     * 封装Intent简单跳转（不含参数）
     *
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }

    /**
     * 封装Intent简单跳转（含参数）
     *
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity, Bundle bundle) {
        Intent intent = new Intent(this, tarActivity);
        intent.putExtra("bundleData", bundle);
        startActivity(intent);
    }

    /**
     * 封装Intent带返回值的跳转（不含参数）
     *
     * @param tarActivity
     * @param requestCode
     */
    protected void intent2Activity4Result(Class<? extends Activity> tarActivity, int requestCode) {
        Intent intent = new Intent(this, tarActivity);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 封装Intent带返回值的跳转（含参数）
     *
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, tarActivity);
        intent.putExtra("bundleData", bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 打印Log
     *
     * @param msg
     */
    protected void showLog(String msg) {
        LogUtils.show(msg);
    }

//     /**
//     * 主线程event事件
//     *
//     * @param event
//     */
//    public void onEventMainThread(BaseEvent event) {
//        //...do something...
//    }

    /**
     * Activity销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        //反注册EventBus
//        EventBus.getDefault.unregister(this);
        //结束Activity，从管理栈中移除
        AppManager.getAppManager().finishActivity(mContext);
    }

    /**
     * 初始化ActionBar
     *
     * @param builder
     */
    public abstract void initActionBar(ActionBarBuilder builder);

}
