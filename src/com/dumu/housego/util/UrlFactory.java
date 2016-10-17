package com.dumu.housego.util;

public class UrlFactory {
	
	 public static String TSFURL="http://www.taoshenfang.com/"; 

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
	
	//��֤���½
	
	public static String PostYZMLoginUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=mob_login_yzm";
		return path;
	}
	
	public static String PostYZMLogincodeUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=getyzm";
		return path;
	}
	

	
	

	// ��ȡ��¼�û�����Ϣ
	public static String PostLoginUserInfoUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_getuserinfo";
		return path;
	}

	// ���ĸ�����Ϣ
	public static String PostChangeUserInfoUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=user&a=api_doprofile";
		return path;
	}

	// ���ĸ���ͷ��
	public static String PostChangeHeadPhotoUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=user&a=api_uploadavatar";
		return path;
	}

	// ��ע���û���������-��ȡ��֤��
	public static String PostFindPasswordCodeUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=lost_getyzm";
		return path;
	}

	// ע���û� �޸�����
	public static String PostFindPasswordUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=member&m=public&a=mod_pwd";
		return path;
	}

	// ����ֻ������Ƿ����ע���Ա
	public static String PostCheckPhoneRegistUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=check_mob";
		return path;
	}

	// ������ģ���б�api
	public static String PostAgentmodelUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=Api&m=user&a=jjrlist";
		return path;
	}
	
	
	
	
	
	
	
	
	//�����Ƽ�λ�б��ȡ��Ӧ����
			public static String GetRecommendListToDetailUrlString(String catid,String id ) {
				String path ="http://www.taoshenfang.com/index.php?g=api&m=house&a=api_shows&catid="+catid+"&id="+id;
				return path;
			}
	
	//�����Ƽ�λ�б��ȡ��Ӧ����
		public static String GetRecommendListToDetailUrl(int catid,int id ) {
			String path ="http://www.taoshenfang.com/index.php?g=api&m=house&a=api_shows&catid="+catid+"&id="+id;
			return path;
		}
	
	//�����Ƽ�λ�б��ȡ��Ӧ����
	public static String PostRecommendListToDetailUrl() {
		String path ="http://www.taoshenfang.com/index.php?g=api&m=house&a=api_shows";
		return path;
	}
	
	
	// ��ȡָ����Ŀ�����б�ӿ�
	// ������ �·� ���ַ� �ⷿ ���ڽ��� �ĸ���Ŀ
	public static String PostFourDataProgramaUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=lists";
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
	public static String GetWapNewHouseHotUrl() {
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

	// ��ȡָ����Ŀ�����б�ӿ�
	public static String GetAssignContentListUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=lists";
		return path;
	}

	
	// ��ע��Դ�ӿ�
		public static String PostGuanZhuHouseUrl() {
			String path = TSFURL+"index.php?g=api&m=house&a=guanzhu_add";
			return path;
		}
		
		// �ҵĹ�ע
				public static String PostGuanZhuErShouUrl() {
					String path = TSFURL+"index.php?g=api&m=user&a=guanzhu";
					return path;
				}
				
		// ȡ����ע
				public static String PostGuanZhuDeleteUrl() {
					String path = TSFURL+"index.php?g=api&m=user&a=guanzhu_del";
					return path;
			}
	
		//预约看房接口
				public static String PostYuYueHouseUrl() {
					String path = TSFURL+"index.php?g=api&m=user&a=yuyue_add";
					return path;
			}	
	
	
	
}
