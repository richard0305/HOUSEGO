package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.ErShouFangRecommendData;
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

public class ErShouFangRecommendAdapter extends BaseAdapter {
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private Context context;
	private LayoutInflater Inflater;

	public ErShouFangRecommendAdapter(List<ErShouFangRecommendData> ershoufangrecommends, Context context) {
		super();
		this.ershoufangrecommends = ershoufangrecommends;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return ershoufangrecommends.size();
	}

	@Override
	public ErShouFangRecommendData getItem(int position) {

		return ershoufangrecommends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_ershoufang_recommend, null);
			holder = new ViewHolder();
			holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
			holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_ershoufang_address);
			holder.tvArea = (TextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
			holder.tvMeterPrice = (TextView) convertView.findViewById(R.id.tv_ershoufang_price_meter);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_ershoufang_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ErShouFangRecommendData n = getItem(position);

		// 显示图片的配置

		String url = "http://www.taoshenfang.com" + n.getThumb();

		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		ImageLoader.getInstance().displayImage(url, holder.ivImg, options);

		// Glide.with(context).load(url).into(holder.ivImg);

		holder.tvTitle.setText(n.getTitle() + "");

		// holder.tvArea.setText(n.getShi()+"��"+n.getTing()+"��
		// "+n.getJianzhumianji()+"�O ��");
		// holder.tvPrice.setText(n.getZongjia()+"");
		// holder.tvAddress.setText("���� "+n.getCity_name()+"
		// "+n.getXiaoquname());

		// int zongjia=Integer.parseInt(n.getZongjia().trim());
		// int mianji=Integer.parseInt(n.getJianzhumianji().trim());
		// int price=(zongjia)*(10000)/mianji;

		// holder.tvMeterPrice.setText(price+"Ԫ/�O");

		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvArea;
		TextView tvPrice;
		TextView tvAddress;
		TextView tvMeterPrice;
		ImageView ivImg;
	}

}
