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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GuanZhuErShouJSONParse {
	public static List<ErShouFangDetails> parseSearch(String json) throws JSONException {
			JSONArray array = new JSONArray(json); 
			List<ErShouFangDetails> ershoufangdetails = new ArrayList<ErShouFangDetails>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				ErShouFangDetails n = new ErShouFangDetails();
				if(obj.getString("fromtable").equals("ershou")){
					JSONObject obj2=obj.getJSONObject("house");
					
					n.setId(obj2.getString("id"));
					n.setCatid(obj2.getString("catid"));
					n.setTitle(obj2.getString("title"));
					n.setXiaoquName(obj2.getString("xiaoquName"));
					n.setShi(obj2.getString("shi"));
					n.setTing(obj2.getString("ting"));
					n.setJianzhumianji(obj2.getString("jianzhumianji"));
					n.setChaoxiang(obj2.getString("chaoxiang"));
					n.setZongjia(obj2.getString("zongjia"));
					n.setCeng(obj2.getString("ceng"));
					n.setZongceng(obj2.getString("zongceng"));
					n.setFangling(obj2.getString("fangling"));
					n.setJianzhutype(obj2.getString("jianzhutype"));
					n.setDitiexian(obj2.getString("ditiexian"));
					n.setThumb(obj2.getString("thumb"));

					ershoufangdetails.add(n);	
				}
			
				
			}
			return ershoufangdetails;
		

	}

}
