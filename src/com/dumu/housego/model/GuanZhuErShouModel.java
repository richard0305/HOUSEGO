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
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.GuanZhuErShouJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class GuanZhuErShouModel implements IGuanZhuErShouModel {

	@Override
	public void loadGuanZhuErShou(final String username, final String table, final AsycnCallBack back) {
		String url = UrlFactory.PostGuanZhuErShouUrl();
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					List<ErShouFangDetails> ershoufangdetails = GuanZhuErShouJSONParse.parseSearch(response);
					back.onSuccess(ershoufangdetails);
				} catch (JSONException e) {

					// try {
					// JSONObject JS = new JSONObject(response);
					//
					// String info=JS.getString("info");
					// back.onError(info);
					// } catch (JSONException e1) {
					// e1.printStackTrace();
					// }

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
				map.put("table", table);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
