package com.zhou.gitproject.refreshlistview.ultralv;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * UItraLIstView默认实现效果
 * Created by zhou on 2016/7/20.
 */
public class UltraLvTest extends BaseActivity {

    @InjectView(R.id.ultra_refresh_frame)
    FrameLayout ultraRefreshFrame;
    @InjectView(R.id.ultra_ptr_frame)
    PtrClassicFrameLayout ultraPtrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultralv_test);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化，设置一些配置
     */
    private void initView() {
        ultraPtrFrame.setLastUpdateTimeRelateObject(this);
        //下拉刷新的阻力，下拉时，下拉距离和显示头部的距离比例，值越大，则越不容易滑动
        ultraPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        //下拉刷新的阻力，下拉时，下拉距离和显示头部的距离比例，值越大，则越不容易滑动
        ultraPtrFrame.setDurationToClose(200);
        //关闭头部的时间 default is false
        ultraPtrFrame.setDurationToCloseHeader(500);
        //当下拉到一定距离时，自动刷新（true），显示释放以刷新（false）
        ultraPtrFrame.setPullToRefresh(false);
        //见名只意
        ultraPtrFrame.setKeepHeaderWhenRefresh(true);
        //刷新事件
        ultraPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view1);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                //数据刷新的回调
                ultraPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ultraPtrFrame.refreshComplete();
                        showToast("刷新成功");
                    }
                }, 1500);
            }
        });
    }

}
