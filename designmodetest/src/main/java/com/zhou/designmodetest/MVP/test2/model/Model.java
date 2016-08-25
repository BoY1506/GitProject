package com.zhou.designmodetest.MVP.test2.model;

import com.zhou.designmodetest.MVP.test2.bean.User;

/**
 * 业务逻辑处理实现类
 * 用于处理复杂的数据操作如网络请求数据
 * Created by zhou on 2016/7/25.
 */
public class Model implements IModel {

    @Override
    public void getData(final ICallback callback) {
        //模拟网络请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    User user = new User("zhoubo");
                    callback.onResult(user.getUserName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 改进：model不处理线程问题
     */
    @Override
    public String getData() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ZB";
    }

}
