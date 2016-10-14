package com.dumu.housego.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.util.ErShouFangDetialJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.NewHouseDetialJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class NewHouseDetailModel implements INewHouseDetailaModel{
	private NewHouseDetail nhb;

	public NewHouseDetailModel() {
		super();
	}

	@Override
	public void FindNewHouseDetail(final String catid, final String id, final AsycnCallBack back) {
		
		new AsyncTask<String, String, NewHouseDetail>() {

			@Override
			protected NewHouseDetail doInBackground(String... params) {

				try {
					String url=UrlFactory.PostRecommendListToDetailUrl();
					Map<String, String> map=new HashMap<String, String>();
					
					map.put("catid",catid);
					map.put("id", id);
					String json=HttpUtils.isToString(HttpUtils.post(url, map));
					NewHouseDetail newhousedetail=NewHouseDetialJSONParse.parseSearch(json);
					
					return newhousedetail;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(NewHouseDetail newhousedetail) {
				back.onSuccess(newhousedetail);
			}
		}.execute();

	}
}

