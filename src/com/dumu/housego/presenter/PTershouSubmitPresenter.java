package com.dumu.housego.presenter;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IPTershouSubmitModel;
import com.dumu.housego.model.PTershouSubmitModel;
import com.dumu.housego.view.IPTershouSubmitView;

public class PTershouSubmitPresenter implements IPTershouSubmitPresenter {
	private IPTershouSubmitModel model;
	private IPTershouSubmitView view;
	
	public PTershouSubmitPresenter(IPTershouSubmitView view) {
		super();
		this.view = view;
		model=new PTershouSubmitModel();
	}

	@Override
	public void PTershouSubmit(String username, String userid, String modelid, ErShouFangDetails e) {
		model.PTershouSubmit(username, userid, modelid, e, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.PTershouSubmit(info);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
