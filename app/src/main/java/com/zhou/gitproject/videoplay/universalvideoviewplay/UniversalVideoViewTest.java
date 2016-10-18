package com.zhou.gitproject.videoplay.universalvideoviewplay;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.utils.UIUtils;
import com.zhou.gitproject.videoplay.universalvideoviewplay.lib.UniversalMediaController;
import com.zhou.gitproject.videoplay.universalvideoviewplay.lib.UniversalVideoView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * UniversalVideoView练习
 * Created by zhou on 2016/9/22.
 */
public class UniversalVideoViewTest extends BaseActivity implements UniversalVideoView.VideoViewCallback {


    @InjectView(R.id.videoView)
    UniversalVideoView mVideoView;
    @InjectView(R.id.media_controller)
    UniversalMediaController mMediaController;
    @InjectView(R.id.video_layout)
    FrameLayout mVideoLayout;
    @InjectView(R.id.start)
    Button mStart;
    @InjectView(R.id.introduction)
    TextView introduction;
    @InjectView(R.id.bottom_layout)
    LinearLayout mBottomLayout;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";

    ActionBarBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universalvideoviewplay_test);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        this.builder = builder;
        builder.setAcBarLeftImg().setAcBarBigText("UniversalVideoView播放");
    }

    private void initView() {
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                showToast("播放完了");
            }
        });
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                cachedHeight = UIUtils.dp2px(UniversalVideoViewTest.this, 220f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("xxx", "onSaveInstanceState Position=" + mVideoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        Log.d("xxx", "onRestoreInstanceState Position=" + mSeekPosition);
    }

//    /**
//     * 横竖屏切换监听
//     *
//     * @param newConfig
//     */
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        //屏幕切换更新视图布局高度
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            //横屏
//            isFullscreen = true;
//        } else {
//            //竖屏
//            isFullscreen = false;
//        }
//    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        showLog("切换屏幕调用了！");
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.VISIBLE);
        }
        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
        if (show) {
            builder.setAcBarLeftImg().setAcBarBigText("UniversalVideoView播放");
        } else {
            builder.hideActionBar();
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d("xxx", "onPause UniversalVideoView callback");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d("xxx", "onStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d("xxx", "onBufferingStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d("xxx", "onBufferingEnd UniversalVideoView callback");
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.start)
    public void onClick() {
        if (mSeekPosition > 0) {
            mVideoView.seekTo(mSeekPosition);
        }
        mVideoView.start();
        mMediaController.setTitle("Big Buck Bunny");
    }
}
