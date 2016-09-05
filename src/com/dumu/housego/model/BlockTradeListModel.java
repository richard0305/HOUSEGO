package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.BlockTradeListJSONParse;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.NewHouseReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class BlockTradeListModel implements IRecommendHouseModel{

	
	public BlockTradeListModel() {
		super();
	}
	
	
	@Override
	public void GetRecommedHouse(final AsycnCallBack back) {
		new AsyncTask<String, String, List<BlockTradeList>>() {

			@Override
			protected List<BlockTradeList> doInBackground(String... params) {

				try {
					String url = UrlFactory.GetBlockTradeListUrl();
					InputStream is;
					is = HttpUtils.get(url);
					String json = HttpUtils.isToString(is);
					List<BlockTradeList> blocktrades;
					blocktrades = BlockTradeListJSONParse.parseSearch(json);
					Log.i("YANGLIJUN", "---->>>>>>>>>>"+blocktrades);
					return blocktrades;
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(java.util.List<BlockTradeList> blocktrades) {
				back.onSuccess(blocktrades);
			}
		}.execute();
		
	}

}
