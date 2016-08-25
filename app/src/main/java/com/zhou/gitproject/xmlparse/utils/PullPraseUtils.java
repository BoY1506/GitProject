package com.zhou.gitproject.xmlparse.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pull解析
 * 类似SAX解析，安卓端用的比较多
 * Created by zhou on 2016/8/18.
 */
public class PullPraseUtils {

    private XmlPullParser parser;

    //总list
    private List<Map<String, String>> list;
    //每个map
    private Map<String, String> map;

    public PullPraseUtils(XmlPullParser parser) {
        this.parser = parser;
    }

    /**
     * 解析数据
     *
     * @return
     */
    public List<Map<String, String>> parseData() {
        list = new ArrayList<>();
        try {
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                //如果不等于END_DOCUMENT
                if (parser.getEventType() == XmlPullParser.START_TAG) {
                    //等于开始item
                    //获取标签的名字
                    String tagName = parser.getName();
                    if (tagName.equals("item")) {
                        map = new HashMap<>();
                        //通过属性名来获取属性值
                        map.put("id", parser.getAttributeValue(null, "id"));
                        //通过属性索引来获取属性值
                        map.put("id", parser.getAttributeValue(1));
                        //通过nextText()获取标签value
                        map.put("name", parser.nextText());
                        //将map加入集合
                        list.add(map);
                    }
                }
                //通过next()来进行下一轮循环
                parser.next();
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
