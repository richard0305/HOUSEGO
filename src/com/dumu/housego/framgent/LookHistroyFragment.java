package com.dumu.housego.framgent;

import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class LookHistroyFragment extends Fragment {
	private Button btnLogin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_look_histroy, null);
		
		
		return view;
	}
	

}
