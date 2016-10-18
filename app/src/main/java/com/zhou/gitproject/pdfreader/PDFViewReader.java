package com.zhou.gitproject.pdfreader;

import android.os.Bundle;
import android.widget.Button;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * pdf文件阅读器练习
 * Created by zhou on 2016/9/23.
 */
public class PDFViewReader extends BaseActivity {

    //    @InjectView(R.id.bt1)
//    Button bt1;
//    @InjectView(R.id.bt2)
//    Button bt2;

    @InjectView(R.id.bt3)
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview_reader);
        ButterKnife.inject(this);
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick(R.id.bt3)
    public void onClick() {
        intent2Activity(PDFViewTest.class);
    }

//    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3})
//    public void onClick(View view) {
//        Intent intent = null;
//        switch (view.getId()) {
//            case R.id.bt1:
//                intent = IntentManager.getWordFileIntent(SDCardUtils.getSDCardPath() + "testWord.docx");
//                break;
//            case R.id.bt2:
//                intent = IntentManager.getExcelFileIntent(SDCardUtils.getSDCardPath() + "testExcel.xlsx");
//                break;
//            case R.id.bt3:
//                intent = IntentManager.getPdfFileIntent(SDCardUtils.getSDCardPath() + "testPdf.pdf");
//                break;
//        }
//        startActivity(intent);
//    }
}
