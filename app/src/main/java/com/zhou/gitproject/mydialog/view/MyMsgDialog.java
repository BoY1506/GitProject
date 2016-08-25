package com.zhou.gitproject.mydialog.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 自定义普通消息fragmentDialog
 * Created by zhou on 2016/8/12.
 */
public class MyMsgDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private ViewHolder holder;

    private OptionListener optionListener;

    public MyMsgDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.context = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        View dialogView = UIUtils.inflate(context, R.layout.dialog_my_msgdialog);
        holder = new ViewHolder(dialogView);
        holder.dialogCancleBtn.setOnClickListener(this);
        holder.dialogConfirmBtn.setOnClickListener(this);
        setContentView(dialogView);
    }

    /**
     * 设置内容
     */
    public MyMsgDialog setDialogMsg(String msg) {
        holder.dialogContentTv.setText(msg);
        return this;
    }

    /**
     * 设置确认按钮
     */
    public MyMsgDialog setOnConfirm(String str, OptionListener listener) {
        holder.dialogConfirmBtn.setText(str);
        this.optionListener = listener;
        return this;
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancle_btn:
                dismiss();
                break;
            case R.id.dialog_confirm_btn:
                if (optionListener != null) {
                    optionListener.onConfirm(this);
                }
                break;
        }
    }

    /**
     * 操作接口
     */
    public interface OptionListener {
        void onConfirm(MyMsgDialog dialog);
    }

    static class ViewHolder {
        @InjectView(R.id.dialog_content_tv)
        TextView dialogContentTv;
        @InjectView(R.id.dialog_cancle_btn)
        TextView dialogCancleBtn;
        @InjectView(R.id.dialog_confirm_btn)
        TextView dialogConfirmBtn;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
