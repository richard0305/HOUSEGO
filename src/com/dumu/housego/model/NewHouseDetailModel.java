package com.dumu.housego.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;
import android.widget.Toast;

public class NewHouseDetailModel implements INewHouseDetailaModel{
	private NewHouseDetail nhb;

	public NewHouseDetailModel() {
		super();
	}

	@Override
	public void FindNewHouseDetail(final String catid, final String id, final AsycnCallBack back) {
		String url=UrlFactory.GetRecommendListToDetailUrl(catid, id);
		Log.i("----------", "yanglijun------==========="+url);
		
		JsonObjectRequest jsonrequest=new JsonObjectRequest(Request.Method.GET, url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				
				Log.i("===============", "yanglijun------"+response.toString());
				try {
					
					nhb.setThumb(response.getString("thumb"));
					nhb.setTitle(response.getString("title"));
					nhb.setJunjia(response.getString("junjia"));
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
		});
		HouseGoApp.getQueue().add(jsonrequest);
		
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
