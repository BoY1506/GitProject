package com.zhou.gitproject.appsplash;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.appsplash.adapter.GuidePagerAdapter;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * App引导页(4张图)
 * Created by zhou on 2016/7/27.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.guide_vp)
    ViewPager guideVp;
    @InjectView(R.id.guide_point1)
    ImageView guidePoint1;
    @InjectView(R.id.guide_point2)
    ImageView guidePoint2;
    @InjectView(R.id.guide_point3)
    ImageView guidePoint3;
    @InjectView(R.id.guide_point4)
    ImageView guidePoint4;

    private GuidePagerAdapter adapter;
    private List<View> views;

    private ImageView[] dots;//下方三个点
    private Button guideStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        ButterKnife.inject(this);
        StatusBarUtils.setImageBackground(this);
        //初始化控件
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //初始化view
        views = new ArrayList<>();
        views.add(UIUtils.inflate(this, R.layout.view_guide_1));
        views.add(UIUtils.inflate(this, R.layout.view_guide_2));
        views.add(UIUtils.inflate(this, R.layout.view_guide_3));
        views.add(UIUtils.inflate(this, R.layout.view_guide_4));
        adapter = new GuidePagerAdapter(views);
        guideVp.setAdapter(adapter);
        guideVp.setOnPageChangeListener(this);
        //初始化点
        initDots();
        //立即体验
        guideStartBtn = (Button) views.get(3).findViewById(R.id.start_btn);
        guideStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("打开app");
                finish();
            }
        });
    }

    /**
     * 初始化点
     */
    private void initDots() {
        dots = new ImageView[views.size()];
        dots[0] = guidePoint1;
        dots[1] = guidePoint2;
        dots[2] = guidePoint3;
        dots[3] = guidePoint4;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.mipmap.shuf_point_sel);
            } else {
                dots[i].setImageResource(R.mipmap.shuf_point_unsel);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
