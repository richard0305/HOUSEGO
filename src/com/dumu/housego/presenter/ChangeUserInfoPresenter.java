package com.dumu.housego.presenter;

import com.dumu.housego.model.ChangeUserInfoModel;
import com.dumu.housego.model.IChangeUserInfoModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IChangeUserInfoView;

public class ChangeUserInfoPresenter implements IChangeUserInfoPresenter{
	private IChangeUserInfoModel model;
	private IChangeUserInfoView view;
	
	public ChangeUserInfoPresenter(IChangeUserInfoView view) {
		super();
		this.view = view;
		model = new ChangeUserInfoModel();
	}

	@Override
	public void ChangeRealName(String userid, String realname, String modelid) {
		model.ChangeRealName(userid, realname, modelid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changeInfo(info);
			}
			@Override
			public void onError(Object error) {
				String info=(String) error;
				view.changeInfo(info);
			}
		});
		
	}

	@Override
	public void ChangeSex(String userid, String sex) {
		model.ChangeSex(userid, sex, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changeInfo(info);
			}
			@Override
			public void onError(Object error) {
				String info=(String) error;
				view.changeInfo(info);
			}
		});
		
	}

	@Override
	public void ChangeGeRenJieshao(String userid, String about) {
		model.ChangeGeRenJieshao(userid, about, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changeInfo(info);
			}
			@Override
			public void onError(Object error) {
				String info=(String) error;
				view.changeInfo(info);
			}
		});
		
	}

	@Override
	public void ChangeSQfenjihao(String tel, String userid) {
		model.ChangeSQfenjihao(tel, userid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changeInfo(info);
			}
			@Override
			public void onError(Object error) {
				String info=(String) error;
				view.changeInfo(info);
			}
		});
		
	}

	@Override
	public void ChangeJBfenjihao(String tel, String userid) {
		model.ChangeJBfenjihao(tel, userid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changeInfo(info);
			}
			@Override
			public void onError(Object error) {
				String info=(String) error;
				view.changeInfo(info);
			}
		});
		
	}

	@Override
	public void ChangePassword(String username, String userid, String oldpwd, String pwd1, String pwd2) {
		model.ChangePassword(username, userid, oldpwd, pwd1, pwd2, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changeInfo(info);
				
			}
			
			@Override
			public void onError(Object error) {
				String info=(String) error;
				view.changeInfo(info);
				
			}
		});
		
	}


}
