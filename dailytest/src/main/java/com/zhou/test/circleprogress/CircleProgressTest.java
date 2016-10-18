package com.zhou.test.circleprogress;

import android.app.Activity;
import android.os.Bundle;

import com.zhou.test.R;
import com.zhou.test.circleprogress.View.CircleProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 自定义圆形进度条
 * Created by zhou on 2016/10/13.
 */
public class CircleProgressTest extends Activity {

    @InjectView(R.id.circle_pb)
    CircleProgressBar circlePb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circleprogress_test);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        startProgress();
    }

    private void startProgress() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                float currentProgress = circlePb.getCurrentProgress();
                ++currentProgress;
                circlePb.setProgress(currentProgress);
                try {
                    sleep(50);
                    if (currentProgress <= 100) {
                        startProgress();
                    } else {
                        circlePb.progressFinished();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
