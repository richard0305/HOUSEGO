package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IPhoneCodeModel;
import com.dumu.housego.model.PhoneCodeModel;
import com.dumu.housego.view.IPhoneCodeView;

public class PhoneCodePresenter implements IPhoneCodePresenter {
	private IPhoneCodeModel model;
	private IPhoneCodeView view;

	public PhoneCodePresenter(IPhoneCodeView view) {
		super();
		this.view = view;
		model = new PhoneCodeModel();
	}

	@Override
	public void LoadMob(String mob) {
		model.GetPhoneCode(mob, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String infomation = success.toString();
				view.setData(infomation);

			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
