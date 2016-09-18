package com.dumu.housego;

import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.presenter.FindPasswordCodePresenter;
import com.dumu.housego.presenter.FindPasswordPresenter;
import com.dumu.housego.presenter.IFindPasswordCodePresenter;
import com.dumu.housego.presenter.IFindPasswordPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IFindPasswordCodeView;
import com.dumu.housego.view.IFindPasswordView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FindPasswordMainActivity extends Activity implements IFindPasswordCodeView, IFindPasswordView {
	private LinearLayout llFindPasswordBack;
	private Button btnFindSendcode, btnFindSubmit;
	private EditText etFindPhonenumb;
	private EditText etFindSmscode;
	private EditText etFindPassword;
	private EditText etFindRepassword;

	private IFindPasswordCodePresenter findcodepresenter;
	private IFindPasswordPresenter findpasswordpresenter;
	private Thread thread = null;
	private boolean tag = true;
	private int i = 60;
	public boolean isChange = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		findpasswordpresenter = new FindPasswordPresenter(this);
		setViews();
		setListener();
		findcodepresenter = new FindPasswordCodePresenter(this);

	}

	private void setListener() {
		llFindPasswordBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btnFindSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				new Thread() {
					public void run() {
						String phonenum = etFindPhonenumb.getText().toString();
						String smscode = etFindSmscode.getText().toString();
						String password = etFindPassword.getText().toString();
						String password2 = etFindRepassword.getText().toString();
						Log.i("yanglijun", "----====+++++======，点击了按钮" + phonenum + smscode + password + password2);
						findpasswordpresenter.FindPassword(phonenum, smscode, password, password2);

					};

				}.start();

			}
		});

		btnFindSendcode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				btnFindSendcode.setClickable(true);
				isChange = true;

				String phonenum = etFindPhonenumb.getText().toString();
				findcodepresenter.FindCode(phonenum);
				changeBtnGetCode();
			}
		});
	}

	protected void changeBtnGetCode() {
		thread = new Thread() {
			@Override
			public void run() {
				if (tag) {
					while (i > 0) {
						i--;
						if (FindPasswordMainActivity.this == null) {
							break;
						}
						// 当文本框内容改变时，结束循环。
						// if (isChange && !btnCode.isClickable()) {
						// isChange = false;
						// break;
						// }
						FindPasswordMainActivity.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								btnFindSendcode.setText("重发(" + i + "s)");
								btnFindSendcode.setClickable(false);
							}
						});
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
					tag = false;
				}
				i = 60;
				tag = true;
				if (FindPasswordMainActivity.this != null) {
					FindPasswordMainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							btnFindSendcode.setText("发送验证码");
							btnFindSendcode.setClickable(true);
						}
					});
				}
			};
		};
		thread.start();

	}

	private void setViews() {
		llFindPasswordBack = (LinearLayout) findViewById(R.id.ll_find_pasword_back);
		btnFindSendcode = (Button) findViewById(R.id.btn_find_sendcode);
		etFindPhonenumb = (EditText) findViewById(R.id.et_find_phonenumb);
		etFindSmscode = (EditText) findViewById(R.id.et_find_smscode);
		etFindPassword = (EditText) findViewById(R.id.et_find_password);
		etFindRepassword = (EditText) findViewById(R.id.et_find_repassword);
		btnFindSubmit = (Button) findViewById(R.id.btn_find_submit);
	}

	@Override
	public void FindCodeFail(String errorMessage) {
		Toast.makeText(getApplicationContext(), "" + errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void FindCodeSuccess() {
		Toast.makeText(getApplicationContext(), "发送成功！", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void FindPasswordFail(String errorMessage) {
		Toast.makeText(getApplicationContext(), "" + errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void FindPasswordSuccess() {
		Toast.makeText(getApplicationContext(), "密码修改成功！", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
	}

}
