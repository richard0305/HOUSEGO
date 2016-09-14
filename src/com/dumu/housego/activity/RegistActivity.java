package com.dumu.housego.activity;

import com.dumu.housego.FindPasswordMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.entity.User;
import com.dumu.housego.presenter.CheckPhoneRegistPresenter;
import com.dumu.housego.presenter.ICheckPhoneRegistPresenter;
import com.dumu.housego.presenter.IPhoneCodePresenter;
import com.dumu.housego.presenter.IRegistPresenter;
import com.dumu.housego.presenter.PhoneCodePresenter;
import com.dumu.housego.presenter.RegistPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.ICheckPhoneRegistView;
import com.dumu.housego.view.IPhoneCodeView;
import com.dumu.housego.view.IRegistView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistActivity extends Activity implements IPhoneCodeView,IRegistView,ICheckPhoneRegistView {
	private Button btnSendCode, btnRegist;
	private LinearLayout llBackLogin;
	private IPhoneCodePresenter presenter;
	private IRegistPresenter registpresenter;
	private ICheckPhoneRegistPresenter checkPresenter;
	
	private EditText etPhoneNum;
	private EditText etSmscode;
	private EditText etPassword;
	private EditText etRepassword;
	private RadioGroup rgRegistType;
	private RadioButton rbAgent;

	 Thread thread=null;
	 private boolean tag12=true;
	    private int i=60;
	    public boolean  isChange=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		presenter = new PhoneCodePresenter(this);
		registpresenter=new RegistPresenter(this);
		checkPresenter=new CheckPhoneRegistPresenter(this);
		setViews();
		btnRegist.setEnabled(false);
		btnSendCode.setEnabled(false);
		setOptains();
		setListener();
		FontHelper.injectFont(findViewById(android.R.id.content));
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
				btnSendCode.setClickable(true);
	            isChange = true;
	            changeBtnGetCode();
				
			}
		});

		btnRegist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				User user=new User();
				String modelid=rbAgent.isChecked()?"36":"35";
				user.setUsername(etPhoneNum.getText().toString());
				user.setModelid(modelid);
				user.setPassword(etPassword.getText().toString());
				user.setPassword2(etRepassword.getText().toString());
				user.setUsername(etPhoneNum.getText().toString());
				user.setYzm(etSmscode.getText().toString());
				registpresenter.Regist(user);
				Toast.makeText(RegistActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();

			}
		});
		
		
		etPhoneNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length()==11){
					btnRegist.setEnabled(true);
					btnSendCode.setEnabled(true);
					String mob=etPhoneNum.getText().toString();
					checkPresenter.checkPhone(mob);
					
				}else{
					btnRegist.setEnabled(false);
					btnSendCode.setEnabled(false);
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

	}

	
	protected void changeBtnGetCode() {
		thread = new Thread() {
            @Override
            public void run() {
                if (tag12) {
                    while (i > 0) {
                        i--;
                        if (RegistActivity.this== null) {
                            break;
                        }
                        //当文本框内容改变时，结束循环。
//                        if (isChange && !btnCode.isClickable()) {
//                            isChange = false;
//                            break;
//                        }
                        RegistActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            	btnSendCode.setText("重发(" + i + "s)");
                            	btnSendCode.setClickable(false);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tag12 = false;
                }
                i = 60;
                tag12 = true;
                if (RegistActivity.this != null) {
                	RegistActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        	btnSendCode.setText("发送验证码");
                        	btnSendCode.setClickable(true);
                        }
                    });
                }
            };
        };
        thread.start();
	
		
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
		Toast.makeText(RegistActivity.this, "发送成功！", Toast.LENGTH_SHORT).show();
	}



	@Override
	public void registSuccess() {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		
	}

	@Override
	public void registFail(String errorMessage) {
		Toast.makeText(RegistActivity.this, errorMessage.toString(), Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void CheckSuccess(String info) {
		Log.i("yanglijun","info=------------------>>>>>>>>>>>>>>>>>>>>>" +info+"");
		Toast.makeText(getApplicationContext(), info+"", Toast.LENGTH_SHORT).show();
	
		
	}

	@Override
	public void CheckFail(String errorMessage) {
		Toast.makeText(getApplicationContext(), errorMessage+"", Toast.LENGTH_SHORT).show();
		
	}


}
