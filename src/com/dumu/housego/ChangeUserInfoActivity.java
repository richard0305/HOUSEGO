package com.dumu.housego;

import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IChangeUserInfoView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfoActivity extends Activity {
	private LinearLayout llBackChange;
	private TextView tvSave;
	private EditText etChangeNickname,etChangeAbout;
	private RadioButton rbChangeSexSecret,rbChangeSexMan,rbChangeSexWoman;
	private RadioGroup rgChangeSex;
	private UserInfo userinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user_info);
		FontHelper.injectFont(findViewById(android.R.id.content));
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		setViews();
		setListener();
		
	}
	private void setListener() {
		llBackChange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		tvSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					
					String userid=userinfo.getUserid();
					String nickname=etChangeNickname.getText().toString();
					String about=etChangeAbout.getText().toString();
					String sex=rbChangeSexMan.isChecked()?"1":(rbChangeSexWoman.isChecked()?"2":"0");
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "请先登录！", Toast.LENGTH_SHORT).show();
				}
				
				
				
				
				
				
				
			}
		});
		
	}
	private void setViews() {
		llBackChange=(LinearLayout) findViewById(R.id.ll_back_change);
		tvSave=(TextView) findViewById(R.id.tv_change_save);
		etChangeAbout=(EditText) findViewById(R.id.et_change_about);
		etChangeNickname=(EditText) findViewById(R.id.et_change_nickname);
		rbChangeSexMan=(RadioButton) findViewById(R.id.rb_change_sex_man);
		rbChangeSexSecret=(RadioButton) findViewById(R.id.rb_change_sex_secret);
		rbChangeSexWoman=(RadioButton) findViewById(R.id.rb_change_sex_woman);
		
		try {
			etChangeNickname.setText(userinfo.getNickname()+"");
			etChangeAbout.setText(userinfo.getAbout()+"");
			
			String sex=userinfo.getSex()+"";
			if(sex=="0"){
				rbChangeSexSecret.setChecked(true);
			}else{
				if(sex=="1"){
					rbChangeSexMan.setChecked(true);
				}else{
					rbChangeSexWoman.setChecked(true);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}
