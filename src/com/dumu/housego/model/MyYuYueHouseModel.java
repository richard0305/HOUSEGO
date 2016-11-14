package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.MyYuYueListJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class MyYuYueHouseModel implements IMyYuYueHouseModel {

	@Override
	public void LoadYuYueData(final String username, final AsycnCallBack back) {
		String url = UrlFactory.PostMyYuYueHouseUrl();
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {

				try {
					Log.e("yanglijunaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaa" + response);
					List<YuYueData> yuyuedatas = MyYuYueListJSONParse.parseSearch(response);

					back.onSuccess(yuyuedatas);

					// if((new JSONObject(response)).get("success").equals(98)))
					// JSONObject obj=new JSONObject(response);
					// String infomation=obj.getString("info").toString();
					// back.onError(infomation);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

	@Override
	public void DeleteYuYue(final String id, final String userid, final String username, final AsycnCallBack back) {
		String url = UrlFactory.PostDeleteMyYuYueHouseUrl();
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {

				try {
					JSONObject obj = new JSONObject(response);
					String infomation = obj.getString("info").toString();
					back.onSuccess(infomation);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> param = new HashMap<String, String>();
				param.put("id", id);
				param.put("userid", userid);
				param.put("username", username);
				return param;
			}
		};
		HouseGoApp.getQueue().add(request);
	}
}
