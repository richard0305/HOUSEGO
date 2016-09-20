package com.dumu.housego.framgent;

import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.FontHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MessageFramgent extends Fragment {
	private RelativeLayout rlMessageNo;
	private UserInfo userinfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.framgent_message, null);
		
		setViews(view);
		setListener();
	
		FontHelper.injectFont(view);
		return view;
	}
	
	@Override
	public void onResume() {
		//判断是否登录，没有登录，跳转到登录界面
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		if(userinfo==null){
		}else{
			//登录后，检测用户是否有Message，没有则显示消息为空按钮
			if(userinfo.getMessage()!="0"){
				rlMessageNo.setVisibility(View.VISIBLE);
			}
		}
		super.onResume();
	}
	
	
	private void setListener() {
		
	}

	private void setViews(View view) {
		rlMessageNo=(RelativeLayout) view.findViewById(R.id.rl_message_no);
	}
}
