package com.zhou.gitproject.contactlist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.contactlist.adapter.ContactAdapter1;
import com.zhou.gitproject.contactlist.bean.Man;
import com.zhou.gitproject.contactlist.view.LetterBar;
import com.zhou.gitproject.contactlist.view.PinnedSectionListView;
import com.zhou.gitproject.utils.ActionBarBuilder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 通讯录实现一
 * Created by zhou on 2016/8/13.
 */
public class ContactList1 extends BaseActivity {

    @InjectView(R.id.contact_lv)
    PinnedSectionListView contactLv;
    @InjectView(R.id.letter_list)
    LetterBar letterList;
    @InjectView(R.id.letter_overlay)
    TextView letterOverlay;

    private List<Man> manList = Man.manList;
    private ContactAdapter1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist_1);
        ButterKnife.inject(this);
        adapter = new ContactAdapter1(this, manList);
        contactLv.setAdapter(adapter);
        contactLv.setShadowVisible(false);
        contactLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getItem(position) instanceof Man) {
                    showToast(((Man) adapter.getItem(position)).getName());
                }
            }
        });
        letterList.setOnLetterSelectedListener(new LetterBar.OnLetterSelectedListener() {
            @Override
            public void onLetterSelected(String letter) {
                if (letter.equals("")) {
                    //为空就隐藏OverlayView
                    letterOverlay.setVisibility(View.GONE);
                } else {
                    //显示OverlayView
                    letterOverlay.setVisibility(View.VISIBLE);
                    letterOverlay.setText(letter);
                    //定位地区list
                    int position = adapter.getLetterPosition(letter);
                    if (position != -1) {
                        contactLv.setSelection(position);
                    }
                }
            }
        });
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

}
