package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.ATQiuZuListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.QiuZuListJSONParse;
import com.dumu.housego.util.UrlFactory;

public class QiuZuListModel implements IQiuZuListModel {

	@Override
	public void QiuZuList(final String username, final String table, final AsycnCallBack back) {
		String url = UrlFactory.PostQiuBuylist();
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					List<QiuzuANDQiuGou> qiuzulists = ATQiuZuListJSONParse.parseSearch(response);
					back.onSuccess(qiuzulists);
				} catch (JSONException e) {
					JSONObject j;
					try {
						j = new JSONObject(response);
						String info=j.getString("info");
						back.onError(info);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
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

	@Override
	public void DeleteQiuZu(final String id, final String userid, final String username, final AsycnCallBack back) {
		String url = UrlFactory.PostQiuZuHouseListDelete();
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject j = new JSONObject(response);
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
				map.put("username", username);
				map.put("id", id);
				map.put("userid", userid);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
