package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.NewHouseListAdapter;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.NewHouseListPresenter;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.view.INewHouseListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

public class NewHouseListActivity extends Activity implements INewHouseListView{
	private LinearLayout llNewhouselistBack;
	private List<NewHouseList>newhouselists;
	private NewHouseListAdapter listadapter;
	private IFourDataProgramePresenter presenter;
	private FourDataPrograma fourdata;
	private ListViewForScrollView lv_newhouselist;
	
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
		lv_newhouselist=(ListViewForScrollView) findViewById(R.id.lv_newhouselist);
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
				i.putExtra("id", Id);
				i.putExtra("catid", catid);
				
				Log.i("YANGLIJUN", "---->>>>>>>>>><<<<<<<<<<<<<<<<<<<========================="+Id+"   "+catid);
				startActivity(i);
				
			}
		});
	}
	
	@Override
	public void showNewHouseList(List<NewHouseList> newhouselists) {
		this.newhouselists=newhouselists;
		listadapter=new NewHouseListAdapter(newhouselists, getApplicationContext());
		lv_newhouselist.setAdapter(listadapter);
		
	}
	
	
	
}
