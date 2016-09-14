package com.dumu.housego;

import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class HousePuchaseMainActivity extends Activity {
	private LinearLayout llHousePuchaseback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_puchase_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
	setViews();
	setListener();
	}
	private void setListener() {
		llHousePuchaseback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
	private void setViews() {
		llHousePuchaseback=(LinearLayout) findViewById(R.id.ll_house_puchase_back);
		
	}

}
