package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.adapter.BlockTradeLsitAdapter;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.BlockTradeProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.BlockTradeProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IBlockTradeProgramaView;
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

public class BlockTradeMainActivity extends Activity implements IBlockTradeProgramaView, OnRefreshListener2<ListView> {
	private LinearLayout llBlockTradeBack;
	private PullToRefreshListView lvBlockTrade;
	private IFourDataProgramePresenter presenter;
	private BlockTradeLsitAdapter adapter;
	private List<BlockTradeList> blocktrades;
	private List<BlockTradeList> lastblocks = new ArrayList<BlockTradeList>();
	private FourDataPrograma fourdata;
	private BlockTradeProgramaModel model = new BlockTradeProgramaModel();
	int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
		presenter = new BlockTradeProgramaPresenter(this);
		fourdata = new FourDataPrograma();
		fourdata.setCatid("7");
		fourdata.setPage("1");
		presenter.LoadProgrameData(fourdata);
		;

	}

	private void setViews() {
		llBlockTradeBack = (LinearLayout) findViewById(R.id.ll_block_trade_back);
		lvBlockTrade = (PullToRefreshListView) findViewById(R.id.lv_blocktrade);
		lvBlockTrade.setMode(PullToRefreshBase.Mode.BOTH);
		lvBlockTrade.setOnRefreshListener(this);
	}

	private void setListener() {
		llBlockTradeBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		lvBlockTrade.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

				Intent i = new Intent(BlockTradeMainActivity.this, BlockTradeDetailActivity.class);
				String Id = lastblocks.get(position).getId();
				String catid = lastblocks.get(position).getCatid();
				String posid = lastblocks.get(position).getPosid();

				i.putExtra("id", Id);
				i.putExtra("catid", catid);
				i.putExtra("posid", posid);
				startActivity(i);
			}
		});

	}

	@Override
	public void showData(List<BlockTradeList> blocktrades) {
		this.blocktrades = blocktrades;
		this.lastblocks.addAll(blocktrades);
		adapter = new BlockTradeLsitAdapter(lastblocks, getApplicationContext());
		lvBlockTrade.setAdapter(adapter);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		fourdata.setCatid("7");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<BlockTradeList> blocks = (List<BlockTradeList>) success;

				// 将数据追加到集合中
				lastblocks.clear();
				lastblocks.addAll(blocks);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lvBlockTrade.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.size() + "个房源！");

			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvBlockTrade.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setCatid("7");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<BlockTradeList> blocks = (List<BlockTradeList>) success;
				if (blocks==null) {
					lvBlockTrade.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				}else{
					// 将数据追加到集合中
					lastblocks.addAll(blocks);
					// 刷新界面
					adapter.notifyDataSetChanged();
					// 关闭上拉加载刷新布局
					lvBlockTrade.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.size() + "个房源！");
	
				}
			
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvBlockTrade.onRefreshComplete();

			}
		});

	}

}
