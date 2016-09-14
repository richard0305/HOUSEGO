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

public class MessageFramgent extends Fragment {
	private Button btnMessageNull;
	private UserInfo userinfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.framgent_message, null);
		
		setViews(view);
		setListener();
		
		//�ж��Ƿ��¼��û�е�¼����ת����¼����
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		if(userinfo==null){
		}else{
			//��¼�󣬼���û��Ƿ���Message��û������ʾ��ϢΪ�հ�ť
			if(userinfo.getMessage()!="0"){
				btnMessageNull.setVisibility(View.GONE);
			}
		}
		
		FontHelper.injectFont(view);
		return view;
	}
	
	private void setListener() {
		
	}

	private void setViews(View view) {
		btnMessageNull=(Button) view.findViewById(R.id.btn_message_null);
	}
}
