package com.dumu.housego.framgent;

import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MessageFramgent extends Fragment {
	private Button btnHouseLogin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.framgent_message, null);
		
		setViews(view);
		setListener();
		return view;
	}
	private void setListener() {
		btnHouseLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), LoginActivity.class));
				
			}
		});
		
	}

	private void setViews(View view) {
		btnHouseLogin=(Button) view.findViewById(R.id.btn_house_login);
		
	}
}
