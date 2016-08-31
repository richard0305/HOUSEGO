package com.dumu.housego.framgent;

import com.dumu.housego.MySettingMainActivity;
import com.dumu.housego.PersonalMainActivity;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyFramgent extends Fragment {
	private TextView tvLoginRegist;
	private RelativeLayout rlSetting;
	private RelativeLayout rlHistory;
	private RelativeLayout rlYuyueguanli;
	private RelativeLayout rlMyentrust;
	private RelativeLayout rlMyrenting;
	private RelativeLayout rlMyershoufang;
	private ImageView ivMyPic;
	

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
		
		ivMyPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i2=new Intent(getActivity(), PersonalMainActivity.class);
				startActivity(i2);
				
			}
		});
		
		rlSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i3=new Intent(getActivity(), MySettingMainActivity.class);
				startActivity(i3);
				
			}
		});
		
	}

	private void setViews(View view) {
		tvLoginRegist=(TextView) view.findViewById(R.id.tv_login_regist);
		rlSetting=(RelativeLayout) view.findViewById(R.id.rl_settings);
		rlHistory=(RelativeLayout) view.findViewById(R.id.rl_Histroy);
		rlMyershoufang=(RelativeLayout) view.findViewById(R.id.rl_Myershoufang);
		rlMyrenting=(RelativeLayout) view.findViewById(R.id.rl_Myrenting);
		rlMyentrust=(RelativeLayout) view.findViewById(R.id.rl_Myentrust);
		rlYuyueguanli=(RelativeLayout) view.findViewById(R.id.rl_Yuyueguanli);
		ivMyPic=(ImageView) view.findViewById(R.id.iv_my_Photo);
	}
}
