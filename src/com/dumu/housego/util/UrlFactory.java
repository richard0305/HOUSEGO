package com.dumu.housego.util;

public class UrlFactory {

	// 注册--获取短信验证码
	public static String GetPhoneCodeUrl(String mob) {

		String PhoneCode = "http://www.taoshenfang.com/index.php?g=api&m=sms&a=reg&mob=" + mob;
		return PhoneCode;
	}
	
	
	
	// 注册--接口
	public static String PostRegistUrl() {
		
		String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_register";
		return path;
		
	}
	
	// 登录--接口
	public static String PostLoginUrl() {
		
		String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_dologin";
		return path;
		
	}
	
	// 获取登录用户的信息
		public static String PostLoginUserInfoUrl() {
			
			String path = "http://www.taoshenfang.com/index.php?g=Member&m=Public&a=api_getuserinfo";
			return path;
		}
	
	//更改个人信息
		public static String PostChangeUserInfoUrl() {
			String path = "http://www.taoshenfang.com/index.php?g=api&m=user&a=api_doprofile";
			return path;
			}
		
		
	//更改个人头像
		public static String PostChangeHeadPhotoUrl() {
			String path ="http://www.taoshenfang.com/index.php?g=api&m=user&a=api_uploadavatar";
			return path;
		}
	
		
		
	//已注册用户忘记密码-获取验证码
		public static String PostFindPasswordCodeUrl() {
			String path ="http://www.taoshenfang.com/index.php?g=api&m=sms&a=lost_getyzm";
			return path;
		}
		
		
		//注册用户 修改密码
				public static String PostFindPasswordUrl() {
					String path ="http://www.taoshenfang.com/index.php?g=member&m=public&a=mod_pwd";
					return path;
				}	
		
		//检测手机号码是否可以注册会员
				public static String PostCheckPhoneRegistUrl() {
					String path ="http://www.taoshenfang.com/index.php?g=api&m=sms&a=check_mob";
					return path;
				}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	//获取指定栏目内容列表接口
	//适用于  新房 二手房 租房 大宗交易 四个栏目
			public static String PostFourDataProgramaUrl() {
			String path ="http://www.taoshenfang.com/index.php?g=api&m=house&a=lists";
			return path;
		}

			
			
	// wap版首页推荐
	public static String GetRecommendHouseUrl() {

		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=12";
		return path;
	}

	// 二手房列表页推荐
	public static String GetErShouFangRecommendUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=8";
		return path;
	}
	
	

	// wap版新房推荐房源 
	public static String GetWapNewHouseUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=13";
		return path;
	}

	// wap版新房咨询房源
	public static String GetWapNewHouseRecommendUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=14";
		return path;
	}
	
	
	// 出租房列表页推荐
	public static String GetRentingUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=9";
		return path;
	}
	
	
	
	// 出租房列表页推荐
		public static String GetBlockTradeListUrl() {
			String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=position&posid=10";
			return path;
		}
		
		
		
		//获取指定栏目内容列表接口
		public static String GetAssignContentListUrl() {
			String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=lists";
			return path;
		}
		
		
		
		
		
	
	
	
	
}
