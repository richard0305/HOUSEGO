package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AgentDataListJSONParse {
	public static List<AgentData> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<AgentData> agentdatas = new ArrayList<AgentData>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			AgentData n = new AgentData();
			JSONObject obj2 = obj.getJSONObject("info");
			
			n.setUserid(obj2.getString("userid"));
			n.setRealname(obj.getString("realname"));
			n.setComm_count(obj.getString("comm_count"));
			n.setUsername(obj2.getString("username"));
			n.setMainarea(obj.getString("mainarea"));
			n.setDengji(obj.getString("dengji"));
			n.setBiaoqian(obj.getString("biaoqian"));
			n.setCtel(obj2.getString("vtel"));
			n.setUserpic(obj2.getString("userpic"));
			

			agentdatas.add(n);
		}
		return agentdatas;

	}

}
