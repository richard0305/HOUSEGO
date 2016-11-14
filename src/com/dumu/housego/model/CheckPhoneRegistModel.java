package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class CheckPhoneRegistModel implements ICheckPhoneRegistModel {

	public CheckPhoneRegistModel() {
		super();
	}

	@Override
	public void checkPhone(final String phonenum, final AsycnCallBack back) {
		String url = UrlFactory.PostCheckPhoneRegistUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getString("success") == "30") {
						String info = obj.getString("info");
						back.onSuccess(info);
					} else {
						String error = obj.getString("info");
						back.onError(error);
					}
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
				Map<String, String> params = new HashMap<String, String>();
				params.put("mob", phonenum);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
	}

}
