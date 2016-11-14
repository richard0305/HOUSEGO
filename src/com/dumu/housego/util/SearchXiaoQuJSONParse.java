package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.entity.SearchXiaoQu;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SearchXiaoQuJSONParse {
	public static List<SearchXiaoQu> parseSearch(String json) throws JSONException {
		JSONArray array = new JSONArray(json);
		List<SearchXiaoQu> xiaoqus = new ArrayList<SearchXiaoQu>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			SearchXiaoQu n = new SearchXiaoQu();

			n.setId(obj.getString("id"));
			n.setTitle(obj.getString("title"));


			xiaoqus.add(n);
		}
		return xiaoqus;

	}

}
