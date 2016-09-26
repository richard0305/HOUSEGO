package com.dumu.housego.util;

import android.text.TextUtils;

public class RemoveBOM {
	public static final String removeBOM(String data) {
		if (TextUtils.isEmpty(data)) {
		return data;
		}

		if (data.startsWith("UTF-8")) {
		return data.substring(1);
		} else {
		return data;
		}
		}
}
