package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MyRentingHouseAgentActivity extends Activity {
	private LinearLayout llBackRentingHouse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_renting_house);
		setViews();
		setListeners();

	}

	private void setListeners() {
		llBackRentingHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	private void setViews() {
		llBackRentingHouse = (LinearLayout) findViewById(R.id.ll_back_renting_house_agent);

	}

}
