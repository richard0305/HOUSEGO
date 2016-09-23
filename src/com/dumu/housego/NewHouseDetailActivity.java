package com.dumu.housego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class NewHouseDetailActivity extends Activity {
	private LinearLayout llNewHouseDetailBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_detail);
		setViews();
		setListener();
		
		String id=getIntent().getStringExtra("id");
		String catid=getIntent().getStringExtra("catid");
		
		
		
		
		
	}

	private void setViews() {
		llNewHouseDetailBack=(LinearLayout) findViewById(R.id.ll_new_house_detail_back);
		
	}

	private void setListener() {
		llNewHouseDetailBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
	
	

}
