package com.dumu.housego;

import com.bumptech.glide.Glide;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.presenter.INewHouseDetailPresenter;
import com.dumu.housego.presenter.NewHouseDetailPresenter;
import com.dumu.housego.view.INewHouseDetailView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewHouseDetailActivity extends Activity implements INewHouseDetailView{
	private LinearLayout llNewHouseDetailBack;
	private ImageView ivNewHouseDetail;
	private TextView  tvNewHouseDetailTitle;
	private TextView tvNewhousedetailPrice;
	private  NewHouseDetail newhousedetail;
	private  INewHouseDetailPresenter presenter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_detail);
		setViews();
		setListener();
		
		String id=getIntent().getStringExtra("id");
		String catid=getIntent().getStringExtra("catid");
		
		
		presenter=new NewHouseDetailPresenter(this);
		presenter.FindNewHousedetail(catid, id);
		
		
		
	}

	private void setViews() {
		llNewHouseDetailBack=(LinearLayout) findViewById(R.id.ll_new_house_detail_back);
		ivNewHouseDetail=(ImageView) findViewById(R.id.iv_new_house_detail);
		tvNewHouseDetailTitle=(TextView) findViewById(R.id.tv_new_house_detail_title);
		tvNewhousedetailPrice=(TextView) findViewById(R.id.tv_newhousedetail_price);
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
	public void showData(NewHouseDetail newhousedetail) {
		this.newhousedetail=newhousedetail;
		
		if(newhousedetail.getThumb()!=null){
			String url="http://www.taoshenfang.com"+newhousedetail.getThumb();
			Log.e("yanglijun", "------========---------+++++++++"+url);
			Glide.with(getApplicationContext()).load(url).into(ivNewHouseDetail);
		}else{
			ivNewHouseDetail.setImageResource(R.drawable.touxiang);
		}
		tvNewHouseDetailTitle.setText(newhousedetail.getTitle());
		tvNewhousedetailPrice.setText(newhousedetail.getJunjia());
		
	}
	
	

}
