package com.dumu.housego;

import java.util.List;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.model.LatLng;
import com.dumu.housego.entity.AreaHouse;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.shenZhenLocation;
import com.dumu.housego.presenter.IMapHouseDataPresenter;
import com.dumu.housego.presenter.MapHouseDataPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IMapHouseDataView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MapHouseMainActivity extends Activity implements IMapHouseDataView{
	private RadioButton rbErShouFang,rbRenting;
	private LinearLayout llMapHouseBack;
	private IMapHouseDataPresenter areapresenter;
	
	
	//覆盖物相关
	private BitmapDescriptor mMarker;
	
	
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	private List<AreaHouse>Areahouses;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_house_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();
		initMarker();
		rbErShouFang.setTextColor(getResources().getColor(R.color.button_ckeck));
		areapresenter=new MapHouseDataPresenter(this);
		areapresenter.LoadMapHouse("ershou");
		
		handler=new  Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					Areahouses=(List<AreaHouse>) msg.obj;
					showAreaHouse(shenZhenLocation.locations);
					break;

				default:
					break;
				}
			}
		};
	
	}
	
	private void showAreaHouse(List<shenZhenLocation> locations) {
	LatLng lat=null;
	Marker marker=null;
		OverlayOptions options;
		InfoWindow infoWindow;
		TextView tv=new TextView(this);
		
		
		try {
			for (shenZhenLocation l : locations) {
				
				for (AreaHouse a : Areahouses) {
					if(a.getName().equals(l.getName())){
						tv.setText(a.getName()+" "+a.getHouse_count());
					}
				}
				lat=new LatLng(l.getLatitude(),l.getLongitude());
				tv.setTextColor(android.graphics.Color.BLACK);
				tv.setPadding(30, 20, 30, 50);
			
				BitmapDescriptor bit=new BitmapDescriptorFactory().fromView(tv);
				
			OnInfoWindowClickListener infowindowListener=new OnInfoWindowClickListener() {
				
					@Override
					public void onInfoWindowClick() {
					}
				};
				
				infoWindow=new InfoWindow(bit,lat,0, infowindowListener);		
				mBaiduMAP.showInfoWindow(infoWindow);
			}
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	
}

	private void initMarker() {
	mMarker=BitmapDescriptorFactory.fromResource(R.drawable.bg_good_rating_ratio);
	
	}
	
	private void setViews() {
		mMapView=(MapView) findViewById(R.id.maphouse_bmapView);
		mBaiduMAP=mMapView.getMap();
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);
	
		
		LatLng latlng=new LatLng(22.546054,114.025974);
		MapStatus status=new MapStatus.Builder().target(latlng).zoom(11.0f).build();
		MapStatusUpdate msu=MapStatusUpdateFactory.newMapStatus(status);
		mBaiduMAP.animateMapStatus(msu);
		
		
		
		rbErShouFang=(RadioButton) findViewById(R.id.btn_ershoufang);
		rbRenting=(RadioButton) findViewById(R.id.btn_renting);
		llMapHouseBack=(LinearLayout) findViewById(R.id.ll_map_house_back);
		
	}

	private void setListeners() {
		
		rbErShouFang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rbErShouFang.setTextColor(getResources().getColor(R.color.button_ckeck));
				rbRenting.setTextColor(getResources().getColor(R.color.button_unckeck));
			}
		});
		
		
		rbRenting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rbErShouFang.setTextColor(getResources().getColor(R.color.button_unckeck));
				rbRenting.setTextColor(getResources().getColor(R.color.button_ckeck));
			}
		});
		
		
		llMapHouseBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	@Override
	public void ShowMapHouse(List<AreaHouse> areahouses) {
		Message msg=new Message();
		msg.what=1;
		msg.obj=areahouses;
		handler.sendMessage(msg);
		
		
	}

	
}
