package com.dumu.housego.framgent;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.R.layout;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.GZErShouFramgent.GuanZhuErShouLsitAdapter.ViewHolder;
import com.dumu.housego.presenter.GuanZhuDeletePresenter;
import com.dumu.housego.presenter.GuanZhuNewPresenter;
import com.dumu.housego.presenter.IGuanZhuDeletePresenter;
import com.dumu.housego.presenter.IGuanZhuNewPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.SwipeListView;
import com.dumu.housego.view.IGuanZhuDeleteView;
import com.dumu.housego.view.IGuanZhuNewView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class GZNewFramgent extends Fragment implements IGuanZhuNewView , IGuanZhuDeleteView{
	private SwipeListView listview;
	private List<NewHouseDetail> newhousedetails;
	private GuanZhuNewLsitAdapter adapter;
	private UserInfo userinfo;
	private IGuanZhuNewPresenter presenter;
	private IGuanZhuDeletePresenter deletePresenter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guanzhu_new, null);
		initView(view);
		setListener();
		
		

		return view;
	}

	 @Override
	 public void onResume() {
		 presenter = new GuanZhuNewPresenter(this);
			deletePresenter = new GuanZhuDeletePresenter(this);
			userinfo = HouseGoApp.getContext().getCurrentUserInfo();
			String username = userinfo.getUsername();
		 deletePresenter = new GuanZhuDeletePresenter(this);
		 presenter.LoadGuanZhuNew(username, "new");
	 super.onResume();
	 }
	

	private void setListener() {
		// TODO Auto-generated method stub

	}

	private void initView(View view) {
		listview = (SwipeListView) view.findViewById(R.id.lv_guanzhu_new);

	}

	@Override
	public void showGuanZhuSuccess(List<NewHouseDetail> newhousedetails) {
		this.newhousedetails = newhousedetails;
		if (newhousedetails != null) {
			adapter = new GuanZhuNewLsitAdapter(newhousedetails, getActivity(), listview.getRightViewWidth());
		} else {
			MyToastShowCenter.CenterToast(getContext(), "你还没有关注新房！");
		}

	}

	@Override
	public void showGuanZhuFail(String errorinfo) {
		MyToastShowCenter.CenterToast(getActivity(), errorinfo);

	}

	public class GuanZhuNewLsitAdapter extends BaseAdapter {
		private List<NewHouseDetail> newhousedetails;
		private Context context;
		private LayoutInflater Inflater;
		private int mRightWidth = 0;

		public GuanZhuNewLsitAdapter(List<NewHouseDetail> newhousedetails, Context context, int rightWidth) {
			super();
			this.newhousedetails = newhousedetails;
			this.context = context;
			mRightWidth = rightWidth;
			this.Inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return newhousedetails.size();
		}

		@Override
		public NewHouseDetail getItem(int position) {

			return newhousedetails.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = Inflater.inflate(R.layout.item_guanzhu_new, null);
				holder = new ViewHolder();
				holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_guanzhuershou);

				holder.tvConntacttel = (TextView) convertView.findViewById(R.id.tv_guanzhu_new_phone);
				holder.tvJunjia = (TextView) convertView.findViewById(R.id.tv_guanzhu_new_junjia);
				holder.tvKaipanshijian = (TextView) convertView.findViewById(R.id.tv_guanzhu_new_time);
				holder.tvLoupandizhi = (TextView) convertView.findViewById(R.id.tv_guanzhu_new_loupandizhi);
				holder.tvMianjiarea = (TextView) convertView.findViewById(R.id.tv_guanzhu_new_area);
				holder.tvShiarea = (TextView) convertView.findViewById(R.id.tv_guanzhu_new_shi);

				holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_guanzhu_xiaoquname);
				holder.item_left = (RelativeLayout) convertView.findViewById(R.id.item_left);
				holder.item_right = (RelativeLayout) convertView.findViewById(R.id.item_right);
				holder.item_right_txt = (TextView) convertView.findViewById(R.id.item_right_txt);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			LinearLayout.LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			holder.item_left.setLayoutParams(lp1);
			LinearLayout.LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
			holder.item_right.setLayoutParams(lp2);

			NewHouseDetail n = getItem(position);

			String url = "http://www.taoshenfang.com" + n.getThumb();
			Glide.with(context).load(url).into(holder.ivImg);

			holder.tvTitle.setText(n.getTitle() + "");

			holder.tvConntacttel.setText(n.getContacttel());
			holder.tvJunjia.setText(n.getJunjia());
			holder.tvKaipanshijian.setText("开盘时间:" + n.getKaipandate() + " / 交房时间:" + n.getJiaofangdate());
			holder.tvLoupandizhi.setText(n.getLoupandizhi());
			holder.tvMianjiarea.setText(n.getMianjiarea() + "平米");
			holder.tvShiarea.setText("1-3 室");

			// ȡ����ע�������ֵ

			holder.item_right_txt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					newhousedetails.remove(position);
					MyToastShowCenter.CenterToast(context, "取消关注");

					listview.hiddenRight(listview.mPreItemView);
					if (myHandler != null) {
						Message msg = myHandler.obtainMessage();
						msg.arg1 = 0;
						msg.arg2 = position;
						myHandler.sendMessage(msg);
					}

				}
			});

			return convertView;
		}

		Handler myHandler = new Handler() {
			public void handleMessage(Message msg) {
				final String id = newhousedetails.get(msg.arg2).getId();

				UserInfo info = HouseGoApp.getContext().getCurrentUserInfo();
				final String userid = info.getUserid();
				final String username = info.getUsername();

				 deletePresenter.deleteGuanZhu(id, userid, username);
				newhousedetails.remove(msg.arg2);
				adapter.notifyDataSetChanged();
			}
		};

		class ViewHolder {
			TextView tvTitle;
			ImageView ivImg;
			TextView tvShiarea;
			TextView tvMianjiarea;
			TextView tvJunjia;
			TextView tvLoupandizhi;
			TextView tvConntacttel;
			TextView tvKaipanshijian;

			TextView item_right_txt;
			RelativeLayout item_left;
			RelativeLayout item_right;
		}
	}

	@Override
	public void deleteGuanZhuSuccess(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGuanZhuFail(String errorinfo) {
		// TODO Auto-generated method stub
		
	}

}
