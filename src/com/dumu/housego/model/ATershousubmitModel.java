package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.HttpUtils;

public class ATershousubmitModel implements IATershousubmitModel {

	@Override
	public void ATershousubmit(final ATerShouSubmit at, final AsycnCallBack back) {

		String url = "http://www.taoshenfang.com/index.php?g=api&m=user&a=add_ershou";
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				JSONObject j;
				try {
					j = new JSONObject(response);
					String info = j.getString("info");
					back.onSuccess(info);
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
				// 必传
				map.put("username", at.getUsername());
				map.put("modelid", at.getModelid());
				map.put("userid", at.getUserid());
				map.put("province", at.getProvince());
				map.put("city", at.getCity());
				map.put("area", at.getArea());
				map.put("xiaoquname", at.getXiaoquname());
				map.put("jingweidu", at.getJingweidu());
				map.put("zongjia", at.getZongjia());
				map.put("title", at.getTitle());
				map.put("desc", at.getDesc());
				map.put("fangling", at.getFangling());
				map.put("jianzhumianji", at.getJianzhumianji());
				// 必传

				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
	}

}
