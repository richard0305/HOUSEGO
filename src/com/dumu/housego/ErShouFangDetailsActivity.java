package com.dumu.housego;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ErShouFangDetailPresenter;
import com.dumu.housego.presenter.GuanZhuHousePresenter;
import com.dumu.housego.presenter.IErShouFangDetailPresenter;
import com.dumu.housego.presenter.IGuanZhuHousePresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IErShouFangDetailView;
import com.dumu.housego.view.IGuanZhuHouseView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ErShouFangDetailsActivity extends Activity implements IErShouFangDetailView,IGuanZhuHouseView{
	
	private RadioButton rbErshoufangGuanzhu;
	private ErShouFangDetails e;
	private IErShouFangDetailPresenter esfPresenter;
	private IGuanZhuHousePresenter guanzhuPresenter;
	private LinearLayout llBackErshoufangdetails;
	
	private UserInfo userinfo;
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
        
		setContentView(R.layout.activity_er_shou_fang_details);
		x.view().inject(this);
		initViews();
		initListener();
		esfPresenter=new ErShouFangDetailPresenter(this);
		guanzhuPresenter=new GuanZhuHousePresenter(this);
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		esfPresenter.FindErShouFangdetail(catid, id);
		
		Log.e("2016-10-9 9:10", "yanglijun-------"+catid+"   "+id);
	}
	
	
	  @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
	        mMapView.onPause();  
	        }  
	
	
	
	private void initListener() {
		rbErshoufangGuanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				userinfo=HouseGoApp.getContext().getCurrentUserInfo();

				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "还没有登录，请先登录！");
				}else{
					//登录后，检测用户是否有Message，没有则显示消息为空按钮
					String fromid=e.getId()+"";
//					String fromid="185";
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
		
	}
	private void initViews() {
		rbErshoufangGuanzhu=(RadioButton) findViewById(R.id.rb_ershoufangguanzhu);
		llBackErshoufangdetails=(LinearLayout) findViewById(R.id.ll_back_ershoufangdetails);
		
		mMapView=(MapView) findViewById(R.id.map_bmapView);
		mBaiduMAP=mMapView.getMap();
		
		
		/**
		 * 改变地图的比例尺
		 */
//		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(15.0f);
//		mBaiduMAP.setMapStatus(msu);
		
		
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
		 *  设置百度地图
		 *  定位到房源经纬度 
		 */
		try {
			String jwd=e.getJingweidu();
			String[] arr=jwd.split(",");
			String j=arr[0].toString();
			String w=arr[1].toString();
			
			double latitude=Double.valueOf(j);
			double longitude=Double.valueOf(w);
			MyToastShowCenter.CenterToast(getApplicationContext(),"经"+latitude+" 纬"+longitude);
			
			LatLng latLng=new LatLng(latitude, longitude);
			MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
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

}
