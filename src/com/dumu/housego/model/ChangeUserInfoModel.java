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
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class ChangeUserInfoModel implements IChangeUserInfoModel {
	private UserInfo userinfo;

	public ChangeUserInfoModel() {
		super();
	}

	@Override
	public void change(final String userid, final String nickname, final String about,final String sex, final AsycnCallBack back) {
		String url=UrlFactory.PostChangeUserInfoUrl();
		CommonRequest request=new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getInt("success") == 54) {
						String infomation=obj.getString("info").toString();
						back.onSuccess(infomation);
					} else {
						back.onError(obj.getString("info"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("userid", userid);
				params.put("nickname", nickname);
				params.put("about", about);
				params.put("sex", sex);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
	}

}
