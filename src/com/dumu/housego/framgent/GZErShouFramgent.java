package com.dumu.housego.framgent;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.ErShouFangMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.R.layout;
import com.dumu.housego.adapter.GuanZhuErShouLsitAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.GuanZhuErShouPresenter;
import com.dumu.housego.presenter.IGuanZhuErShouPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IGuanZhuErShouView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GZErShouFramgent extends Fragment implements IGuanZhuErShouView{
	private IGuanZhuErShouPresenter presenter;
	private UserInfo userinfo;
	private List<ErShouFangDetails>ershoufangdetails;
	private ListView lvGuanZhuErShou;
	private GuanZhuErShouLsitAdapter adapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragment_guanzhu_ershou, null);
	
	presenter=new GuanZhuErShouPresenter(this);
	initViews(view);
	setListener();
	
	
	return view;
	}
	
	private void initViews(View view) {
		lvGuanZhuErShou=(ListView) view.findViewById(R.id.lv_guanzhu_ershou);
		
	}

	private void setListener() {
		lvGuanZhuErShou.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				 Intent i = new Intent(getContext(),ErShouFangDetailsActivity.class);
				 String catid=ershoufangdetails.get(position).getCatid();
				 String ID=ershoufangdetails.get(position).getId();
				 i.putExtra("catid", catid);
				 i.putExtra("id", ID);
				 startActivity(i);
				
			}
		});
		
	}

	@Override
	public void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		String username=userinfo.getUsername();
		presenter.LoadGuanZhuErShou(username);
		MyToastShowCenter.CenterToast(getContext(), "发送了数据");
		super.onResume();
	}



	@Override
	public void showGuanZhuFail(String errorinfo) {
		MyToastShowCenter.CenterToast(getContext(), errorinfo);
		
	}

	@Override
	public void showGuanZhuSuccess(List<ErShouFangDetails> ershoufangdetails) {
		this.ershoufangdetails=ershoufangdetails;
		adapter=new GuanZhuErShouLsitAdapter(ershoufangdetails, getContext());
		lvGuanZhuErShou.setAdapter(adapter);
		
	}

}
