package com.dumu.housego.util;

public class UrlFactory {

	// 注册--获取短信验证码
	public static String GetPhoneCodeUrl(String mob) {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=reg&mob=" + mob;
		return PhoneCode;
	}
	
	//wap版首页推荐
	public static String GetRecommendHouseUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=12";
		return path;
	}

	//二手房列表页推荐
		public static String GetErShouFangUrl() {

			String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=8";
			return path;
		}
		
		
		//wap版新房推荐房源
		public static String GetWapNewHouseUrl() {

			String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=13";
			return path;
		}
}
