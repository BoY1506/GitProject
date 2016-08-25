package com.zhou.gitproject.xmlparse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;
import com.zhou.gitproject.xmlparse.utils.DOMPraseUtils;
import com.zhou.gitproject.xmlparse.utils.PullPraseUtils;
import com.zhou.gitproject.xmlparse.utils.SAXPraserHandler;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 三种xml解析练习
 * Created by zhou on 2016/8/18.
 */
public class XMLParseTest extends BaseActivity {

    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;
    @InjectView(R.id.bt4)
    Button bt4;
    @InjectView(R.id.result_tv)
    TextView resultTv;

    private InputStream xmlIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlparse_test);
        ButterKnife.inject(this);
        initData();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                resultTv.setText(getResultForSAX().toString());
                break;
            case R.id.bt2:
                resultTv.setText(getResultForPull().toString());
                break;
            case R.id.bt3:
                resultTv.setText(getResultForDOM().toString());
                break;
            case R.id.bt4:
                resultTv.setText("");
                break;
        }
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        try {
            xmlIs = getResources().getAssets().open("channel.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SAX解析
     *
     * @return
     */
    public List<Map<String, String>> getResultForSAX() {
        try {
            //创建SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //创建paraser
            SAXParser parser = factory.newSAXParser();
            //创建reader
            XMLReader reader = parser.getXMLReader();
            //创建handler
            SAXPraserHandler handler = new SAXPraserHandler();
            //设置hander
            reader.setContentHandler(handler);
            //开始解析
            InputSource is = new InputSource(xmlIs);
            reader.parse(is);
            //获取解析结果
            return handler.getList();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Pull解析
     *
     * @return
     */
    public List<Map<String, String>> getResultForPull() {
        try {
            //创建PullParserFactory
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //创建XmlPullParser
            XmlPullParser parser = factory.newPullParser();
            //设置数据
            parser.setInput(xmlIs, "UTF-8");
            PullPraseUtils praseUtils = new PullPraseUtils(parser);
            return praseUtils.parseData();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DOM解析
     *
     * @return
     */
    public List<Map<String, String>> getResultForDOM() {
        try {
            //创建DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //创建DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            //获取Document
            Document document = builder.parse(xmlIs);
            DOMPraseUtils praseUtils = new DOMPraseUtils(document);
            return praseUtils.parseData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (xmlIs != null) {
                xmlIs.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
