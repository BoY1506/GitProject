package com.zhou.gitproject.mydialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.mydialog.view.MyEtFragmentDialog;
import com.zhou.gitproject.mydialog.view.MyMsgDialog;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * fragmentDialog练习
 * 有点：可适配屏幕旋转
 * Created by zhou on 2016/8/12.
 */
public class FragmentDialogTest extends BaseActivity implements MyEtFragmentDialog.GetInputListener {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentdialog_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }


    @OnClick({R.id.bt1, R.id.bt2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                showMsgDialog();
                break;
            case R.id.bt2:
                showFragDialog();
                break;
        }
    }

    /**
     * 显示showMsgDialog
     */
    private void showMsgDialog() {
        new MyMsgDialog(this)
                .setDialogMsg("是否拨打电话：400-079-0603")
                .setOnConfirm("呼叫", new MyMsgDialog.OptionListener() {
                    @Override
                    public void onConfirm(MyMsgDialog dialog) {
                        dialog.dismiss();
                        showToast("呼叫电话400-079-0603");
                    }
                }).show();
    }

    /**
     * showFragDialog
     */
    private void showFragDialog() {
        new MyEtFragmentDialog()
                .setGetInputListener(this)
                .show(getFragmentManager(), "ET_DIALOG");
    }

    @Override
    public void getInput(String name, String pwd) {
        showToast("用户名：" + name + " 密码：" + pwd);
    }

}
