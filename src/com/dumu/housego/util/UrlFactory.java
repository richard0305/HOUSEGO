package com.dumu.housego.util;

public class UrlFactory {

	public static String TSFURL = "http://www.taoshenfang.com/";
	//指定小区下的所有房源
	public static String PostXiaoQuAllHosue() {
		String path = TSFURL + "index.php?g=api&m=map&a=house";
		return path;
	}
	
	//指定区域下所有小区
	public static String PostXiaoQuMapHouse() {
		String path = TSFURL + "index.php?g=api&m=map&a=xiaoqu";
		return path;
	}
	
	
	//指定行政区下所有区域的房源套数
	
	public static String PostAreaMapHouse() {
		String path = TSFURL + "index.php?g=api&m=map&a=area_house";
		return path;
	}
	
	//添加评论
	public static String PostAddAgentComment() {
		String path = TSFURL + "index.php?g=Api&m=user&a=jjr_comm_add";
		return path;
	}
	
	
	
	//经纪人评论
	
	public static String GetAgentComment(String id) {
		String path = TSFURL + "index.php?g=Api&m=user&a=jjr_comment&id="+id;
		return path;
	}
	
	//经纪人详情
	public static String GetAgentDetail(String id) {
		String path = TSFURL + "index.php?g=Api&m=user&a=jjrshow&id="+id;
		return path;
	}
	
	//二手房发布
		public static String PostATershouSubmit() {
			String path = TSFURL + "index.php?g=api&m=user&a=add_ershou";
			return path;
		}
	
	//二手房/出租房删除
	public static String PostDeleteErShouChuZuList() {
		String path = TSFURL + "index.php?g=api&m=user&a=house_del";
		return path;
	}

	//出租房发布
	public static String PostATrentingSubmit() {
		String path = TSFURL + "index.php?g=api&m=user&a=add_chuzu";
		return path;
	}

	
	
	//小区搜索
	public static String PostSearchXiaoQu() {
		String path = TSFURL + "index.php?g=api&m=user&a=xiaoqu_search";
		return path;
	}

	// 删除求购
	public static String PostBuyHousedelete() {
		String path = TSFURL + "index.php?g=api&m=user&a=qiugou_del";
		return path;
	}

	// 求购列表/求租列表/求购管理/求租管理
	public static String PostQiuBuylist() {
		String path = TSFURL + "index.php?g=api&m=user&a=qiu_list";
		return path;
	}

	// 发布求购
	public static String PostBuyHouseSubmit() {
		String path = TSFURL + "index.php?g=api&m=user&a=qiugou_add";
		return path;
	}

	// 删除求租
	public static String PostQiuZuHouseListDelete() {
		String path = TSFURL + "index.php?g=api&m=user&a=qiuzu_del";
		return path;
	}

	// 发布求租

	public static String PostQiuZuHouseSubmit() {
		String path = TSFURL + "index.php?g=api&m=user&a=qiuzu_add";
		return path;
	}

	// 获取地区列表接口
	public static String PostAddressList() {
		String path = TSFURL + "index.php?g=api&m=house&a=diqu";
		return path;
	}

	// 成交房源
	public static String PostChengJiaoHouseList() {
		String path = TSFURL + "index.php?g=api&m=user&a=chengjiao";
		return path;
	}

	// 二手房/出租房列表
	public static String PostSubmitESorCZList() {
		String path = TSFURL + "index.php?g=api&m=user&a=house_list";
		return path;
	}

	// 直接修改密码
	public static String PostChangePassword() {
		String path = TSFURL + "index.php?g=member&m=public&a=mod_pwd2";
		return path;
	}

	// 申请400电话
	public static String GetShenQing400Url(String tel, String userid) {

		String path = TSFURL + "index.php?g=api&m=api&a=add_vtel&tel=" + tel + "&userid=" + userid;
		return path;
	}

	// 申请400电话
	public static String GetJieBang400Url(String tel, String userid) {
		String path = TSFURL + "index.php?g=api&m=api&a=remove_vtel&tel=" + tel + "&userid=" + userid;
		return path;
	}

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

	// ��֤���½

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

	// �����Ƽ�λ�б��ȡ��Ӧ����
	public static String GetRecommendListToDetailUrlString(String catid, String id) {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=api_shows&catid=" + catid + "&id=" + id;
		return path;
	}

	// �����Ƽ�λ�б��ȡ��Ӧ����
	public static String GetRecommendListToDetailUrl(int catid, int id) {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=api_shows&catid=" + catid + "&id=" + id;
		return path;
	}

	// �����Ƽ�λ�б��ȡ��Ӧ����
	public static String PostRecommendListToDetailUrl() {
		String path = "http://www.taoshenfang.com/index.php?g=api&m=house&a=api_shows";
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
		String path = TSFURL + "index.php?g=api&m=house&a=guanzhu_add";
		return path;
	}

	// �ҵĹ�ע
	public static String PostGuanZhuErShouUrl() {
		String path = TSFURL + "index.php?g=api&m=user&a=guanzhu";
		return path;
	}

	// ȡ����ע
	public static String PostGuanZhuDeleteUrl() {
		String path = TSFURL + "index.php?g=api&m=user&a=guanzhu_del";
		return path;
	}

	// 预约看房接口
	public static String PostYuYueHouseUrl() {
		String path = TSFURL + "index.php?g=api&m=house&a=yuyue_add";
		return path;
	}

	// 我的预约看房
	public static String PostMyYuYueHouseUrl() {
		String path = TSFURL + "index.php?g=api&m=user&a=yuyue";
		return path;
	}

	// 删除预约
	public static String PostDeleteMyYuYueHouseUrl() {
		String path = TSFURL + "index.php?g=api&m=user&a=yuyue_del";
		return path;
	}

	// 获取行政区房源套数
	public static String PostXingzhengHouseUrl() {
		String path = TSFURL + "index.php?g=api&m=map&a=city_house";
		return path;
	}

	// 指定行政区下所以区域的房源套数
	public static String PostzhidingXingzhengHouseUrl() {
		String path = TSFURL + "index.php?g=api&m=map&a=area_house";
		return path;
	}

	// 指定小区下所有的房源套数
	public static String PostzhidingxiaoquHouseUrl() {
		String path = TSFURL + "index.php?g=api&m=map&a=house";
		return path;
	}

}
