package com.dumu.housego;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.bumptech.glide.Glide;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.presenter.INewHouseDetailPresenter;
import com.dumu.housego.presenter.NewHouseDetailPresenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.INewHouseDetailView;

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

public class NewHouseDetailActivity extends Activity implements INewHouseDetailView{
	private  INewHouseDetailPresenter presenter;
	private NewHouseDetail e;
	@ViewInject(R.id.ll_new_house_detail_back)LinearLayout llNewHouseDetailBack;
	@ViewInject(R.id.tv_newhousedetail_price)TextView tvNewhousedetailPrice;
	@ViewInject(R.id.iv_new_house_detail)ImageView ivNewHouse;
	@ViewInject(R.id.tv_new_house_detail_title)TextView tvTitle;
	@ViewInject(R.id.tv_newhousedetail_title)TextView tvTitle1;
	@ViewInject(R.id.tv_newhousedetail_housetype)TextView tvHousetype;
	@ViewInject(R.id.tv_newhousedetail_kaipanshijian)TextView tvkaipanshijian;
	@ViewInject(R.id.tv_newhousedetail_jiaofangshijian)TextView tvjiaofangshijian;
	@ViewInject(R.id.tv_newhousedetail_updatetime)TextView tvNewhousedetailUpdatetime;
	@ViewInject(R.id.tv_newhousedetail_maintype)TextView tvNewhousedetailMaintype;
	@ViewInject(R.id.tv_newhousedetail_loupandizhi)TextView tvNewhousedetailLoupandizhi;
	
	@ViewInject(R.id.tv_newhousedetail_kaifashang)TextView tvNewhousedetailKaifashang;
	@ViewInject(R.id.tv_newhousedetail_zuixinkaipan)TextView tvNewhousedetailZuixinkaipan;
	@ViewInject(R.id.tv_newhousedetail_chanquannianxian)TextView tvNewhousedetailChanquannianxian;
	@ViewInject(R.id.tv_newhousedetail_zuixinjiaofang)TextView tvNewhousedetailZuixinjiaofang;
	@ViewInject(R.id.tv_newhousedetail_dianping)TextView tvNewhousedetailDianping;
	
	
	
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				e=(NewHouseDetail) msg.obj;
				
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
		setContentView(R.layout.activity_new_house_detail);
		x.view().inject(this);
		setListener();
		String id=getIntent().getStringExtra("Id");
		String catid=getIntent().getStringExtra("catid");
		presenter=new NewHouseDetailPresenter(this);
		presenter.FindNewHousedetail(catid, id);
		
	}

	protected void Show() {
		
		try {
			if(e.getThumb().startsWith("http://www.tao")){
				Glide.with(this).load(e.getThumb()).into(ivNewHouse);
			}else{
				String url="http://www.taoshenfang.com"+e.getThumb();
				Glide.with(this).load(url).into(ivNewHouse);
			}
			tvNewhousedetailLoupandizhi.setText(e.getLoupandizhi());
			tvNewhousedetailMaintype.setText(e.getZhulihuxing());
			tvNewhousedetailUpdatetime.setText(TimeTurnDate.getStringDate(e.getUpdatetime()));
			
			tvHousetype.setText(e.getFangwuyongtu()+"");
			tvjiaofangshijian.setText(e.getJiaofangdate()+"");
			tvkaipanshijian.setText(e.getKaipandate()+"");
			tvNewhousedetailPrice.setText(e.getJunjia()+"ิช/ฉO");
			tvTitle.setText(e.getTitle()+"");
			tvTitle1.setText(e.getTitle()+"");
			
			tvNewhousedetailKaifashang.setText(e.getKaifashang());;
			tvNewhousedetailZuixinkaipan.setText(e.getKaipandate());;
			tvNewhousedetailChanquannianxian.setText(e.getChanquannianxian());;
			tvNewhousedetailZuixinjiaofang.setText(e.getJiaofangdate());;
			tvNewhousedetailDianping.setText(e.getDianping());
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("TAG", "TAG"+e.getMessage());
		}

		
		
		
	}


	private void setListener() {
		llNewHouseDetailBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void showNewHouseDetailData(NewHouseDetail news) {
		Message msg=new Message();
		msg.what=1;
		msg.obj=news;
		
		handler.sendMessage(msg);
	}
	
	

}
