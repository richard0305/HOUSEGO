package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.BlockTradeList;
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

public class BlockTradeLsitAdapter extends BaseAdapter {
	private List<BlockTradeList> blocktrades;
	private Context context;
	private LayoutInflater Inflater;

	public BlockTradeLsitAdapter(List<BlockTradeList> blocktrades, Context context) {
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
	public BlockTradeList getItem(int position) {

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
			convertView = Inflater.inflate(R.layout.item_blocktrade_recommend, null);
			holder = new ViewHolder();
			holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
			holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_ershoufang_address);
			holder.tvArea = (TextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
			holder.tvMeterPrice = (TextView) convertView.findViewById(R.id.tv_ershou_nianxain);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_ershoufang_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		BlockTradeList n = getItem(position);

		String url = "http://www.taoshenfang.com" + n.getThumb();

		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		ImageLoader.getInstance().displayImage(url, holder.ivImg, options);

		holder.tvTitle.setText(n.getTitle() + "");
		
		// holder.tvPrice.setText(n.getZongjia()+"");
		 holder.tvAddress.setText(n.getHezuofangshi()+"/"+n.getWuyetype());

		holder.tvArea.setText(n.getCityname()+" "+n.getAreaname()+"/"+n.getZhandimianji()+"㎡");

		holder.tvPrice.setText(n.getZongjia()+"");
		if(n.getShiyongnianxian()==null||!n.getShiyongnianxian().equals("")){
			holder.tvMeterPrice.setText("使用年限:999");
		}else{
			holder.tvMeterPrice.setText("使用年限:"+n.getShiyongnianxian());
		}

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
