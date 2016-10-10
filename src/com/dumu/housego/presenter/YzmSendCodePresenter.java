package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IYzmSendCodeModel;
import com.dumu.housego.model.YzmSendCodeModel;
import com.dumu.housego.view.IYzmSendCodeView;

public class YzmSendCodePresenter implements IYzmSendCodePresenter{
	private IYzmSendCodeModel model;
	private IYzmSendCodeView view;
	
	
	public YzmSendCodePresenter(IYzmSendCodeView view) {
		super();
		this.view = view;
		model=new YzmSendCodeModel();
	}


	@Override
	public void YzmSendCode(String phonenum) {
		model.YzmSendCode(phonenum, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String infomation= success.toString();
				view.YzmSendCode(infomation);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
