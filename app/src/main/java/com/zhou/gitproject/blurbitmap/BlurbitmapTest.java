package com.zhou.gitproject.blurbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.blurbitmap.utils.BlurBitmap;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 快速图片高斯模糊处理
 * Created by zhou on 2016/8/18.
 */
public class BlurbitmapTest extends BaseActivity {

    @InjectView(R.id.activity_main_blured_img)
    ImageView activityMainBluredImg;
    @InjectView(R.id.activity_main_origin_img)
    ImageView activityMainOriginImg;
    @InjectView(R.id.activity_main_seekbar)
    SeekBar activityMainSeekbar;
    @InjectView(R.id.activity_main_progress_tv)
    TextView activityMainProgressTv;

    //透明度
    private int mAlpha;
    //原始图片
    private Bitmap mTempBitmap;
    //模糊后的图片
    private Bitmap mFinalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurbitmap_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor(this, Color.parseColor("#ff6600"));
        // 获取图片
        mTempBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mm);
        mFinalBitmap = BlurBitmap.blur(this, mTempBitmap);
        // 填充模糊后的图像和原图
        activityMainBluredImg.setImageBitmap(mFinalBitmap);
        activityMainOriginImg.setImageBitmap(mTempBitmap);
        // 处理seekbar滑动事件
        setSeekBar();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("高斯模糊");
    }

    /**
     * 处理seekbar滑动事件
     */
    private void setSeekBar() {
        activityMainSeekbar.setMax(100);
        activityMainSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                activityMainOriginImg.setAlpha((int) (255 - mAlpha * 2.55));
                activityMainProgressTv.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
