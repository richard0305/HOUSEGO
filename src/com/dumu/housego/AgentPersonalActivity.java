package com.dumu.housego;

import com.dumu.housego.framgent.AGChengJiaohouseFragment;
import com.dumu.housego.framgent.ATershouListFragment;
import com.dumu.housego.framgent.ATershouSubmitFragment;
import com.dumu.housego.framgent.ATrentingListFragment;
import com.dumu.housego.framgent.ATrentingSubmitFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AgentPersonalActivity extends FragmentActivity {
	public static final int AGENT_ERSHOU_LIST=0;//经纪人二手房列表
	public static final int AGENT_ERSHOU_SUBMIT=1;//经纪人二手房发布
	public static final int AGENT_RENTING_LIST=2;//经纪人出租房列表
	public static final int AGENT_RENTING_SUBMIT=3;//经纪人出租房发布
	public static final int AGENT_CHENGJIAO=4;    //经纪人成交房源
	
	
	private FrameLayout flAgentFragment;
	private Fragment fragment;
	int DisplayType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_personal);
		DisplayType=getIntent().getIntExtra("FRAGMENT_KEY", AGENT_ERSHOU_LIST);
		initView();
		initData();
		
	}
	
	private void initData() {
		
		switch (DisplayType) {
		case AGENT_ERSHOU_LIST:
			fragment=new ATershouListFragment();
			break;
		case AGENT_RENTING_LIST:
			fragment=new ATrentingListFragment();
			break;
		case AGENT_CHENGJIAO:
			fragment=new AGChengJiaohouseFragment();
			break;
			
		default:
			break;
		}
		
		
		
		FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.fl_agent_fragment, fragment);
		trans.commitAllowingStateLoss();
		
	}

	
	private void initView() {
		flAgentFragment=(FrameLayout) findViewById(R.id.fl_agent_fragment);
		
	}


}
