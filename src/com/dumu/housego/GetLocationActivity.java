package com.dumu.housego;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GetLocationActivity extends Activity {
	
	
	private static final int LOCATION=0;
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	private LatLng latlng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location);
		initView();
		setListener();
		
	}
	private void initView() {
		mMapView=(MapView)findViewById(R.id.baidu_map);
		mBaiduMAP=mMapView.getMap();
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMAP.setMapStatus(msu);
		
	}
	private void setListener() {
		mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				Intent i=new Intent(GetLocationActivity.this,PuTongMyGuanZhuActivity.class);
				double latitude=arg0.latitude;
				double longitude=arg0.longitude;
				Log.e("LatLng", "LatLng"+latitude+" " +longitude);
				i.putExtra("latitude", latitude);
				i.putExtra("longitude", longitude);
				setResult(LOCATION, i);
				finish();
			}
		});
		
	}


}
