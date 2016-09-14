package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.NewHouseRecommendAdapter;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.NewHouseRecommendPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.INewHouseRecommendView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.AdapterView.OnItemClickListener;

public class NewHouseMainActivity extends Activity implements INewHouseRecommendView{
	private ImageView ivNewHouseBack;
	private List<NewHouseRecommendData>newRecommends;
	private ListView lvLoushizixun;
	private IRecommendHousePresenter presenter;
	private NewHouseRecommendAdapter adapter;
	private ScrollView scrollview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
		presenter=new NewHouseRecommendPresenter(this);
		presenter.LoadRecommend();
	}
	private void setListener() {
		ivNewHouseBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NewHouseMainActivity.this.finish();
				
			}
		});
		
		lvLoushizixun.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Intent i = new Intent(NewHouseMainActivity.this, WapRecommedMainActivity.class);
				String url = "http://www.taoshenfang.com" + newRecommends.get(position).getThumb();
				String title=newRecommends.get(position).getTitle();
				String content=newRecommends.get(position).getDescription();
				i.putExtra("content", content);
				i.putExtra("url", url);
				i.putExtra("title", title);
				startActivity(i);
			}
		});
		
	}
	private void setViews() {
		ivNewHouseBack=(ImageView) findViewById(R.id.iv_newhouse_back);
		lvLoushizixun=(ListView) findViewById(R.id.lv_loushizixun);
	
		scrollview=(ScrollView)findViewById(R.id.newHouse_scrollview);
		scrollview.smoothScrollTo(0, 0);
	
	}
	@Override
	public void showData(List<NewHouseRecommendData> newrecommends) {
		this.newRecommends=newrecommends;
		adapter=new NewHouseRecommendAdapter(newrecommends, getApplicationContext());
		lvLoushizixun.setAdapter(adapter);
	}

}
