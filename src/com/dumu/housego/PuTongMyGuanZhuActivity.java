package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.GZErShouFramgent;
import com.dumu.housego.framgent.GZNewFramgent;
import com.dumu.housego.framgent.PTQiuZuListFragment;
import com.dumu.housego.framgent.PTershouListFragment;
import com.dumu.housego.framgent.PTershouSumbitFragment;
import com.dumu.housego.framgent.PTrentingListFragment;
import com.dumu.housego.framgent.PTrentingSumbitFragment;
import com.dumu.housego.presenter.IMyYuYueHousePresenter;
import com.dumu.housego.util.MyToastShowCenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PuTongMyGuanZhuActivity extends FragmentActivity {
	
	
	private static final int LOCATION=0;
	
	private UserInfo userinfo=HouseGoApp.getContext().getCurrentUserInfo();
	@ViewInject(R.id.guanzhu_ershou)RadioButton btnErShou;
	@ViewInject(R.id.guanzhu_new)RadioButton btnNew;
	@ViewInject(R.id.rg_guanzhu_control)RadioGroup rgGuanZhu;
	@ViewInject(R.id.guanzhu_viewpage)ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragments;
	private LinearLayout llBackPutongguanzhu;	
	
	private RelativeLayout window_putong_guanzhu;
	private String username=userinfo.getUsername();
	private String t="1";
	private FrameLayout rl_container;
	private Fragment fragment;
	private String tag;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pu_tong_my_guan_zhu);
		x.view().inject(this);
		initView();
		setViewPagerAdapter();
		initData();
		setListener();
		
		
	}

	private void initData() {
		tag=getIntent().getStringExtra("v");
		
		
		if(tag.equals("guanzhu")){
			window_putong_guanzhu.setVisibility(View.VISIBLE);
			
		}else if(tag.equals("ershouhouse")){
			
			rl_container.setVisibility(View.VISIBLE);
			fragment=new PTershouListFragment();
			FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.rl_container, fragment);
			trans.commitAllowingStateLoss();
			
		}else if(tag.equals("rentinghouse")){
			rl_container.setVisibility(View.VISIBLE);
			
			fragment=new PTrentingListFragment();
			FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.rl_container, fragment);
			trans.commitAllowingStateLoss();
		}else if(tag.equals("qiuzu")){
			rl_container.setVisibility(View.VISIBLE);
			
			fragment=new PTQiuZuListFragment();
			FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.rl_container, fragment);
			trans.commitAllowingStateLoss();
		}
		
		
		
		
		
		
	}

	private void initView() {

		rl_container=(FrameLayout) findViewById(R.id.rl_container);
		
		btnErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
		llBackPutongguanzhu=(LinearLayout) findViewById(R.id.ll_back_putongguanzhu);
		window_putong_guanzhu=(RelativeLayout) findViewById(R.id.window_putong_guanzhu);
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




}
