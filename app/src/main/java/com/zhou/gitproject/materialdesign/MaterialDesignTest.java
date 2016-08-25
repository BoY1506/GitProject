package com.zhou.gitproject.materialdesign;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Andorid Design Support新控件的使用
 * Created by zhou on 2016/8/19.
 */
public class MaterialDesignTest extends BaseActivity {

    @InjectView(R.id.bt)
    Button bt;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;
    @InjectView(R.id.til)
    TextInputLayout til;
    @InjectView(R.id.action_btn)
    FloatingActionButton actionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialdesign_test);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        //TextInputLayout
        til.setHint("请输入密码");
        EditText et = til.getEditText();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 6) {
                    til.setError("密码不能超过6位");
                    til.setErrorEnabled(true);
                } else {
                    til.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //FloatingActionButton
        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar();
            }
        });
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick({R.id.bt, R.id.bt2, R.id.bt3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                startActivity(new Intent(MaterialDesignTest.this, TabsLayoutTest.class));
                break;
            case R.id.bt2:
                startActivity(new Intent(MaterialDesignTest.this, MyNavicationTest.class));
                break;
            case R.id.bt3:
                startActivity(new Intent(MaterialDesignTest.this, CoordinatorLayoutTest.class));
                break;
        }
    }

    /**
     * snackbar
     */

    private void showSnackBar() {
        final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), "你好，SnackBar~", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.parseColor("#ff6600"))
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                }).show();
    }

}
