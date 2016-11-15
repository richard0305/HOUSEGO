package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.adapter.SubmitErshouListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ATershouListDeletePresenter;
import com.dumu.housego.presenter.IATershouListDeletePresenter;
import com.dumu.housego.presenter.ISubmitErShouListpresenter;
import com.dumu.housego.presenter.SubmitErShoulistpresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IATershouListDeleteView;
import com.dumu.housego.view.ISubmitErShouListView;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
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

public class ATershouListFragment extends Fragment implements ISubmitErShouListView,IATershouListDeleteView {
	private LinearLayout ll_back_agentershoulist;
	private TextView tv_agentershoulist_submit;
	private ListView fragmentershoulist;
	private SubmitErshouListAdapter adapter;
	private ISubmitErShouListpresenter presenter;
	private UserInfo userinfo;
	private List<ErShouFangDetails> ershoudetails;
	private IATershouListDeletePresenter deletepresenter;
	String Id;
	String username;
	String userid;
	String table;
	private int POSI;
	private PopupWindow pop;
	private LinearLayout ll_popup_delete;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_agent_ershou_list, null);
		presenter = new SubmitErShoulistpresenter(this);
		deletepresenter=new ATershouListDeletePresenter(this);
		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		username = userinfo.getUsername();
		 userid = userinfo.getUserid();
		 table = "ershou";

		initview(view);
		PopDelete();
		setListener(view);
		presenter.SubmitErShouList(username, userid, table);
		return view;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
	}

	private void setListener(View view) {
		ll_back_agentershoulist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});

		tv_agentershoulist_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new ATershouSubmitFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		
		fragmentershoulist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent i = new Intent(getActivity(), ErShouFangDetailsActivity.class);
				 Id = ershoudetails.get(position).getId();
				String catid = ershoudetails.get(position).getCatid();
				String posid = ershoudetails.get(position).getPosid();

				i.putExtra("id", Id);
				i.putExtra("catid", catid);
				i.putExtra("posid", posid);
				startActivity(i);
			}
		});
		fragmentershoulist.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		
				ATershouListFragment.this.POSI=position;
				Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);

				ll_popup_delete.setAnimation(anim);
				pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
				return true;
			}
		});
		
	}

	private void initview(View view) {
		ll_back_agentershoulist = (LinearLayout) view.findViewById(R.id.ll_back_agentershoulist);
		tv_agentershoulist_submit = (TextView) view.findViewById(R.id.tv_agentershoulist_submit);
		fragmentershoulist = (ListView) view.findViewById(R.id.fragmentershoulist);
	}

	@Override
	public void SubmitListError(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		
	}

	@Override
	public void SubmitListSuccess(List<ErShouFangDetails> ershoudetails) {
		this.ershoudetails = ershoudetails;
		adapter = new SubmitErshouListAdapter(ershoudetails, getActivity());
		fragmentershoulist.setAdapter(adapter);

	}

	@Override
	public void ATershouListDelete(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		if(info.equals("删除成功")){
			this.ershoudetails.remove(POSI);
			adapter.notifyDataSetChanged();
		}
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
		tv.setText("是否删除该二手房");
		
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_delete.clearAnimation();
			}
		});

		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String lock = ershoudetails.get(POSI).getLock();

				String ID=ershoudetails.get(POSI).getId();
			
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
	
	
}
