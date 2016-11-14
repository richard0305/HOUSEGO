package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.User;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.UrlFactory;

public class UserModel implements IUserModel, ILoginModel, IYzmLoginModel {

	private User user;

	public UserModel() {
		super();
	}

	@Override
	public void regist(final User user, final AsycnCallBack back) {

		String url = UrlFactory.PostRegistUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getInt("success") == 20) {
						String infomation = obj.getString("info").toString();
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
		}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();

				params.put("username", user.getUsername());
				params.put("modelid", user.getModelid());
				params.put("password", user.getPassword());
				params.put("password2", user.getPassword2());
				params.put("yzm", user.getYzm());

				return params;

			}
		};
		HouseGoApp.getQueue().add(request);

	}

	@Override
	public void login(final String phonenum, final String password, final AsycnCallBack back) {
		String url = UrlFactory.PostLoginUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getInt("success") == 37) {
						String userid = obj.getString("userid");
						user = new User();
						user.setUserid(userid);
						HouseGoApp app = HouseGoApp.getContext();
						app.SaveCurrentUser(user);

						String infomation = obj.getString("info").toString();
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

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", phonenum);
				params.put("password", password);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);
	}

	// ��֤���½
	@Override
	public void Yzmlogin(final String shortnumber, final String shortYZM, final AsycnCallBack back) {
		String url = UrlFactory.PostYZMLoginUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getInt("success") == 45) {
						String userid = obj.getString("userid");
						user = new User();
						user.setUserid(userid);
						HouseGoApp app = HouseGoApp.getContext();
						app.SaveCurrentUser(user);

						String infomation = obj.getString("info").toString();
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
		}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("mob", shortnumber);
				params.put("yzm", shortYZM);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
