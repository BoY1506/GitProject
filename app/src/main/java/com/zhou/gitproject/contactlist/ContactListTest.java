package com.zhou.gitproject.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 通讯录的实现
 * Created by zhou on 2016/8/13.
 */
public class ContactListTest extends BaseActivity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist_test);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick({R.id.bt1, R.id.bt2})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt1:
                intent = new Intent(ContactListTest.this, ContactList1.class);
                break;
            case R.id.bt2:
                intent = new Intent(ContactListTest.this, ContactList2.class);
                break;
        }
        startActivity(intent);
    }
}
