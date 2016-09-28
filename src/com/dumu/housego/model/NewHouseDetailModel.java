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
	public void FindNewHouseDetail(final String catid, final String id, final AsycnCallBack back) {
		String url=UrlFactory.PostRecommendListToDetailUrl();
		
		Log.i("----------", "yanglijun------==========="+url);
		
	CommonRequest request=new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				
				Log.i("===============", "yanglijun------"+response.toString());
				Log.e("===============", "yanglijun------"+response.toString());
				try {
				JSONObject obj=new JSONObject(response);
					nhb=new NewHouseDetail();
					nhb.setThumb(obj.getString("thumb"));
					nhb.setTitle(obj.getString("title"));
					nhb.setJunjia(obj.getString("junjia"));
					back.onSuccess(nhb);
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
			Map<String, String> params = new HashMap<String, String>();
			params.put("catid", catid);
			params.put("id", id);
			return params;
		}
	};
		HouseGoApp.getQueue().add(request);
		
//		StringRequest request=new StringRequest( url, new Listener<String>() {
//
//			@Override
//			public void onResponse(String response) {
//				try {
//					
//					Log.i("===============", "yanglijun------"+response);
////					String data=RemoveBOM.removeBOM(response);
////					if(response != null && response.startsWith("\ufeff"))
////					{
////						response =  response.substring(1);
////					}
////					JSONObject obj = new JSONObject(response);
//					
//					JSONObject obj=new JSONObject(response);
//					nhb.setThumb(obj.getString("thumb"));
//					nhb.setTitle(obj.getString("title"));
//					nhb.setJunjia(obj.getString("junjia"));
//					
//					back.onSuccess(nhb);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				back.onError(error.getMessage());
//				
//			}
//		});
//		
//		HouseGoApp.getQueue().add(request);
//		
	}

}
