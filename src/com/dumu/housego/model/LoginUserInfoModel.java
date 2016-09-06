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

public class LoginUserInfoModel implements ILoginUserInfoModel{
	private UserInfo userinfo;
	public LoginUserInfoModel() {
		super();
	}
	@Override
	public void login(final String userid, final AsycnCallBack back) {
		String url = UrlFactory.PostLoginUserInfoUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					userinfo=new UserInfo();
					userinfo.setUserid(obj.getString("userid"));
					userinfo.setUsername(obj.getString("username"));
					userinfo.setPassword(obj.getString("password"));
					userinfo.setEncrypt(obj.getString("encrypt"));
					userinfo.setChecked(obj.getString("checked"));
					userinfo.setSex(obj.getString("sex"));
					userinfo.setAbout(obj.getString("about"));
					userinfo.setHeat(obj.getString("heat"));
					userinfo.setTheme(obj.getString("theme"));
					userinfo.setPraise(obj.getString("praise"));
					userinfo.setAttention(obj.getString("attention"));
					userinfo.setFans(obj.getString("fans"));
					userinfo.setShare(obj.getString("share"));
					userinfo.setNickname(obj.getString("nickname"));
					userinfo.setUserpic(obj.getString("userpic"));
					userinfo.setRegdate(obj.getString("regdate"));
					userinfo.setLastdate(obj.getString("lastdate"));
					userinfo.setRegip(obj.getString("regip"));
					userinfo.setLastip(obj.getString("lastip"));
					userinfo.setLoginnum(obj.getString("loginnum"));
					userinfo.setEmail(obj.getString("email"));
					userinfo.setGroupid(obj.getString("groupid"));
					userinfo.setAreaid(obj.getString("Areaid"));
					userinfo.setAmount(obj.getString("amount"));
					userinfo.setPoint(obj.getString("point"));
					userinfo.setModelid(obj.getString("modelid"));
					userinfo.setMessage(obj.getString("message"));
					userinfo.setIslock(obj.getString("islock"));
					userinfo.setVip(obj.getString("vip"));
					userinfo.setOverduedate(obj.getString("overduedate"));
					
					HouseGoApp app=HouseGoApp.getContext();
					app.SaveCurrentUserInfo(userinfo);;
					
					
					back.onSuccess(userinfo);
					
					
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
				params.put("userid", userid);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
