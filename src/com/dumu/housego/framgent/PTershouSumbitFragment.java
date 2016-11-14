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

public class PTershouSumbitFragment extends Fragment {
	private LinearLayout ll_back_putongershou;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pt_ershou_sumbit, null);
		initView(view);
		setListener();
		return view;
	}

	private void initView(View view) {
		ll_back_putongershou = (LinearLayout) view.findViewById(R.id.ll_back_putongershou);

	}

	private void setListener() {
		ll_back_putongershou.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Fragment fragment = new PTershouListFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});

	}
}
