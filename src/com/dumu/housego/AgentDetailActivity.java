package com.dumu.housego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class AgentDetailActivity extends Activity {
	private LinearLayout llAgentdetailBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_detail);
		initViews();
		initListener();
	}

	private void initViews() {
		llAgentdetailBack = (LinearLayout) findViewById(R.id.ll_agentdetail_back);

	}

	private void initListener() {
		llAgentdetailBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
