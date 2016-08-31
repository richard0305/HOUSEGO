package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class AgentMainActivity extends Activity {
	private ImageView ivAgentBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_main);
		setViews();
		setListener();
	}

	private void setListener() {
		ivAgentBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}

	private void setViews() {
		ivAgentBack=(ImageView) findViewById(R.id.iv_agent_back);
	}

}
