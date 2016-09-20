package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.AgentMainActivity;
import com.dumu.housego.BlockTradeMainActivity;
import com.dumu.housego.ErShouFangMainActivity;
import com.dumu.housego.HousePuchaseMainActivity;
import com.dumu.housego.MapHouseMainActivity;
import com.dumu.housego.NewHouseMainActivity;
import com.dumu.housego.ProprietorMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.RentingMainActivity;
import com.dumu.housego.WapRecommedMainActivity;
import com.dumu.housego.WebHousePriceDetailActivity;
import com.dumu.housego.adapter.RecommendHouseAdapter;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.RecommendHousePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IShopGuideView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FirstFramgent extends Fragment implements IShopGuideView {
	private RecommendHouseAdapter recommendAdapter;
	private List<RecommendNews> recommends;
	private IRecommendHousePresenter presenter;
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
	private TextView tvMonthNumber, tvPriceNumber, tvHouseNumber;
	private LinearLayout llSearch;
	private ScrollView scrollview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.framgent_first, null);
		setViews(view);
		setListener();
		presenter = new RecommendHousePresenter(this);
		presenter.LoadRecommend();
		FontHelper.injectFont(view);
		return view;
	}

	private void setListener() {
		rbBigBussnies.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), BlockTradeMainActivity.class));

			}
		});

		rbErShouFang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ErShouFangMainActivity.class));

			}
		});

		rbJinJiRen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), AgentMainActivity.class));

			}
		});

		rbMapHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), MapHouseMainActivity.class));

			}
		});

		rbNewHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), NewHouseMainActivity.class));

			}
		});

		rbShopHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), HousePuchaseMainActivity.class));

			}
		});

		rbYeZhuWeiTuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ProprietorMainActivity.class));

			}
		});

		rbZuFang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), RentingMainActivity.class));

			}
		});

		lvShopHouseGudie.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Intent i = new Intent(getActivity(), WapRecommedMainActivity.class);
				String url = "http://www.taoshenfang.com" + recommends.get(position).getThumb();
				String title=recommends.get(position).getTitle();
				String content=recommends.get(position).getDescription();
				i.putExtra("content", content);
				i.putExtra("url", url);
				i.putExtra("title", title);
				startActivity(i);
			}
		});
		
		
		rlHouseDetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), WebHousePriceDetailActivity.class));
				
			}
		});

	}

	private void setViews(View view) {

		etFirstSearch = (EditText) view.findViewById(R.id.et_first_search);
		lvShopHouseGudie = (ListView) view.findViewById(R.id.lv_ShopGuide);
		rlHouseDetails = (RelativeLayout) view.findViewById(R.id.HouseDetails);
		rlShopHouseGuide = (RelativeLayout) view.findViewById(R.id.ShopHouseGuide);
		tvHouseNumber = (TextView) view.findViewById(R.id.tv_housenumber);
		tvMonthNumber = (TextView) view.findViewById(R.id.tv_number);
		tvPriceNumber = (TextView) view.findViewById(R.id.tv_pricenumber);
		rbBigBussnies = (RadioButton) view.findViewById(R.id.rb_dazongjiaoyi);
		rbErShouFang = (RadioButton) view.findViewById(R.id.rb_ershoufang);
		rbJinJiRen = (RadioButton) view.findViewById(R.id.rb_jingjiren);
		rbMapHouse = (RadioButton) view.findViewById(R.id.rb_dituzhaofang);
		rbNewHouse = (RadioButton) view.findViewById(R.id.rb_xinfang);
		rbShopHouse = (RadioButton) view.findViewById(R.id.rb_goufangxuzhi);
		rbYeZhuWeiTuo = (RadioButton) view.findViewById(R.id.rb_yezhuweituo);
		rbZuFang = (RadioButton) view.findViewById(R.id.rb_zufang);
		llSearch = (LinearLayout) view.findViewById(R.id.ll_search);
		
		
		scrollview=(ScrollView) view.findViewById(R.id.first_scrollview);
		scrollview.smoothScrollTo(0, 0);
	}

	@Override
	public void showData(List<RecommendNews> recommends) {

		this.recommends = recommends;
		recommendAdapter = new RecommendHouseAdapter(recommends, getActivity());
		lvShopHouseGudie.setAdapter(recommendAdapter);

	}

}
