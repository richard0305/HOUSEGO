package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class BlockTradeDetailActivity extends Activity {
	private LinearLayout llBlockTradeDetailBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_detail);
		setViews();
		setListener();
		String id=getIntent().getStringExtra("id");
		String catid=getIntent().getStringExtra("catid");
		String posid=getIntent().getStringExtra("posid");
		
			Log.i("yanglijun", "<<<>>>>____++++_____"+id+catid);
		
		
	}
	private void setViews() {
		llBlockTradeDetailBack=(LinearLayout) findViewById(R.id.ll_block_trade_detail_back);
		
	}
	private void setListener() {
		llBlockTradeDetailBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
