package com.zhou.gitproject.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.barcode.zxing.activity.CaptureActivity;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 二维码扫描练习
 * Created by zhou on 2016/7/29.
 */
public class QRcodeTest extends BaseActivity {

    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick(R.id.btn)
    public void onClick() {
        Intent startScan = new Intent(QRcodeTest.this, CaptureActivity.class);
        startActivityForResult(startScan, 0);
        overridePendingTransition(R.anim.anim_activity_in, R.anim.anim_activity_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            text.setText(result);
        }
    }

}
