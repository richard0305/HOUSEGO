package com.dumu.housego.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentCommentList;
import com.dumu.housego.entity.AgentDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.AgentCommentListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class AgentDetailModel implements IAgentDetailModel {

	@Override
	public void AgentDetail(String userid, final AsycnCallBack back) {
		String url=UrlFactory.GetAgentDetail(userid);
		CommonRequest request=new CommonRequest(Method.GET, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject j=new JSONObject(response);
				AgentDetail a=new AgentDetail();
				a.setUserid(j.getString("userid"));
				a.setCardnumber(j.getString("cardnumber"));
				a.setMainarea(j.getString("mainarea"));
				a.setSfzpic(j.getString("sfzpic"));
				a.setLeixing(j.getString("leixing"));
				
				a.setConame(j.getString("coname"));
				a.setBiaoqian(j.getString("biaoqian"));
				a.setDengji(j.getString("dengji"));
				a.setRealname(j.getString("realname"));
				a.setWorktime(j.getString("worktime"));
				a.setJiav(j.getString("jiav"));
				a.setMainareaids(j.getString("mainareaids"));
				
				JSONObject i=j.getJSONObject("base");
				a.setUsername(i.getString("username"));
				a.setSex(i.getString("sex"));
				a.setAbout(i.getString("about"));
				a.setRegdate(i.getString("regdate"));
				a.setVtel(i.getString("vtel"));
				a.setCtel(i.getString("ctel"));
				a.setUserpic(i.getString("userpic"));
				
				back.onSuccess(a);
				
				
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
				
			}
		});
		HouseGoApp.getQueue().add(request);
		
	}

	
	
	
	@Override
	public void AgentComment(String userid, final AsycnCallBack back) {
		String url=UrlFactory.GetAgentComment(userid);
		CommonRequest request=new CommonRequest(Method.GET, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					List<AgentCommentList>comments=AgentCommentListJSONParse.parseSearch(response);
					back.onSuccess(comments);
				} catch (JSONException e) {
					try {
						JSONObject j=new JSONObject(response);
						String info=j.getString("info");
						back.onError(info);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					
					e.printStackTrace();
				}
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		HouseGoApp.getQueue().add(request);
		
	}

}
