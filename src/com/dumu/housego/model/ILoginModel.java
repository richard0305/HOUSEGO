package com.dumu.housego.model;

/**
 *Created by yanglijun 2016-6-28ионГ9:27:29
 */
public interface ILoginModel extends IModel{
	void login(String phonenum,String password,AsycnCallBack back);
}
