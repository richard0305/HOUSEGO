package com.dumu.housego.model;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RegistPhoneCode;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.Gson;

import android.util.Log;

public class PhoneCodeModel implements IPhoneCodeModel {
	private RequestQueue queue;

	public PhoneCodeModel() {
		super();
		this.queue = Volley.newRequestQueue(HouseGoApp.getContext());

	}

	@Override
	public void GetPhoneCode(String mob, final AsycnCallBack back) {

		String url = UrlFactory.GetPhoneCodeUrl(mob);
		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Gson gson = new Gson();
				RegistPhoneCode phone = gson.fromJson(response, RegistPhoneCode.class);
				String info = phone.getInfo();

				back.onSuccess(info);

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());

			}
		});
		queue.add(request);

	}

}
