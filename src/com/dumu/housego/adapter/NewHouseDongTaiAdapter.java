package com.dumu.housego.adapter;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.NewDongTai;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewHouseDongTaiAdapter extends BaseAdapter {
	private List<NewDongTai> dongtais;
	private Context context;
	private LayoutInflater Inflater;

	public NewHouseDongTaiAdapter(List<NewDongTai> dongtais, Context context) {
		super();
		this.dongtais = dongtais;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return dongtais.size();
	}

	
	@Override
	public NewDongTai getItem(int position) {
		return dongtais.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_newhouse_dongtai, null);
			holder = new ViewHolder();
			holder.tvBiaoqian=(TextView) convertView.findViewById(R.id.tv_biaoqian);
			holder.tvDes=(TextView) convertView.findViewById(R.id.tv_des);
			holder.tvTitle=(TextView) convertView.findViewById(R.id.tv_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final NewDongTai n = getItem(position);
		holder.tvBiaoqian.setText(n.getBiaoqian());
		holder.tvDes.setText(n.getDescription());
		holder.tvTitle.setText(n.getTitle());
		return convertView;

	}

	class ViewHolder {
		TextView tvTitle,tvDes,tvBiaoqian;
	}
}
