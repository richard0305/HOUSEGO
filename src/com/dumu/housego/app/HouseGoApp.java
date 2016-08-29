package com.dumu.housego.app;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class HouseGoApp extends Application{
	private static HouseGoApp context;
	private static RequestQueue Queue;
	private static HouseGoApp housegoapp;
	
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
	
}
