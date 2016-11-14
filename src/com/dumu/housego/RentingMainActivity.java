package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.RentingProgramaModel;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.RentingProgramaPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IRentingProgramaView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class RentingMainActivity extends Activity implements IRentingProgramaView, OnRefreshListener2<ListView> {

	private LinearLayout llRentingBack;
	private ErShouFangRecommendAdapter adapter;
	private IFourDataProgramePresenter presenter;
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private FourDataPrograma fourdata;
	private PullToRefreshListView lvRenting;
	private List<ErShouFangRecommendData> lastrentings = new ArrayList<ErShouFangRecommendData>();
	int page = 1;
	private RentingProgramaModel model = new RentingProgramaModel();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();

		presenter = new RentingProgramaPresenter(this);
		fourdata = new FourDataPrograma();
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
		lvRenting.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				String catid = lastrentings.get(position).getCatid();
				String Id = lastrentings.get(position).getId();
				Intent i = new Intent(getApplicationContext(), RentingDetailActivity.class);
				i.putExtra("catid", catid);
				i.putExtra("id", Id);
				startActivity(i);

			}
		});

	}

	private void setViews() {
		llRentingBack = (LinearLayout) findViewById(R.id.ll_renting_back);
		lvRenting = (PullToRefreshListView) findViewById(R.id.lv_rentinglist);
		lvRenting.setMode(PullToRefreshBase.Mode.BOTH);
		lvRenting.setOnRefreshListener(this);
	}

	@Override
	public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends = ershoufangrecommends;
		this.lastrentings.addAll(ershoufangrecommends);
		adapter = new ErShouFangRecommendAdapter(lastrentings, getApplicationContext());
		lvRenting.setAdapter(adapter);

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		fourdata.setCatid("8");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;

				// 将数据追加到集合中
				lastrentings.clear();
				lastrentings.addAll(ershous);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lvRenting.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.size() + "个房源！");

			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvRenting.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setCatid("8");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {

				List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;
				if (ershous==null) {
					lvRenting.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				}else{
					
				// 将数据追加到集合中
				lastrentings.addAll(ershous);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lvRenting.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.size() + "个房源！");
				
				}
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvRenting.onRefreshComplete();

			}
		});

	}

}
