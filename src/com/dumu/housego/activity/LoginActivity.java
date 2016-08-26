package com.dumu.housego.activity;

import java.util.ArrayList;

import com.dumu.housego.R;

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
import android.widget.TextView;

public class LoginActivity extends Activity {
	private TextView tvCancle;
	private TextView tvRegist;
	private Button btnLogin,btnLoginSendCode;
	
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

		
	}

	private void setAdapter() {
		pager.setAdapter(new PagerAdapter() {

			//viewpager中的组件数量
			@Override
			public int getCount() {
				return viewContainter.size();
			}
          //滑动切换的时候销毁当前的组件
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((ViewPager) container).removeView(viewContainter.get(position));
			}
          //每次滑动的时候生成的组件
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
		// 取消tab下面的长横线
		tabStrip.setDrawFullUnderline(false);
		// 设置tab的背景色
		tabStrip.setBackgroundColor(getResources().getColor(R.color.tab_back));
		// 设置当前tab页签的下划线颜色
		tabStrip.setTabIndicatorColor(getResources().getColor(R.color.tab_red));
		tabStrip.setTextSpacing(100);
		
		
		GradientDrawable dra=new GradientDrawable();
		dra.setShape(GradientDrawable.RECTANGLE); // 画框
		dra.setStroke(1, R.color.send_code_rectangle);
		dra.setColor(getResources().getColor(R.color.send_code_back));
//		btnLoginSendCode.setBackgroundDrawable(dra);
		
	}

	private void setViews() {
		pager = (ViewPager) this.findViewById(R.id.login_viewpager);
		tabStrip = (PagerTabStrip) this.findViewById(R.id.login_tabstrip);
		btnLogin=(Button) findViewById(R.id.btn_Login);
		tvCancle=(TextView) findViewById(R.id.tv_cancle);
		tvRegist=(TextView) findViewById(R.id.tv_regist);
		btnLoginSendCode=(Button) findViewById(R.id.btn_quicklogin_sendcode);
		
		View general = LayoutInflater.from(this).inflate(R.layout.login_general, null);
		View shortcut = LayoutInflater.from(this).inflate(R.layout.login_shortcut, null);
		
		
		viewContainter.add(general);
		viewContainter.add(shortcut);
		titleContainer.add("普通登录");
		titleContainer.add("验证码快捷登录");
	}

}
