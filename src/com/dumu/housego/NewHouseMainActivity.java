package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.NewHouseHotAdapter;
import com.dumu.housego.adapter.NewHouseRecommendAdapter;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.presenter.INewHouseHotPresenter;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.NewHouseHotPresenter;
import com.dumu.housego.presenter.NewHouseRecommendPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.INewHouseHotView;
import com.dumu.housego.view.INewHouseRecommendView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class NewHouseMainActivity extends Activity implements INewHouseRecommendView,INewHouseHotView{
	private ImageView ivNewHouseBack;
	private List<NewHouseRecommendData>newRecommends;
	private ListView lvLoushizixun,lvRexiaofangyuan;
	private IRecommendHousePresenter presenter;
	private NewHouseRecommendAdapter adapter;
	private ScrollView scrollview;
	private TextView tv_newhouse_morenews_rexiao;
	private INewHouseHotPresenter hotPresenter;
	private List<NewHouseHotRecommend> newhousehots;
	private NewHouseHotAdapter hotadapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
		presenter=new NewHouseRecommendPresenter(this);
		hotPresenter=new NewHouseHotPresenter(this);
		presenter.LoadRecommend();
		hotPresenter.LoadNewHouseHot();
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
		
		tv_newhouse_morenews_rexiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), NewHouseListActivity.class));
				
			}
		});
		
		lvRexiaofangyuan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i1=new Intent(getApplicationContext(), NewHouseDetailActivity.class);
				NewHouseHotRecommend hot=	newhousehots.get(position);
				i1.putExtra("Id", hot.getId());
				i1.putExtra("catid", hot.getCatid());
				startActivity(i1);
				
			}
		});
		
	}
	private void setViews() {
		ivNewHouseBack=(ImageView) findViewById(R.id.iv_newhouse_back);
		lvLoushizixun=(ListView) findViewById(R.id.lv_loushizixun);
		lvRexiaofangyuan=(ListView) findViewById(R.id.lv_rexiaofangyuan);
		tv_newhouse_morenews_rexiao=(TextView) findViewById(R.id.tv_newhouse_morenews_rexiao);
		
		scrollview=(ScrollView)findViewById(R.id.newHouse_scrollview);
		scrollview.smoothScrollTo(0, 0);
	
	}
	@Override
	public void showData(List<NewHouseRecommendData> newrecommends) {
		this.newRecommends=newrecommends;
		adapter=new NewHouseRecommendAdapter(newrecommends, getApplicationContext());
		lvLoushizixun.setAdapter(adapter);
	}
	@Override
	public void showNewHouseHot(List<NewHouseHotRecommend> newhousehots) {
		this.newhousehots=newhousehots;
		hotadapter=new NewHouseHotAdapter(newhousehots, getApplicationContext());
		Log.i("richard==================", "11111111111---------------------"+newhousehots);
		lvRexiaofangyuan.setAdapter(hotadapter);
		
	}

}
