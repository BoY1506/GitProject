package com.zhou.mvpapp.net;

import android.content.DialogInterface;

import com.zhou.mvpapp.base.IBaseView;
import com.zhou.mvpapp.utils.AppManager;
import com.zhou.mvpapp.utils.DialogUtils;
import com.zhou.mvpapp.utils.Logger;
import com.zhou.mvpapp.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 简化请求返回Callback
 * Created by zhou on 2016/9/1.
 */
public abstract class SimpleCallback<T> implements Callback<BaseResponse<T>> {

    /**
     * 请求成功
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        //返回成功
        onAllDone();
        //Http 状态码code:[200,300）
        if (response.isSuccessful() && response.errorBody() == null) {
            BaseResponse result = response.body();
            switch (result.getCode()) {
                case 200:
                    //返回成功
                    convert(result.getMsg(), (T) result.getData());
                    break;
                case 402:
                    //未登录
                    DialogUtils.loginDialog(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    break;
                case 311:
                    //服务器异常
                    ToastUtils.showShortToast("服务器繁忙，请重试");
                    break;
                default:
                    //返回失败,直接提示
                    ToastUtils.showShortToast(result.getMsg());
                    break;
            }
        } else {
            //返回失败
            Logger.show("error code" + response.code());
            Logger.show("error message" + response.message());
            ToastUtils.showShortToast("网络请求返回异常");
        }
    }

    /**
     * 请求失败
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
        if (!call.isCanceled()) {
            //请求失败
            onAllDone();
            ToastUtils.showShortToast("请检查网络连接是否正常");
        }
    }

    /**
     * 取消加载提示
     */
    public void onAllDone() {
        //隐藏加载控件
        ((IBaseView) AppManager.getInstance().getCurActivity()).hideLoadingViews();
    }

    /**
     * 提供给用户返回结果和实体
     *
     * @param msg
     * @param data
     */
    public abstract void convert(String msg, T data);

}
