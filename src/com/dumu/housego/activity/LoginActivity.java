package com.dumu.housego.activity;

import java.util.ArrayList;

import com.dumu.housego.FindPasswordMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.User;
import com.dumu.housego.framgent.MyFramgent;
import com.dumu.housego.presenter.ILoginPresenter;
import com.dumu.housego.presenter.ILoginUserInfoPresenter;
import com.dumu.housego.presenter.LoginPresenter;
import com.dumu.housego.presenter.LoginUserInfoPresenter;
import com.dumu.housego.view.ILoginUserInfoView;
import com.dumu.housego.view.ILoginView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements ILoginView,ILoginUserInfoView{
	private TextView tvCancle;
	private TextView tvRegist,tvForgivepw;
	private Button btnLogin,btnShortLoginSendCode;
	private ILoginPresenter generalpresenter;
	
	private EditText etGeneralUsername;
	private EditText etGeneralPassword;
	
	private EditText etShortUsername;
	private EditText etShortLoginSendCode;
	
	private User user;
	private ILoginUserInfoPresenter userinfoPresenter;
	
	 Thread thread=null;
	 private boolean tag=true;
	    private int i=60;
	    public boolean  isChange=false;
	
	 ViewPager pager = null;
	 PagerTabStrip tabStrip = null;
	 ArrayList<View> viewContainter = new ArrayList<View>();
	 ArrayList<String> titleContainer = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		setViews();
		setOptains();
		setAdapter();
		setListener();
		generalpresenter=new LoginPresenter(this);
		userinfoPresenter=new LoginUserInfoPresenter(this);
	}

	private void setListener() {
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
			}
		});

		
		
		tvCancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		tvRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, RegistActivity.class));
				
			}
		});
		
		tvForgivepw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, FindPasswordMainActivity.class));
				
			}
		});
		
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String Gphonenum=etGeneralUsername.getText().toString();
				String password=etGeneralPassword.getText().toString();
				generalpresenter.login(Gphonenum, password);
				
				
			}
		});
		

		
	}

	private void setAdapter() {
		pager.setAdapter(new PagerAdapter() {

			//viewpager�е��������
			@Override
			public int getCount() {
				return viewContainter.size();
			}
          //�����л���ʱ�����ٵ�ǰ�����
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((ViewPager) container).removeView(viewContainter.get(position));
			}
          //ÿ�λ�����ʱ�����ɵ����
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				((ViewPager) container).addView(viewContainter.get(position));
				return viewContainter.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getItemPosition(Object object) {
				return super.getItemPosition(object);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titleContainer.get(position);
			}
		});
		
	}

	private void setOptains() {
		// ȡ��tab����ĳ�����
		tabStrip.setDrawFullUnderline(false);
		// ����tab�ı���ɫ
		tabStrip.setBackgroundColor(getResources().getColor(R.color.tab_back));
		// ���õ�ǰtabҳǩ���»�����ɫ
		tabStrip.setTabIndicatorColor(getResources().getColor(R.color.tab_red));
		tabStrip.setTextSpacing(100);
		
		
		GradientDrawable dra=new GradientDrawable();
		dra.setShape(GradientDrawable.RECTANGLE); // ����
		dra.setStroke(1, R.color.send_code_rectangle);
		dra.setColor(getResources().getColor(R.color.send_code_back));
//		btnLoginSendCode.setBackgroundDrawable(dra);
		
	}

	private void setViews() {
		View general = LayoutInflater.from(this).inflate(R.layout.login_general, null);
		View shortcut = LayoutInflater.from(this).inflate(R.layout.login_shortcut, null);
		pager = (ViewPager) this.findViewById(R.id.login_viewpager);
		tabStrip = (PagerTabStrip) this.findViewById(R.id.login_tabstrip);
		btnLogin=(Button) findViewById(R.id.btn_Login);
		tvCancle=(TextView) findViewById(R.id.tv_cancle);
		tvRegist=(TextView) findViewById(R.id.tv_regist);
		btnShortLoginSendCode=(Button)shortcut.findViewById(R.id.btn_quicklogin_sendcode);
		tvForgivepw=(TextView) findViewById(R.id.tv_forgivepw);
		

		
		etGeneralUsername=(EditText) general.findViewById(R.id.et_genal_login_phonenumber);
		etGeneralPassword=(EditText) general.findViewById(R.id.et_genal_login_lock);
		
		etShortUsername=(EditText) shortcut.findViewById(R.id.et_short_login_phonenumber);
		etShortLoginSendCode=(EditText) shortcut.findViewById(R.id.et_short_login_lock_quick);
		viewContainter.add(general);
		viewContainter.add(shortcut);
		titleContainer.add("��ͨ��¼");
		titleContainer.add("��֤���ݵ�¼");
	}

	@Override
	public void loginFail(String errorMessage) {
		Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void loginSuccess() {
		Toast.makeText(getApplicationContext(), "��½�ɹ�", Toast.LENGTH_SHORT).show();
		
	
		user=HouseGoApp.getContext().getCurrentUser();
		String userid=user.getUserid();
		userinfoPresenter.login(userid);
		
		
	}

	@Override
	public void loginUserInfoFail(String errorMessage) {
		Toast.makeText(getApplicationContext(), "��ȡ�û���Ϣʧ��", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void loginUserInfoSuccess() {
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}

}
