package com.dumu.housego.framgent;

import com.dumu.housego.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ATrentingListFragment extends Fragment{
	private TextView tv_agentrentinglist_submit;
	private LinearLayout ll_back_agentrentinglist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_agent_renting_list, null);
		initView(view);
		setListener();
		return view;
	}

	private void initView(View view) {
		tv_agentrentinglist_submit=(TextView) view.findViewById(R.id.tv_agentrentinglist_submit);
		ll_back_agentrentinglist=(LinearLayout) view.findViewById(R.id.ll_back_agentrentinglist);
	}

	private void setListener() {
		tv_agentrentinglist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new ATrentingSubmitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
				trans.commitAllowingStateLoss();	
			}
		});
		
		ll_back_agentrentinglist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
	}

}
