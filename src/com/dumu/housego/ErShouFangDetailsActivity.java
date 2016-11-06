package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ErShouFangDetailPresenter;
import com.dumu.housego.presenter.GuanZhuHousePresenter;
import com.dumu.housego.presenter.IErShouFangDetailPresenter;
import com.dumu.housego.presenter.IGuanZhuHousePresenter;
import com.dumu.housego.presenter.IYuYueHousePresenter;
import com.dumu.housego.presenter.YuYueHousePresenter;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IErShouFangDetailView;
import com.dumu.housego.view.IGuanZhuHouseView;
import com.dumu.housego.view.IYuYueHouseView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ErShouFangDetailsActivity extends Activity implements IYuYueHouseView, IErShouFangDetailView,IGuanZhuHouseView{
	private String dateyuyue;
	private String timeyuyue;
	
	
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	private RadioButton rbErshoufangGuanzhu;
	private ErShouFangDetails e;
	private IErShouFangDetailPresenter esfPresenter;
	private IGuanZhuHousePresenter guanzhuPresenter;
	private LinearLayout llBackErshoufangdetails;
	private RelativeLayout rlBaidumap;
	 UserInfo userinfo=HouseGoApp.getContext().getCurrentUserInfo();
	private MyReboundScrollView ErshoufangScrollview;
	private boolean isFirstIn=true;
	private TextView tvYuyuekanfang;
	public LinearLayout llBackYuyuekanfang;
	private IYuYueHousePresenter yuyuepresenter;
	
	private Button btnYuyuekanfang;
	
	
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
	
	
	private Spinner yuyuedateSpinner;
	private Spinner yuyuetimeSpinner;
	
	private List<String> spinnerList1 = new ArrayList<String>();
	private List<String> spinnerList2 = new ArrayList<String>();
	private ArrayAdapter<String> Spinneradapter1;   
	private ArrayAdapter<String> Spinneradapter2;   
	
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
	
	private RelativeLayout ershoufang_feiyuyue;
	private RelativeLayout rlErshoufangYuyuewindows;
	private BitmapDescriptor mCurrentMarker;
	private PopupWindow pop;
	private LinearLayout ll_popup;
	private LinearLayout ll_cancle;
	private View parentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		parentView=getLayoutInflater().inflate(R.layout.activity_er_shou_fang_details, null);
        
		setContentView(parentView);
		x.view().inject(this);
		initViews();
		showPopWindow();
		initListener();
		
		
		
		long times=System.currentTimeMillis();
		long oneday=24L * 60 * 60 * 1000;
		String today=TimeTurnDate.getStringDate(times);
		String tomorrow=TimeTurnDate.getStringDate(times+oneday);
		String afterTomrrow=TimeTurnDate.getStringDate(times+2*oneday);
		spinnerList1.add(today);
		spinnerList1.add(tomorrow);
		spinnerList1.add(afterTomrrow);
		
		spinnerList2.add("09:00-12:00");
		spinnerList2.add("13:00-15:00");
		
		Spinneradapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerList1); 
		Spinneradapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerList2); 
		Spinneradapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		Spinneradapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		yuyuedateSpinner.setAdapter(Spinneradapter1); 
		yuyuetimeSpinner.setAdapter(Spinneradapter2);
		
		
		
		esfPresenter=new ErShouFangDetailPresenter(this);
		guanzhuPresenter=new GuanZhuHousePresenter(this);
		yuyuepresenter=new YuYueHousePresenter(this);
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		esfPresenter.FindErShouFangdetail(catid, id);
		
		Log.e("2016-10-9 9:10", "yanglijun-------"+catid+"   "+id);
		
		
		
		
	}
	

	
	
	
	private void showPopWindow() {
pop = new PopupWindow(ErShouFangDetailsActivity.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		ll_cancle=(LinearLayout) view.findViewById(R.id.ll_cancle);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_btn1);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_btn2);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_cancel);
		bt1.setClickable(false);
		bt1.setText("预约看房管理");
		bt2.setText("立即预约");
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			
				
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ershoufang_feiyuyue.setVisibility(View.GONE);
				rlErshoufangYuyuewindows.setVisibility(View.VISIBLE);
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		
		
	}





	private void initListener() {
		rbErshoufangGuanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "还没有登录，请先登录！");
				}else{
					//��¼�󣬼���û��Ƿ���Message��û������ʾ��ϢΪ�հ�ť
					String fromid=e.getId()+"";
					String fromtable="ershou";
					String userid=userinfo.getUserid();
					String username=userinfo.getUsername();
					String type="二手房";
					String t="1";
					guanzhuPresenter.LoadGuanZhuHouse(fromid, fromtable, userid, username, type, t);
				}

				
				MyToastShowCenter.CenterToast(getApplicationContext(), "点击了关注");
			}
		});
		
		llBackErshoufangdetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	
		
		
		rlBaidumap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				MyToastShowCenter.CenterToast(getApplicationContext(), "点击了地图！！！");
				
				if(e.getJingweidu()==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
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
		
		tvYuyuekanfang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "您还没有登录，请先登录");
					handler.postDelayed(new Runnable() {
						public void run() {
							startActivity(new Intent(getApplicationContext(),LoginActivity.class));
						}
					}, 1000);
				}else{
					Animation anim=AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this, R.anim.activity_translate_in);
					
					ll_popup.setAnimation(anim);
					ll_cancle.setAnimation(anim);
					pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				}
				
				
				
			}
		});
		
		mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				
				if(e.getJingweidu()==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
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
		
		
		
		 llBackYuyuekanfang.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ershoufang_feiyuyue.setVisibility(View.VISIBLE);
					rlErshoufangYuyuewindows.setVisibility(View.GONE);
					
				}
			});
			 
			 yuyuedateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				 dateyuyue=spinnerList1.get(position);
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
					
				}
			});
			 
			 
			 
			 yuyuetimeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				timeyuyue=spinnerList2.get(position);
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
						
					}
				});
			 
			 
			 
			 
			 btnYuyuekanfang.setOnClickListener(new OnClickListener() {


				@Override
				public void onClick(View v) {
					
				String formid=e.getId();
				 String fromtable="ershou";
				 String username=userinfo.getUsername();
				 String fromuser=e.getUsername();
				 String type="二手房";
					
				String yuyuedate=dateyuyue;
				 String yuyuetime=timeyuyue;
				 String t="1";
				 
			
				yuyuepresenter.loadyuyue(formid, fromtable, username, fromuser, type, yuyuedate, yuyuetime, t);
					
				handler.postDelayed(new Runnable() {
					public void run() {
						ershoufang_feiyuyue.setVisibility(View.VISIBLE);
						rlErshoufangYuyuewindows.setVisibility(View.GONE);	
					}
				}, 1000);
				
				}
			});
			
		
		
		
	}
	protected void NewAlertDialog() {
		
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();  
		alertDialog.show();  
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.dialog_main_info);  
		TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);  
		tv_title.setText("是否去预约");  
		TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);  
		Button btnCancle=(Button) window.findViewById(R.id.btn_dialog_cancle);
		Button btnSure=(Button) window.findViewById(R.id.btn_dialog_sure);
		btnCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
		btnSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ershoufang_feiyuyue.setVisibility(View.GONE);
				rlErshoufangYuyuewindows.setVisibility(View.VISIBLE);
				alertDialog.cancel();
			}
		});
		
		
		/**
		 * 
		 */
		 
		 
		
	}


	private void initViews() {
		rbErshoufangGuanzhu=(RadioButton) findViewById(R.id.rb_ershoufangguanzhu);
		llBackErshoufangdetails=(LinearLayout) findViewById(R.id.ll_back_ershoufangdetails);
		rlBaidumap=(RelativeLayout) findViewById(R.id.rl_baidumap);
		ErshoufangScrollview=(MyReboundScrollView)findViewById(R.id.ershoufang_scrollview);
		tvYuyuekanfang=(TextView) findViewById(R.id.tv_yuyuekanfang);
		
		yuyuedateSpinner=(Spinner) findViewById(R.id.spinner_yuyuedate);
		yuyuetimeSpinner=(Spinner) findViewById(R.id.spinner_yuyuetime);
		
		ershoufang_feiyuyue=(RelativeLayout) findViewById(R.id.ershoufang_feiyuyue);
		rlErshoufangYuyuewindows=(RelativeLayout) findViewById(R.id.rl_ershoufang_yuyuewindows);
		
		llBackYuyuekanfang=(LinearLayout) findViewById(R.id.ll_back_yuyuekanfnag);
		btnYuyuekanfang=(Button) findViewById(R.id.btn_yuyuekanfang);
		
		mMapView=(MapView) findViewById(R.id.ershou_bmapView);
		mBaiduMAP=mMapView.getMap();
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);
		

		
		
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
		tvShoujia.setText(e.getZongjia()+"万");
		tvHuXing.setText(e.getShi()+"室"+e.getTing()+"厅");
		tvershoufang_mianji.setText(e.getJianzhumianji()+"平米");
		tvChaoxiang.setText(e.getChaoxiang()+"");
		tvguapaishijian.setText(e.getGuapaidate()+"");
		
		double zongjia=Integer.valueOf(e.getZongjia()).doubleValue();
		double mianji=Integer.valueOf(e.getJianzhumianji()).doubleValue();
		double junjia=zongjia/mianji;
		int x=(int) (junjia+0.5);
		tvJunjia.setText(x+"万元/平");
		
		
		tvLouceng.setText(e.getCeng()+"/"+e.getZongceng());
		tvLouxing.setText(e.getJianzhutype()+"");
		tvQuyu.setText(e.getCityname()+" "+e.getAreaname());
		tvZhuangxiu.setText(e.getZhuangxiu()+"");
		TvFangling.setText(e.getFangling()+"年");
		TvFangyuanbianhao.setText(e.getBianhao()+"");
		TvXiaoqu.setText(e.getXiaoqu()+"");
//		
		tvHexinmaidian.setText(e.getHexinmaidian());
		tvJiaotongchuxing.setText(e.getJiaotong());
//		
		tvWuyeleixing.setText(e.getJiaoyiquanshu());
		if(e.getIsweiyi().equals("是")){
			tvMaifangjiatingweiyi.setText("唯一");
		}else{
			tvMaifangjiatingweiyi.setText("非唯一");
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
			
			// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩  
			MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
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


	@Override
	public void yuYueSuccess(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}


	@Override
	public void yuYueFail(String error) {
		MyToastShowCenter.CenterToast(getApplicationContext(), error);
		
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
