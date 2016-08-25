package com.zhou.gitproject.appupdate;

import android.os.Bundle;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.appupdate.utils.UpdateManager;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * App检测更新
 * Created by zhou on 2016/8/19.
 */
public class AppUpdateTest extends BaseActivity {

    @InjectView(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appupdate_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick(R.id.bt)
    public void onClick() {
        UpdateManager manager = new UpdateManager(this);
        manager.checkUpdate();
    }

}
