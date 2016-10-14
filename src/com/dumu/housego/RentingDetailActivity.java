package com.dumu.housego;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.bumptech.glide.Glide;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.presenter.IRentingDetailPresenter;
import com.dumu.housego.presenter.RentingDetailPresenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IRentingDetailView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RentingDetailActivity extends Activity implements IRentingDetailView{
	private LinearLayout llBackRentingdetails;
	private IRentingDetailPresenter presenter;
	private RentingDetail b;
	
	@ViewInject(R.id.iv_renting_pic)ImageView ivRentingpic;
	@ViewInject(R.id.ershoufang_shoujia)TextView tvRentingshoujia;
	@ViewInject(R.id.ershoufang_huxing)TextView tvRentinghuxing;
	@ViewInject(R.id.ershoufang_mianji)TextView tvRentingmianji;
	
	@ViewInject(R.id.renting_tedian)TextView tvRentingTedian;
	@ViewInject(R.id.renting_louceng)TextView tvRentingLouceng;
	@ViewInject(R.id.renting_chaoxiang)TextView tvRentingChaoxiang;
	@ViewInject(R.id.renting_zhuangxiu)TextView tvRentingZhuangxiu;
	@ViewInject(R.id.renting_location)TextView tvRentingLocation;
	@ViewInject(R.id.renting_mianji)TextView tvRentingMianji;
	@ViewInject(R.id.renting_huxing)TextView tvRentingHuxing;
	@ViewInject(R.id.renting_fangshi)TextView tvRentingFangshi;
	@ViewInject(R.id.renting_fabushijian)TextView tvRentingFabushijian;
	@ViewInject(R.id.renting_ditie)TextView tvRentingDitie;
	@ViewInject(R.id.renting_xiaoqu)TextView tvRentingXiaoqu;
	@ViewInject(R.id.tv_fangyuanliangdian)TextView tvFangyuanliangdian;
	@ViewInject(R.id.tv_jiaotongchuxing)TextView tvJiaotongchuxing;
	@ViewInject(R.id.tv_renting_lishijilu)TextView tvRentingLishijilu;
	
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				b=(RentingDetail) msg.obj;
				Log.e("==============", "`````+++++++++~~~~~~"+b);
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
		setContentView(R.layout.activity_renting_detail);
		setViews();
		x.view().inject(this);
		setListener();
		presenter=new RentingDetailPresenter(this);
		
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		presenter.LoadRenting(catid, id);
		
		
	}
	
	protected void Show() {

		if(b.getThumb().startsWith("http://www.tao")){
			
			Glide.with(getApplicationContext()).load(b.getThumb()).into(ivRentingpic);
		}else{
			String url="http://www.taoshenfang.com"+b.getThumb();
			Log.e("yanglijun", "------========---------+++++++++"+url);
			Glide.with(getApplicationContext()).load(url).into(ivRentingpic);
		}
		
		tvRentinghuxing.setText(" "+b.getShi()+"室"+b.getTing()+"厅");
		tvRentingmianji.setText(" "+b.getMianji()+"平米");
		tvRentingshoujia.setText(" "+b.getZujin()+"元/月");
		
		 tvRentingTedian.setText(" "+b.getFangwupeitao());;
		 tvRentingLouceng.setText(" "+b.getCeng()+"/"+b.getZongceng());
		 tvRentingChaoxiang.setText(" "+b.getChaoxiang());
		 tvRentingZhuangxiu.setText(" "+b.getZhuangxiu());
		 tvRentingLocation.setText(" "+b.getCityname()+" "+b.getAreaname());;
		tvRentingMianji.setText(" "+b.getMianji()+"㎡");;
		tvRentingHuxing.setText(" "+b.getHuxingjieshao());;
		tvRentingFangshi.setText(" "+b.getZulin());
		tvRentingFabushijian.setText(" "+TimeTurnDate.getStringDate(b.getInputtime()));;
		 tvRentingDitie.setText(" "+b.getDitiexian());
		 tvRentingXiaoqu.setText(" "+b.getXiaoqu());
		 tvFangyuanliangdian.setText(" "+b.getShenghuopeitao());
		 tvJiaotongchuxing.setText(" "+b.getJiaotong());
		 tvRentingLishijilu.setText("近一个月新增记录"+b.getMonthviews()+"位");
		
		
		
		
		
	}
	
	private void setViews() {
		llBackRentingdetails=(LinearLayout) findViewById(R.id.ll_back_rentingdetails);
		
	}
	
	private void setListener() {
		llBackRentingdetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			finish();
			}
		});
	}
	
	@Override
	public void GetRenting(RentingDetail detail) {
	Message msg=new Message();
	msg.what=1;
	msg.obj=detail;
	handler.sendMessage(msg);
		
	}


}
