package com.dumu.housego.activity;

import com.dumu.housego.R;
import com.dumu.housego.entity.User;
import com.dumu.housego.presenter.IPhoneCodePresenter;
import com.dumu.housego.presenter.IRegistPresenter;
import com.dumu.housego.presenter.PhoneCodePresenter;
import com.dumu.housego.presenter.RegistPresenter;
import com.dumu.housego.view.IPhoneCodeView;
import com.dumu.housego.view.IRegistView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistActivity extends Activity implements IPhoneCodeView,IRegistView {
	private Button btnSendCode, btnRegist;
	private LinearLayout llBackLogin;
	private IPhoneCodePresenter presenter;
	private IRegistPresenter registpresenter;
	
	
	private EditText etPhoneNum;
	private EditText etSmscode;
	private EditText etPassword;
	private EditText etRepassword;
	private RadioGroup rgRegistType;
	private RadioButton rbAgent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		presenter = new PhoneCodePresenter(this);
		registpresenter=new RegistPresenter(this);
		
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
				String number = etPhoneNum.getText().toString();
				presenter.LoadMob(number + "");
				Toast.makeText(RegistActivity.this, "点击了验证码", Toast.LENGTH_SHORT).show();
			}
		});

		btnRegist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				User user=new User();
				String modelid=rbAgent.isChecked()?"36":"35";
				user.setUsername(etPhoneNum.getText().toString());
				user.setModelid(modelid);
				Log.i("yanglijun", "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+modelid+"");
				user.setPassword(etPassword.getText().toString());
				user.setPassword2(etRepassword.getText().toString());
				user.setUsername(etPhoneNum.getText().toString());
				user.setYzm(etSmscode.getText().toString());
				registpresenter.Regist(user);
				Toast.makeText(RegistActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();

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
		etPhoneNum= (EditText) findViewById(R.id.et_phonenumb);
		etPassword= (EditText) findViewById(R.id.et_password);
		etRepassword= (EditText) findViewById(R.id.et_repassword);
		etSmscode=(EditText) findViewById(R.id.et_smscode);
		rbAgent=(RadioButton) findViewById(R.id.rb_agent);
		
		

	}

	@Override
	public void setData(String infomation) {
		Toast.makeText(RegistActivity.this, infomation.toString(), Toast.LENGTH_SHORT).show();
	}



	@Override
	public void registSuccess() {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		
	}

	@Override
	public void registFail(String errorMessage) {
		Toast.makeText(RegistActivity.this, errorMessage.toString(), Toast.LENGTH_SHORT).show();
		
	}


}
