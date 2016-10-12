package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.GuanZhuErShouJSONParse;
import com.dumu.housego.util.GuanZhuNewJSONParse;
import com.dumu.housego.util.UrlFactory;

public class GuanZhuNewModel implements IGuanZhuNewModel{

	@Override
	public void loadGuanZhuNew(final String username, final AsycnCallBack back) {
		String url=UrlFactory.PostGuanZhuErShouUrl();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					List<NewHouseDetail> newhousedetails=GuanZhuNewJSONParse.parseSearch(response);
					back.onSuccess(newhousedetails);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		},new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String>map=new HashMap<String, String>();
				map.put("username", username);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
