package com.zhou.gitproject.cityselect2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhou.gitproject.R;
import com.zhou.gitproject.cityselect2.bean.City;
import com.zhou.gitproject.cityselect2.utils.PingYinUtil;
import com.zhou.gitproject.cityselect2.view.MyGridView;
import com.zhou.gitproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 城市列表适配器
 * Created by zhou on 2016/8/23.
 */
public class CityListAdapter extends BaseAdapter {

    private Context context;
    private ViewHolder viewHolder;
    private List<City> hisCityList;
    private List<City> hotCityList;
    private List<City> cityList;
    //总类表
    private List<Object> allCityList;
    //右侧字母导航栏索引
    private Map<String, Integer> letterPosition;

    private static final int TYPE_CUR_CITY = 0;
    private static final int TYPE_HIS_CITY = 1;
    private static final int TYPE_HOT_CITY = 2;
    private static final int TYPE_ALL_CITY = 3;
    private static final int TYPE_CITY_LETTER = 4;
    private static final int TYPE_CITY_NAME = 5;

    public static final int LOCATING = 1;//定位中
    public static final int LOCATION_SUCCESS = 2;//定位成功
    public static final int LOCATION_FAILED = 3;//定位失败

    //当前城市定位状态
    private int locationCode = LOCATING;
    //当前城市定位名称
    private String curCityName = "";

    private OnCurCityClickListener onCurCityClickListener;
    private OnModdleCityGvItemClickListener onModdleCityGvItemClickListener;

    public CityListAdapter(Context context, List<City> hisCityList, List<City> hotCityList, List<City> cityList) {
        this.context = context;
        this.hisCityList = hisCityList;
        this.hotCityList = hotCityList;
        this.cityList = cityList;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        allCityList = new ArrayList<>();
        letterPosition = new HashMap<>();
        //城市按拼音排序
        Collections.sort(cityList);
        //加入前三项
        allCityList.add("定位");
        allCityList.add("最近");
        allCityList.add("热门");
        allCityList.add("全部");
        letterPosition.put("定位", 0);
        letterPosition.put("最近", 1);
        letterPosition.put("热门", 2);
        letterPosition.put("全部", 3);
        //加入首字母到总列表
        for (int i = 0; i < cityList.size(); i++) {
            //获取首字母
            City city = cityList.get(i);
            String firstLetter = getFirstLetter(city.getName());
            //填充右侧栏导航数据
            if (!letterPosition.containsKey(firstLetter)) {
                letterPosition.put(firstLetter, allCityList.size());
                allCityList.add(firstLetter);
            }
            //填充总数据
            allCityList.add(city);
        }
    }

    /**
     * 获取名称首字母
     */
    private String getFirstLetter(String name) {
        String firstLetter = "";
        char c = PingYinUtil.getPingYin(name).toUpperCase().charAt(0);
        if (c >= 'A' && c <= 'Z') {
            firstLetter = String.valueOf(c);
        }
        return firstLetter;
    }

