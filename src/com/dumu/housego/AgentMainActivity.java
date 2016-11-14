package com.dumu.housego;

import java.util.List;

import com.dumu.housego.adapter.AgentDataAdapter;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.presenter.AgentModelDataPresenter;
import com.dumu.housego.presenter.IAgentModelDataPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IAgentModelDataView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class AgentMainActivity extends Activity implements IAgentModelDataView {
	private ImageView ivAgentBack;
	private ListView lvAgentList;
	private AgentDataAdapter agentDataAdapter;
	private List<AgentData> agentdatas;
	private IAgentModelDataPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		presenter = new AgentModelDataPresenter(this);
		String catid = "52";
		presenter.FindAgentModelData(catid);
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

		lvAgentList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getApplicationContext(), AgentDetailActivity.class);

				startActivity(i);
			}
		});

	}

	private void setViews() {
		ivAgentBack = (ImageView) findViewById(R.id.iv_agent_back);
		lvAgentList = (ListView) findViewById(R.id.lv_agent_list);
	}

	@Override
	public void showAgentModelData(List<AgentData> agentdatas) {
		this.agentdatas = agentdatas;
		agentDataAdapter = new AgentDataAdapter(agentdatas, this);
		lvAgentList.setAdapter(agentDataAdapter);

	}

}
