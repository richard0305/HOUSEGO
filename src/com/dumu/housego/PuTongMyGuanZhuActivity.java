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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PuTongMyGuanZhuActivity extends FragmentActivity {
	
	private static final int LOCATION=0;
	
	private TextView tv_chuzhu_dituzuobiao;
	private RelativeLayout rl_my_dituzuobiao;
	private UserInfo userinfo=HouseGoApp.getContext().getCurrentUserInfo();
	@ViewInject(R.id.guanzhu_ershou)RadioButton btnErShou;
	@ViewInject(R.id.guanzhu_new)RadioButton btnNew;
	@ViewInject(R.id.rg_guanzhu_control)RadioGroup rgGuanZhu;
	@ViewInject(R.id.guanzhu_viewpage)ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragments;
	private LinearLayout llBackPutongyuyue;
	private LinearLayout llBackPutongguanzhu;	
	
	private RelativeLayout window_putong_guanzhu,window_putong_ershou,window_putong_rentinghouse;
	private IMyYuYueHousePresenter MyYuYuePresenter;
	private String username=userinfo.getUsername();
	private String t="1";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pu_tong_my_guan_zhu);
		x.view().inject(this);
		initView();
		setViewPagerAdapter();
		setListener();
		
	}

	private void initView() {
//		PTMyErShouFragment ptershouFragment=new PTMyErShouFragment();
//		getFragmentManager().beginTransaction().add(R.layout.fragment_ershou_pt, ptershouFragment).;

		
		tv_chuzhu_dituzuobiao=(TextView) findViewById(R.id.tv_chuzhu_dituzuobiao);
		rl_my_dituzuobiao=(RelativeLayout) findViewById(R.id.rl_my_dituzuobiao);
		
		String w=getIntent().getStringExtra("v");
		
		btnErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
		llBackPutongguanzhu=(LinearLayout) findViewById(R.id.ll_back_putongguanzhu);
		window_putong_guanzhu=(RelativeLayout) findViewById(R.id.window_putong_guanzhu);
		window_putong_rentinghouse=(RelativeLayout) findViewById(R.id.window_putong_rentinghouse);
		window_putong_ershou=(RelativeLayout)findViewById(R.id.window_putong_ershou);
		
		if(w.equals("guanzhu")){
			window_putong_guanzhu.setVisibility(View.VISIBLE);
		}else if(w.equals("rentinghouse")){
			window_putong_rentinghouse.setVisibility(View.VISIBLE);
		}else if(w.equals("ershouhouse")){
			window_putong_ershou.setVisibility(View.VISIBLE);
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
		
		
		
		llBackPutongguanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		rl_my_dituzuobiao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(PuTongMyGuanZhuActivity.this, GetLocationActivity.class);
				startActivityForResult(i, LOCATION);
			}
		});
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case LOCATION:
//			Intent i=getIntent();
//			double latitude=i.getDoubleExtra("latitude", 0);
//			double longitude=i.getDoubleExtra("longitude", 0);
			double latitude=data.getDoubleExtra("latitude", 0);
			double longitude=data.getDoubleExtra("longitude", 0);
			Log.e("LatLng2", "LatLng2=="+latitude+" " +longitude);
			tv_chuzhu_dituzuobiao.setText(latitude+","+longitude);
			break;

		default:
			break;
	}
	}
	
	private void setViewPagerAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new GZErShouFramgent());
		fragments.add(new GZNewFramgent());
		fragments.add(new PTMyErShouFragment());
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
