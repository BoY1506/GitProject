package com.zhou.gitproject.shufbanner;

import android.os.Bundle;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.shufbanner.view.ShufBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhou on 2016/7/23.
 */
public class ShufBannerTest extends BaseActivity {

    @InjectView(R.id.shuf_banner)
    ShufBanner shufBanner;

    private List<String> imgUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shufbanner_test);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        imgUrls.add("http://p2.so.qhimg.com/bdr/_240_/t015c33350ca273e11c.jpg");
        imgUrls.add("http://p2.so.qhimg.com/bdr/_240_/t01d1687ee991ac0bc2.png");
        imgUrls.add("http://p0.so.qhimg.com/t01fc821c3675338635.jpg");
        //启动轮播图
        shufBanner.startShufBanner(imgUrls);
        shufBanner.setOnBannerClcikListener(new ShufBanner.ShufBannerClickListener() {
            @Override
            public void onClick(int position) {
                showToast("第：" + position + "张图");
            }
        });
    }

}
