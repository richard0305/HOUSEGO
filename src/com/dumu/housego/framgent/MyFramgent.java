package com.dumu.housego.framgent;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.bumptech.glide.Glide;
import com.dumu.housego.MyRentingHouseAgentActivity;
import com.dumu.housego.MySettingMainActivity;
import com.dumu.housego.PersonalMainActivity;
import com.dumu.housego.PuTongMyGuanZhuActivity;
import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.FontHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	private UserInfo userinfo;
	private RelativeLayout rlPTMyGuanZhu;
private LinearLayout llMysettingAgentLogin,llMysettingPuTongLogin,llMysettingNologin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.framgent_my, null);
		setViews(view);
		x.view().inject(view);
		setListener();
		

		
		FontHelper.injectFont(view);
		return view;
	}
	
	@Override
	public void onResume() {
		
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		if(userinfo!=null){
		tvLoginRegist.setText(userinfo.getNickname()+"");
		String imgurl=userinfo.getUserpic();
		Glide.with(getContext()).load(imgurl).into(ivMyPic);
		tvLoginRegist.setClickable(false);
		ivMyPic.setClickable(true);
		
		if(userinfo.getModelid().equals("35")){
			llMysettingPuTongLogin.setVisibility(View.VISIBLE);
		}else{
			llMysettingAgentLogin.setVisibility(View.VISIBLE);
		}
		
		
		}else{
			llMysettingNologin.setVisibility(View.VISIBLE);
			ivMyPic.setClickable(false);
			tvLoginRegist.setClickable(true);
		}
		
		super.onResume();
	}
	
	
	

	private void setListener() {
	
		
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
		
		rlMyrenting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i4=new Intent(getActivity(), MyRentingHouseAgentActivity.class);
				startActivity(i4);
				
			}
		});
		
		tvLoginRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					startActivity(new Intent(getContext(), LoginActivity.class));
			}
		});
		
		rlPTMyGuanZhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getActivity(), PuTongMyGuanZhuActivity.class));
				
			}
		});
		
		
	}

	
	
	private void setViews(View view) {
		tvLoginRegist=(TextView) view.findViewById(R.id.tv_my_login_regist);
		rlSetting=(RelativeLayout) view.findViewById(R.id.rl_settings);
		rlHistory=(RelativeLayout) view.findViewById(R.id.rl_Histroy);
		rlMyershoufang=(RelativeLayout) view.findViewById(R.id.rl_Myershoufang);
		rlMyrenting=(RelativeLayout) view.findViewById(R.id.rl_Myrenting);
		rlMyentrust=(RelativeLayout) view.findViewById(R.id.rl_Myentrust);
		rlYuyueguanli=(RelativeLayout) view.findViewById(R.id.rl_Yuyueguanli);
		ivMyPic=(ImageView) view.findViewById(R.id.iv_my_Photo);
		
		llMysettingNologin=(LinearLayout) view.findViewById(R.id.ll_mysetting_nologin);
		llMysettingAgentLogin=(LinearLayout) view.findViewById(R.id.ll_mysetting_agent_login);
		llMysettingPuTongLogin=(LinearLayout) view.findViewById(R.id.ll_mysetting_putong_login);
		rlPTMyGuanZhu=(RelativeLayout) view.findViewById(R.id.rl_putong_myguanzhu);
		
	}
}
