package com.dumu.housego.util;

public class UrlFactory {

	// 注册--获取短信验证码
	public static String GetPhoneCodeUrl(String mob) {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=reg&mob=" + mob;
		return PhoneCode;
	}
	
	//wap版首页推荐
	public static String GetRecommendHouseUrl() {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=12";
		return PhoneCode;
	}

}
