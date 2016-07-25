package com.zhou.gitproject.popfilter.github;

import android.os.Bundle;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;

import butterknife.ButterKnife;

/**
 * github上一个开源筛选控件
 * Created by zhou on 2016/7/19.
 */
public class GithubFilter extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_filter);
        ButterKnife.inject(this);
    }


}
