package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewHouseListAdapter extends BaseAdapter {
	private List<NewHouseList> newhouselists;
	private Context context;
	private LayoutInflater Inflater;
	

	public NewHouseListAdapter(List<NewHouseList> newhouselists, Context context) {
		super();
		this.newhouselists = newhouselists;
		this.context = context;
		this.Inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return newhouselists.size();
	}

	@Override
	public NewHouseList getItem(int position) {
		
		return newhouselists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=Inflater.inflate(R.layout.item_new_house_list, null);
			holder = new ViewHolder();
			holder.newhouselist_address=(TextView) convertView.findViewById(R.id.newhouselist_address);
			holder.newhouselist_price=(TextView) convertView.findViewById(R.id.newhouselist_price);
			holder.newhouselist_area=(TextView) convertView.findViewById(R.id.newhouselist_area);
			holder.newhouselist_title=(TextView) convertView.findViewById(R.id.newhouselist_title);
			holder.newhouselist_iv=(ImageView) convertView.findViewById(R.id.newhouselist_iv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		NewHouseList n=getItem(position);
		if(n.getThumb().startsWith("http://www.tao")){
			Glide.with(context).load(n.getThumb()).into(holder.newhouselist_iv);
		}else{
			String url="http://www.taoshenfang.com"+n.getThumb();
			Glide.with(context).load(url).into(holder.newhouselist_iv);
		}
		
	
		holder.newhouselist_address.setText(n.getLoupandizhi());
		holder.newhouselist_title.setText(n.getTitle());
		holder.newhouselist_price.setText(n.getJunjia()+"元/㎡");
		holder.newhouselist_area.setText(n.getCityname()+" "+n.getAreaname());
		
		
		
		return convertView;
	}
	
	class ViewHolder {
		TextView newhouselist_address;
		TextView newhouselist_price;
		TextView newhouselist_area;
		TextView newhouselist_title;
		ImageView newhouselist_iv;
		
	}

}
