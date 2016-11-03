package com.dumu.housego.framgent;

import com.dumu.housego.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PTrentingSumbitFragment extends Fragment{
	private LinearLayout ll_back_putongrenting;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_renting_sumbit, null);
		initView(view);
		setListener();
		return view;
	}
	private void initView(View view) {
		ll_back_putongrenting=(LinearLayout) view.findViewById(R.id.ll_back_putongrenting);
		
	}
	private void setListener() {
		ll_back_putongrenting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				 Fragment fragment=new PTrentingListFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();				
			}
		});
		
	}

}
