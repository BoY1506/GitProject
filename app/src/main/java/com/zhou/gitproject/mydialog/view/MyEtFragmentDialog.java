package com.zhou.gitproject.mydialog.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.zhou.gitproject.R;

/**
 * 自定义输入框fragmentDialog
 * Created by zhou on 2016/8/12.
 */
public class MyEtFragmentDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_my_editdialog, null);
        final EditText name = (EditText) view.findViewById(R.id.user_name_et);
        final EditText pwd = (EditText) view.findViewById(R.id.user_pwd_et);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setTitle("用户登录")
                .setView(view)
                        // Add action buttons
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (getInputListener != null) {
                            getInputListener.getInput(name.getText().toString(), pwd.getText().toString());
                        }
                    }
                }).setNegativeButton("取消", null);
        return builder.create();
    }

    private GetInputListener getInputListener;

    public MyEtFragmentDialog setGetInputListener(GetInputListener listener) {
        this.getInputListener = listener;
        return this;
    }

    /**
     * 获取内容接口
     */
    public interface GetInputListener {
        void getInput(String name, String pwd);
    }

}
