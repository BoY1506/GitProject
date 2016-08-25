package com.zhou.designmodetest.MVP.test2.presenter;

import android.os.Handler;
import android.os.Looper;

import com.zhou.designmodetest.MVP.test2.base.BasePresenter;
import com.zhou.designmodetest.MVP.test2.model.IModel;
import com.zhou.designmodetest.MVP.test2.model.Model;
import com.zhou.designmodetest.MVP.test2.view.IView;

/**
 * 控制器实现类
 * 持有View和Model，来控制数据获取和显示，自身也可处理数据
 * Created by zhou on 2016/7/25.
 */
public class Presenter extends BasePresenter<IView> {

    //持有View与Model
    private IModel iModel;

    private Handler handler;

    public Presenter() {
        iModel = new Model();
        handler = new Handler(Looper.getMainLooper());
    }

//    public void performOnClick() {
//        //从model中获取数据，在view里显示
//        mView.showLoading();
//        iModel.getData(new IModel.ICallback() {
//            @Override
//            public void onResult(String data) {
//                //presenter不仅控制model和view，也可对数据进行进一步加工处理
//                //view只负责怎么渲染，但presenter可以控制如何渲染
//                String str = "这个人的名字是：" + data;
//                mView.setData(str);
//                mView.hideLoading();
//            }
//        });
//    }

    /**
     * 改进：presenter控制线程操作
     */
    public void performOnClick() {
        mView.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String str = iModel.getData();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.setData(str);
                        mView.hideLoading();
                    }
                });
            }
        }).start();
    }

}
