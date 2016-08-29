package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class RentingMainActivity extends Activity {
	
	private LinearLayout llRentingBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_main);
		setViews();
		setListener();
	}
	private void setListener() {
		llRentingBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RentingMainActivity.this.finish();
				
			}
		});
		
	}
	private void setViews() {
		llRentingBack=(LinearLayout) findViewById(R.id.ll_renting_back);
		
	}


}
