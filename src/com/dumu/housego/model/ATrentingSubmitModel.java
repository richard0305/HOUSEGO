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
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class ATrentingSubmitModel implements IATrentingSubmitModel{

	@Override
	public void ATrentingSubmit(final RentingDetail r, final AsycnCallBack back) {
		String url=UrlFactory.PostATrentingSubmit();
		CommonRequest request=new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				try {
					JSONObject 	J = new JSONObject(response);
					String info =J.getString("info");
					back.onSuccess(info);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				
			}
		},new  ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String>m=new HashMap<String, String>();
				m.put("username",r.getUsername());
				m.put("modelid", r.getModelid());
				m.put("userid", r.getUserid());
				m.put("province", r.getProvince());
				m.put("city", r.getCity());
				m.put("area", r.getArea());
				m.put("xiaoquname", r.getXiaoquname());
				m.put("jingweidu", r.getJingweidu());
				m.put("shi", r.getShi());
				m.put("ting", r.getTing());
				m.put("wei", r.getWei());
				m.put("mianji", r.getMianji());
				m.put("zujin", r.getZujin());
				m.put("title", r.getTitle());
				m.put("desc", r.getDesc());
				m.put("address", r.getAddress());
				m.put("ceng", r.getCeng());
				m.put("zongceng", r.getZongceng());
				m.put("fangling", r.getFangling());
				//可传
				m.put("zhuangxiu", r.getZhuangxiu());
				m.put("chaoxiang", r.getChaoxiang());
				m.put("leixing", r.getLeixing());
				m.put("jianzhutype", r.getJianzhutype());
				m.put("zulin", r.getZulin());
				m.put("fukuan", r.getFukuan());
				m.put("fangwupeitao", r.getFangwupeitao());
				m.put("ditiexian", r.getDitiexian());
				m.put("biaoqian", r.getBiaoqian());
				m.put("huxingjieshao", r.getHuxingjieshao());
				m.put("liangdian", r.getLiangdian());
				m.put("czreason", r.getCzreason());
				m.put("xiaoquintro", r.getXiaoquintro());
				m.put("zxdesc", r.getZxdesc());
				m.put("shenghuopeitao", r.getShenghuopeitao());
				m.put("jiaotong", r.getJiaotong());
				m.put("yezhushuo", r.getYezhushuo());
				
				
				return m;
			}
		};
		HouseGoApp.getQueue().add(request);
		
	}

}
