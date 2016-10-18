package com.zhou.gitproject.baidumap;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;
import com.zhou.gitproject.utils.ActionBarBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 百度地图练习
 * Created by zhou on 2016/8/30.
 */
public class BaiduMapTest extends BaseActivity {

    @InjectView(R.id.mapview)
    MapView mapView;
    @InjectView(R.id.type_normal)
    Button typeNormal;
    @InjectView(R.id.type_satellite)
    Button typeSatellite;
    @InjectView(R.id.type_traffic)
    Button typeTraffic;

    private BaiduMap map;
    private Vibrator vibrator;

    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();

    private boolean isFirstIn = true;

    PoiSearch mPoiSearch;//poi检索
    RoutePlanSearch mRoutePlanSearch;//公交线路检索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap_test);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    public void initActionBar(ActionBarBuilder builder) {
        builder.hideActionBar();
    }

    /**
     * 初始化
     */
    private void initView() {
        map = mapView.getMap();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //定位
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        LocationClientOption lcPption = new LocationClientOption();
        lcPption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        lcPption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        lcPption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(lcPption);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

//        //poi检索
//        mPoiSearch = PoiSearch.newInstance();
//        mPoiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
//            @Override
//            public void onGetPoiResult(PoiResult poiResult) {
//                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//                    return;
//                }
//                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
//                    //map.clear();
//                    //创建PoiOverlay
//                    PoiOverlay overlay = new MyPoiOverlay(map);
//                    //设置overlay可以处理标注点击事件
//                    map.setOnMarkerClickListener(overlay);
//                    //设置PoiOverlay数据
//                    overlay.setData(poiResult);
//                    //添加PoiOverlay到地图中
//                    overlay.addToMap();
//                    overlay.zoomToSpan();
//                    return;
//                }
//            }
//
//            @Override
//            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
//                if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
//                    //详情检索失败
//                    // result.error请参考SearchResult.ERRORNO
//                } else {
//                    //检索成功
//                    showLog(poiDetailResult.toString());
//                }
//            }
//
//            @Override
//            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//            }
//        });
//        PoiCitySearchOption pcsOption = new PoiCitySearchOption();
//        pcsOption.city("苏州")//城市
//                .keyword("美食")//关键字
//                .pageNum(10);//页数
//        mPoiSearch.searchInCity(pcsOption);

//        //公交线路检索
//        mRoutePlanSearch = RoutePlanSearch.newInstance();
//        mRoutePlanSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
//            @Override
//            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
//
//            }
//
//            @Override
//            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
//                showLog("回调结果了");
//                showLog(transitRouteResult.error.toString());
//                if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
//                    //未找到结果
//                    showToast("未找到结果");
//                    return;
//                }
//                if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//                    //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//                    SuggestAddrInfo suggestAddrInfo = transitRouteResult.getSuggestAddrInfo();
//                    showToast("起终点或途经点地址有岐义");
//                    return;
//                }
//                if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
//                    showLog("回调成功了");
//                    TransitRouteLine transitRouteLine = transitRouteResult.getRouteLines().get(0);
//                    //创建公交路线规划线路覆盖物
//                    TransitRouteOverlay overlay = new TransitRouteOverlay(map);
//                    //设置公交路线规划数据
//                    overlay.setData(transitRouteLine);
//                    //将公交路线规划覆盖物添加到地图中
//                    overlay.addToMap();
//                    overlay.zoomToSpan();
//                }
//            }
//
//            @Override
//            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
//
//            }
//
//            @Override
//            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
//
//            }
//        });
//        PlanNode startNode = PlanNode.withCityNameAndPlaceName("苏州", "淞泽家园二区");
//        PlanNode endNode = PlanNode.withCityNameAndPlaceName("苏州", "苏州火车站");
//        mRoutePlanSearch.transitSearch(new TransitRoutePlanOption().city("苏州").from(startNode).to(endNode));

        //标注
//        //定义Maker坐标点
//        LatLng point = new LatLng(39.963175, 116.400244);
//        //构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_maker);
////        // 通过marker的icons设置一组图片，再通过period设置多少帧刷新一次图片资源
////        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
////        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka));
////        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.icon_markb));
////        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.icon_markc));
//        //构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions options = new MarkerOptions()
//                .icon(bitmap)//设置图标
////                .icons(giflist)//设置gif图片组
//                .position(point)//设置位置
////                .zIndex(9)//maker所在层级
////                .period(20)//刷新频率
////                .alpha(0.5f)//透明度
//                .animateType(MarkerOptions.MarkerAnimateType.drop)//动画效果
//                .draggable(true);//可拖拽
//        //在地图上添加Marker，并显示
//        Marker marker = (Marker) map.addOverlay(options);
//        //设置拖拽监听器
//        map.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
//            @Override
//            public void onMarkerDrag(Marker marker) {
//                //拖拽中
//            }
//
//            @Override
//            public void onMarkerDragEnd(Marker marker) {
//                //拖拽结束
//                showToast("经度：" + marker.getPosition().longitude + "，纬度：" + marker.getPosition().latitudeE6);
//            }
//
//            @Override
//            public void onMarkerDragStart(Marker marker) {
//                //开始拖拽
//                vibrator.vibrate(30);
//            }
//        });

//        //添加多边形标注
//        //定义多边形的五个顶点
//        LatLng pt1 = new LatLng(39.93923, 116.357428);
//        LatLng pt2 = new LatLng(39.91923, 116.327428);
//        LatLng pt3 = new LatLng(39.89923, 116.347428);
//        LatLng pt4 = new LatLng(39.89923, 116.367428);
//        LatLng pt5 = new LatLng(39.91923, 116.387428);
//        List<LatLng> pts = new ArrayList<LatLng>();
//        pts.add(pt1);
//        pts.add(pt2);
//        pts.add(pt3);
//        pts.add(pt4);
//        pts.add(pt5);
//        //构建用户绘制多边形的Option对象
//        OverlayOptions polygonOption = new PolygonOptions()
//                .points(pts)
//                .stroke(new Stroke(5, 0xAA00FF00))
//                .fillColor(0xAAFFFF00);
//        //在地图上添加多边形Option，用于显示
//        map.addOverlay(polygonOption);

//        //添加文字标注
//        //定义文字所显示的坐标点
//        LatLng llText = new LatLng(39.86923, 116.397428);
//        //构建文字Option对象，用于在地图上添加文字
//        OverlayOptions textOption = new TextOptions()
//                .bgColor(0xAAFFFF00)
//                .fontSize(34)
//                .fontColor(0xFFFF00FF)
//                .text("百度地图SDK")
//                .rotate(-30)
//                .position(llText);
//        //在地图上添加该文字对象并显示
//        map.addOverlay(textOption);
    }

    @OnClick({R.id.type_normal, R.id.type_satellite, R.id.type_traffic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type_normal:
                map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.type_satellite:
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.type_traffic:
                if (map.isTrafficEnabled()) {
                    typeTraffic.setText("打开实时交通");
                    map.setTrafficEnabled(false);
                } else {
                    typeTraffic.setText("关闭实时交通");
                    map.setTrafficEnabled(true);
                }
                break;
        }
    }

    /**
     * 定位监听器
     * 获取当前位置信息，将地图更新到当前位置，并设置标注到当前位置
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || map == null) {
                return;
            }
            //获取到定位信息
            // 构造定位数据(这里不需要)
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
////                    .direction(100)// 此处设置开发者获取到的方向信息，顺时针0-360
//                    .latitude(location.getLatitude())//纬度
//                    .longitude(location.getLongitude())//经度
//                    .build();
//            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_maker);
//            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, bitmap);
            // 设置定位数据
            //map.setMyLocationData(locData);
            //map.setMyLocationConfigeration(config);
            if (isFirstIn) {
                //更新地图位置
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                map.setMapStatus(msu);
                initMarker(latLng);
                isFirstIn = false;
            }
        }
    }

    /**
     * 初始化标注点
     */
    private void initMarker(LatLng point) {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_maker);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions options = new MarkerOptions()
                .icon(bitmap)//设置图标
                .position(point)//设置位置
                .animateType(MarkerOptions.MarkerAnimateType.grow)//动画效果
                .draggable(true);//可拖拽
        //在地图上添加Marker，并显示
        map.addOverlay(options);
        //设置拖拽监听器
        map.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                showToast("经度：" + marker.getPosition().longitude + "，纬度：" + marker.getPosition().latitude);
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
                vibrator.vibrate(30);
            }
        });
    }

//    /**
//     * 自定义overlay类
//     */
//    private class MyPoiOverlay extends PoiOverlay {
//        public MyPoiOverlay(BaiduMap baiduMap) {
//            super(baiduMap);
//        }
//
//        @Override
//        public boolean onPoiClick(int index) {
//            //uid是POI检索中获取的POI ID信息
//            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption()).poiUid(getPoiResult().getAllPoi().get(index).uid));
//            return true;
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        map.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停止定位
        map.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPoiSearch != null) {
            mPoiSearch.destroy();
        }
        if (mRoutePlanSearch != null) {
            mRoutePlanSearch.destroy();
        }
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
}
