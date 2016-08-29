package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ErShouFangMainActivity extends Activity {
	private LinearLayout llErshoufang;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_er_shou_fang_main);
		setViews();
		setListener();
	}
	private void setListener() {
		llErshoufang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ErShouFangMainActivity.this.finish();
				
			}
		});
		
	}
	private void setViews() {
		llErshoufang=(LinearLayout) findViewById(R.id.ll_ershoufang_back);
		
	}

}
