package com.dumu.housego;

import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MySettingMainActivity extends Activity {
	private LinearLayout llSettingBack;
	private TextView tvLoginOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_setting_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
	}

	private void setListener() {
		llSettingBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		tvLoginOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HouseGoApp.clearData(null);
				Intent logoutIntent = new Intent(getApplicationContext(), LoginActivity.class);
				// logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
				// Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(logoutIntent);

			}
		});

	}

	private void setViews() {
		llSettingBack = (LinearLayout) findViewById(R.id.ll_setting_back);
		tvLoginOut = (TextView) findViewById(R.id.tv_setting_LoginOut);
	}

	@Override
	protected void onStop() {

		super.onStop();
	}

	@Override
	protected void onDestroy() {

		finish();

		super.onDestroy();
	}

}
