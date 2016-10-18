package com.zhou.gitproject.videoplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.videoplay.universalvideoviewplay.UniversalVideoViewTest;
import com.zhou.gitproject.videoplay.videoviewplay.VideoViewPlayTest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 视频播放练习
 * Created by zhou on 2016/9/22.
 */
public class VideoPlayTest extends BaseActivity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
//                Uri uri = Uri.parse(SDCardUtils.getSDCardPath() + "vketang/video/编写木马程序.wmv");
                Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                //调用系统自带的播放器
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "video/wmv");
                startActivity(intent);
                break;
            case R.id.bt2:
                intent2Activity(VideoViewPlayTest.class);
                break;
            case R.id.bt3:
                intent2Activity(UniversalVideoViewTest.class);
                break;
        }
    }
}
