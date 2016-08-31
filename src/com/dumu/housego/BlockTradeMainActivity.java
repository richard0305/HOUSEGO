package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class BlockTradeMainActivity extends Activity {
	private LinearLayout llBlockTradeBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_main);
		setViews();
		setListener();
		
	}
	private void setViews() {
		llBlockTradeBack=(LinearLayout) findViewById(R.id.ll_block_trade_back);
		
	}
	private void setListener() {
		llBlockTradeBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}


}
