package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.NewHouseReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class NewhouseRecommendModel implements IRecommendHouseModel{

	
	public NewhouseRecommendModel() {
		super();
	}
	
	
	@Override
	public void GetRecommedHouse(final AsycnCallBack back) {
		new AsyncTask<String, String, List<NewHouseRecommendData>>() {

			@Override
			protected List<NewHouseRecommendData> doInBackground(String... params) {

				try {
					String url = UrlFactory.GetWapNewHouseRecommendUrl();
					InputStream is;
					is = HttpUtils.get(url);
					String json = HttpUtils.isToString(is);
					List<NewHouseRecommendData> newrecommends;
					newrecommends = NewHouseReconmendJSONParse.parseSearch(json);
					Log.i("YANGLIJUN", "---->>>>>>>>>>"+newrecommends);
					return newrecommends;
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(java.util.List<NewHouseRecommendData> newrecommends) {
				back.onSuccess(newrecommends);
			}
		}.execute();
		
	}

}
