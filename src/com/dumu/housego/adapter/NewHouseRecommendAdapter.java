package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewHouseRecommendAdapter extends BaseAdapter {
	private List<NewHouseRecommendData> newrecommends;
	private Context context;
	private LayoutInflater Inflater;
	

	public NewHouseRecommendAdapter(List<NewHouseRecommendData> newrecommends, Context context) {
		super();
		this.newrecommends = newrecommends;
		this.context = context;
		this.Inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return newrecommends.size();
	}

	@Override
	public NewHouseRecommendData getItem(int position) {
		
		return newrecommends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=Inflater.inflate(R.layout.item_recommend_house, null);
			holder = new ViewHolder();
			holder.ivRecommendImg=(ImageView) convertView.findViewById(R.id.iv_recommend_img);
			holder.tvRecommendContent=(TextView) convertView.findViewById(R.id.tv_recommend_content);
			holder.tvRecommendTitle=(TextView) convertView.findViewById(R.id.tv_recommend_title);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		NewHouseRecommendData newrecommend=getItem(position);
		String url="http://www.taoshenfang.com"+newrecommend.getThumb();
		Glide.with(context).load(url).into(holder.ivRecommendImg);
		holder.tvRecommendContent.setText(newrecommend.getDescription());
		holder.tvRecommendTitle.setText(newrecommend.getTitle());
		
		
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tvRecommendTitle;
		TextView tvRecommendContent;
		ImageView ivRecommendImg;
	}

}
