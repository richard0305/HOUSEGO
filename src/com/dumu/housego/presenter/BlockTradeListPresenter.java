package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.model.BlockTradeListModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRecommendHouseModel;
import com.dumu.housego.view.IBlockTradeListView;
import com.dumu.housego.view.IErShouFangRecommendView;

public class BlockTradeListPresenter implements IRecommendHousePresenter{
	private IRecommendHouseModel model;
	private IBlockTradeListView view;
	
	public BlockTradeListPresenter(IBlockTradeListView view) {
		super();
		this.view = view;
		model=new BlockTradeListModel();
	}


	@Override
	public void LoadRecommend() {
	model.GetRecommedHouse(new AsycnCallBack() {
		
		@Override
		public void onSuccess(Object success) {
			List<BlockTradeList>blocktrades=(List<BlockTradeList>) success;
			view.showData(blocktrades);
		}
		
		@Override
		public void onError(Object error) {
			// TODO Auto-generated method stub
			
		}
	});
		
	}

}
