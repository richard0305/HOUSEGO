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

public class FindPasswordModel implements IFindPasswordModel{

	public FindPasswordModel() {
		super();
	}
	@Override
	public void FindPassword(final String phonenum, final String smscode, final String password, final String password2, final AsycnCallBack back) {
		String url=UrlFactory.PostFindPasswordUrl();
		CommonRequest request=new CommonRequest(Request.Method.POST, url,new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getString("success")=="51") {
						String info=obj.getString("info");
						back.onSuccess(info);
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
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("mob", phonenum);
				params.put("yzm", smscode);
				params.put("pwd1", password);
				params.put("pwd2", password2);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
