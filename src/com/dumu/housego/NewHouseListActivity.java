package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.adapter.NewHouseListAdapter;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.NewHouseListModel;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.NewHouseListPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.INewHouseListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NewHouseListActivity extends Activity implements INewHouseListView,OnRefreshListener2<ListView>{
	private LinearLayout llNewhouselistBack;
	private List<NewHouseList>newhouselists;
	private NewHouseListAdapter listadapter;
	private IFourDataProgramePresenter presenter;
	private FourDataPrograma fourdata;
	private PullToRefreshListView lv_newhouselist;
	private List<NewHouseList>lastnews=new ArrayList<NewHouseList>();
	int page=1;
	
	private NewHouseListModel model=new NewHouseListModel();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_list);
		setViews();
		setListener();
		presenter=new NewHouseListPresenter(this);
		fourdata=new FourDataPrograma();
		fourdata.setCatid("3");
		fourdata.setPage("1");
		presenter.LoadProgrameData(fourdata);
		
	}
	private void setViews() {
		llNewhouselistBack=(LinearLayout) findViewById(R.id.ll_newhouselist_back);
		lv_newhouselist=(PullToRefreshListView) findViewById(R.id.lv_newhouselist);
		lv_newhouselist.setMode(PullToRefreshBase.Mode.BOTH);
		lv_newhouselist.setOnRefreshListener(this);
	}
	private void setListener() {
		llNewhouselistBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		lv_newhouselist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NewHouseList n=newhouselists.get(position);
				Intent i=new Intent(getApplicationContext(), NewHouseDetailActivity.class);
				String Id=n.getId();
				String catid=n.getCatid();
				i.putExtra("Id", Id);
				i.putExtra("catid", catid);
				
				startActivity(i);
				
			}
		});
	}
	
	@Override
	public void showNewHouseList(List<NewHouseList> newhouselists) {
		this.newhouselists=newhouselists;
		this.lastnews.addAll(newhouselists);
		listadapter=new NewHouseListAdapter(lastnews, getApplicationContext());
		lv_newhouselist.setAdapter(listadapter);
		
	}
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page=1;
		fourdata.setCatid("3");
		fourdata.setPage(page+"");
		
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<NewHouseList>news=(List<NewHouseList>) success;
				
				//将数据追加到集合中
				lastnews.clear();
				lastnews.addAll(news);
				//刷新界面
				listadapter.notifyDataSetChanged();
				//关闭上拉加载刷新布局
				lv_newhouselist.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了"+lastnews.size()+"个房源！");
				
			}
			
			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lv_newhouselist.onRefreshComplete();
				
			}
		});
		
	}
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page=page+1;
		fourdata.setCatid("3");
		fourdata.setPage(page+"");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
	List<NewHouseList>news=(List<NewHouseList>) success;
				
				//将数据追加到集合中
				lastnews.addAll(news);
				//刷新界面
				listadapter.notifyDataSetChanged();
				//关闭上拉加载刷新布局
				lv_newhouselist.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了"+lastnews.size()+"个房源！");
				
				
			}
			
			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lv_newhouselist.onRefreshComplete();
				
			}
		});
		
	}
	
	
	
}
