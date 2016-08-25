package com.zhou.gitproject.xmlparse.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DOM解析
 * 一次读入整篇文档树，占用内存较大
 * 适用于需要经常使用的数据
 * Created by zhou on 2016/8/18.
 */
public class DOMPraseUtils {

    private Document document;

    //总list
    private List<Map<String, String>> list;
    //每个map
    private Map<String, String> map;

    public DOMPraseUtils(Document document) {
        this.document = document;
    }

    /**
     * 解析数据
     *
     * @return
     */
    public List<Map<String, String>> parseData() {
        list = new ArrayList<>();
        //得到 "根节点"
        Element root = document.getDocumentElement();
        //获取根节点的所有items的节点
        NodeList items = root.getElementsByTagName("item");
        //遍历所有节点
        for (int i = 0; i < items.getLength(); i++) {
            map = new HashMap<>();
            //得到每个item，如果是ELEMENT_NODE，转为element
            Element item = (Element) items.item(i);
            //获取属性
            map.put("id", item.getAttribute("id"));
            map.put("url", item.getAttribute("url"));
            //获取value
            map.put("name", item.getFirstChild().getNodeValue());
            list.add(map);
        }
        return list;
    }

}
