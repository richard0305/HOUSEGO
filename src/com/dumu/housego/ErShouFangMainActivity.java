package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.presenter.ErShouFangRecommendPresenter;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.view.IErShouFangRecommendView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ErShouFangMainActivity extends Activity implements IErShouFangRecommendView{
	private LinearLayout llErshoufang;
	private ErShouFangRecommendAdapter adapter;
	private ListView lvErshoufangRecommend;
	private IRecommendHousePresenter presenter;
	private List<ErShouFangRecommendData> ershoufangrecommends;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_er_shou_fang_main);
		setViews();
		setListener();
		presenter=new ErShouFangRecommendPresenter(this);
		presenter.LoadRecommend();
		
	}
	private void setListener() {
		llErshoufang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ErShouFangMainActivity.this.finish();
				
			}
		});
		
		lvErshoufangRecommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				
				Intent i = new Intent(ErShouFangMainActivity.this, WebErSshouFangMainActivity.class);
				String url = "http://www.taoshenfang.com" + ershoufangrecommends.get(position).getUrl();
				i.putExtra("url", url);
				startActivity(i);
			}
		});
		
	}
	
	private void setViews() {
		llErshoufang=(LinearLayout) findViewById(R.id.ll_ershoufang_back);
		lvErshoufangRecommend=(ListView) findViewById(R.id.lv_ershoufang_recommend);
	}
	@Override
	public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends=ershoufangrecommends;
		
		adapter=new ErShouFangRecommendAdapter(ershoufangrecommends, getApplicationContext());
		lvErshoufangRecommend.setAdapter(adapter);
		
	}

}
