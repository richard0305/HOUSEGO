package com.dumu.housego.activity;

import com.dumu.housego.R;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RegistActivity extends Activity {
	private Button btnSendCode,btnRegist;
	private LinearLayout llBackLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		setViews();
		setOptains();
		setListener();
		
	}

	private void setListener() {
		llBackLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btnSendCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(RegistActivity.this, "暂时没有接入数据", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	btnRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(RegistActivity.this, "暂时没有接入数据", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
	}
	

	private void setOptains() {
		/*
		 * 给 button 添加边框及底色
		 */
		GradientDrawable drawable=new GradientDrawable();
		drawable.setShape(GradientDrawable.RECTANGLE); // 画框
		drawable.setStroke(1, R.color.send_code_rectangle);
		drawable.setColor(getResources().getColor(R.color.send_code_back));
		btnSendCode.setBackgroundDrawable(drawable);
		
	}

	private void setViews() {
		btnSendCode=(Button) findViewById(R.id.btn_sendcode);
		llBackLogin=(LinearLayout) findViewById(R.id.ll_back_login);
		btnRegist=(Button) findViewById(R.id.btn_Regist);
		
	}

	
}
