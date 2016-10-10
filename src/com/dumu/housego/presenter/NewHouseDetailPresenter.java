package com.dumu.housego.presenter;

import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.model.BlockTradeDetailModel;
import com.dumu.housego.model.IBlockTradeDetailaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.INewHouseDetailaModel;
import com.dumu.housego.model.NewHouseDetailModel;
import com.dumu.housego.view.IBlockTradeDetailView;
import com.dumu.housego.view.INewHouseDetailView;

public class NewHouseDetailPresenter implements INewHouseDetailPresenter{
	private INewHouseDetailaModel model;
	private INewHouseDetailView view;
	
	
	
	public NewHouseDetailPresenter(INewHouseDetailView view) {
		super();
		this.view = view;
		model=new NewHouseDetailModel();
	}



	@Override
	public void FindNewHousedetail(String catid, String id) {
		model.FindNewHouseDetail(catid, id, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				
			}
			
			@Override
			public void onError(Object error) {
				
			}
		});
			
		


	}
}
