package com.zhou.gitproject.cityselect2;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.cityselect2.adapter.CityListAdapter;
import com.zhou.gitproject.cityselect2.adapter.SearchCityResultAdapter;
import com.zhou.gitproject.cityselect2.bean.City;
import com.zhou.gitproject.cityselect2.db.MySqliteManager;
import com.zhou.gitproject.cityselect2.view.LetterListView;
import com.zhou.gitproject.statusbar.utils.StatusBarUtils;
import com.zhou.gitproject.utils.ActionBarBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 仿美团城市选择界面
 * Created by zhou on 2016/8/23.
 */
public class CitySelectTest extends BaseActivity {

    @InjectView(R.id.city_lv)
    ListView cityLv;
    @InjectView(R.id.letter_lv)
    LetterListView letterLv;
    @InjectView(R.id.overlay_tv)
    TextView overlayTv;
    @InjectView(R.id.search_clear_icon)
    ImageView searchClearIcon;
    @InjectView(R.id.search_et)
    EditText searchEt;
    @InjectView(R.id.city_result_lv)
    ListView cityResultLv;
    @InjectView(R.id.city_result_tv)
    TextView cityResultTv;

    private List<City> hisCityData;
    private List<City> hotCityData;
    private List<City> cityData;
    private List<City> resultCityData;
    private MySqliteManager sqliteManager;

    private CityListAdapter adapter;
    private SearchCityResultAdapter resultAdapter;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityselect_test);
        ButterKnife.inject(this);
        StatusBarUtils.setColor(this, Color.parseColor("#ff6600"));
        initData();
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.setAcBarLeftImg().setAcBarBigText("切换城市");
    }

    /**
     * 初始化数据
     */
    private void initData() {
        sqliteManager = MySqliteManager.getManagerInstance(this);
        hisCityData = getHisCity();
        hotCityData = City.hotList;
        cityData = City.cityList;
        resultCityData = new ArrayList<>();
    }

    /**
     * 获取历史访问城市
     */
    public List<City> getHisCity() {
        List<City> cities = new ArrayList<>();
        //在APP清除缓存的时候可以将城市表清空
        showLog("记录总大小：" + sqliteManager.query2("select * from his_city").size());
        ArrayList<String[]> list = sqliteManager.query2("select * from his_city order by _id desc limit 0, 3");
        for (String[] str : list) {
            City city = new City(Integer.parseInt(str[1]), str[2]);
            cities.add(city);
        }
        return cities;
    }

    /**
     * 初始化View
     */
    private void initView() {
        //listView
        adapter = new CityListAdapter(this, hisCityData, hotCityData, cityData);
        cityLv.setAdapter(adapter);
        //resultListView
        resultAdapter = new SearchCityResultAdapter(this, resultCityData);
        cityResultLv.setAdapter(resultAdapter);
        //右侧字母导航栏
        letterLv.setOnTouchingLetterChangedListener(new LetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                if (!TextUtils.isEmpty(s)) {
                    //显示OverlayView
                    overlayTv.setText(s);
                    overlayTv.setVisibility(View.VISIBLE);
                    //定位地区list
                    int position = adapter.getLetterPosition(s);
                    if (position != -1) {
                        cityLv.setSelection(position);
                    }
                } else {
                    overlayTv.setVisibility(View.GONE);
                }
            }
        });
        //热门城市点击事件
        adapter.setOnModdleCityGvItemClickListener(new CityListAdapter.OnModdleCityGvItemClickListener() {
            @Override
            public void onModdleCityGvItemClick(City city) {
                saveSelCity(city);
            }
        });
        //ListView点击事件
        cityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 2) {
                    //从第三项开始点
                    if (adapter.getItem(position) instanceof City) {
                        saveSelCity((City) adapter.getItem(position));
                    }
                }
            }
        });
        //百度地图
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        initLocation();
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //当前城市点击事件
        adapter.setOnCurCityClickListener(new CityListAdapter.OnCurCityClickListener() {
            @Override
            public void onCurCityClick() {
                if (adapter.isLocateSuccess()) {
                    showToast(adapter.getCurCity());
                } else {
                    //重新定位
                    adapter.updateCurCityLocate(CityListAdapter.LOCATING, "");
                }
            }
        });
        //城市搜索
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //更新搜索结果视图
                updateSearchView(s.toString());
            }
        });
        cityResultLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveSelCity(resultCityData.get(position));
            }
        });
    }

    /**
     * 初始化百度地图
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);
    }

    /**
     * 搜索城市
     */
    private void updateSearchView(String searchStr) {
        if (TextUtils.isEmpty(searchStr)) {
            searchClearIcon.setVisibility(View.GONE);
            cityLv.setVisibility(View.VISIBLE);
            letterLv.setVisibility(View.VISIBLE);
            cityResultLv.setVisibility(View.GONE);
            cityResultTv.setVisibility(View.GONE);
        } else {
            searchClearIcon.setVisibility(View.VISIBLE);
            //先清空数据
            resultCityData.clear();
            for (City city : cityData) {
                if (city.getName().contains(searchStr)) {
                    //添加到搜索结果
                    resultCityData.add(city);
                }
            }
            resultAdapter.setData(resultCityData);
            //更新显示
            cityLv.setVisibility(View.GONE);
            letterLv.setVisibility(View.GONE);
            if (resultCityData.size() == 0) {
                //无结果
                cityResultLv.setVisibility(View.GONE);
                cityResultTv.setVisibility(View.VISIBLE);
            } else {
                //有结果
                cityResultLv.setVisibility(View.VISIBLE);
                cityResultTv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.search_clear_icon)
    public void onClick() {
        //清除搜索
        searchEt.setText("");
    }

    /**
     * 保存访问城市
     * sqlite设置有则自改无则插入：unique + replace
     */
    private void saveSelCity(City city) {
        ContentValues values = new ContentValues();
        values.put("city_id", city.getId());
        values.put("city_name", city.getName());
        sqliteManager.singleReplace("his_city", values);
        showToast(city.getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启定位器
        mLocationClient.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //关闭地图定位
        mLocationClient.stop();
    }

    /**
     * 定位监听器
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (TextUtils.isEmpty(location.getCity())) {
                //定位失败
                adapter.updateCurCityLocate(CityListAdapter.LOCATION_FAILED, "重新定位");
            } else {
                //定位成功
                adapter.updateCurCityLocate(CityListAdapter.LOCATION_SUCCESS, location.getCity().substring(0, location.getCity().length()));
            }
        }
    }

}
