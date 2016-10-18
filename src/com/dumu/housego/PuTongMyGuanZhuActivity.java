package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.GZErShouFramgent;
import com.dumu.housego.framgent.GZNewFramgent;
import com.dumu.housego.presenter.IMyYuYueHousePresenter;
import com.dumu.housego.presenter.MyYuYueHousePresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IMyYuYueHouseView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class PuTongMyGuanZhuActivity extends FragmentActivity implements IMyYuYueHouseView{
	
	private UserInfo userinfo=HouseGoApp.getContext().getCurrentUserInfo();
	@ViewInject(R.id.guanzhu_ershou)RadioButton btnErShou;
	@ViewInject(R.id.guanzhu_new)RadioButton btnNew;
	@ViewInject(R.id.rg_guanzhu_control)RadioGroup rgGuanZhu;
	@ViewInject(R.id.guanzhu_viewpage)ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragments;
	private LinearLayout llBackPutongyuyue;
	private LinearLayout llBackPutongguanzhu;	
	
	private RelativeLayout window_putong_yuyue;
	private RelativeLayout window_putong_guanzhu;
	
	private IMyYuYueHousePresenter MyYuYuePresenter;
	private String username=userinfo.getUsername();
	private String t="1";
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pu_tong_my_guan_zhu);
		MyYuYuePresenter=new MyYuYueHousePresenter(this);
		x.view().inject(this);
		initView();
		setViewPagerAdapter();
		setListener();
		
	}

	private void initView() {
		String w=getIntent().getStringExtra("v");
		
		btnErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
		llBackPutongyuyue=(LinearLayout) findViewById(R.id.ll_back_putongyuyue);
		llBackPutongguanzhu=(LinearLayout) findViewById(R.id.ll_back_putongguanzhu);
		window_putong_guanzhu=(RelativeLayout) findViewById(R.id.window_putong_guanzhu);
		window_putong_yuyue=(RelativeLayout) findViewById(R.id.window_putong_yuyue);
		
		
		if(w.equals("guanzhu")){
			window_putong_guanzhu.setVisibility(View.VISIBLE);
		}else if(w.equals("yuyue")){
			window_putong_yuyue.setVisibility(View.VISIBLE);
			MyYuYuePresenter.LoadMyYuYueHosue(username, t);
			
		}
	
		
		
		
		
		
		
		
	}

	private void setListener() {
		
		rgGuanZhu.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.guanzhu_ershou:
					viewPager.setCurrentItem(0);
					btnErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
					btnNew.setTextColor(getResources().getColor(R.color.button_unckeck));
					break;
				case R.id.guanzhu_new:
					viewPager.setCurrentItem(1);
					btnNew.setTextColor(getResources().getColor(R.color.button_ckeck));
					btnErShou.setTextColor(getResources().getColor(R.color.button_unckeck));
					break;
			
				}
				
			}
		});
		
		
		llBackPutongyuyue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		llBackPutongguanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	private void setViewPagerAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new GZErShouFramgent());
		fragments.add(new GZNewFramgent());
		
		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

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

	@Override
	public void LoadYuYueData(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
	}


}
