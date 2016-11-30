package com.dumu.housego.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.util.AgentDataListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class AgentModelDataModel implements IAgentModelDataModel {
	private List<AgentData> agentdatas;

	public AgentModelDataModel() {
		super();
	}

	@Override
	public void FindAgentModelData(final String catid,final String ct,final String bq,final String kw, final AsycnCallBack back) {
		String url = UrlFactory.PostAgentmodelUrl();
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {

				try {
					agentdatas = AgentDataListJSONParse.parseSearch(response);

					Log.i("yanglijun", "<<<<>>>>___<<<<>>>>-----array" + agentdatas);

					back.onSuccess(agentdatas);
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
				params.put("catid", catid);
				params.put("ct", ct);
				params.put("bq", bq);
				params.put("kw", kw);
				return params;
			}
		};
		HouseGoApp.getQueue().add(request);

	}

}
