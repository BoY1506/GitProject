package com.zhou.designmodetest.MVP.test2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhou.designmodetest.MVP.test2.base.BaseMvpActivity;
import com.zhou.designmodetest.MVP.test2.presenter.Presenter;
import com.zhou.designmodetest.MVP.test2.view.IView;
import com.zhou.designmodetest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 逻辑：点击按钮加载数据显示到textView上
 * View只接受点击事件，和拿到数据后处理显示
 * Created by zhou on 2016/7/25.
 */
public class ShowTextActivity extends BaseMvpActivity<IView, Presenter> implements IView {

    @InjectView(R.id.show_btn)
    Button showBtn;
    @InjectView(R.id.show_tv)
    TextView showTv;
    @InjectView(R.id.loading_pb)
    ProgressBar loadingPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        ButterKnife.inject(this);
    }

    /**
     * 创建自己的presenter
     *
     * @return
     */
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @OnClick(R.id.show_btn)
    public void onClick() {
        //让presenter去处理操作
        mPresenter.performOnClick();
    }

    /**
     * 设置请求到的数据
     *
     * @param data
     */
    @Override
    public void setData(String data) {
        showTv.setText(data);
    }

    @Override
    public void showLoading() {
        loadingPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingPb.setVisibility(View.GONE);
    }

}
