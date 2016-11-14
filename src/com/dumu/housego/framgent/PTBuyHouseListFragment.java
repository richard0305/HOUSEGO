package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.BuyHouseListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.BuyHouseDeletePresenter;
import com.dumu.housego.presenter.BuyHouseListPresenter;
import com.dumu.housego.presenter.IBuyHouseDeletePresenter;
import com.dumu.housego.presenter.IBuyHouseListPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IBuyHouseDeleteView;
import com.dumu.housego.view.IBuyHouseListView;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PTBuyHouseListFragment extends Fragment implements IBuyHouseListView, IBuyHouseDeleteView {
	private TextView tv_ptbuyhouse_submit;
	private LinearLayout ll_back_pt_buyhouselist;
	private ListView lv_buyhouse_list;
	private List<QiuZuBuyHouseList> lists;
	private IBuyHouseListPresenter listPresenter;
	private BuyHouseListAdapter adapter;
	private UserInfo userinfo;
	private IBuyHouseDeletePresenter deletePresenter;
	private PopupWindow pop;
	private LinearLayout ll_popup_delete;
	private int position;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pt_buyhouselist, null);
		listPresenter = new BuyHouseListPresenter(this);
		deletePresenter = new BuyHouseDeletePresenter(this);
		initView(view);
		PopDelete();
		setListener(view);
		return view;

	}

	@Override
	public void onResume() {
		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		String username = userinfo.getUsername();
		listPresenter.buyHouse(username, "userqiugou");
		super.onResume();
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

		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_delete.clearAnimation();
			}
		});

		bt1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String lock = lists.get(position).getLock();
				String ID = lists.get(position).getId();
				String userid = userinfo.getUserid();
				String username = userinfo.getUsername();

				deletePresenter.DeletebuyHouse(ID, userid, username);

				if (lock.equals("0")) {
					lists.remove(position);
					adapter.notifyDataSetChanged();
				}

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
		tv_ptbuyhouse_submit = (TextView) view.findViewById(R.id.tv_ptbuyhouse_submit);
		ll_back_pt_buyhouselist = (LinearLayout) view.findViewById(R.id.ll_back_pt_buyhouselist);
		lv_buyhouse_list = (ListView) view.findViewById(R.id.lv_buyhouse_list);
	}

	private void setListener(final View PopView) {
		tv_ptbuyhouse_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new PTBuyHouseSubmitFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});

		ll_back_pt_buyhouselist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});

		lv_buyhouse_list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				PTBuyHouseListFragment.this.position = position;

				Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);

				ll_popup_delete.setAnimation(anim);
				pop.showAtLocation(PopView, Gravity.BOTTOM, 0, 0);

				return true;
			}
		});

	}

	@Override
	public void buyhouseSuccess(List<QiuZuBuyHouseList> lists) {
		this.lists = lists;
		adapter = new BuyHouseListAdapter(lists, getActivity());
		lv_buyhouse_list.setAdapter(adapter);

	}

	@Override
	public void buyhouseFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);

	}

	@Override
	public void deleteBuyHouse(String info) {

		MyToastShowCenter.CenterToast(getActivity(), info);

	}
}
