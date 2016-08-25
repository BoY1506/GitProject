package com.zhou.test.win8imitation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhou.test.R;
import com.zhou.test.win8imitation.view.ScaleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 仿win8布局练习
 * Created by zhou on 2016/7/27.
 */
public class Win8Test extends Activity {

    @InjectView(R.id.c_joke)
    ScaleImageView cJoke;
    @InjectView(R.id.c_idea)
    ScaleImageView cIdea;
    @InjectView(R.id.c_constellation)
    ScaleImageView cConstellation;
    @InjectView(R.id.c_recommend)
    ScaleImageView cRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win8_test);
        ButterKnife.inject(this);
        cJoke.setOnClickIntent(new ScaleImageView.OnViewClickListener() {

            @Override
            public void onViewClick(ScaleImageView view) {
                Toast.makeText(Win8Test.this, "笑话", Toast.LENGTH_SHORT).show();
            }
        });
        cIdea.setOnClickIntent(new ScaleImageView.OnViewClickListener() {

            @Override
            public void onViewClick(ScaleImageView view) {
                Toast.makeText(Win8Test.this, "创意", Toast.LENGTH_SHORT).show();
            }
        });
        cConstellation.setOnClickIntent(new ScaleImageView.OnViewClickListener() {

            @Override
            public void onViewClick(ScaleImageView view) {
                Toast.makeText(Win8Test.this, "星座", Toast.LENGTH_SHORT).show();
            }
        });
        cRecommend.setOnClickIntent(new ScaleImageView.OnViewClickListener() {

            @Override
            public void onViewClick(ScaleImageView view) {
                Toast.makeText(Win8Test.this, "推荐", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
