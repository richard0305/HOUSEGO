package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.RentingProgramaPresenter;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.view.IErShouFangRecommendView;
import com.dumu.housego.view.IRentingProgramaView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class RentingMainActivity extends Activity implements IRentingProgramaView{
	
	private LinearLayout llRentingBack;
	private ErShouFangRecommendAdapter adapter;
	private IFourDataProgramePresenter presenter;
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private FourDataPrograma fourdata;
	private ListViewForScrollView lvRenting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_main);
		setViews();
		setListener();
		
		presenter=new RentingProgramaPresenter(this);
		fourdata=new FourDataPrograma();
		fourdata.setCatid("8");
		fourdata.setPage("1");
		presenter.LoadProgrameData(fourdata);
		
		
	}
	private void setListener() {
		llRentingBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RentingMainActivity.this.finish();
				
			}
		});
		
	}
	private void setViews() {
		llRentingBack=(LinearLayout) findViewById(R.id.ll_renting_back);
		lvRenting=(ListViewForScrollView) findViewById(R.id.lv_renting_list);
	}
	
	
	@Override
	public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends=ershoufangrecommends;
		Log.i("dumu", ershoufangrecommends+"");
		adapter=new ErShouFangRecommendAdapter(ershoufangrecommends, getApplicationContext());
		lvRenting.setAdapter(adapter);
		
	}


}
