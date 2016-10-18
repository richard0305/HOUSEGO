package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IMyYuYueHouseModel;
import com.dumu.housego.model.MyYuYueHouseModel;
import com.dumu.housego.view.IMyYuYueHouseView;

public class MyYuYueHousePresenter implements IMyYuYueHousePresenter{

	private IMyYuYueHouseModel model;
	private IMyYuYueHouseView view;
	
	public MyYuYueHousePresenter(IMyYuYueHouseView view) {
		super();
		this.view = view;
		model=new MyYuYueHouseModel();
	}

	@Override
	public void LoadMyYuYueHosue(String username, String t) {
		model.LoadYuYueData(username, t,new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.LoadYuYueData(info);
				
			}
			
			@Override
			public void onError(Object error) {
				String errorinfo=(String) error;
				view.LoadYuYueData(errorinfo);
				
			}
		});
		
	}

}
