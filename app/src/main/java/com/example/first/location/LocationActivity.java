package com.example.first.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.*;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.first.R;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private static final String PERMISSION_1 = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_2 = Manifest.permission.READ_PHONE_STATE;
    private static final String PERMISSION_3 = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public LocationClient mLocationClient;
    private TextView tvPosition;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);
        tvPosition = findViewById(R.id.tv_position);
        mMapView = findViewById(R.id.mv_position);
        mBaiduMap = mMapView.getMap();
        //显示卫星图层
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //地图上显示我的位置
        mBaiduMap.setMyLocationEnabled(true);

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                PERMISSION_1) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(PERMISSION_1);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                PERMISSION_2) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(PERMISSION_2);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                PERMISSION_3) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(PERMISSION_3);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(new MyLocationListener());
        //配置定位参数
        LocationClientOption option = new LocationClientOption();
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要>=1000ms才是有效的
        option.setScanSpan(2000);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址描述
        option.setIsNeedLocationDescribe(true);
        //可选，设置是否需要设备方向结果
        option.setNeedDeviceDirect(false);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationMode.Hight_Accuracy);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 ){
                    for (int result : grantResults) {
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有权限才能使用此程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:break;
        }
    }

    private void getPosition(BDLocation bdLocation){
        StringBuilder currentPosition = new StringBuilder();
        //获取纬度信息
        currentPosition.append("维度").append(bdLocation.getLatitude()).append("\n");
        //获取经度信息
        currentPosition.append("经度").append(bdLocation.getLongitude()).append("\n");
        currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
        currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
        currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
        currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
        currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");

        //获取定位类型
        currentPosition.append("定位类型：");
        if(bdLocation.getLocType() == BDLocation.TypeGpsLocation){
            currentPosition.append("GPS");
        }else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            currentPosition.append("网络");
        }
        tvPosition.setText(currentPosition);
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.latitude(location.getLongitude());
        MyLocationData data = builder.build();
        mBaiduMap.setMyLocationData(data);
    }

    class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation ||
                    bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
//            getPosition(bdLocation);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mMapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
    }
}
