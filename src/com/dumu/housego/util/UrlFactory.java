package com.dumu.housego.util;

public class UrlFactory {

	// ע��--��ȡ������֤��
	public static String GetPhoneCodeUrl(String mob) {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=reg&mob=" + mob;
		return PhoneCode;
	}

	// wap����ҳ�Ƽ�
	public static String GetRecommendHouseUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=12";
		return path;
	}

	// ���ַ��б�ҳ�Ƽ�
	public static String GetErShouFangUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=8";
		return path;
	}
	
	

	// wap���·��Ƽ���Դ
	public static String GetWapNewHouseUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=13";
		return path;
	}

	// wap���·���ѯ��Դ
	public static String GetWapNewHouseRecommendUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=14";
		return path;
	}
}
