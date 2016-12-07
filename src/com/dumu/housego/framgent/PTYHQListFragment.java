package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.YHQListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YHQ;
import com.dumu.housego.presenter.IYhqListPresenter;
import com.dumu.housego.presenter.YHQListPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IYhqListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class PTYHQListFragment extends Fragment implements IYhqListView{
	private ImageView iv_yhq_back;
	private ListView lv_yhqlist;
	private List<YHQ>yhqs;
	private YHQListAdapter adapter;
	private IYhqListPresenter presenter;
	private UserInfo userinfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_ptyhq_list	, null);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		presenter=new YHQListPresenter(this);
		presenter.yhqList(userinfo.getUserid());
		initView(view);
		setlisenter();
		return view;
	}
	
	private void setlisenter() {
		iv_yhq_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
	}

	private void initView(View view) {
		iv_yhq_back=(ImageView) view.findViewById(R.id.iv_yhq_back);
		lv_yhqlist=(ListView) view.findViewById(R.id.lv_yhqlist);
	}


	@Override
	public void yhqListsuccess(List<YHQ> yhqs) {
		this.yhqs=yhqs;
		adapter=new YHQListAdapter(yhqs, getActivity());
		lv_yhqlist.setAdapter(adapter);
		
	}

	@Override
	public void yhqfail(String info) {
	MyToastShowCenter.CenterToast(getActivity(), info);
		
	}

	@Override
	public void yhqDelete(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		
	}
}
