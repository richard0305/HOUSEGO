package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ErShouFangReconmendJSONParse {
	public static List<ErShouFangRecommendData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json); 
		List<ErShouFangRecommendData> ershoufangrecommends = new ArrayList<ErShouFangRecommendData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			ErShouFangRecommendData n = new ErShouFangRecommendData();
			JSONObject obj2=obj.getJSONObject("data");
			
			n.setId(obj.getString("id"));
			n.setCatid(obj.getString("catid"));
			n.setPosid(obj.getString("posid"));
			
			n.setTitle(obj2.getString("title"));
			n.setXiaoquname(obj2.getString("xiaoquname"));
			n.setZongjia(obj2.getString("zongjia"));
			n.setJianzhumianji(obj2.getString("jianzhumianji"));
			n.setTing(obj2.getString("ting"));
			n.setShi(obj2.getString("shi"));
			n.setThumb(obj2.getString("thumb"));
			n.setCity(obj2.getString("city"));
			n.setArea(obj2.getString("area"));
			n.setUrl(obj2.getString("url"));
			n.setProvince_name(obj2.getString("province_name"));
			n.setCity_name(obj2.getString("city_name"));
			n.setArea_name(obj2.getString("area_name"));
			ershoufangrecommends.add(n);
		}
		return ershoufangrecommends;

	}

}
