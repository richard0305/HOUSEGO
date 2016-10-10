package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.dumu.housego.framgent.GZErShouFramgent;
import com.dumu.housego.framgent.GZNewFramgent;

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

public class PuTongMyGuanZhuActivity extends FragmentActivity {
	
	@ViewInject(R.id.guanzhu_ershou)RadioButton btnErShou;
	@ViewInject(R.id.guanzhu_new)RadioButton btnNew;
	@ViewInject(R.id.rg_guanzhu_control)RadioGroup rgGuanZhu;
	@ViewInject(R.id.guanzhu_viewpage)ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pu_tong_my_guan_zhu);
		x.view().inject(this);
		btnErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
		setViewPagerAdapter();
		setListener();
		
				
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
