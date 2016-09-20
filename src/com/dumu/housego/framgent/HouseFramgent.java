package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.NoScrollViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HouseFramgent extends Fragment {
	private Button btnHouseLogin;
	private NoScrollViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragmentss;
	private LinearLayout llContorl;
	private Button btnLookDate, btnLookHistroy;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.framgent_house, null);
		setViews(view);
		setListener();
		setViewPagerAdapter();
		btnLookDate.setTextColor(getResources().getColor(android.R.color.white));
		btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
		FontHelper.injectFont(view);
		return view;
	}

	private void setViewPagerAdapter() {
		fragmentss = new ArrayList<Fragment>();
		fragmentss.add(new LookDateFragment());
		fragmentss.add(new LookHistroyFragment());
		pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
		viewPager.setAdapter(pagerAdapter);

	}

	private void setListener() {
		btnLookDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0);
				btnLookDate.setTextColor(getResources().getColor(android.R.color.white));
				btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
				btnLookHistroy.setTextColor(getResources().getColor(android.R.color.holo_red_light));
				btnLookHistroy.setBackgroundColor(getResources().getColor(android.R.color.white));
			}
		});
		btnLookHistroy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(1);
				btnLookHistroy.setTextColor(getResources().getColor(android.R.color.white));
				btnLookHistroy.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
				btnLookDate.setTextColor(getResources().getColor(android.R.color.holo_red_light));
				btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.white));
			}
		});

	}

	private void setViews(View view) {
		viewPager = (NoScrollViewPager) view.findViewById(R.id.house_viewpage);
		llContorl = (LinearLayout) view.findViewById(R.id.ll_contorl);
		btnLookDate = (Button) view.findViewById(R.id.tv_look_date);
		btnLookHistroy = (Button) view.findViewById(R.id.tv_look_histroy);
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentss.get(position);
		}

		@Override
		public int getCount() {
			return fragmentss.size();
		}

	}
}
