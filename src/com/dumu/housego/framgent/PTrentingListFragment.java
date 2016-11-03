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
import android.widget.ListView;
import android.widget.TextView;

public class PTrentingListFragment extends Fragment{
	private LinearLayout ll_back_putongrentinglist;
	private TextView tv_putongrentinglist_submit;
	private ListView lv_submit_renting_list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_renting_list, null);
		initView(view);
		setListener();
		return view;
	}
	
	
	private void initView(View view) {
		ll_back_putongrentinglist=(LinearLayout) view.findViewById(R.id.ll_back_putongrentinglist);
		tv_putongrentinglist_submit=(TextView) view.findViewById(R.id.tv_putongrentinglist_submit);
		lv_submit_renting_list=(ListView) view.findViewById(R.id.lv_submit_renting_list);
	}
	
	private void setListener() {
		ll_back_putongrentinglist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
		tv_putongrentinglist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new PTrentingSumbitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		
	}

}
