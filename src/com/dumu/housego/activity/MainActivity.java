package com.dumu.housego.activity;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.FirstFramgent;
import com.dumu.housego.framgent.HouseFramgent;
import com.dumu.housego.framgent.MessageFramgent;
import com.dumu.housego.framgent.MyFramgent;
import com.dumu.housego.util.FontHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity{
	
	private RadioGroup rgControl;
	private RadioButton rbFrist;
	private RadioButton rbMessage;
	private RadioButton rbHouse;
	private RadioButton rbMy;
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragments;
	private UserInfo userinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setViews();
		rbFrist.setTextColor(getResources().getColor(R.color.button_ckeck));
		setViewPagerAdapter();
		setListeners();
		
		FontHelper.injectFont(findViewById(android.R.id.content));
		
	}
	
	@Override
	protected void onResume() {
		HouseGoApp app=HouseGoApp.getContext();
		userinfo=app.getLoginInfo(getApplicationContext());
		app.SaveCurrentUserInfo(userinfo);
		
		super.onResume();
	}


		
	

	private void setViews() {
		rgControl = (RadioGroup) findViewById(R.id.rg_control);
		rbFrist = (RadioButton) findViewById(R.id.first);
		rbMessage = (RadioButton) findViewById(R.id.message);
		rbHouse= (RadioButton) findViewById(R.id.house);
		rbMy = (RadioButton) findViewById(R.id.my);
		viewPager = (ViewPager) findViewById(R.id.viewpage);
	}


	private void setViewPagerAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new FirstFramgent());
		fragments.add(new MessageFramgent());
		fragments.add(new HouseFramgent());
		fragments.add(new MyFramgent());
		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

	}

	private void setListeners() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				
				case 0:
					rbFrist.setChecked(true);
					rbFrist.setTextColor(getResources().getColor(R.color.button_ckeck));
					rbMessage.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbHouse.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbMy.setTextColor(getResources().getColor(R.color.button_unckeck));
					break;
				case 1:
					rbMessage.setChecked(true);
					rbFrist.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbMessage.setTextColor(getResources().getColor(R.color.button_ckeck));
					rbHouse.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbMy.setTextColor(getResources().getColor(R.color.button_unckeck));
					
					//判断是否登录，没有登录，跳转到登录界面
					userinfo=HouseGoApp.getContext().getCurrentUserInfo();
					if(userinfo==null){
						startActivity(new Intent(getApplicationContext(), LoginActivity.class));
					}
					
					
					
					break;
				case 2:
					rbHouse.setChecked(true);
					rbFrist.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbMessage.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbHouse.setTextColor(getResources().getColor(R.color.button_ckeck));
					rbMy.setTextColor(getResources().getColor(R.color.button_unckeck));
					break;
				case 3:
					rbMy.setChecked(true);
					rbFrist.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbMessage.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbHouse.setTextColor(getResources().getColor(R.color.button_unckeck));
					rbMy.setTextColor(getResources().getColor(R.color.button_ckeck));
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		rgControl.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.first:
					viewPager.setCurrentItem(0);
					break;
				case R.id.message:
					viewPager.setCurrentItem(1);
					
				
					break;
				case R.id.house:
					viewPager.setCurrentItem(2);
					break;
				case R.id.my:
					viewPager.setCurrentItem(3);
					break;
				}
			}
		});

	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

	}






}
