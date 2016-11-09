package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.adapter.BlockTradeLsitAdapter.ViewHolder;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.util.TimeTurnDate;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BuyHouseListAdapter extends BaseAdapter {
	private List<QiuZuBuyHouseList> qiuzulists;
	private Context context;
	private LayoutInflater Inflater;
	

	public BuyHouseListAdapter(List<QiuZuBuyHouseList> qiuzulists, Context context) {
		super();
		this.qiuzulists = qiuzulists;
		this.context = context;
		this.Inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return qiuzulists.size();
	}

	@Override
	public QiuZuBuyHouseList getItem(int position) {
		
		return qiuzulists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=Inflater.inflate(R.layout.item_qiuzu_list, null);
			holder = new ViewHolder();
			holder.tvDetails=(TextView) convertView.findViewById(R.id.tv_qiuzulist_detail);
			holder.tvTime=(TextView) convertView.findViewById(R.id.tv_qiuzulist_time);
			holder.tvTitle=(TextView) convertView.findViewById(R.id.tv_qiuzulist_title);
			
		
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		QiuZuBuyHouseList n=getItem(position);
			
		
		holder.tvTitle.setText(n.getTitle());
		
		String time=TimeTurnDate.getStringDateMoreMORE(Long.valueOf(n.getInputtime()));
		holder.tvTime.setText("发布时间："+time);
		
		
		
		holder.tvDetails.setText(n.getShi()+"室 / "+n.getZongjiarange()+"万元");
		
		
		return convertView;
		
	}
	
	class ViewHolder {
		TextView tvTitle;
		TextView tvDetails;
		TextView tvTime;
	
	}
}
