package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.adapter.BlockTradeLsitAdapter.ViewHolder;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.SearchXiaoQu;
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

public class SearchXiaoQuAdapter extends BaseAdapter {
	private List<SearchXiaoQu> xiaoqus;
	private Context context;
	private LayoutInflater Inflater;

	public SearchXiaoQuAdapter(List<SearchXiaoQu> xiaoqus, Context context) {
		super();
		this.xiaoqus = xiaoqus;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return xiaoqus.size();
	}

	@Override
	public SearchXiaoQu getItem(int position) {

		return xiaoqus.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_search_xiaoqu, null);
			holder = new ViewHolder();
			holder.tvSearchTitle = (TextView) convertView.findViewById(R.id.tv_search_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SearchXiaoQu n = getItem(position);
			
		holder.tvSearchTitle.setText(n.getTitle());
		return convertView;

	}

	class ViewHolder {
		TextView tvSearchTitle;

	}
}
