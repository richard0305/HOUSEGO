package com.dumu.housego;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.os.Bundle;

public class BaiduMapActivity extends Activity {
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	LatLng latLng=null;
	private BitmapDescriptor mCurrentMarker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baidu_map);
		double latitude=getIntent().getDoubleExtra("latitude", -1);
		double longitude=getIntent().getDoubleExtra("longitude", -1);
		latLng=new LatLng( longitude,latitude);
		initView();
		showMap();
		
	}
	private void showMap() {
		MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
		
		
		// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）  
		mCurrentMarker = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_gcoding);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(latLng)  
		    .icon(mCurrentMarker);  
		//在地图上添加Marker，并显示  
		mBaiduMAP.addOverlay(option);
		mBaiduMAP.animateMapStatus(msu);
		
	}
	private void initView() {
		mMapView=(MapView)findViewById(R.id.baidu_map);
		mBaiduMAP=mMapView.getMap();
		
		/**
		 * 改变地图的比例尺
		 */
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMAP.setMapStatus(msu);
	}
	
	
	
	
}
