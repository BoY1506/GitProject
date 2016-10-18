package com.zhou.mvpapp.utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Call管理类
 * Created by zhou on 2016/7/28.
 */
public class CallManager {

    private static List<Call> callList;
    private static CallManager instance;

    private CallManager() {
        callList = new ArrayList<>();
    }

    /**
     * 单一实例
     */
    public static CallManager getInstance() {
        if (instance == null) {
            synchronized (CallManager.class) {
                if (instance == null) {
                    instance = new CallManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加call
     *
     * @param call
     * @return
     */
    public Call addCall(Call call) {
        if (call != null) {
            callList.add(call);
        }
        return call;
    }

    /**
     * 取消call，清空list
     */
    public void cancleCall() {
        for (Call call : callList) {
            call.cancel();
        }
        callList.clear();
    }

}
