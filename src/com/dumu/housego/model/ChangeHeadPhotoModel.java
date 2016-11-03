package com.dumu.housego.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import android.util.Base64;
import android.util.Log;

public class ChangeHeadPhotoModel implements IChangeHeadPhotoModel {
	private UserInfo userinfo;

	public ChangeHeadPhotoModel() {
		super();
	}



	@Override
	public void changeHead(final String userid, final String imagePath, final AsycnCallBack back) {
		String url=UrlFactory.PostChangeHeadPhotoUrl();
		CommonRequest request=new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("====================","response"+ response);
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getBoolean("success")==true) {
						String picurl=obj.getString("avatarUrls").toString();
						Log.e("====================","picurl"+ picurl);
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
				String image =getImageStr(imagePath);
				params.put("userid", userid);
				params.put("__avatar1", image);
				return params;
			}
		};
		
		HouseGoApp.getQueue().add(request);
	}
	
	public static String getImageStr(String filePath) {

		//将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in =null;

		byte[] data =null;

		//读取图片字节数组

		try{

		in =new FileInputStream(filePath);

		data =new byte[in.available()];

		in.read(data);

		in.close();

		}catch(IOException e) {

		e.printStackTrace();

		}

		//对字节数组Base64编码

		//返回Base64编码过的字节数组字符串

		return Base64.encodeToString(data,Base64.DEFAULT);

		}

	

}
