package com.dumu.housego;

import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ProprietorMainActivity extends Activity {

	private LinearLayout llProprietorBack;
	private RelativeLayout rlRentingProprietor;
	private RelativeLayout rlErshoufangProprietor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proprietor_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();

	}

	private void setListener() {
		llProprietorBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		rlErshoufangProprietor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), ErshouFangProprietorActivity.class));

			}
		});

		rlRentingProprietor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), RentingProprietorActivity.class));

			}
		});

	}

	private void setViews() {
		llProprietorBack = (LinearLayout) findViewById(R.id.ll_proprietor_back);
		rlRentingProprietor = (RelativeLayout) findViewById(R.id.rl_renting_proprietor);
		rlErshoufangProprietor = (RelativeLayout) findViewById(R.id.rl_ershoufang_proprietor);
	}

}
