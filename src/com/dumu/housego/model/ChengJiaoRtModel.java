package com.dumu.housego.model;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.RentingReconmendJSONParse;
import com.dumu.housego.util.SubMitErShouListJSONParse;
import com.dumu.housego.util.SubMitErShouListJSONParse2;
import com.dumu.housego.util.SubMitRentingListJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class ChengJiaoRtModel implements IChengJiaoRTModel {

	@Override
	public void ChengJiaoRT(final String username, final String table, final AsycnCallBack back) {
		String url = UrlFactory.PostChengJiaoHouseList();
		CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
//				try {
//					List<RentingRecommendData> ershoudetails = RentingReconmendJSONParse.parseSearch(response);
//					Log.e("成交房源", "成交房源="+response);
//					Log.e("成交房源", "成交房源11111111="+ershoudetails);
//					back.onSuccess(ershoudetails);
//					
//				} catch (JSONException e) {
//					if(response!=null){
//						
//					 try {
//					 JSONObject JS = new JSONObject(response);
//					
//					 String info=JS.getString("info");
//					 back.onError(info);
//					 } catch (JSONException e1) {
//					 e1.printStackTrace();
//					 }
//					}else{
//					  back.onError("无成交数据");
//						
//					}
//					e.printStackTrace();
//				}
//				
				if(!response.equals(null)){
					try {
						List<RentingRecommendData> ershoudetails = RentingReconmendJSONParse.parseSearch(response);
						Log.e("成交房源", "成交房源="+response);
						Log.e("成交房源", "成交房源11111111="+ershoudetails);
						back.onSuccess(ershoudetails);
				}catch (JSONException e) {
					 try {
						 JSONObject JS = new JSONObject(response);
						
						 String info=JS.getString("info");
						 back.onError(info);
						 } catch (JSONException e1) {
						 e1.printStackTrace();
						 }
					 e.printStackTrace();
				}
				
				
				
				}else{
					 back.onError("无成交数据");
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				map.put("table", table);

				return map;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
