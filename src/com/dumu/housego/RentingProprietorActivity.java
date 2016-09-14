package com.dumu.housego;

import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class RentingProprietorActivity extends Activity {
	private LinearLayout llRentingProprietorBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_proprietor);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();
	}
	private void setListeners() {
		llRentingProprietorBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
	private void setViews() {
		llRentingProprietorBack=(LinearLayout) findViewById(R.id.ll_renting_proprietor_back);
		
	}

}
