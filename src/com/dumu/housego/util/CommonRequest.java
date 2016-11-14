package com.dumu.housego.util;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.widget.SeekBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by yanglijun 2016-6-27����3:29:13
 */
public class CommonRequest extends StringRequest {
	public static String JSESSIONID = null;

	public CommonRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	public CommonRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
		super(url, listener, errorListener);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = super.getHeaders();
		if (headers == null || headers.equals(Collections.emptyMap())) {
			headers = new HashMap<String, String>();
		}
		if (JSESSIONID != null) {
			headers.put("Cookie", JSESSIONID);
		}

		return headers;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		Map<String, String> headers = response.headers;
		String sessionid = headers.get("Set-Cookie");
		if (sessionid != null) {
			JSESSIONID = sessionid.split(";")[0];
		}
		String parsed;
		try {
			parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
	}

}
