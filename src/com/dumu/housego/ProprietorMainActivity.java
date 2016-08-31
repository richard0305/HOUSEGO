package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ProprietorMainActivity extends Activity {

	private LinearLayout llProprietorBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proprietor_main);
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
		
		
	}
	private void setViews() {
		llProprietorBack=(LinearLayout) findViewById(R.id.ll_proprietor_back);
		
	}

}
