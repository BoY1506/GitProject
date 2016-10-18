package com.zhou.gitproject.videoplay.videoviewplay;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 普通的videoView播放
 * Created by zhou on 2016/9/22.
 */
public class VideoViewPlayTest extends BaseActivity {

    @InjectView(R.id.vv)
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoviewplay_test);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("ViewoView播放");
    }

    private void initView() {
//        Uri uri = Uri.parse(SDCardUtils.getSDCardPath() + "vketang/video/编写木马程序.wmv");
        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        vv.setMediaController(new MediaController(this));
        vv.setVideoURI(uri);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                showToast("准备完毕");
            }
        });
        vv.requestFocus();
        vv.start();
    }

}
