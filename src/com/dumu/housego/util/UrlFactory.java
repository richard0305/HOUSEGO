package com.dumu.housego.util;

public class UrlFactory {

	// ע��--��ȡ������֤��
	public static String GetPhoneCodeUrl(String mob) {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=reg&mob=" + mob;
		return PhoneCode;
	}
	
	
	
	// ע��--�ӿ�
	public static String PostRegistUrl() {
		
		String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_register";
		return path;
		
	}
	
	// ��¼--�ӿ�
	public static String PostLoginUrl() {
		
		String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_dologin";
		return path;
		
	}
	
	// ��ȡ��¼�û�����Ϣ
		public static String PostLoginUserInfoUrl() {
			
			String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_getuserinfo";
			return path;
		}
	
	
	
	
	
	
	

	// wap����ҳ�Ƽ�
	public static String GetRecommendHouseUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=12";
		return path;
	}

	// ���ַ��б�ҳ�Ƽ�
	public static String GetErShouFangRecommendUrl() {
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
	
	
	// ���ⷿ�б�ҳ�Ƽ�
	public static String GetRentingUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=9";
		return path;
	}
	
	
	
	// ���ⷿ�б�ҳ�Ƽ�
		public static String GetBlockTradeListUrl() {
			String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=10";
			return path;
		}
		
		
		
		//��ȡָ����Ŀ�����б�ӿ�
		public static String GetAssignContentListUrl() {
			String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=lists";
			return path;
		}
		
		
		
		
		
	
	
	
	
}
