package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PersonalMainActivity extends Activity {
	private LinearLayout llPersonalBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_main);
		setViews();
		setListeners();
	}
	private void setListeners() {
		llPersonalBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PersonalMainActivity.this.finish();
				
			}
		});
		
	}
	private void setViews() {
		llPersonalBack=(LinearLayout) findViewById(R.id.ll_personal_back);
		
	}

	
}
