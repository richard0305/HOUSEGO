package com.dumu.housego.presenter;

import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.model.ATershousubmitModel;
import com.dumu.housego.model.IATershousubmitModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IATershouSubmitView;

public class ATershousubmitPresenter implements IATerhsouSubmitPresenter {

	private IATershousubmitModel model;
	private IATershouSubmitView view;

	public ATershousubmitPresenter(IATershouSubmitView view) {
		super();
		this.view = view;
		model = new ATershousubmitModel();
	}

	@Override
	public void ATershousubmit(ATerShouSubmit at) {
		model.ATershousubmit(at, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.ATershou(info);

			}

			@Override
			public void onError(Object error) {

			}
		});

	}

}
