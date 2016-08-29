package com.dumu.housego.model;

public interface IModel {
	public interface  AsycnCallBack {
		void onSuccess(Object success);
		void onError(Object error);
	}
}
