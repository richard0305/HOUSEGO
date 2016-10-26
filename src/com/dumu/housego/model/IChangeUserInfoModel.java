package com.dumu.housego.model;

/**
 *Created by yanglijun 2016-6-28����9:27:29
 */
public interface IChangeUserInfoModel extends IModel{
	void ChangeRealName(String userid,String realname,String modelid,AsycnCallBack back);
	void ChangeSex(String userid,String sex,AsycnCallBack back);
	void ChangeGeRenJieshao(String userid,String about,AsycnCallBack back);
	void ChangeSQfenjihao(String tel,String userid,AsycnCallBack back);
	void ChangeJBfenjihao(String tel,String userid,AsycnCallBack back);
	void ChangePassword(String username,String userid,String oldpwd,String pwd1,
			String pwd2,AsycnCallBack back);
}
