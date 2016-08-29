package com.dumu.housego.model;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RegistPhoneCode;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.Gson;

import android.util.Log;

public class PhoneCodeModel implements IPhoneCodeModel{
	
	
	public PhoneCodeModel() {
		super();
	}

	@Override
	public void GetPhoneCode(String mob, final AsycnCallBack back) {
		
		String url=UrlFactory.GetPhoneCodeUrl(mob);
		StringRequest request=new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Gson gson=new Gson();
				RegistPhoneCode info=gson.fromJson(response, RegistPhoneCode.class);
				Log.i("yanglijun", "info"+info);
				
				back.onSuccess(info);
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
				
			}
		});
		HouseGoApp.getQueue().add(request);
		
	}



}
