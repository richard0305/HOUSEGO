package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.ErShouFangMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.CarouselPagerAdapter;
import com.dumu.housego.util.CarouselViewPager;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.NoScrollViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HouseFramgent extends Fragment implements CarouselViewPager.OnPageChangeListener {
	private Button btnHouseLogin;
	private NoScrollViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<Fragment> fragmentss;
	private LinearLayout llContorl;
	private UserInfo userinfo;
	private Button btnLookDate,btnLookHistroy ,btnHouseNoLoginLogin;
	private LinearLayout ll_agent_house,ll_noLogin_house,ll_putong_house;

	private CarouselViewPager mCarouselView;
	private List<ImageView> ivList = new ArrayList<ImageView>();
    private int[] ivIds = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

    private ImageView[] indicationPoint;//指示点控件
    private LinearLayout pointLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.framgent_house, null);
		setViews(view);
		   initData();
		setListener();
		setViewPagerAdapter();
		btnLookDate.setTextColor(getResources().getColor(android.R.color.white));
		btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
		FontHelper.injectFont(view);
		return view;
	}
	
	
	
	
	   private void initData() {
	        for (int i = 0; i < ivIds.length; i++) {
	            ImageView iv = new ImageView(getActivity());
	            iv.setImageResource(ivIds[i]);
	            ivList.add(iv);
	        }

	        //动态生成指示点
	        indicationPoint = new ImageView[ivList.size()];
	        for (int i = 0; i < indicationPoint.length; i++) {
	            ImageView point = new ImageView(getActivity());
	            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(10, 10);
	            layout.setMargins(10, 0, 10, 0);
	            point.setLayoutParams(layout);

	            indicationPoint[i] = point;
	            if (i == 0) {
	                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
	            } else {
	                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
	            }
	            pointLayout.addView(point);
	        }

	        mCarouselView.setAdapter(new CarouselPagerAdapter(ivList));
	        mCarouselView.addOnPageChangeListener(this);
	        //设置图片展示的时间
	        mCarouselView.setDisplayTime(2000);
	        //图片开始轮播
	        mCarouselView.start();

	    }
	
	
	
	

	@Override
	public void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		if(userinfo!=null){
		
				if(userinfo.getModelid().equals("35")){
					ll_putong_house.setVisibility(View.VISIBLE);
					}else{
					ll_agent_house.setVisibility(View.VISIBLE);
					}
		
		}else{
			ll_noLogin_house.setVisibility(View.VISIBLE);
		}
		super.onResume();
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
		
		
		btnHouseNoLoginLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ErShouFangMainActivity.class));
			}
		});

	}

	private void setViews(View view) {
		viewPager = (NoScrollViewPager) view.findViewById(R.id.house_viewpage);
		llContorl = (LinearLayout) view.findViewById(R.id.ll_contorl);
		btnLookDate = (Button) view.findViewById(R.id.tv_look_date);
		btnLookHistroy = (Button) view.findViewById(R.id.tv_look_histroy);
	
		ll_agent_house=(LinearLayout) view.findViewById(R.id.ll_agent_house);
		ll_noLogin_house=(LinearLayout) view.findViewById(R.id.ll_noLogin_house);
		ll_putong_house=(LinearLayout) view.findViewById(R.id.ll_putong_house);
	
	
	     mCarouselView = (CarouselViewPager) view.findViewById(R.id.nologin_mCarouselView);
	        pointLayout = (LinearLayout) view.findViewById(R.id.nologin_pointLayout);
	
	        btnHouseNoLoginLogin=(Button) view.findViewById(R.id.btn_house_noLogin_Login);
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
	
	
	

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPointColor(position % ivList.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setPointColor(int selectItem) {
        for (int i = 0; i < indicationPoint.length; i++) {
            if (i == selectItem) {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

        }
    }
}
