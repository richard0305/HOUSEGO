package com.dumu.housego.app;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dumu.housego.R;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;

import android.app.Application;

public class HouseGoApp extends Application {
	private static HouseGoApp context;
	private static RequestQueue Queue;
	private static HouseGoApp housegoapp;
	private User user;
	private UserInfo userinfo;

	public static HouseGoApp getContext() {
		return context;
	}

	public static RequestQueue getQueue() {
		return Queue;
	}

	public HouseGoApp() {
		super();

	}

	public static HouseGoApp getHousegoapp() {
		return housegoapp;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		housegoapp=this;
		context = this;
		Queue = Volley.newRequestQueue(context);
		
		
		
		   
	}

	/**
	 * 保存当前用户
	 */
	public void SaveCurrentUser(User user) {
		this.user = user;
	}

	public User getCurrentUser() {
		return this.user;

	}

	/**
	 * 保存当前用户信息
	 */
	public void SaveCurrentUserInfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public UserInfo getCurrentUserInfo() {
		return this.userinfo;

	}
	

}
