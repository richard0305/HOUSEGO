package com.dumu.housego;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ErShouFangDetailPresenter;
import com.dumu.housego.presenter.GuanZhuHousePresenter;
import com.dumu.housego.presenter.IErShouFangDetailPresenter;
import com.dumu.housego.presenter.IGuanZhuHousePresenter;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IErShouFangDetailView;
import com.dumu.housego.view.IGuanZhuHouseView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ErShouFangDetailsActivity extends Activity implements IErShouFangDetailView,IGuanZhuHouseView{
	
	private RadioButton rbErshoufangGuanzhu;
	private ErShouFangDetails e;
	private IErShouFangDetailPresenter esfPresenter;
	private IGuanZhuHousePresenter guanzhuPresenter;
	private LinearLayout llBackErshoufangdetails;
	private RelativeLayout rlBaidumap;
	private UserInfo userinfo;
	private MyReboundScrollView ErshoufangScrollview;
	private boolean isFirstIn=true;
	
	private BaiduMap mBaiduMAP;
	public static MapView mMapView;
	//��λ���
	private LocationClient mLocationClient;
//	private MyLocationListener mLocationListener;
	
	@ViewInject(R.id.iv_housepic)ImageView ivIMG;
	@ViewInject(R.id.tv_ershoufangdetails)TextView tvtitle;
	@ViewInject(R.id.ershoufang_shoujia)TextView tvShoujia;
	@ViewInject(R.id.ershoufang_huxing)TextView tvHuXing;
	@ViewInject(R.id.tv_junjia)TextView tvJunjia;
	@ViewInject(R.id.tv_louceng)TextView tvLouceng;
	@ViewInject(R.id.tv_zhuangxiu)TextView tvZhuangxiu;
	@ViewInject(R.id.Tv_fangling)TextView TvFangling;
	@ViewInject(R.id.tv_kanfangshijian)TextView tvKanfangshijian;
	@ViewInject(R.id.tv_guapaishijian)TextView tvguapaishijian;
	@ViewInject(R.id.tv_chaoxiang)TextView tvChaoxiang;
	@ViewInject(R.id.tv_louxing)TextView tvLouxing;
	@ViewInject(R.id.tv_quyu)TextView tvQuyu;
	@ViewInject(R.id.Tv_xiaoqu)TextView TvXiaoqu;
	@ViewInject(R.id.Tv_fangyuanbianhao)TextView TvFangyuanbianhao;
	@ViewInject(R.id.ershoufang_mianji)TextView tvershoufang_mianji;
	
	@ViewInject(R.id.tv_hexinmaidian1)TextView tvHexinmaidian;
	@ViewInject(R.id.tv_jiaotongchuxing1)TextView tvJiaotongchuxing;
	
	@ViewInject(R.id.tv_wuyeleixing)TextView tvWuyeleixing;
	@ViewInject(R.id.tv_maifangjiatingweiyi)TextView tvMaifangjiatingweiyi;
	@ViewInject(R.id.tv_jushangcijiaoyi)TextView tvJushangcijiaoyi;
	@ViewInject(R.id.tv_maifangjiatingshoutao)TextView tvMaifangjiatingshoutao;
	@ViewInject(R.id.tv_jizhengfangshi)TextView tvJizhengfangshi;
	@ViewInject(R.id.tv_housearea)TextView tvHousearea;
	@ViewInject(R.id.tv_zhuanrangshuifei)TextView tvZhuanrangshuifei;
	@ViewInject(R.id.tv_daikuanleixing)TextView tvDaikuanleixing;
	@ViewInject(R.id.tv_daikuanjine)TextView tvDaikuanjine;
	@ViewInject(R.id.tv_daikuanqixian)TextView tvDaikuanqixian;
	
	@ViewInject(R.id.tv_jizhunlilv)TextView tvJizhunlilv;
	@ViewInject(R.id.tv_lixi)TextView tvLixi;
	@ViewInject(R.id.tv_housetotilprice)TextView tvHousetotilprice;
	@ViewInject(R.id.tv_weekhistroy)TextView tvWeekhistroy;
	@ViewInject(R.id.tv_totalhistroy)TextView tvTotalhistroy;
	
	
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				e=(ErShouFangDetails) msg.obj;
				Show();
				break;

			default:
				break;
			}
		}
	};
	private BitmapDescriptor mCurrentMarker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
        
		setContentView(R.layout.activity_er_shou_fang_details);
		x.view().inject(this);
		initViews();
		//��ʼ����λ
//		initLocation();
		initListener();
		esfPresenter=new ErShouFangDetailPresenter(this);
		guanzhuPresenter=new GuanZhuHousePresenter(this);
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		esfPresenter.FindErShouFangdetail(catid, id);
		
		Log.e("2016-10-9 9:10", "yanglijun-------"+catid+"   "+id);
		
		
		
		
	}
	
	
