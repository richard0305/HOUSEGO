package com.dumu.housego.framgent;

import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFramgent extends Fragment {
	private TextView tvLoginRegist;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.framgent_my, null);
		setViews(view);
		setListener();
		return view;
	}

	private void setListener() {
		tvLoginRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i=new Intent(getActivity(), LoginActivity.class);
			startActivity(i);
			}
		});
		
	}

	private void setViews(View view) {
		tvLoginRegist=(TextView) view.findViewById(R.id.tv_login_regist);
		
	}
}
