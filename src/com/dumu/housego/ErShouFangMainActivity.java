package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.ErShouFangProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ErShouFangProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IErShouFangRecommendView;
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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

public class ErShouFangMainActivity extends Activity implements IErShouFangRecommendView, OnRefreshListener2<ListView> {
	private LinearLayout llErshoufang;
	private ErShouFangRecommendAdapter adapter;
	private IFourDataProgramePresenter presenter;
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private List<ErShouFangRecommendData> lastershous = new ArrayList<ErShouFangRecommendData>();
	private FourDataPrograma fourdata;
	private Spinner ershoufangQuyuSp1;
	private Spinner ershoufangQuyuSp2;
	private Spinner ershoufangQuyuSp3;
	private Spinner ershoufangQuyuSp4;

	private ErShouFangProgramaModel model = new ErShouFangProgramaModel();

	private List<String> spinnerList1 = new ArrayList<String>();
	private List<String> spinnerList2 = new ArrayList<String>();
	private List<String> spinnerList3 = new ArrayList<String>();
	private List<String> spinnerList4 = new ArrayList<String>();
	private ArrayAdapter<String> Spinneradapter;

	private PullToRefreshListView refreshListview;
	private boolean isFirstIn = true;
	private int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_er_shou_fang_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();


		presenter = new ErShouFangProgramaPresenter(this);

		fourdata = new FourDataPrograma();
		fourdata.setCatid("6");
		fourdata.setPage("1");
		presenter.LoadProgrameData(fourdata);
		Spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList1);
		Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ershoufangQuyuSp1.setAdapter(Spinneradapter);
		String str = (String) ershoufangQuyuSp1.getSelectedItem();
	}

	private void setListener() {
		llErshoufang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ErShouFangMainActivity.this.finish();

			}
		});

		refreshListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				//
				Intent i = new Intent(ErShouFangMainActivity.this, ErShouFangDetailsActivity.class);
				String catid = lastershous.get(position).getCatid();
				String ID = lastershous.get(position).getId();
				i.putExtra("catid", catid);
				i.putExtra("id", ID);
				startActivity(i);
			}
		});

	}

	private void setViews() {
		// 刷新
		refreshListview = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		refreshListview.setMode(PullToRefreshBase.Mode.BOTH);
		refreshListview.setOnRefreshListener(this);
		// 设置刷新声音
		// SoundPullEventListener<ListView> soundListener=new
		// SoundPullEventListener<ListView>(this);
		// soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.);
		//
		//
		llErshoufang = (LinearLayout) findViewById(R.id.ll_ershoufang_back);
		ershoufangQuyuSp1 = (Spinner) findViewById(R.id.ershoufang_quyu_sp1);
		ershoufangQuyuSp2 = (Spinner) findViewById(R.id.ershoufang_quyu_sp2);
		ershoufangQuyuSp3 = (Spinner) findViewById(R.id.ershoufang_quyu_sp3);
		ershoufangQuyuSp4 = (Spinner) findViewById(R.id.ershoufang_quyu_sp4);

	}

	@Override
	public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends = ershoufangrecommends;

		this.lastershous.addAll(ershoufangrecommends);
		Log.e("2016-10-10", "2016-10-10" + lastershous);
		// if(page==1){
		adapter = new ErShouFangRecommendAdapter(lastershous, getApplicationContext());
		refreshListview.setAdapter(adapter);
		// }else{
		// adapter.notifyDataSetChanged();
		// }

	}
	
	
	
	
	
	
	
	/**
	 * 刷新
	 */

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		this.lastershous.clear();
		page = 1;
		fourdata.setCatid("6");
		fourdata.setPage(page + "");
		presenter.LoadProgrameData(fourdata);

		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;

				// 将数据追加到集合中
				lastershous.clear();
				lastershous.addAll(ershous);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				refreshListview.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastershous.size() + "个房源！");
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				refreshListview.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;
				if (ershous!=null) {
					lastershous.addAll(ershous);
					// 刷新界面
					adapter.notifyDataSetChanged();
					// 关闭上拉加载刷新布局
					refreshListview.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastershous.size() + "个房源！");
				}else{
					refreshListview.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				}
				
			
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				refreshListview.onRefreshComplete();

			}
		});

	}

}
