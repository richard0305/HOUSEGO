package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class PaySuccessModel implements IPaySuccessModel{

	@Override
	public void PayInfo(final String resultStatus, final String jine, final String order_no, AsycnCallBack back) {
		String url=UrlFactory.PostPayStatusSuccess();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("response", "response-------="+response);
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String>p=new HashMap<String, String>();
				p.put("resultStatus", resultStatus);
				p.put("jine", jine);
				p.put("order_no", order_no);
				return p;
			}
		};
		HouseGoApp.getQueue().add(request);
				
		
	}

}
