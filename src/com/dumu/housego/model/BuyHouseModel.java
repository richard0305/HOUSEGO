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
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.util.BuyHouseListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class BuyHouseModel implements IBuyHouseModel{

	@Override
	public void buyhouselist(final String username,final String table, final AsycnCallBack back) {
		String url=UrlFactory.PostQiuBuylist();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					List<QiuZuBuyHouseList>lists=BuyHouseListJSONParse.parseSearch(response);
					back.onSuccess(lists);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String >map=new HashMap<String, String>();
				map.put("username", username);
				map.put("table", table);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
	}

	@Override
	public void buyhousedelete(final String id, final String userid, final String username, final AsycnCallBack back) {
		String url=UrlFactory.PostBuyHousedelete();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				try {
					JSONObject 	J = new JSONObject(response);
					String info=J.getString("info");
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
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String >map=new HashMap<String, String>();
				map.put("id", id);
				map.put("userid", userid);
				map.put("username", username);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
	}
 
}
