package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class RentingDetailActivity extends Activity {
	private LinearLayout llBackRentingdetails;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_detail);
		setViews();
		setListener();
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		
		
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


}
