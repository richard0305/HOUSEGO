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
	public void Change(String userid, String nickname, String about, String sex) {
		model.change(userid, nickname, about, sex, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
			view.changeSuccess();
				
			}
			
			@Override
			public void onError(Object error) {
				view.changeFail(error.toString());
				
			}
		});
	}

}
