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
		
		
		// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩  
		mCurrentMarker = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_mylocation);  
		//����MarkerOption�������ڵ�ͼ�����Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(latLng)  
		    .icon(mCurrentMarker);  
		//�ڵ�ͼ�����Marker������ʾ  
		mBaiduMAP.addOverlay(option);
		mBaiduMAP.animateMapStatus(msu);
		
	}
	private void initView() {
		mMapView=(MapView)findViewById(R.id.baidu_map);
		mBaiduMAP=mMapView.getMap();
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);
		/**
		 * �ı��ͼ�ı�����
		 */
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(17.0f);
		mBaiduMAP.setMapStatus(msu);
	}
	
	
	
	
}
