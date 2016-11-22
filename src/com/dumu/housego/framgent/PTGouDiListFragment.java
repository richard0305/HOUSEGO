package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.GouDiListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.GouDiInfo;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.GoudiListPresenter;
import com.dumu.housego.presenter.IGoudiListPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IGoudiListView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class PTGouDiListFragment extends Fragment implements IGoudiListView{
	private ListView lv_goudilist;
	private LinearLayout ll_goudilist_back;
	private List<GouDiInfo>goudis;
	private GouDiListAdapter listadapter;
	private IGoudiListPresenter listpresenter;
	private String userid;
	private UserInfo userinfo;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_goudi_list, null);
		initview(view);
		setListener();
		return view;
	}
	private void initview(View view) {
		lv_goudilist=(ListView) view.findViewById(R.id.lv_goudilist);
		ll_goudilist_back=(LinearLayout) view.findViewById(R.id.ll_goudilist_back);
	}
	private void setListener() {
		ll_goudilist_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
	}
	
	@Override
	public void onResume() {
		listpresenter=new GoudiListPresenter(this);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		userid=userinfo.getUserid();
		listpresenter.GoudiList(userid);
		super.onResume();
	}
	@Override
	public void GouDilistSuccess(List<GouDiInfo> goudis) {
		this.goudis=goudis;
		listadapter=new GouDiListAdapter(goudis, getActivity());
		lv_goudilist.setAdapter(listadapter);
		
	}
	@Override
	public void GouDiFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		
	}
}
