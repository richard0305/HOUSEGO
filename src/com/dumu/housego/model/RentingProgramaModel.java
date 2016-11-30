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
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.RentingReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class RentingProgramaModel implements IFourDataProgramaModel {

	public RentingProgramaModel() {
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
					List<RentingRecommendData> ershoufangrecommends;
					ershoufangrecommends = RentingReconmendJSONParse.parseSearch(response);
					Log.i("YANGLIJUN", "<<<<<<<<<<<<<<<<<<---->>>>>>>>>>----data" + ershoufangrecommends);

					back.onSuccess(ershoufangrecommends);
				} catch (JSONException e) {
					back.onError("无对应房源数据！");
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
			

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("catid", fourdata.getCatid());
				params.put("page", fourdata.getPage());
				params.put("ct", fourdata.getCt());
				params.put("ar", fourdata.getAr());
				params.put("dt", fourdata.getDt());
				params.put("zj", fourdata.getZj());
				params.put("mj", fourdata.getMj());
				params.put("shi", fourdata.getShi());
				params.put("qs", fourdata.getQs());
				params.put("yt", fourdata.getYt());
				params.put("xq", fourdata.getXq());
				params.put("cx", fourdata.getCx());
				params.put("lc", fourdata.getLc());
				params.put("zl", fourdata.getZl());
				params.put("zx", fourdata.getZx());
				params.put("kf", fourdata.getKf());
				params.put("kwds", fourdata.getKwds());

				return params;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
