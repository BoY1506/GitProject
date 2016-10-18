package com.zhou.mvpapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhou.mvpapp.R;
import com.zhou.mvpapp.utils.ActionBarBuilder;
import com.zhou.mvpapp.utils.AppManager;
import com.zhou.mvpapp.utils.DialogUtils;
import com.zhou.mvpapp.utils.Logger;
import com.zhou.mvpapp.utils.StatusBarUtils;
import com.zhou.mvpapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * activity基类
 * Created by zhou on 2016/8/31.
 */
public abstract class BaseActivity<T extends IBasePresenter> extends AutoLayoutActivity implements IBaseView {

    //activity引用
    protected Context mContext;
    //主布局容器
    protected LinearLayout baseLayoutLl;
    //presenter对象
    protected T presenter;

    //加载进度条
    protected ProgressDialog loadingDialog;
    //加载relativeLayout
    protected RelativeLayout loadingRelativeLayout;

    public static final int LOADING_VIEW_DIALOG = 1;//加载类型dialog
    public static final int LOADING_VIEW_RELATIVELAYOUT = 2;//加载类型relativeLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置沉浸式状态栏
        StatusBarUtils.setColor(this, Color.parseColor("#FF6600"));
        // 添加Activity到管理栈
        AppManager.getInstance().addActivity(this);
        //初始化presenter
        presenter = initPresenter();
        //绑定presenter
        attachIVeiw();
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
        return new ActionBarBuilder(this, R.id.mactionbar);
    }

    /**
     * 显示吐司
     */
    @Override
    public void showToast(String msg) {
        ToastUtils.showShortToast(msg);
    }

    /**
     * 打印Log
     */
    @Override
    public void showLog(String msg) {
        Logger.show(msg);
    }

    /**
     * 获取Bundle参数
     */
    @Override
    public Bundle getComeBundle() {
        return getIntent().getBundleExtra("bundle");
    }

    /**
     * 初始化loading控件
     */
    @Override
    public void initLoadingView() {

    }

    /**
     * 显示加载控件
     */
    @Override
    public void showLoadingViews() {
        if (loadingDialog != null) {
            DialogUtils.showDialog(loadingDialog);
        }
        if (loadingRelativeLayout != null) {
            loadingRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示加载控件(按类型)
     */
    @Override
    public void showLoadingViews(int type) {
        switch (type) {
            case LOADING_VIEW_DIALOG:
                //显示加载dialog
                if (loadingDialog != null) {
                    DialogUtils.showDialog(loadingDialog);
                }
                break;
            case LOADING_VIEW_RELATIVELAYOUT:
                //显示加载relativeLayout
                if (loadingRelativeLayout != null) {
                    loadingRelativeLayout.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    /**
     * 隐藏加载控件
     */
    @Override
    public void hideLoadingViews() {
        if (loadingDialog != null) {
            DialogUtils.hideDialog(loadingDialog);
        }
        if (loadingRelativeLayout != null) {
            loadingRelativeLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 绑定视图
     * 由于在activity的onCreate方法里就要进行网络请求
     * 所以这个方法要放到onCreate里
     */
    protected void attachIVeiw() {
        if (presenter != null) {
            //绑定视图
            presenter.attachView(mContext, this);
        }
    }

    /**
     * 关闭activity
     * 解绑视图，移除堆栈
     */
    @Override
    protected void onDestroy() {
        if (presenter != null) {
            //解绑视图
            presenter.detachView();
        }
        //结束Activity，从管理栈中移除
        AppManager.getInstance().finishActivity(this);
        super.onDestroy();
    }

    /**
     * 初始化ActionBar
     *
     * @param builder
     */
    public abstract void initActionBar(ActionBarBuilder builder);

    /**
     * 创建presenter
     */
    public abstract T initPresenter();

}
