package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.SubmitErshouList;

public interface ISubmitErShouListView {
	void SubmitListSuccess(List<SubmitErshouList>submitershous);
	void SubmitListError(String info);
}
