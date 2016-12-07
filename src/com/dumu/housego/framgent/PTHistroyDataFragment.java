package com.dumu.housego.framgent;

import com.dumu.housego.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PTHistroyDataFragment extends Fragment{
	private LinearLayout llHistroyBack;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_histroy, null);
		initView(view);
		setListener();
		return view;
	}
	private void initView(View view) {
		llHistroyBack=(LinearLayout) view.findViewById(R.id.ll_histroy_back);
		
	}
	private void setListener() {
		llHistroyBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
	}
}
