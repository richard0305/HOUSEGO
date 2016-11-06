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

public class PTQiuZuListFragment extends Fragment{
	private ListView lv_qiuzu_list;
	private LinearLayout ll_back_ptqiuzulist;
	private TextView tv_ptqiuzu_submit;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_qiuzhulist, null);
		initView(view);
		setListener();
		return view;
	}
	private void initView(View view) {
		lv_qiuzu_list=(ListView) view.findViewById(R.id.lv_qiuzu_list);
		ll_back_ptqiuzulist=(LinearLayout) view.findViewById(R.id.ll_back_ptqiuzulist);
		tv_ptqiuzu_submit=(TextView) view.findViewById(R.id.tv_ptqiuzu_submit);
		
	}
	private void setListener() {
		tv_ptqiuzu_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new PTQiuZuSubmitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		
		ll_back_ptqiuzulist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
	}

}
