package com.zhou.mvpapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhou.mvpapp.utils.DialogUtils;
import com.zhou.mvpapp.utils.Logger;
import com.zhou.mvpapp.utils.ToastUtils;

/**
 * Fragment基类
 * Created by zhou on 2016/8/31.
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {

    //activity引用
    protected Context mContext;
    //presenter对象
    protected T presenter;

    //加载进度条
    protected ProgressDialog loadingDialog;
    //加载relativeLayout
    protected RelativeLayout loadingRelativeLayout;

    public static final int LOADING_VIEW_DIALOG = 1;//加载类型dialog
    public static final int LOADING_VIEW_RELATIVELAYOUT = 2;//加载类型relativeLayout

    //是否隐藏
    private static final String IS_HIDDEN = "IS_HIDDEN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //解决同级Fragment重叠问题
            boolean isSupportHidden = savedInstanceState.getBoolean(IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        mContext = getActivity();
        //初始化presenter
        presenter = initPresenter();
        //绑定presenter
        attachIVeiw();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
     * 获取bundle参数
     */
    @Override
    public Bundle getComeBundle() {
        return getArguments().getBundle("bundle");
    }

    /**
     * 初始化loading控件
     */
    @Override
    public void initLoadingView() {

    }

    /**
     * 显示加载控件(需要子类自己调用)
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
     * 显示加载控件(按类型)(需要子类自己调用)
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
     * 隐藏加载控件(需要子类自己调用)
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
     * 所以这个方法要放在onCreate里
     */
    protected void attachIVeiw() {
        if (presenter != null) {
            //绑定视图
            presenter.attachView(mContext, this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(IS_HIDDEN, isHidden());
    }

    /**
     * 关闭fragment
     * 解绑视图
     */
    @Override
    public void onDestroy() {
        if (presenter != null) {
            //解绑视图
            presenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 创建presenter
     */
    public abstract T initPresenter();

}
