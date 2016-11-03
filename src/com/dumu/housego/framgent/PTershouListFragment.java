package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.SubmitErshouListAdapter;
import com.dumu.housego.app.HouseGoApp;
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

public class PTershouListFragment extends Fragment implements ISubmitErShouListView{
	private ListView lvSubmitErshouList;
	private SubmitErshouListAdapter adapter;
	private List<SubmitErshouList>submitershous;
	private ISubmitErShouListpresenter presenter;
	private UserInfo userinfo;
	
	private TextView tv_putongershoulist_submit;
	private LinearLayout ll_back_putongershoulist;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_ershou_list, null);
		
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		String username=userinfo.getUsername();
		String userid=userinfo.getUserid();
		String table="ershou";
		
		presenter=new SubmitErShoulistpresenter(this);
		
		initView(view);
	
		setListener();
		presenter.SubmitErShouList(username, userid, table);
	
		return view;
	}
	
	


	private void setListener() {
		ll_back_putongershoulist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		tv_putongershoulist_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment fragment=new PTershouSumbitFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
				
			}
		});
		
		
	}

	private void initView(View view) {
		lvSubmitErshouList=(ListView) view.findViewById(R.id.lv_submit_ershou_list1);
		ll_back_putongershoulist=(LinearLayout) view.findViewById(R.id.ll_back_putongershoulist);
		tv_putongershoulist_submit=(TextView) view.findViewById(R.id.tv_putongershoulist_submit);
	}


	@Override
	public void SubmitListSuccess(List<SubmitErshouList> submitershous) {
		this.submitershous=submitershous;
			
			adapter=new SubmitErshouListAdapter(submitershous, getActivity());
			lvSubmitErshouList.setAdapter(adapter);
		
	}


	@Override
	public void SubmitListError(String info) {
		MyToastShowCenter.CenterToast(getActivity(),info);
		
	}
}
