package com.zhou.gitproject.pictest.piccrop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 基于uCrop框架的图片裁剪
 * Created by zhou on 2016/9/10.
 */
public class PicCrpoTest extends BaseActivity {

    @InjectView(R.id.camera_btn)
    Button cameraBtn;
    @InjectView(R.id.gallery_btn)
    Button galleryBtn;
    @InjectView(R.id.avatar_iv)
    ImageView avatarIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piccrop_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("设置头像");
    }

    @OnClick({R.id.camera_btn, R.id.gallery_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_btn:
                break;
            case R.id.gallery_btn:
                break;
        }
    }

}
