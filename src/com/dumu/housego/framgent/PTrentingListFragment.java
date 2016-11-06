package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.SubmitErshouListAdapter;
import com.dumu.housego.adapter.SubmitRentingListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ISubmitErShouListpresenter;
import com.dumu.housego.presenter.ISubmitRentingListpresenter;
import com.dumu.housego.presenter.SubmitErShoulistpresenter;
import com.dumu.housego.presenter.SubmitRentinglistpresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ISubmitErShouListView;
import com.dumu.housego.view.ISubmitRentingListView;

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

public class PTrentingListFragment extends Fragment implements ISubmitRentingListView{
	private LinearLayout ll_back_putongrentinglist;
	private TextView tv_putongrentinglist_submit;
	private ListView lv_submit_renting_list;
	private List<SubmitErshouList> submitershous;
	private ISubmitRentingListpresenter presenter;
	private SubmitRentingListAdapter adapter;
	private UserInfo userinfo;
	List<RentingDetail> rentingdetails;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_renting_list, null);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		String username=userinfo.getUsername();
		String userid=userinfo.getUserid();
		String table="chuzu";
		presenter=new SubmitRentinglistpresenter(this);
		
		initView(view);
		
		setListener();
		presenter.SubmitRentingList(username, userid, table);
		return view;
	}
	
	
	private void initView(View view) {
		ll_back_putongrentinglist=(LinearLayout) view.findViewById(R.id.ll_back_putongrentinglist);
		tv_putongrentinglist_submit=(TextView) view.findViewById(R.id.tv_putongrentinglist_submit);
		lv_submit_renting_list=(ListView) view.findViewById(R.id.lv_submit_renting_list);
	}
	
	private void setListener() {
		ll_back_putongrentinglist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
		tv_putongrentinglist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new PTrentingSumbitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		
	}



	@Override
	public void SubmitListError(String info) {
		MyToastShowCenter.CenterToast(getActivity(),info);
		
	}




	@Override
	public void SubmitListSuccess(List<RentingDetail> rentingdetails) {
		this.rentingdetails=rentingdetails;
		adapter=new SubmitRentingListAdapter(rentingdetails, getActivity());
		lv_submit_renting_list.setAdapter(adapter);
		
		
	}

}
