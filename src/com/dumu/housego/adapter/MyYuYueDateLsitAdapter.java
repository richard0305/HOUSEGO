package com.dumu.housego.adapter;

import java.text.ParseException;
import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.util.NoUnderlineSpan;
import com.dumu.housego.util.TimeTurnDate;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyYuYueDateLsitAdapter extends BaseAdapter {
	private List<YuYueData> yuyuedatas;
	private Context context;
	private LayoutInflater Inflater;

	public MyYuYueDateLsitAdapter(List<YuYueData> yuyuedatas, Context context) {
		super();
		this.yuyuedatas = yuyuedatas;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return yuyuedatas.size();
	}

	@Override
	public YuYueData getItem(int position) {

		return yuyuedatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_myyuyue_kanfang, null);
			holder = new ViewHolder();
			holder.ivIMage = (ImageView) convertView.findViewById(R.id.myyuyue_kanfang_image_zhuangzai);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.myyuyue_kanfang_title);
			holder.tvYuYueDate = (TextView) convertView.findViewById(R.id.myyuyue_kanfang_time);
			holder.tvZhuangtai = (TextView) convertView.findViewById(R.id.myyuyue_kanfang_zhuangtai);
			holder.tvGuoqi = (TextView) convertView.findViewById(R.id.myyuyue_kanfang_guoqi);
			holder.ll_show_phone = (LinearLayout) convertView.findViewById(R.id.ll_show_phone);
			holder.tvPhone = (TextView) convertView.findViewById(R.id.myyuyue_kanfang_phone);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		YuYueData n = getItem(position);
		String a[] = n.getYuyuetime().split("-");
		String b = a[1];
		// 预约超时处理
		try {
			long currenttime = System.currentTimeMillis();
			String gmtTime = n.getYuyuedate() + " " + b;
			long times = TimeTurnDate.getLongByGMT(gmtTime);

			if (times < currenttime) {
				holder.tvGuoqi.setText("(已过期)");
				holder.tvYuYueDate.setTextColor(android.graphics.Color.RED);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (n.getZhuangtai().equals("预约成功")) {
			holder.ll_show_phone.setVisibility(View.VISIBLE);
			holder.tvPhone.setText(n.getFromuser());

			// 去掉TextView属性autolink的下划线
			NoUnderlineSpan mNoUnderlineSpan = new NoUnderlineSpan();
			if (holder.tvPhone.getText() instanceof Spannable) {
				Spannable s = (Spannable) holder.tvPhone.getText();
				s.setSpan(mNoUnderlineSpan, 0, s.length(), Spanned.SPAN_MARK_MARK);
			}

		}

		holder.tvTitle.setText(n.getTitle() + " ");
		holder.tvYuYueDate.setText(n.getYuyuedate() + " " + n.getYuyuetime());
		holder.tvZhuangtai.setText(n.getZhuangtai());
		holder.ivIMage.setImageResource(R.drawable.right_corner_tag_stop);

		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvGuoqi;
		TextView tvZhuangtai;
		TextView tvYuYueDate;
		ImageView ivIMage;
		LinearLayout ll_show_phone;
		TextView tvPhone;
	}

}
