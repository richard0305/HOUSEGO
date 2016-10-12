package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;
import com.google.gson.JsonObject;

import android.util.Log;

public class NewHouseDetailModel implements INewHouseDetailaModel{
	private NewHouseDetail nhb;

	public NewHouseDetailModel() {
		super();
	}

	@Override
	public void FindNewHouseDetail(final String catid, final String id, AsycnCallBack back) {
		String url=UrlFactory.PostRecommendListToDetailUrl();
		
		Log.e("NewHouseDetailActivity", "¿ªÊ¼ÁË");
		Log.e("NewHouseDetailActivity", "url = " + url);
		
		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("NewHouseDetailActivity", "response"+response);
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		})
		
		 {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("catid", catid);
				map.put("id", id);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
		
//		CommonRequest request=new CommonRequest(Request.Method.POST, url, new Listener<String>() {
//			@Override
//			public void onResponse(String response) {
//				Log.e("NewHouseDetailActivity", "response"+response);
//				
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// TODO Auto-generated method stub
//				Log.v("========2016-10-8", "yanglijun"+error.getMessage());
//			}
//		})
//		{
//			
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				Map<String, String> params=new HashMap<String, String>();
//				params.put("catid", catid);
//				params.put("id", id);
//				return params;
//			}
////			
////			@Override
////			public Map<String, String> getHeaders() throws AuthFailureError {
////				  HashMap<String, String> headers = new HashMap<String, String>();
////	                // do not add anything here
////	                return headers;
////			}
////			
//		};
//		HouseGoApp.getQueue().add(request);
	}


}
