package com.zhou.designmodetest.MVP.test2.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * MVPActivity父类
 * 处理公用逻辑
 * Created by zhou on 2016/8/3.
 */
public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends Activity {

    /**
     * 持有的presenter
     */
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    /**
     * 子类自己实例化presenter
     * 每一个子类new一个新的自己的presenter
     *
     * @return
     */
    protected abstract T initPresenter();

}
