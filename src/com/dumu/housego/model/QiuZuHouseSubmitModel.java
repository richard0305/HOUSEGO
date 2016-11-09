package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class QiuZuHouseSubmitModel implements IQiuZuHouseSubmitModel {

	@Override
	public void QiuZuSubmit(final String username, final String province, final String city, final String area, final String zulin, final String shi,
			final String zujinrange, final String chenghu, final String title, final AsycnCallBack back) {
		String url=UrlFactory.PostQiuZuHouseSubmit();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj=new JSONObject(response);
					String info=obj.getString("info");
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
				Map<String, String>map=new HashMap<String, String>();
				map.put("username", username);
				map.put("province", province);
				map.put("city", city);
				map.put("area", area);
				map.put("zulin", zulin);
				map.put("shi", shi);
				map.put("zujinrange", zujinrange);
				map.put("chenghu", chenghu);
				map.put("title", title);
				return map;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
