package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.QiuzuANDQiuGou;
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

public class ATQiuGouLsitAdapter extends BaseAdapter {
	private List<QiuzuANDQiuGou> blocktrades;
	private Context context;
	private LayoutInflater Inflater;

	public ATQiuGouLsitAdapter(List<QiuzuANDQiuGou> blocktrades, Context context) {
		super();
		this.blocktrades = blocktrades;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return blocktrades.size();
	}

	@Override
	public QiuzuANDQiuGou getItem(int position) {

		return blocktrades.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_qiugou_at, null);
			holder = new ViewHolder();
			holder.tv_qiuzu_area=(TextView) convertView.findViewById(R.id.tv_qiugou_area);
			holder.tv_qiuzu_jine=(TextView) convertView.findViewById(R.id.tv_qiugou_jine);
			holder.tv_qiuzu_phone=(TextView) convertView.findViewById(R.id.tv_qiugou_phone);
			holder.tvtime=(TextView) convertView.findViewById(R.id.tv_gou_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		QiuzuANDQiuGou n = getItem(position);
		holder.tv_qiuzu_area.setText("深圳"+n.getCity_name()+n.getArea_name());
		holder.tv_qiuzu_jine.setText(n.getZongjiarange()+"");
		holder.tv_qiuzu_phone.setText(n.getChenghu()+" "+n.getUsername());
		
		long time=Long.valueOf(n.getInputtime());
		String t=TimeTurnDate.getStringDateMoreMORE(time);
		holder.tvtime.setText(t);

		return convertView;
	}

	class ViewHolder {
		TextView tvtime;
		TextView tv_qiuzu_area;
		TextView tv_qiuzu_phone;
		TextView tv_qiuzu_jine;
	}

}
