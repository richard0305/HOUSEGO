package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.util.MyToastShowCenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GuanZhuErShouLsitAdapter extends BaseAdapter {
	private List<ErShouFangDetails> ershoufangdetails;
	private Context context;
	private LayoutInflater Inflater;
	private int mRightWidth = 0;

	public GuanZhuErShouLsitAdapter(List<ErShouFangDetails> ershoufangdetails, Context context,int rightWidth) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
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
		
		holder.tvArea.setText(n.getArea()+"平米");
		holder.tvChaoxiang.setText(n.getChaoxiang());
		holder.tvDitiexian.setText(n.getDitiexian());
		holder.tvFangxing.setText(n.getShi()+"室"+n.getTing()+"厅");
		holder.tvJianzhuType.setText(n.getFangling()+"房龄"+n.getJianzhutype());
		
		int zongjia=Integer.parseInt(n.getZongjia())*10000;
		int mianji=Integer.parseInt(n.getJianzhumianji());
		
		holder.tvJunjias.setText(zongjia/mianji+"元/㎡");
		
		holder.tvLouceng.setText(n.getCeng()+"(共"+n.getZongceng()+"层)");
		holder.tvPrice.setText(n.getZongjia());
		holder.tvXiaoquName.setText(n.getXiaoquName());
		
		holder.item_right_txt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyToastShowCenter.CenterToast(context, "取消关注");
				
			}
		});
	 
		
		
		
		return convertView;
	}
	
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
