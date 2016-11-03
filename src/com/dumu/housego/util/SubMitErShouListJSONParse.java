package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.entity.SubmitErshouList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SubMitErShouListJSONParse {
	public static List<SubmitErshouList> parseSearch(String json) throws JSONException {
			JSONArray array = new JSONArray(json); 
			List<SubmitErshouList> submitershous = new ArrayList<SubmitErshouList>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				SubmitErshouList n = new SubmitErshouList();
					
					n.setId(obj.getString("id"));
					n.setCatid(obj.getString("catid"));
					n.setTitle(obj.getString("title"));
					n.setXiaoquname(obj.getString("xiaoquname"));
					n.setAreaname(obj.getString("areaname"));
					n.setCityname(obj.getString("cityname"));
					n.setLoudong(obj.getString("loudong"));
					n.setMenpai(obj.getString("menpai"));
					n.setUsername(obj.getString("username"));
					
					
					n.setZongjia(obj.getString("zongjia"));
					n.setCeng(obj.getString("ceng"));
					n.setZongceng(obj.getString("zongceng"));
					n.setThumb(obj.getString("thumb"));

					submitershous.add(n);	
				}
			
				
			return submitershous;
		

	}

}
