package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.RentingDetailActivity;
import com.dumu.housego.adapter.SubmitRentingListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ISubmitRentingListpresenter;
import com.dumu.housego.presenter.SubmitRentinglistpresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ISubmitRentingListView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ATrentingListFragment extends Fragment implements ISubmitRentingListView {
	private TextView tv_agentrentinglist_submit;
	private LinearLayout ll_back_agentrentinglist;
	private ListView fragmentrentinglist;
	private ISubmitRentingListpresenter presenter;
	private UserInfo userinfo;
	private List<SubmitErshouList> submitershous;
	private SubmitRentingListAdapter adapter;
	List<RentingDetail> rentingdetails;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_agent_renting_list, null);
		presenter = new SubmitRentinglistpresenter(this);

		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		String username = userinfo.getUsername();
		String userid = userinfo.getUserid();
		String table = "chuzu";

		initView(view);
		setListener();
		presenter.SubmitRentingList(username, userid, table);
		return view;
	}

	private void initView(View view) {
		tv_agentrentinglist_submit = (TextView) view.findViewById(R.id.tv_agentrentinglist_submit);
		ll_back_agentrentinglist = (LinearLayout) view.findViewById(R.id.ll_back_agentrentinglist);
		fragmentrentinglist = (ListView) view.findViewById(R.id.fragmentrentinglist);
	}

	private void setListener() {
		tv_agentrentinglist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new ATrentingSubmitFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
				trans.commitAllowingStateLoss();
			}
		});

		ll_back_agentrentinglist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();

			}
		});

		fragmentrentinglist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent i = new Intent(getActivity(), RentingDetailActivity.class);
				String Id = rentingdetails.get(position).getId();
				String catid = rentingdetails.get(position).getCatid();
				String posid = rentingdetails.get(position).getPosid();

				i.putExtra("id", Id);
				i.putExtra("catid", catid);
				i.putExtra("posid", posid);
				startActivity(i);

			}
		});
	}

	@Override
	public void SubmitListError(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);

	}

	@Override
	public void SubmitListSuccess(List<RentingDetail> rentingdetails) {
		this.rentingdetails = rentingdetails;
		adapter = new SubmitRentingListAdapter(rentingdetails, getActivity());
		fragmentrentinglist.setAdapter(adapter);

	}

}
