package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RegistPhoneCode;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.Gson;

import android.util.Log;

public class YzmSendCodeModel implements IYzmSendCodeModel {

	@Override
	public void YzmSendCode(final String mob,final AsycnCallBack back) {
		String url=UrlFactory.PostYZMLogincodeUrl();
		CommonRequest request=new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Gson gson=new Gson();
				RegistPhoneCode phone=gson.fromJson(response, RegistPhoneCode.class);
				String info=phone.getInfo();
				Log.i("yanglijun", "info"+info);
				
				back.onSuccess(info);
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("mob", mob);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
