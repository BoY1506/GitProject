package com.zhou.mvpapp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * gson解析工具类
 * Created by zhou on 2016/1/22.
 */
public class GsonUtils {

    //gson对象
    public static Gson gson = new Gson();

    /**
     * 返回单个实体
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, cls);
    }

    /**
     * 返回实体list
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> getBeanList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                list.add(getBean(json.toString(), cls));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 返回实体list
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> getBeanList(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    /**
     * 返回单个Map
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> getMap(String jsonString) {
        return gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    /**
     * 返回Maplist
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getMapList(String jsonString) {
        return gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
    }

    /**
     * 根据Map构建Json字符串
     *
     * @param map
     * @return
     */
    public static String map2Json(Map map) {
        return gson.toJson(map);
    }

    /**
     * 根据List<Map>构建Json字符串
     *
     * @param list
     * @return
     */
    public static String list2Json(List<Map<String, Object>> list) {
        return gson.toJson(list);
    }

}
