package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class ErShouFangProgramaModel implements IFourDataProgramaModel {

	public ErShouFangProgramaModel() {
		super();
	}

	@Override
	public void GetRecommedHouse(final FourDataPrograma fourdata, final AsycnCallBack back) {
		String url = UrlFactory.PostFourDataProgramaUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i("YANGLIJUN", "<<<<<<<<<<<<<<<<<<---->>>>>>>>>>----data" + response);
				try {
					List<ErShouFangRecommendData> ershoufangrecommends;
					ershoufangrecommends = ErShouFangReconmendJSONParse.parseSearch(response);
					Log.i("YANGLIJUN", "<<<<<<<<<<<<<<<<<<---->>>>>>>>>>----data" + ershoufangrecommends);

					back.onSuccess(ershoufangrecommends);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				back.onError(error.getMessage());

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("catid", fourdata.getCatid());
				params.put("page", fourdata.getPage());
				// params.put("catid", fourdata.getCatid());
				// params.put("catid", fourdata.getCatid());

				return params;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