//	  private void initLocation() {
//		mLocationClient=new LocationClient(this);
//		mLocationListener=new MyLocationListener();
//		mLocationClient.registerLocationListener(mLocationListener);
//		
//		LocationClientOption option=new LocationClientOption();
//		option.setCoorType("bd09ll");
//		option.setIsNeedAddress(true);
//		option.setOpenGps(true);
//		option.setScanSpan(1000);
//		
//		mLocationClient.setLocOption(option);
//		
//	}


	@Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onPause();  
	        }  
	
	
	
	private void initListener() {
		rbErshoufangGuanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				userinfo=HouseGoApp.getContext().getCurrentUserInfo();

				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "��û�е�¼�����ȵ�¼��");
				}else{
					//��¼�󣬼���û��Ƿ���Message��û������ʾ��ϢΪ�հ�ť
					String fromid=e.getId()+"";
					String fromtable="ershou";
					String userid=userinfo.getUserid();
					String username=userinfo.getUsername();
					String type="���ַ�";
					String t="1";
					guanzhuPresenter.LoadGuanZhuHouse(fromid, fromtable, userid, username, type, t);
				}

				
				MyToastShowCenter.CenterToast(getApplicationContext(), "����˹�ע");
			}
		});
		
		llBackErshoufangdetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
//		mMapView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(e.getJingweidu()==null){
//					MyToastShowCenter.CenterToast(getApplicationContext(), "��Դ�ľ�γ��Ϊ��");
//				}else{
//					String jwd=e.getJingweidu();
//					String[] arr=jwd.split(",");
//					String j=arr[0].toString();
//					String w=arr[1].toString();
//					
//					double latitude=Double.valueOf(j);
//					double longitude=Double.valueOf(w);
//					
//
//					
//					Intent i=new Intent(getApplicationContext(), BaiduMapActivity.class);
//					i.putExtra("latitude", latitude);
//					i.putExtra("longitude", longitude);
//					startActivity(i);
//				}
//		
//				
//			}
//		});
		
		mMapView.setOnTouchListener(new OnTouchListener() { 
			@Override 
			public boolean onTouch(View v, MotionEvent event) { 
			  if (event.getAction() == MotionEvent.ACTION_UP) { 
				  
			 
				  ErshoufangScrollview.requestDisallowInterceptTouchEvent(false);  
				  if(e.getJingweidu()==null){
						MyToastShowCenter.CenterToast(getApplicationContext(), "��Դ�ľ�γ��Ϊ��");
					}else{
						String jwd=e.getJingweidu();
						String[] arr=jwd.split(",");
						String j=arr[0].toString();
						String w=arr[1].toString();
						
						double latitude=Double.valueOf(j);
						double longitude=Double.valueOf(w);
						
	
						
						Intent i=new Intent(getApplicationContext(), BaiduMapActivity.class);
						i.putExtra("latitude", latitude);
						i.putExtra("longitude", longitude);
						startActivity(i);
					 }  
			  }else{   
				  
				  ErshoufangScrollview.requestDisallowInterceptTouchEvent(true);  
			  } 
			return false; 
			}

		
			});
		
		
		rlBaidumap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				MyToastShowCenter.CenterToast(getApplicationContext(), "����˵�ͼ������");
				
				if(e.getJingweidu()==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "��Դ�ľ�γ��Ϊ��");
				}else{
					String jwd=e.getJingweidu();
					String[] arr=jwd.split(",");
					String j=arr[0].toString();
					String w=arr[1].toString();
					
					double latitude=Double.valueOf(j);
					double longitude=Double.valueOf(w);
					
					Intent i=new Intent(getApplicationContext(), BaiduMapActivity.class);
					i.putExtra("latitude", latitude);
					i.putExtra("longitude", longitude);
					startActivity(i);
				}
		
				
			}
		});
		
		
		
	}
	private void initViews() {
		rbErshoufangGuanzhu=(RadioButton) findViewById(R.id.rb_ershoufangguanzhu);
		llBackErshoufangdetails=(LinearLayout) findViewById(R.id.ll_back_ershoufangdetails);
		rlBaidumap=(RelativeLayout) findViewById(R.id.rl_baidumap);
		ErshoufangScrollview=(MyReboundScrollView)findViewById(R.id.ershoufang_scrollview);
		
		
		mMapView=(MapView) findViewById(R.id.map_bmapView);
		mBaiduMAP=mMapView.getMap();
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);
		
		
		UiSettings settings=mBaiduMAP.getUiSettings();
		settings.setAllGesturesEnabled(false);
