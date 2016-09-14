package com.dumu.housego;

import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MapHouseMainActivity extends Activity {
	private RadioButton rbErShouFang,rbNewHouse,rbRenting;
	private LinearLayout llMapHouseBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_house_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();
		rbErShouFang.setTextColor(getResources().getColor(R.color.button_ckeck));
	}
	private void setViews() {
		rbErShouFang=(RadioButton) findViewById(R.id.btn_ershoufang);
		rbNewHouse=(RadioButton) findViewById(R.id.btn_newhouse);
		rbRenting=(RadioButton) findViewById(R.id.btn_renting);
		llMapHouseBack=(LinearLayout) findViewById(R.id.ll_map_house_back);
	}
	private void setListeners() {
		
		rbErShouFang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rbErShouFang.setTextColor(getResources().getColor(R.color.button_ckeck));
				rbNewHouse.setTextColor(getResources().getColor(R.color.button_unckeck));
				rbRenting.setTextColor(getResources().getColor(R.color.button_unckeck));
			}
		});
		
		rbNewHouse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rbErShouFang.setTextColor(getResources().getColor(R.color.button_unckeck));
				rbNewHouse.setTextColor(getResources().getColor(R.color.button_ckeck));
				rbRenting.setTextColor(getResources().getColor(R.color.button_unckeck));
			}
		});
		rbRenting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rbErShouFang.setTextColor(getResources().getColor(R.color.button_unckeck));
				rbNewHouse.setTextColor(getResources().getColor(R.color.button_unckeck));
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

	
}
