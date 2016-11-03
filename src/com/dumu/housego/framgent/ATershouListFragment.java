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

public class ATershouListFragment extends Fragment{
	private LinearLayout ll_back_agentershoulist;
	private TextView tv_agentershoulist_submit;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_agent_ershou_list, null);
		initview(view);
		setListener();
		return view;
	}
	private void setListener() {
		ll_back_agentershoulist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		tv_agentershoulist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new ATershouSubmitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
				trans.commitAllowingStateLoss();	
			}
		});
		
	}
	private void initview(View view) {
		ll_back_agentershoulist=(LinearLayout) view.findViewById(R.id.ll_back_agentershoulist);
		tv_agentershoulist_submit=(TextView) view.findViewById(R.id.tv_agentershoulist_submit);
		
	}
}
