package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NewHouseMainActivity extends Activity {
	private ImageView ivNewHouseBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_main);
		setViews();
		setListener();
	}
	private void setListener() {
		ivNewHouseBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NewHouseMainActivity.this.finish();
				
			}
		});
		
	}
	private void setViews() {
		ivNewHouseBack=(ImageView) findViewById(R.id.iv_newhouse_back);
		
	}

}
