package com.zhou.gitproject.pdfreader;

import android.graphics.Canvas;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.utils.SDCardUtils;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * PDFView库练习
 * Created by zhou on 2016/9/23.
 */
public class PDFViewTest extends BaseActivity {

    @InjectView(R.id.pdfView)
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview_test);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("PDF查看器");
    }

    private void initView() {
        File pdfFile = new File(SDCardUtils.getSDCardPath() + "testPdf.pdf");
        showLog(pdfFile.getAbsolutePath());
        pdfView.fromFile(pdfFile)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float v, float v1, int i) {
                        showLog("在划线");
                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int i) {
                        showToast("加载完成");
                        showLog("加载完成");
                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int i, int i1) {
                        showLog("页面变化");
                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable throwable) {
                        showToast("加载失败");
                        showLog("加载失败");
                    }
                })
                .enableAnnotationRendering(true)
                .password("123456")
                .scrollHandle(null)
                .load();
    }

}
