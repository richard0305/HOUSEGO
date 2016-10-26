package com.dumu.housego.presenter;
/**
 *Created by yanglijun 2016-6-28����9:24:01
 */
public interface IChangeUserInfoPresenter {
	void ChangeRealName(String userid,String realname,String modelid);
	void ChangeSex(String userid,String sex);
	void ChangeGeRenJieshao(String userid,String about);
	void ChangeSQfenjihao(String tel,String userid);
	void ChangeJBfenjihao(String tel,String userid);
	void ChangePassword(String username,String userid,String oldpwd,String pwd1,String pwd2);
}
