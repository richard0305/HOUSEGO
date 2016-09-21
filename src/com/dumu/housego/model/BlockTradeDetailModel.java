package com.dumu.housego.model;

import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

public class BlockTradeDetailModel implements IBlockTradeDetailaModel{
	private BlockTradeDetail b;

	public BlockTradeDetailModel() {
		super();
	}

	@Override
	public void FindBlockTradeDetail(String catid, String id, AsycnCallBack back) {
		String url=UrlFactory.PostRecommendListToDetailUrl(catid, id);
		CommonRequest request=new CommonRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj=new JSONObject(response);
					b.setTypeid(obj.getString("typeid"));
					b.setTitle(obj.getString("title"));
					b.setStyle(obj.getString("style"));
					b.setThumb(obj.getString("thumb"));
					
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		HouseGoApp.getQueue().add(request);
		
	}
	
	
}
