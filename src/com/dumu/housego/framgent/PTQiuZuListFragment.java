package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.QiuZuListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.IQiuZuListDeletePresenter;
import com.dumu.housego.presenter.IQiuZuListPresenter;
import com.dumu.housego.presenter.QiuZuListDeletePresenter;
import com.dumu.housego.presenter.QiuZuListPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IQiuZuListDeleteView;
import com.dumu.housego.view.IQiuZuListView;

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
import android.widget.Toast;

public class PTQiuZuListFragment extends Fragment implements IQiuZuListView, IQiuZuListDeleteView {
	private ListView lv_qiuzu_list;
	private LinearLayout ll_back_ptqiuzulist;
	private TextView tv_ptqiuzu_submit;
	private List<QiuZuBuyHouseList> qiuzulists;
	private QiuZuListAdapter adapter;
	private IQiuZuListPresenter listpresenter;
	private IQiuZuListDeletePresenter deletePresenter;
	private UserInfo userinfo;
	private PopupWindow pop;
	private LinearLayout ll_popup_delete;
	private int position;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pt_qiuzhulist, null);
		listpresenter = new QiuZuListPresenter(this);
		deletePresenter = new QiuZuListDeletePresenter(this);
		initView(view);
		PopDelete();

		setListener(view);
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

		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_delete.clearAnimation();
			}
		});

		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String lock = qiuzulists.get(position).getLock();
				String ID = qiuzulists.get(position).getId();
				String userid = userinfo.getUserid();
				String username = userinfo.getUsername();

				deletePresenter.DeleteQiuZuList(ID, userid, username);
				if (lock.equals("0")) {
					qiuzulists.remove(position);
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

	@Override
	public void onResume() {

		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		String username = userinfo.getUsername();
		listpresenter.qiuzulist(username, "userqiuzu");

		super.onResume();
	}

	private void initView(View view) {
		lv_qiuzu_list = (ListView) view.findViewById(R.id.lv_qiuzu_list);
		ll_back_ptqiuzulist = (LinearLayout) view.findViewById(R.id.ll_back_ptqiuzulist);
		tv_ptqiuzu_submit = (TextView) view.findViewById(R.id.tv_ptqiuzu_submit);

	}

	private void setListener(final View PopView) {
		tv_ptqiuzu_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new PTQiuZuSubmitFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});

		ll_back_ptqiuzulist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();

			}
		});

		lv_qiuzu_list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				PTQiuZuListFragment.this.position = position;

				Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);

				ll_popup_delete.setAnimation(anim);
				pop.showAtLocation(PopView, Gravity.BOTTOM, 0, 0);
				return true;
			}
		});

	}

	@Override
	public void QiuZuListSuccess(List<QiuZuBuyHouseList> qiuzulists) {
		this.qiuzulists = qiuzulists;
		adapter = new QiuZuListAdapter(qiuzulists, getActivity());
		lv_qiuzu_list.setAdapter(adapter);

	}

	@Override
	public void QiuZuListFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);

	}

	@Override
	public void qiuzuDelete(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);

	}

}
