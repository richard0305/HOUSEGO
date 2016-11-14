package com.dumu.housego;

import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ErshouFangProprietorActivity extends Activity {
	private LinearLayout llErshoufangProprietorBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ershou_fang_proprietor);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();
	}

	private void setListeners() {
		llErshoufangProprietorBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	private void setViews() {
		llErshoufangProprietorBack = (LinearLayout) findViewById(R.id.ll_ershoufang_proprietor_back);

	}
}
