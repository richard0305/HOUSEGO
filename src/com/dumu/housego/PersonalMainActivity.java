package com.dumu.housego;

import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonalMainActivity extends Activity {
	private LinearLayout llPersonalBack;
	private RelativeLayout rlPersonalInfo, rlMyTouxiang;
	private TextView tvPersonalNickname,tvPersonalPhone;
	private UserInfo userinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_main);
		setViews();
		setListeners();
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		tvPersonalNickname.setText(userinfo.getNickname());
		tvPersonalPhone.setText(userinfo.getUsername());
		
		
	}

	private void setListeners() {
		llPersonalBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PersonalMainActivity.this.finish();

			}
		});
		
		rlMyTouxiang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		
		rlPersonalInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getApplicationContext(), ChangeUserInfoActivity.class));
			}
		});

	}

	private void setViews() {
		llPersonalBack = (LinearLayout) findViewById(R.id.ll_personal_back);
		rlPersonalInfo = (RelativeLayout) findViewById(R.id.rl_personal_info);
		rlMyTouxiang = (RelativeLayout) findViewById(R.id.rl_MyTouxiang);
		tvPersonalNickname=(TextView) findViewById(R.id.tv_personal_nickname);
		tvPersonalPhone=(TextView) findViewById(R.id.tv_personal_phone);
	}


}
