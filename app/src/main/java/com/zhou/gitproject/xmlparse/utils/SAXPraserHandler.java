package com.zhou.gitproject.xmlparse.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SAX解析，事件驱动型解析，PC端用的比较多
 * 需要实现ContentHandler接口
 * 一般实现DefaultHandler即可
 * Created by zhou on 2016/8/18.
 */
public class SAXPraserHandler extends DefaultHandler {

    //总list
    private List<Map<String, String>> list;
    //每个map
    private Map<String, String> map;

    //当前状态
    private int currentState = 0;

    //状态码
    private static final int ITEM = 0x0005;

    /**
     * 文档开始通知
     */
    @Override
    public void startDocument() throws SAXException {
        //初始化list
        list = new ArrayList<>();
    }

    /**
     * 标签开始通知
     * 这里更新状态码和处理element属性
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        map = new HashMap<>();
        switch (localName) {
            case "item":
                //处理item标签
                currentState = ITEM;
                for (int i = 0; i < attributes.getLength(); i++) {
                    //循环获取属性
                    if (attributes.getLocalName(i).equals("id")) {
                        map.put("id", attributes.getValue(i));
                    } else if (attributes.getLocalName(i).equals("url")) {
                        map.put("url", attributes.getValue(i));
                    }
                }
                break;
            default:
                currentState = 0;
                break;
        }
    }

    /**
     * 接口字符块通知
     * 这里还原状态码和处理element值
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = String.valueOf(ch, start, length);
        switch (currentState) {
            case ITEM:
                //item标签
                map.put("name", str);
                break;
            default:
                break;
        }
        //还原状态码
        currentState = 0;
    }

    /**
     * 接收标签结束通知
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals("item")) {
            //将map加入list
            list.add(map);
        }
    }


    /**
     * 接收文档结束通知
     */
    @Override
    public void endDocument() throws SAXException {
        //结束
        super.endDocument();
    }

    /**
     * 获取list
     *
     * @return
     */
    public List<Map<String, String>> getList() {
        return list;
    }

}
