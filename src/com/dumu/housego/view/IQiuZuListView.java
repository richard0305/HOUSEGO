package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.QiuZuBuyHouseList;

public interface IQiuZuListView {
	void QiuZuListSuccess(List<QiuZuBuyHouseList> qiuzulists);

	void QiuZuListFail(String info);
}
