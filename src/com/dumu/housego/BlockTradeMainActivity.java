package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.BlockTradeLsitAdapter;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.presenter.BlockTradeProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IBlockTradeProgramaView;

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

public class BlockTradeMainActivity extends Activity implements IBlockTradeProgramaView{
	private LinearLayout llBlockTradeBack;
	private ListView lvBlockTrade;
	private IFourDataProgramePresenter presenter;
	private BlockTradeLsitAdapter adapter;
	private List<BlockTradeList> blocktrades; 
	private FourDataPrograma fourdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
		presenter=new BlockTradeProgramaPresenter(this);
		fourdata=new FourDataPrograma();
		fourdata.setCatid("7");
		fourdata.setPage("1");
		presenter.LoadProgrameData(fourdata);;
		
	}
	private void setViews() {
		llBlockTradeBack=(LinearLayout) findViewById(R.id.ll_block_trade_back);
		lvBlockTrade=(ListView) findViewById(R.id.lv_blocktrade);
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
				String Id=blocktrades.get(position).getId();
				String catid=blocktrades.get(position).getCatid();
				String posid=blocktrades.get(position).getPosid();
				
				Log.i("yanglijun", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+Id+catid+posid);
//				String url = blocktrades.get(position).getUrl();
				i.putExtra("id", Id);
				i.putExtra("catid", catid);
				i.putExtra("posid", posid);
				startActivity(i);
			}
		});
		
		
	}
	@Override
	public void showData(List<BlockTradeList> blocktrades) {
		this.blocktrades=blocktrades;
		adapter=new BlockTradeLsitAdapter(blocktrades, getApplicationContext());
		lvBlockTrade.setAdapter(adapter);
	}


}
