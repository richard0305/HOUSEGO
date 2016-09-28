package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.util.CarouselPagerAdapter;
import com.dumu.housego.util.CarouselViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LookDateFragment extends Fragment implements CarouselViewPager.OnPageChangeListener{
	private Button btnHouseDateLogin;
	private CarouselViewPager mCarouselView;
	private List<ImageView> ivList = new ArrayList<ImageView>();
    private int[] ivIds = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

    private ImageView[] indicationPoint;//指示点控件
    private LinearLayout pointLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_look_date, null);
		 initViews(rootView);
	        initData();
	        initListener();
	        return rootView;
	    }

	    private void initListener() {
	    	btnHouseDateLogin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), LoginActivity.class));
					
					
				}
			});
		
	}

		private void initViews(View rootView) {
	        mCarouselView = (CarouselViewPager) rootView.findViewById(R.id.mCarouselView);
	        pointLayout = (LinearLayout) rootView.findViewById(R.id.pointLayout);
	        btnHouseDateLogin=(Button) rootView.findViewById(R.id.btn_house_date_Login);
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
