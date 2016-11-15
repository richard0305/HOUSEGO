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
import com.dumu.housego.model.IATershouListDeleteModel;
import com.dumu.housego.presenter.ATershouListDeletePresenter;
import com.dumu.housego.presenter.IATershouListDeletePresenter;
import com.dumu.housego.presenter.ISubmitRentingListpresenter;
import com.dumu.housego.presenter.SubmitRentinglistpresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IATershouListDeleteView;
import com.dumu.housego.view.ISubmitRentingListView;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ATrentingListFragment extends Fragment implements ISubmitRentingListView ,IATershouListDeleteView{
	private TextView tv_agentrentinglist_submit;
	private LinearLayout ll_back_agentrentinglist;
	private ListView fragmentrentinglist;
	private ISubmitRentingListpresenter presenter;
	private UserInfo userinfo;
	private List<SubmitErshouList> submitershous;
	private SubmitRentingListAdapter adapter;
	List<RentingDetail> rentingdetails;
	private PopupWindow pop;
	private LinearLayout ll_popup_delete;
	private int POSI;
	String username;
	String userid;
	String table;
	private IATershouListDeletePresenter deletepresenter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_agent_renting_list, null);
		presenter = new SubmitRentinglistpresenter(this);
		deletepresenter=new ATershouListDeletePresenter(this);
		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		 username = userinfo.getUsername();
	 userid = userinfo.getUserid();
		 table = "chuzu";

		initView(view);
		PopDelete();
		setListener();
		presenter.SubmitRentingList(username, userid, table);
		return view;
	}

	private void PopDelete() {

		pop = new PopupWindow(getActivity());

		View view = getActivity().getLayoutInflater().inflate(R.layout.item_popupwindows_delete, null);

		ll_popup_delete = (LinearLayout) view.findViewById(R.id.ll_popup_delete);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_delete);

		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_sure);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
		TextView tv=(TextView) view.findViewById(R.id.item_popupwindows_title);
		tv.setText("是否删除该出租房");
		
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_delete.clearAnimation();
			}
		});

		bt1.setOnClickListener(new OnClickListener() {
			

		

			public void onClick(View v) {
				String lock = rentingdetails.get(POSI).getLock();

				String ID=rentingdetails.get(POSI).getId();
			
				deletepresenter.DeleteershouList(username, userid, table, ID);
				
//				if (lock.equals("0")) {
//					ershoudetails.remove(position);
//					adapter.notifyDataSetChanged();
//				}

				pop.dismiss();
				ll_popup_delete.clearAnimation();
			}
		});

		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_delete.clearAnimation();
			}
		});

		
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
		
		fragmentrentinglist.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				ATrentingListFragment.this.POSI=position;
				Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);

				ll_popup_delete.setAnimation(anim);
				pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
				return true;
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

	@Override
	public void ATershouListDelete(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		if(info.equals("删除成功")){
			this.rentingdetails.remove(POSI);
			adapter.notifyDataSetChanged();
		}
		
	}

}
