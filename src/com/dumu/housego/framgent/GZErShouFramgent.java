package com.dumu.housego.framgent;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.GuanZhuDeletePresenter;
import com.dumu.housego.presenter.GuanZhuErShouPresenter;
import com.dumu.housego.presenter.IGuanZhuDeletePresenter;
import com.dumu.housego.presenter.IGuanZhuErShouPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.SwipeListView;
import com.dumu.housego.view.IGuanZhuDeleteView;
import com.dumu.housego.view.IGuanZhuErShouView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GZErShouFramgent extends Fragment implements IGuanZhuErShouView,IGuanZhuDeleteView{
	private IGuanZhuErShouPresenter presenter;
	private UserInfo userinfo;
	private List<ErShouFangDetails>ershoufangdetails;
	private SwipeListView lvGuanZhuErShou;
	private GuanZhuErShouLsitAdapter adapter;
	private IGuanZhuDeletePresenter deletePresenter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragment_guanzhu_ershou, null);
	
	
	presenter=new GuanZhuErShouPresenter(this);
	deletePresenter=new GuanZhuDeletePresenter(this);
	initViews(view);
	setListener();
	
	return view;
	}
	
	private void initViews(View view) {
		lvGuanZhuErShou=(SwipeListView) view.findViewById(R.id.lv_guanzhu_ershou);
		
	}

	private void setListener() {
		lvGuanZhuErShou.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				 Intent i = new Intent(getContext(),ErShouFangDetailsActivity.class);
				 String catid=ershoufangdetails.get(position).getCatid();
				 String ID=ershoufangdetails.get(position).getId();
				 i.putExtra("catid", catid);
				 i.putExtra("id", ID);
				 startActivity(i);
				
			}
		});
		
		lvGuanZhuErShou.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long ID) {
				MyToastShowCenter.CenterToast(getContext(), "������"+position);
				 String id=ershoufangdetails.get(position).getId();
				 UserInfo info=HouseGoApp.getContext().getCurrentUserInfo();
				String userid=info.getUserid();
				 String username=info.getUsername();
				deletePresenter.deleteGuanZhu(id, userid, username);
				
				return true;
			}
		});
		
	}

	

	@Override
	public void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		String username=userinfo.getUsername();
		presenter.LoadGuanZhuErShou(username);
		MyToastShowCenter.CenterToast(getContext(), "����������");
		super.onResume();
	}



	@Override
	public void showGuanZhuFail(String errorinfo) {
		MyToastShowCenter.CenterToast(getContext(), errorinfo);
		
	}

	@Override
	public void showGuanZhuSuccess(List<ErShouFangDetails> ershoufangdetails) {
		this.ershoufangdetails=ershoufangdetails;
		adapter=new GuanZhuErShouLsitAdapter(ershoufangdetails, getContext(), lvGuanZhuErShou.getRightViewWidth());
		lvGuanZhuErShou.setAdapter(adapter);
		
	}

	@Override
	public void deleteGuanZhuSuccess(String info) {
		MyToastShowCenter.CenterToast(getContext(), info+"zhutou");
		
	}

	@Override
	public void deleteGuanZhuFail(String errorinfo) {
		MyToastShowCenter.CenterToast(getContext(), errorinfo+"zhutou");
		
	}

	
	/**
	 * 				adapter��
	 * **/
	
	public class GuanZhuErShouLsitAdapter extends BaseAdapter
	//implements IGuanZhuDeleteView
	{
		private List<ErShouFangDetails> ershoufangdetails;
		private Context context;
		private LayoutInflater Inflater;
		private int mRightWidth = 0;
		
		
		
		public GuanZhuErShouLsitAdapter(List<ErShouFangDetails> ershoufangdetails, Context context,int rightWidth
			) {
			super();
			this.ershoufangdetails = ershoufangdetails;
			this.context = context;
			mRightWidth = rightWidth;
			this.Inflater=LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return ershoufangdetails.size();
		}

		@Override
		public ErShouFangDetails getItem(int position) {
			
			return ershoufangdetails.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null){
				convertView=Inflater.inflate(R.layout.item_guanzhu_ershou, null);
				holder = new ViewHolder();
			holder.ivImg=(ImageView) convertView.findViewById(R.id.iv_guanzhuershou);
			holder.tvArea=(TextView) convertView.findViewById(R.id.tv_guanzhu_area);
			holder.tvChaoxiang=(TextView) convertView.findViewById(R.id.tv_guanzhu_chaoxiang);
			holder.tvDitiexian=(TextView) convertView.findViewById(R.id.tv_guanzhu_ditiexian);
			holder.tvFangxing=(TextView) convertView.findViewById(R.id.tv_guanzhu_fangxing);
			holder.tvJianzhuType=(TextView) convertView.findViewById(R.id.tv_guanzhu_jianzhutype);
			holder.tvJunjias=(TextView) convertView.findViewById(R.id.tv_guanzhu_junjia);
			holder.tvLouceng=(TextView) convertView.findViewById(R.id.tv_guanzhu_louceng);
			holder.tvPrice=(TextView) convertView.findViewById(R.id.tv_guanzhu_title);
			holder.tvTitle=(TextView) convertView.findViewById(R.id.tv_guanzhu_xiaoquname);
			holder.tvXiaoquName=(TextView) convertView.findViewById(R.id.tv_guanzhu_zongjia);
			holder.item_left = (LinearLayout) convertView.findViewById(R.id.item_left);
			holder.item_right = (RelativeLayout) convertView.findViewById(R.id.item_right);
			holder.item_right_txt = (TextView) convertView.findViewById(R.id.item_right_txt);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			
			LinearLayout.LayoutParams lp1 = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			holder.item_left.setLayoutParams(lp1);
			LinearLayout.LayoutParams lp2 = new LayoutParams(mRightWidth,
					LayoutParams.MATCH_PARENT);
			holder.item_right.setLayoutParams(lp2);
			
			
			ErShouFangDetails n=getItem(position);
			
			String url="http://www.taoshenfang.com"+n.getThumb();
			Glide.with(context).load(url).into(holder.ivImg);
			
			holder.tvTitle.setText(n.getTitle()+"");
			
			holder.tvArea.setText(n.getArea()+"ƽ��");
			holder.tvChaoxiang.setText(n.getChaoxiang());
			holder.tvDitiexian.setText(n.getDitiexian());
			holder.tvFangxing.setText(n.getShi()+"��"+n.getTing()+"��");
			holder.tvJianzhuType.setText(n.getFangling()+"����"+n.getJianzhutype());
			
			int zongjia=Integer.parseInt(n.getZongjia())*10000;
			int mianji=Integer.parseInt(n.getJianzhumianji());
			
			holder.tvJunjias.setText(zongjia/mianji+"Ԫ/�O");
			
			holder.tvLouceng.setText(n.getCeng()+"(��"+n.getZongceng()+"��)");
			holder.tvPrice.setText(n.getZongjia());
			holder.tvXiaoquName.setText(n.getXiaoquName());
			
			//ȡ����ע�������ֵ
	
			
			
			
			holder.item_right_txt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					ershoufangdetails.remove(position);
//					Presenter.deleteGuanZhu(id, userid, username);
//					MyToastShowCenter.CenterToast(context, "ȡ����ע");
					lvGuanZhuErShou.hiddenRight(lvGuanZhuErShou.mPreItemView);
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
				final String id=ershoufangdetails.get(msg.arg2).getId();
				
				UserInfo info=HouseGoApp.getContext().getCurrentUserInfo();
				final String userid=info.getUserid();
				final String username=info.getUsername();
				
				deletePresenter.deleteGuanZhu(id, userid, username);
				ershoufangdetails.remove(msg.arg2);
				adapter.notifyDataSetChanged();
			}
		};
		
		class ViewHolder {
			TextView tvTitle;
			TextView tvArea;
			TextView tvPrice;
			TextView tvChaoxiang;
			TextView tvFangxing;
			TextView tvDitiexian;
			TextView tvLouceng;
			TextView tvJianzhuType;
			TextView tvXiaoquName;
			TextView tvJunjias;
			ImageView ivImg;
			TextView item_right_txt;
			LinearLayout item_left;
			RelativeLayout item_right;
		}
	}
	
	/**
	 * 				adapter��
	 * **/
	
}
