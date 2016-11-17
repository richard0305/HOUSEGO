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
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class PTershouSubmitModel implements IPTershouSubmitModel{

	@Override
	public void PTershouSubmit(final String username, final String userid, final String modelid, final ErShouFangDetails e,
			final AsycnCallBack back) {
		String url=UrlFactory.PostATershouSubmit();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				try {
					JSONObject 	j = new JSONObject(response);
					String info=j.getString("info");
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
				Map<String, String> m = new HashMap<String, String>();
				m.put("username", username);
				m.put("userid", userid);
				m.put("modelid", modelid);
				
				m.put("province", e.getProvince());
				m.put("city", e.getCity());
				m.put("area", e.getArea());
				m.put("xiaoquname", e.getXiaoquname());
				m.put("chenghu", e.getChenghu());
				m.put("zongjia", e.getZongjia());
				m.put("loudong", e.getLoudong());
				m.put("menpai", e.getMenpai());
				
				m.put("jingweidu", e.getJingweidu());
				m.put("curceng", e.getCurceng());
				m.put("zongceng", e.getZongceng());
				m.put("ceng", e.getCeng());
				m.put("pub_type", e.getPub_type());
				m.put("hidetel", e.getHidetel());
				m.put("title", e.getTitle());
				return m;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
