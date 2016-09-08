package com.dumu.housego.model;

import java.io.ByteArrayOutputStream;
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

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Base64;

public class ChangeHeadPhotoModel implements IChangeHeadPhotoModel {
	private UserInfo userinfo;

	public ChangeHeadPhotoModel() {
		super();
	}



	@Override
	public void changeHead(final String userid, final Bitmap bitmap, final AsycnCallBack back) {
		String url=UrlFactory.PostChangeHeadPhotoUrl();
		CommonRequest request=new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getBoolean("success")==true) {
						String picurl=obj.getString("avatarUrls").toString();
						back.onSuccess(picurl);
						userinfo=HouseGoApp.getContext().getCurrentUserInfo();
						userinfo.setUserpic(picurl);
						
						HouseGoApp app=HouseGoApp.getContext();
						app.SaveCurrentUserInfo(userinfo);
						
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
				
			   	ByteArrayOutputStream baos = new ByteArrayOutputStream();//outputstream  
			 
                bitmap.compress(CompressFormat.PNG, 100, baos);  
                byte[] appicon = baos.toByteArray();// 转为byte数组  
                String avatar=Base64.encodeToString(appicon, Base64.DEFAULT);
				params.put("userid", userid);
				params.put("__avatar1", avatar);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
	}

}
