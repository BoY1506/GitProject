package com.zhou.gitproject.multipicker.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 城市信息提取类
 * Created by zhou on 2016/7/14.
 */
public class CityConvert {

    private Context context;

    //把全国的省市区的信息以json的格式保存，解析完成后赋值为null
    private JSONObject mJsonObj;

//    //所有省
//    public static String[] mProvinceDatas;
//    //key - 省 value - 市s
//    public static Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
//    //key - 市 values - 区s
//    public static Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

    //所有省
    public static ArrayList<String> mProvinceDatas;
    //key - 省 value - 市s
    public static Map<String, ArrayList<String>> mCitisDatasMap = new HashMap<String, ArrayList<String>>();
    //key - 市 values - 区s
    public static Map<String, ArrayList<String>> mAreaDatasMap = new HashMap<String, ArrayList<String>>();

    private static CityConvert instance;

    private CityConvert(Context context) {
        this.context = context;
        //初始化数据
        initJsonData();
        initDatas();
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static CityConvert getInstance(Context context) {
        if (instance == null) {
            synchronized (CityConvert.class) {
                if (instance == null) {
                    instance = new CityConvert(context);
                }
            }
        }
        return instance;
    }

    /**
     * 从assert文件夹中读取省市区的json文件，然后转化为json对象
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open("mycity.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "gbk"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析整个Json对象，完成后释放Json对象的内存
     */
    private void initDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            mProvinceDatas = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                // 每个省的json对象
                JSONObject jsonP = jsonArray.getJSONObject(i);
                // 省名字
                String province = jsonP.getString("p");

                mProvinceDatas.add(province);

                JSONArray jsonCs = null;
                try {
                    /**
                     * Throws JSONException if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("c");
                } catch (Exception e1) {
                    continue;
                }
                // 当前省的所有市
                ArrayList<String> mCitiesDatas = new ArrayList<>();
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    // 市名字
                    String city = jsonCity.getString("n");
                    mCitiesDatas.add(city);
                    JSONArray jsonAreas = null;
                    try {
                        /**
                         * Throws JSONException if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("a");
                    } catch (Exception e) {
                        continue;
                    }
                    // 当前市的所有区
                    ArrayList<String> mAreasDatas = new ArrayList<>();
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        // 区域的名称
                        String area = jsonAreas.getJSONObject(k).getString("s");
                        mAreasDatas.add(area);
                    }
                    mAreaDatasMap.put(city, mAreasDatas);
                }
                mCitisDatasMap.put(province, mCitiesDatas);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

}
