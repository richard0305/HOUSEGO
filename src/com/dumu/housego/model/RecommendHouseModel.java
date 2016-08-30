package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class RecommendHouseModel implements IRecommendHouseModel {

	public RecommendHouseModel() {
		super();
	}

	@Override
	public void GetRecommedHouse(final AsycnCallBack back) {

		new AsyncTask<String, String, List<RecommendNews>>() {

			@Override
			protected List<RecommendNews> doInBackground(String... params) {

				try {
					String url = UrlFactory.GetRecommendHouseUrl();
					InputStream is = HttpUtils.get(url);
					String json = HttpUtils.isToString(is);
					
					JSONObject obj = new JSONObject(json);
					JSONArray ary = obj.getJSONArray("data");

					List<RecommendNews> recommends = new ArrayList<RecommendNews>();
					for (int i = 0; i < ary.length(); i++) {
						JSONObject m;
						m = ary.getJSONObject(i);
						RecommendNews n = new RecommendNews();
						n.setTitle(m.getString("title"));
						n.setDescription(m.getString("description"));
						n.setThumb(m.getString("thumb"));
						n.setUrl(m.getString("url"));
						recommends.add(n);
						Log.i("yanglijun", "--->>>>>>"+recommends);
						return recommends;
					}
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}
				return null;
			}
				protected void onPostExecute(java.util.List<RecommendNews> recommends) {
					back.onSuccess(recommends);
				};
		}.execute();
	}
}