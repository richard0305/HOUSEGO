package com.dumu.housego.model;

/**
 *Created by yanglijun 2016-6-28ионГ9:27:29
 */
public interface IChangeUserInfoModel extends IModel{
	void change(String userid,String nickname,String about,String sex,AsycnCallBack back);
}
