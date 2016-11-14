package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.QiuZuBuyHouseList;

public interface IBuyHouseListView {
	void buyhouseSuccess(List<QiuZuBuyHouseList> lists);

	void buyhouseFail(String info);
}
