package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.adapter.BlockTradeLsitAdapter.ViewHolder;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.BlockTradeList;
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

public class AgentDataAdapter extends BaseAdapter {
	private List<AgentData> agentdatas;
	private Context context;
	private LayoutInflater Inflater;

	public AgentDataAdapter(List<AgentData> agentdatas, Context context) {
		super();
		this.agentdatas = agentdatas;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return agentdatas.size();
	}

	@Override
	public AgentData getItem(int position) {

		return agentdatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_agent_data_list, null);
			holder = new ViewHolder();
			holder.ivAgentdataPic = (ImageView) convertView.findViewById(R.id.iv_agentdata_pic);
			holder.tvAgentdataBiaoqian = (TextView) convertView.findViewById(R.id.tv_agentdata_biaoqian);
			holder.tvAgentdataLevel = (TextView) convertView.findViewById(R.id.tv_agentdata_level);
			holder.tvAgentdataMainarea = (TextView) convertView.findViewById(R.id.tv_agentdata_mainarea);
			holder.tvAgentdataName = (TextView) convertView.findViewById(R.id.tv_agentdata_name);
			holder.tvAgentdataPhone1 = (TextView) convertView.findViewById(R.id.tv_agentdata_phone1);
			holder.tvAgentdataPoint = (TextView) convertView.findViewById(R.id.tv_agentdata_point);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AgentData n = getItem(position);
		String url = n.getUserpic();

		// Glide.with(context).load(url).into(holder.ivAgentdataPic);
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		ImageLoader.getInstance().displayImage(url, holder.ivAgentdataPic, options);
		holder.tvAgentdataBiaoqian.setText(n.getBiaoqian());
		holder.tvAgentdataLevel.setText(n.getDengji());
		holder.tvAgentdataMainarea.setText(n.getMainarea());
		holder.tvAgentdataName.setText(n.getRealname());
		holder.tvAgentdataPhone1.setText(n.getCtel());
		holder.tvAgentdataPoint.setText("98%");

		return convertView;

	}

	class ViewHolder {
		TextView tvAgentdataPoint;
		TextView tvAgentdataBiaoqian;
		TextView tvAgentdataPhone1;
		TextView tvAgentdataMainarea;
		TextView tvAgentdataLevel;
		TextView tvAgentdataName;
		ImageView ivAgentdataPic;
	}
}