    @Override
    public int getCount() {
        return allCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return allCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (allCityList.get(position) instanceof City) {
            return TYPE_CITY_NAME;
        } else {
            switch ((String) allCityList.get(position)) {
                case "定位":
                    return TYPE_CUR_CITY;
                case "最近":
                    return TYPE_HIS_CITY;
                case "热门":
                    return TYPE_HOT_CITY;
                case "全部":
                    return TYPE_ALL_CITY;
                default:
                    return TYPE_CITY_LETTER;
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_CUR_CITY:
                //当前城市
                return getCurCityView();
            case TYPE_HIS_CITY:
                //最近访问城市
                return getHisCityView();
            case TYPE_HOT_CITY:
                //热门城市
                return getHotCityView();
            case TYPE_ALL_CITY:
                //全部城市
                return getAllCityView();
            case TYPE_CITY_LETTER:
                //城市字母
                return getCityLetterView(position, convertView, parent);
            default:
                //城市名称
                return getCityNameView(position, convertView, parent);
        }
    }

    /**
     * 获取当前城市视图
     */
    public View getCurCityView() {
        View convertView = UIUtils.inflate(context, R.layout.item_cur_city);
        TextView curCityHintTv = (TextView) convertView.findViewById(R.id.cur_city_hint_tv);
        TextView curCityTv = (TextView) convertView.findViewById(R.id.cur_city_tv);
        ProgressBar curCityPb = (ProgressBar) convertView.findViewById(R.id.cur_city_rb);
        switch (locationCode) {
            case LOCATING:
                //定位中
                curCityPb.setVisibility(View.VISIBLE);
                curCityHintTv.setText("正在定位...");
                curCityTv.setText(curCityName);
                curCityTv.setVisibility(View.GONE);
                break;
            case LOCATION_SUCCESS:
                //定位成功
                curCityPb.setVisibility(View.GONE);
                curCityHintTv.setText("当前城市");
                curCityTv.setText(curCityName);
                curCityTv.setVisibility(View.VISIBLE);
                break;
            case LOCATION_FAILED:
                //定位失败
                curCityPb.setVisibility(View.GONE);
                curCityHintTv.setText("未定位到城市");
                curCityTv.setText(curCityName);
                curCityTv.setVisibility(View.VISIBLE);
                break;
        }
        curCityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCurCityClickListener != null) {
                    onCurCityClickListener.onCurCityClick();
                }
            }
        });
        return convertView;
    }

    /**
     * 获取最近访问城市视图
     */
    public View getHisCityView() {
        View convertView = UIUtils.inflate(context, R.layout.item_moddle_city);
        ((TextView) convertView.findViewById(R.id.moddle_city_hint_tv)).setText("最近访问城市");
        final MyGridView hisCityGv = (MyGridView) convertView.findViewById(R.id.moddle_city_gv);
        hisCityGv.setAdapter(new ModdleCityGvAdapter(context, hisCityList, hisCityGv));
        hisCityGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onModdleCityGvItemClickListener != null) {
                    onModdleCityGvItemClickListener.onModdleCityGvItemClick(hisCityList.get(position));
                }
            }
        });
        return convertView;
    }

    /**
     * 获取热门城市视图
     */
    public View getHotCityView() {
        View convertView = UIUtils.inflate(context, R.layout.item_moddle_city);
        ((TextView) convertView.findViewById(R.id.moddle_city_hint_tv)).setText("热门城市");
        final MyGridView hotCityGv = (MyGridView) convertView.findViewById(R.id.moddle_city_gv);
        hotCityGv.setAdapter(new ModdleCityGvAdapter(context, hotCityList, hotCityGv));
        hotCityGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onModdleCityGvItemClickListener != null) {
                    onModdleCityGvItemClickListener.onModdleCityGvItemClick(hotCityList.get(position));
                }
            }
        });
        return convertView;
    }

    /**
     * 获取全部城市视图
     */
    public View getAllCityView() {
        return UIUtils.inflate(context, R.layout.item_all_city);
    }

    /**
     * 获取城市首字母视图
     */
    public View getCityLetterView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = UIUtils.inflate2(context, R.layout.item_citylist_c_letter, parent);
            viewHolder.cityLetter = (TextView) convertView.findViewById(R.id.city_letter_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cityLetter.setText((String) getItem(position));
        return convertView;
    }

    /**
     * 获取城市名称视图
     */
    public View getCityNameView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = UIUtils.inflate2(context, R.layout.item_citylist_c_name, parent);
            viewHolder.cityName = (TextView) convertView.findViewById(R.id.city_name_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cityName.setText(((City) getItem(position)).getName());
        return convertView;
    }

    /**
     * 返回右侧栏索引位置
     */
    public int getLetterPosition(String letter) {
        Integer positoin = letterPosition.get(letter);
        return positoin == null ? -1 : positoin;
    }

    /**
     * 更新定位情况
     */
    public void updateCurCityLocate(int locationCode, String cityStr) {
        this.locationCode = locationCode;
        this.curCityName = cityStr;
        notifyDataSetChanged();
    }

    /**
     * 获取城市定位情况
     */
    public boolean isLocateSuccess() {
        return locationCode == LOCATION_SUCCESS;
    }

    /**
     * 获取当前城市
     */
    public String getCurCity() {
        return curCityName;
    }

    static class ViewHolder {
        TextView cityLetter;
        TextView cityName;
    }

    /**
     * 当前城市点击
     */
    public interface OnCurCityClickListener {
        void onCurCityClick();
    }

    public void setOnCurCityClickListener(OnCurCityClickListener onCurCityClickListener) {
        this.onCurCityClickListener = onCurCityClickListener;
    }


    /**
     * 最近、热门城市点击
     */
    public interface OnModdleCityGvItemClickListener {
        void onModdleCityGvItemClick(City city);
    }

    public void setOnModdleCityGvItemClickListener(OnModdleCityGvItemClickListener onModdleCityGvItemClickListener) {
        this.onModdleCityGvItemClickListener = onModdleCityGvItemClickListener;
    }

}
