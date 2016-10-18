package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class MyYuYueHouseModel implements IMyYuYueHouseModel{

	@Override
	public void LoadYuYueData(final String username, final String t, final AsycnCallBack back) {
		String url=UrlFactory.PostMyYuYueHouseUrl();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				try {
					JSONObject obj=new JSONObject(response);
					String infomation=obj.getString("info").toString();
					back.onSuccess(infomation);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String , String>map=new HashMap<String, String>();
				map.put("username", username);
				map.put("t", t);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
