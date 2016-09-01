package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class FindPasswordMainActivity extends Activity {
	private LinearLayout llFindPasswordBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password_main);
		setViews();
		setListener();
		
	}
	private void setListener() {
		llFindPasswordBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	private void setViews() {
		llFindPasswordBack=(LinearLayout) findViewById(R.id.ll_find_pasword_back);
		
	}

}
