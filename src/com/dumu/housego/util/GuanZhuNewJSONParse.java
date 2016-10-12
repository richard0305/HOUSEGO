package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GuanZhuNewJSONParse {
	public static List<NewHouseDetail> parseSearch(String json) throws JSONException {
			JSONArray array = new JSONArray(json); 
			List<NewHouseDetail> newhousedetails = new ArrayList<NewHouseDetail>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				NewHouseDetail n = new NewHouseDetail();
				if(obj.getString("fromtable").equals("new")){
					JSONObject obj2=obj.getJSONObject("house");
					
					n.setId(obj2.getString("id"));
					n.setCatid(obj2.getString("catid"));
					n.setTitle(obj2.getString("title"));
					n.setThumb(obj2.getString("thumb"));
					n.setShiarea(obj2.getString("shiarea"));
					n.setMainjiarea(obj2.getString("mianjiarea"));
					n.setJunjia(obj2.getString("junjia"));
					n.setLoupandizhi(obj2.getString("loupandizhi"));
					n.setContacttel(obj2.getString("contacttel"));
					n.setKaipandate(obj2.getString("kaipandate"));
					n.setJiaofangdate(obj2.getString("jiaofangdate"));
					

					newhousedetails.add(n);	
				}
			
				
			}
			return newhousedetails;
		

	}

}
