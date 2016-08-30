package com.dumu.housego.activity;

import com.dumu.housego.R;
import com.dumu.housego.presenter.IPhoneCodePresenter;
import com.dumu.housego.presenter.PhoneCodePresenter;
import com.dumu.housego.view.IPhoneCodeView;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RegistActivity extends Activity implements IPhoneCodeView {
	private Button btnSendCode, btnRegist;
	private LinearLayout llBackLogin;
	private IPhoneCodePresenter presenter;
	private EditText etPhoneNumber;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		presenter = new PhoneCodePresenter(this);
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
				String number = etPhoneNumber.getText().toString();
				presenter.LoadMob(number + "");
				Toast.makeText(RegistActivity.this, "点击了验证码", Toast.LENGTH_SHORT).show();
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
		GradientDrawable drawable = new GradientDrawable();
		drawable.setShape(GradientDrawable.RECTANGLE); // 画框
		drawable.setStroke(1, R.color.send_code_rectangle);
		drawable.setColor(getResources().getColor(R.color.send_code_back));
		btnSendCode.setBackgroundDrawable(drawable);

	}

	private void setViews() {
		btnSendCode = (Button) findViewById(R.id.btn_sendcode);
		llBackLogin = (LinearLayout) findViewById(R.id.ll_back_login);
		btnRegist = (Button) findViewById(R.id.btn_Regist);
		etPhoneNumber = (EditText) findViewById(R.id.et_phonenumb);

	}

	@Override
	public void setData(String infomation) {
		Toast.makeText(RegistActivity.this, infomation.toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showData() {
		// TODO Auto-generated method stub

	}

}
