package com.dumu.housego.util;

public class UrlFactory {

	// ע��--��ȡ������֤��
	public static String GetPhoneCodeUrl(String mob) {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=reg&mob=" + mob;
		return PhoneCode;
	}
	
	//wap����ҳ�Ƽ�
	public static String GetRecommendHouseUrl() {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=12";
		return PhoneCode;
	}

}