//		settings.setOverlookingGesturesEnabled(false);
//		settings.setScrollGesturesEnabled(false);
//		settings.setZoomGesturesEnabled(false);
//		
		/**
		 * �ı��ͼ�ı�����
		 */
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(18.0f);
		mBaiduMAP.setMapStatus(msu);
		
		
	}
	
	@Override
	public void showErshoufangData(ErShouFangDetails ershoufangdetail) {
		Message msg=new Message();
		msg.what=1;
		msg.obj=ershoufangdetail;
		handler.sendMessage(msg);
		
	}
	
	
	private void Show(){
		String url="http://www.taoshenfang.com"+e.getThumb();
		Glide.with(this).load(url).into(ivIMG);
		tvtitle.setText(e.getTitle());
		tvShoujia.setText(e.getZongjia()+"��");
		tvHuXing.setText(e.getShi()+"��"+e.getTing()+"��");
		tvershoufang_mianji.setText(e.getJianzhumianji()+"ƽ��");
		tvChaoxiang.setText(e.getChaoxiang()+"");
		tvguapaishijian.setText(e.getGuapaidate()+"");
		
		double zongjia=Integer.valueOf(e.getZongjia()).doubleValue();
		double mianji=Integer.valueOf(e.getJianzhumianji()).doubleValue();
		double junjia=zongjia/mianji;
		int x=(int) (junjia+0.5);
		tvJunjia.setText(x+"��Ԫ/ƽ");
		
		
		tvLouceng.setText(e.getCeng()+"/"+e.getZongceng());
		tvLouxing.setText(e.getJianzhutype()+"");
		tvQuyu.setText(e.getCityname()+" "+e.getAreaname());
		tvZhuangxiu.setText(e.getZhuangxiu()+"");
		TvFangling.setText(e.getFangling()+"��");
		TvFangyuanbianhao.setText(e.getBianhao()+"");
		TvXiaoqu.setText(e.getXiaoqu()+"");
//		
		tvHexinmaidian.setText(e.getHexinmaidian());
		tvJiaotongchuxing.setText(e.getJiaotong());
//		
		tvWuyeleixing.setText(e.getJiaoyiquanshu());
		if(e.getIsweiyi().equals("��")){
			tvMaifangjiatingweiyi.setText("Ψһ");
		}else{
			tvMaifangjiatingweiyi.setText("��Ψһ");
		}
	
		tvJushangcijiaoyi.setText(e.getShangcijiaoyi());;
////		tvMaifangjiatingshoutao.setText(e.get);
////		tvJizhengfangshi.setText(e.get);
		tvHousearea.setText(e.getJianzhumianji());;
////		tvZhuanrangshuifei.setText(e.getz);
////		tvDaikuanleixing.setText(e.get);
////		tvDaikuanjine;
////		tvDaikuanqixian;
////		tvJizhunlilv;
////		tvLixi;
////		tvHousetotilprice;
//		
		tvWeekhistroy.setText(e.getWeekviews());
		tvTotalhistroy.setText(e.getViews());
		
		/**
		 *  ���ðٶȵ�ͼ
		 *  ��λ����Դ��γ�� 
		 */
		try {
			String jwd=e.getJingweidu();
			String[] arr=jwd.split(",");
			String j=arr[0].toString();
			String w=arr[1].toString();
			
			double latitude=Double.valueOf(j);
			double longitude=Double.valueOf(w);
			
			LatLng latLng=new LatLng( longitude,latitude);
			
			MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
			
			/**
			 * 
			 */
//			MapStatus.Builder builder=new MapStatus.Builder();
//			mBaiduMAP.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
			/**
			 * 
			 */
			
			// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩  
			mCurrentMarker = BitmapDescriptorFactory  
			    .fromResource(R.drawable.icon_gcoding);  
			//����MarkerOption�������ڵ�ͼ�����Marker  
			OverlayOptions option = new MarkerOptions()  
			    .position(latLng)  
			    .icon(mCurrentMarker);  
			//�ڵ�ͼ�����Marker������ʾ  
			mBaiduMAP.addOverlay(option);
			mBaiduMAP.animateMapStatus(msu);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	

	@Override
	public void GuanZhuSuccess(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
	}
	

	@Override
	public void GuanZhuFail(String errorinfo) {
		MyToastShowCenter.CenterToast(getApplicationContext(), errorinfo);
	}
	
//	private class MyLocationListener implements BDLocationListener{
//
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			MyLocationData data=new MyLocationData.Builder()//
//					.accuracy(location.getRadius())//
//					.latitude(location.getLatitude())//
//					.longitude(location.getLongitude())//
//					.build();
//			
////			MyLocationConfiguration config=new MyLocationConfiguration(LocationMode.NORMAL, arg1, arg2);
//			
//			if(isFirstIn){
//				
//				isFirstIn=false;
//				LatLng ll=new LatLng(location.getLatitude(), location.getLongitude());
//				MapStatus.Builder builder=new MapStatus.Builder();
//				builder.target(ll).zoom(18.0f);
//				
//				
//				
//			}
//			
//			
//		}
//		
//	}

	

}
