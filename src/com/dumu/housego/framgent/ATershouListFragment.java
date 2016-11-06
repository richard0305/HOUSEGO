package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.SubmitErshouListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ISubmitErShouListpresenter;
import com.dumu.housego.presenter.SubmitErShoulistpresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ISubmitErShouListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ATershouListFragment extends Fragment implements ISubmitErShouListView{
	private LinearLayout ll_back_agentershoulist;
	private TextView tv_agentershoulist_submit;
	private ListView fragmentershoulist;
	private SubmitErshouListAdapter adapter;
	private ISubmitErShouListpresenter presenter;
	private UserInfo userinfo;
	List<ErShouFangDetails> ershoudetails;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_agent_ershou_list, null);
		presenter=new SubmitErShoulistpresenter(this);
		
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		String username=userinfo.getUsername();
		String userid=userinfo.getUserid();
		String table="ershou";
		
		initview(view);
		setListener();
		presenter.SubmitErShouList(username, userid, table);
		return view;
	}
	private void setListener() {
		ll_back_agentershoulist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		tv_agentershoulist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new ATershouSubmitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
				trans.commitAllowingStateLoss();	
			}
		});
		
	}
	private void initview(View view) {
		ll_back_agentershoulist=(LinearLayout) view.findViewById(R.id.ll_back_agentershoulist);
		tv_agentershoulist_submit=(TextView) view.findViewById(R.id.tv_agentershoulist_submit);
		fragmentershoulist=(ListView) view.findViewById(R.id.fragmentershoulist);
	}
	
	
	@Override
	public void SubmitListError(String info) {
		MyToastShowCenter.CenterToast(getActivity(),info);
		
	}
	@Override
	public void SubmitListSuccess(List<ErShouFangDetails> ershoudetails) {
		this.ershoudetails=ershoudetails;
		adapter=new SubmitErshouListAdapter(ershoudetails, getActivity());
		fragmentershoulist.setAdapter(adapter);
		
	}
}
