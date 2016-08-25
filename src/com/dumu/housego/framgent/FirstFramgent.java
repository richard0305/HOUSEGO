package com.dumu.housego.framgent;

import com.dumu.housego.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstFramgent extends Fragment {
	private EditText etFirstSearch;
	private ListView lvShopHouseGudie;
	private RadioButton rbErShouFang;
	private RadioButton rbNewHouse;
	private RadioButton rbZuFang;
	private RadioButton rbJinJiRen;
	private RadioButton rbYeZhuWeiTuo;
	private RadioButton rbBigBussnies;
	private RadioButton rbMapHouse;
	private RadioButton rbShopHouse;
	private RelativeLayout rlHouseDetails;
	private RelativeLayout rlShopHouseGuide;
	private TextView tvMonthNumber,tvPriceNumber,tvHouseNumber;
	private LinearLayout llSearch;

	// private layout

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.framgent_first, null);

		setViews(view);
		setListener();
		return view;
	}

	private void setListener() {
		rbBigBussnies.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
	}

	private void setViews(View view) {
		etFirstSearch=(EditText) view.findViewById(R.id.et_first_search);
		lvShopHouseGudie=(ListView) view.findViewById(R.id.lv_ShopGuide);
		rlHouseDetails=(RelativeLayout) view.findViewById(R.id.HouseDetails);
		rlShopHouseGuide=(RelativeLayout) view.findViewById(R.id.ShopHouseGuide);
		tvHouseNumber=(TextView) view.findViewById(R.id.tv_housenumber);
		tvMonthNumber=(TextView) view.findViewById(R.id.tv_number);
		tvPriceNumber=(TextView) view.findViewById(R.id.tv_pricenumber);
		rbBigBussnies=(RadioButton) view.findViewById(R.id.rb_dazongjiaoyi);
		rbErShouFang=(RadioButton) view.findViewById(R.id.rb_ershoufang);
		rbJinJiRen=(RadioButton) view.findViewById(R.id.rb_jingjiren);
		rbMapHouse=(RadioButton) view.findViewById(R.id.rb_dituzhaofang);
		rbNewHouse=(RadioButton) view.findViewById(R.id.rb_xinfang);
		rbShopHouse=(RadioButton) view.findViewById(R.id.rb_goufangxuzhi);
		rbYeZhuWeiTuo=(RadioButton) view.findViewById(R.id.rb_yezhuweituo);
		rbZuFang=(RadioButton) view.findViewById(R.id.rb_zufang);
		llSearch=(LinearLayout) view.findViewById(R.id.ll_search);
		
	}
}
