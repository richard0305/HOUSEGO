package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.adapter.RentingRecommendAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.RentingProgramaModel;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.RentingProgramaPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IRentingProgramaView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RentingMainActivity extends Activity implements View.OnClickListener,IRentingProgramaView, OnRefreshListener2<ListView> {

	private LinearLayout llRentingBack;
	private RentingRecommendAdapter  adapter;
	private IFourDataProgramePresenter presenter;
	private List<RentingRecommendData> ershoufangrecommends;
	private FourDataPrograma fourdata;
	private PullToRefreshListView lvRenting;
	private List<RentingRecommendData> lastrentings = new ArrayList<RentingRecommendData>();
	int page = 1;
	private RentingProgramaModel model = new RentingProgramaModel();
	private TextView rentingSpinner1,rentingSpinner2,rentingSpinner3,rentingSpinner4; 
	private String city="2";
	private String area;
	private List<Address> minarea;
	private List<String>MinArea=new ArrayList<String>();
	
	private int posi;
	private PopupWindow QuYupop;
	private LinearLayout llpopupSpinnerQuyu;
	protected List<String> SpinnerQuYu=new ArrayList<String>();
	protected SpinnerAdapter spinneradapter1;
	private AddressModel addmodel = new AddressModel();
	private LinearLayout ll_renting_spinner;
	private PopupWindow Tpop;
	private LinearLayout llpopupSpinner2;
	private SpinnerAdapter spinneradapter;
	private PopupWindow pop;
	private LinearLayout ll_popup_spinner;
	private PopupWindow Mpop;
	private LinearLayout llpopupSpinnerMore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		showPopWindowQuyu();
		showPopWindowprice();
		showPopWindowType();
		showPopWindowMore();
		setListener();

		presenter = new RentingProgramaPresenter(this);
		fourdata = new FourDataPrograma();
		fourdata.setCatid("8");
		fourdata.setPage("1");
		fourdata.setCt("");
		fourdata.setAr("");
		fourdata.setDt("");
		fourdata.setZj("");
		fourdata.setShi("");
		
		fourdata.setMj("");
		fourdata.setQs("");
		fourdata.setYt("");
		fourdata.setXq("");
		fourdata.setCx("");
		fourdata.setLc("");
		fourdata.setZl("");
		fourdata.setZx("");
		fourdata.setKf("");
		fourdata.setKwds("");
		
		
		presenter.LoadProgrameData(fourdata);

	}

	private void setListener() {
		llRentingBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RentingMainActivity.this.finish();
			}
		});
		
		lvRenting.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				String catid = lastrentings.get(position-1).getCatid();
				String Id = lastrentings.get(position-1).getId();
				Intent i = new Intent(getApplicationContext(), RentingDetailActivity.class);
				i.putExtra("catid", catid);
				i.putExtra("id", Id);
				startActivity(i);
			}
		});
		
		rentingSpinner1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
				llpopupSpinnerQuyu.
				setAnimation(anim);
				QuYupop.showAsDropDown(ll_renting_spinner, 0, 0);
			}
		});
		
		rentingSpinner2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
				llpopupSpinner2.
				setAnimation(anim);
				Tpop.showAsDropDown(ll_renting_spinner, 0, 0);
			}
		});
		rentingSpinner3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
				ll_popup_spinner.
				setAnimation(anim);
				pop.showAsDropDown(ll_renting_spinner, 0, 0);
			}
		});
		rentingSpinner4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
				llpopupSpinnerMore.
				setAnimation(anim);
				Mpop.showAsDropDown(ll_renting_spinner, 0, 0);
			}
		});
	}

	private void setViews() {
		llRentingBack = (LinearLayout) findViewById(R.id.ll_renting_back);
		lvRenting = (PullToRefreshListView) findViewById(R.id.lv_rentinglist);
		lvRenting.setMode(PullToRefreshBase.Mode.BOTH);
		lvRenting.setOnRefreshListener(this);
		
		ll_renting_spinner=(LinearLayout) findViewById(R.id.ll_renting_spinner);
		
		rentingSpinner1=(TextView) findViewById(R.id.renting_quyu_sp1);
		rentingSpinner2=(TextView) findViewById(R.id.renting_quyu_sp2);
		rentingSpinner3=(TextView) findViewById(R.id.renting_quyu_sp3);
		rentingSpinner4=(TextView) findViewById(R.id.renting_quyu_sp4);
	}

	@Override
	public void showData(List<RentingRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends = ershoufangrecommends;
		this.lastrentings.addAll(ershoufangrecommends);
		adapter = new RentingRecommendAdapter(lastrentings, getApplicationContext());
		lvRenting.setAdapter(adapter);

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		fourdata.setCatid("8");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<RentingRecommendData> ershous = (List<RentingRecommendData>) success;

				// 将数据追加到集合中
				lastrentings.clear();
				lastrentings.addAll(ershous);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lvRenting.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.size() + "个房源！");

			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvRenting.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setCatid("8");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {

				List<RentingRecommendData> ershous = (List<RentingRecommendData>) success;
				if (ershous==null) {
					lvRenting.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				}else{
					
				// 将数据追加到集合中
				lastrentings.addAll(ershous);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lvRenting.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.size() + "个房源！");
				
				}
			}
			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvRenting.onRefreshComplete();

			}
		});

	}
	
	
	/**
	 * spinner
	 */
	private void showPopWindowType() {
		pop = new PopupWindow(RentingMainActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner, null);

		ll_popup_spinner = (LinearLayout) view.findViewById(R.id.ll_popup_spinner);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.MATCH_PARENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner);
		ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list);
		spinneradapter=new SpinnerAdapter(SpinnerData.HouseType, getApplicationContext());
		lvspinner1.setAdapter(spinneradapter);
		
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_spinner.clearAnimation();
			}
		});
		
		lvspinner1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0){
					rentingSpinner3.setText("房型");
					fourdata.setShi("");
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
				}else if(position==6){
					rentingSpinner3.setText("5室以上");
					fourdata.setShi("5-");
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
				}else{
					String shi=SpinnerData.HouseType.get(position).split("室")[0];
					
					rentingSpinner3.setText(SpinnerData.HouseType.get(position));
					fourdata.setShi(shi);
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
				}
				pop.dismiss();
				ll_popup_spinner.clearAnimation();
			}
		});
		
	}
	
	
	
	
	
	
	/**
	 * spinner
	 */
	private void showPopWindowprice() {
		Tpop = new PopupWindow(RentingMainActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner2, null);

		llpopupSpinner2 = (LinearLayout) view.findViewById(R.id.ll_popup_spinner2);
		Tpop.setWidth(LayoutParams.MATCH_PARENT);
		Tpop.setHeight(LayoutParams.MATCH_PARENT);
		Tpop.setBackgroundDrawable(new BitmapDrawable());
		Tpop.setFocusable(true);
		Tpop.setOutsideTouchable(true);
		Tpop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner2);
		ListViewForScrollView lvspinner1 = (ListViewForScrollView) view.findViewById(R.id.lv_popwin_list2);
		final EditText etlow=(EditText) view.findViewById(R.id.ershou_lowprice_et);
		final EditText ethigh=(EditText) view.findViewById(R.id.ershou_highprice_et);
		TextView tvlowhigh=(TextView) view.findViewById(R.id.tv_low_high);
		
		tvlowhigh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String low=etlow.getText().toString();
				String high=ethigh.getText().toString();
				
				
				if(low.equals("")&&!high.equals("")){
					fourdata.setZj("0-"+high);
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
					Tpop.dismiss();
					llpopupSpinner2.clearAnimation();
				}else if(!low.equals("")&&high.equals("")){
					fourdata.setZj(low+"-");
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
					Tpop.dismiss();
					llpopupSpinner2.clearAnimation();
				}else if(!low.equals("")&&!high.equals("")){
					int l=Integer.valueOf(low);
					int h=Integer.valueOf(high);
					
					if(l>h){
						MyToastShowCenter.CenterToast(getApplicationContext(), "最高价必须大于最低价！");
					}else if(l==h){
						fourdata.setZj(low);
						lastrentings.clear();
						presenter.LoadProgrameData(fourdata);
						Tpop.dismiss();
						llpopupSpinner2.clearAnimation();
					}else{
						fourdata.setZj(low+"-"+high);
						lastrentings.clear();
						presenter.LoadProgrameData(fourdata);
						Tpop.dismiss();
						llpopupSpinner2.clearAnimation();
					}
				}
				//
				ethigh.setText("");
				etlow.setText("");
				
			}
		});
		
		
		spinneradapter=new SpinnerAdapter(SpinnerData.ZuJin, getApplicationContext());
		lvspinner1.setAdapter(spinneradapter);
		
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Tpop.dismiss();
				llpopupSpinner2.clearAnimation();
			}
		});
		
		lvspinner1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0){
					rentingSpinner2.setText("价格");
					fourdata.setZj("");
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
				}else if(position==8){
					rentingSpinner2.setText("10000元以上");
					fourdata.setZj("10000-");
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
				}
				else{
				
					String price=SpinnerData.ZuJin.get(position).split("万")[0];
					rentingSpinner2.setText(SpinnerData.ZuJin.get(position));
					fourdata.setZj(price);
					Log.e("````````````````````````", "price="+price);
					lastrentings.clear();
					presenter.LoadProgrameData(fourdata);
				}
				Tpop.dismiss();
				llpopupSpinner2.clearAnimation();
			}
		});
		
	}
	
	
	

	/**
	 * spinner
	 */
	private void showPopWindowQuyu() {
		QuYupop = new PopupWindow(RentingMainActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner3, null);

		llpopupSpinnerQuyu = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_quyu);
		QuYupop.setWidth(LayoutParams.MATCH_PARENT);
		QuYupop.setHeight(LayoutParams.MATCH_PARENT);
		QuYupop.setBackgroundDrawable(new BitmapDrawable());
		QuYupop.setFocusable(true);
		QuYupop.setOutsideTouchable(true);
		QuYupop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_quyu);
		final ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list_quyu);
		final ListView lvspinner2 = (ListView) view.findViewById(R.id.lv_popwin_list_area);
		RadioGroup rgQuyu=(RadioGroup) view.findViewById(R.id.rg_popwin_title);
		RadioButton rbquyu=(RadioButton) view.findViewById(R.id.rb_popwin_quyu);
		RadioButton rbDitie=(RadioButton) view.findViewById(R.id.rb_popwin_ditie);
		
		rbDitie.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lvspinner2.setVisibility(View.GONE);
				SpinnerQuYu=SpinnerData.DiTie;
				spinneradapter1=new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
				lvspinner1.setAdapter(spinneradapter1);
			}
		});
	rbquyu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lvspinner2.setVisibility(View.VISIBLE);
				SpinnerQuYu=SpinnerData.Area;
				spinneradapter1=new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
				lvspinner1.setAdapter(spinneradapter1);
			}
		});
		
		
		spinneradapter1=new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
		lvspinner1.setAdapter(spinneradapter1);
		
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QuYupop.dismiss();
				llpopupSpinnerQuyu.clearAnimation();
			}
		});
		
		lvspinner1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(SpinnerQuYu.equals(SpinnerData.DiTie)){
					if(position==0){
						rentingSpinner1.setText("区域");
						fourdata.setCt("");
						fourdata.setAr("");
						fourdata.setDt("");
						lastrentings.clear();
						presenter.LoadProgrameData(fourdata);
						QuYupop.dismiss();
						llpopupSpinnerQuyu.clearAnimation();
					}else{
						rentingSpinner1.setText(SpinnerQuYu.get(position));
						String dt=SpinnerQuYu.get(position).split("号")[0];
						fourdata.setCt("");
						fourdata.setAr("");
						fourdata.setDt(dt);
						lastrentings.clear();
						presenter.LoadProgrameData(fourdata);
						QuYupop.dismiss();
						llpopupSpinnerQuyu.clearAnimation();
					}
				}else{
				
					switch (position) {
					case 0:
						rentingSpinner1.setText("区域");
						fourdata.setCt("");
						fourdata.setAr("");
						fourdata.setDt("");
						lastrentings.clear();
						posi=0;
						presenter.LoadProgrameData(fourdata);
						QuYupop.dismiss();
						llpopupSpinnerQuyu.clearAnimation();
						break;
					case 1:
						city="2";
						posi=1;
						MinArea.clear();
					case 2:
						city="4";
						posi=2;
						MinArea.clear();
						break;
					case 3:
						city="5";
						posi=3;
						MinArea.clear();
						break;
					case 4:
						city="6";
						posi=4;
						MinArea.clear();
						break;
					case 5:
						city="7";
						posi=5;
						MinArea.clear();
						break;
					case 6:
						city="8";
						posi=6;
						MinArea.clear();
						break;
					case 7:
						city="9";
						posi=7;
						MinArea.clear();
						break;
					case 8:
						city="10";
						posi=8;
						MinArea.clear();
						break;
					case 9:
						city="11";
						posi=9;
						MinArea.clear();
						break;
					case 10:
						city="12";
						posi=10;
						MinArea.clear();
						break;
					case 11:
						city="13";
						posi=11;
						MinArea.clear();
						break;
					case 12:
						city="14";
						posi=12;
						MinArea.clear();
						break;
					default:
						break;
					}
					
					addmodel.address(city, new AsycnCallBack() {
						@Override
						public void onSuccess(Object success) {
							minarea = (List<Address>) success;
							for (Address address : minarea) {
								String ad = address.getName();
								RentingMainActivity.this.MinArea.add(ad);
							}
							spinneradapter1=new SpinnerAdapter(MinArea, getApplicationContext());
							lvspinner2.setAdapter(spinneradapter1);
						}
						@Override
						public void onError(Object error) {
						}
					});
				}
			}
		});
		
		lvspinner2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0){
					rentingSpinner1.setText(SpinnerData.Area.get(posi));
					fourdata.setCt(city);
					fourdata.setAr("");
					fourdata.setDt("");
				}else{
				String pid=minarea.get(position).getPid();
				rentingSpinner1.setText(minarea.get(position).getName());
				fourdata.setCt(city);
				fourdata.setAr(pid);
				fourdata.setDt("");
				}
				lastrentings.clear();
				presenter.LoadProgrameData(fourdata);
				QuYupop.dismiss();
				llpopupSpinnerQuyu.clearAnimation();
				
			}
		});
		
	}

	/**
	 * spinner_more
	 */
	private void showPopWindowMore() {
		Mpop = new PopupWindow(RentingMainActivity.this);
		View view = getLayoutInflater().inflate(R.layout.item_renting_spinner4, null);
		llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
		Mpop.setWidth(LayoutParams.MATCH_PARENT);
		Mpop.setHeight(LayoutParams.MATCH_PARENT);
		Mpop.setBackgroundDrawable(new BitmapDrawable());
		Mpop.setFocusable(true);
		Mpop.setOutsideTouchable(true);
		Mpop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
		view.findViewById(R.id.renting_chaoxiang_dong).setOnClickListener(this);
		view.findViewById(R.id.renting_chaoxiang_nan).setOnClickListener(this);
		view.findViewById(R.id.renting_chaoxiang_xi).setOnClickListener(this);
		view.findViewById(R.id.renting_chaoxiang_bei).setOnClickListener(this);
		view.findViewById(R.id.renting_chaoxiang_nanbei).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_wushi).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_wu_qi).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_qi_jiu).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_jiu_shiyi).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_shiyi_shisi).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_shisi_shiqi).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_shiqi_ershi).setOnClickListener(this);
		view.findViewById(R.id.renting_mianji_ershi).setOnClickListener(this);
		view.findViewById(R.id.renting_biaoqian_jingzhuangxiu).setOnClickListener(this);
		view.findViewById(R.id.renting_biaoqian_suishikanfang).setOnClickListener(this);
		view.findViewById(R.id.renting_louceng_low).setOnClickListener(this);
		view.findViewById(R.id.renting_louceng_center).setOnClickListener(this);
		view.findViewById(R.id.renting_louceng_high).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_shangpinfang).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_cunweitongjian).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_kaifangshangjianshe).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_gerenzijianshe).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_junquchanfang).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_wujingchanfang).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_gongyechangzufang).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_gongyechanquanfang).setOnClickListener(this);
		view.findViewById(R.id.renting_wuye_qita).setOnClickListener(this);
		view.findViewById(R.id.renting_fangshi_zhengzu).setOnClickListener(this);
		view.findViewById(R.id.renting_fangshi_hezu).setOnClickListener(this);
		view.findViewById(R.id.renting_type_zuzhai).setOnClickListener(this);
		view.findViewById(R.id.renting_type_gongyu).setOnClickListener(this);
		view.findViewById(R.id.renting_type_shangpu).setOnClickListener(this);
		view.findViewById(R.id.renting_type_xiezilou).setOnClickListener(this);
		view.findViewById(R.id.renting_spinner_cancle).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.parent_spinner_more:
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_chaoxiang_dong:
			rentingSpinner4.setText("东");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("东");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_chaoxiang_nan:
			rentingSpinner4.setText("南");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("南");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_chaoxiang_xi:
			rentingSpinner4.setText("西");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("西");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_chaoxiang_bei:
			rentingSpinner4.setText("北");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("北");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_chaoxiang_nanbei:
			rentingSpinner4.setText("南北");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("南北");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
			
		case R.id.renting_mianji_ershi:
			rentingSpinner4.setText("200平以上");
			lastrentings.clear();
			fourdata.setMj("200平以上");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_mianji_jiu_shiyi:
			rentingSpinner4.setText("90-110平");
			lastrentings.clear();
			fourdata.setMj("90-110平");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_mianji_qi_jiu:
			rentingSpinner4.setText("70-90平");
			lastrentings.clear();
			fourdata.setMj("70-90平");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_mianji_shiqi_ershi:
			rentingSpinner4.setText("170-200平");
			lastrentings.clear();
			fourdata.setMj("170-200平");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_mianji_shisi_shiqi:
			rentingSpinner4.setText("140-170平");
			lastrentings.clear();
			fourdata.setMj("140-170平");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
			
		case R.id.renting_mianji_shiyi_shisi:
			rentingSpinner4.setText("110-140平");
			lastrentings.clear();
			fourdata.setMj("110-140平");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_mianji_wu_qi:
			rentingSpinner4.setText("50-70平");
			lastrentings.clear();
			fourdata.setMj("50-70平");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_mianji_wushi:
			rentingSpinner4.setText("50平以下");
			lastrentings.clear();
			fourdata.setMj("50平以下");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.renting_biaoqian_jingzhuangxiu:
			rentingSpinner4.setText("精装");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("精装");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_biaoqian_suishikanfang:
			rentingSpinner4.setText("随时看房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("随时看房");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		case R.id.renting_louceng_low:
			rentingSpinner4.setText("低层");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("低层");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_louceng_center:
			rentingSpinner4.setText("中层");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("中层");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_louceng_high:
			rentingSpinner4.setText("高层");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("高层");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
			
		case R.id.renting_wuye_cunweitongjian:
			rentingSpinner4.setText("村委统建");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("村委统建");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		case R.id.renting_wuye_gerenzijianshe:
			rentingSpinner4.setText("个人自建房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("个人自建房");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_gongyechangzufang:
			rentingSpinner4.setText("工业长租房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("工业长租房");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_gongyechanquanfang:
			rentingSpinner4.setText("工业产权房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("工业产权房");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_junquchanfang:
			rentingSpinner4.setText("广东省军区军产房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("广东省军区军产房");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_kaifangshangjianshe:
			rentingSpinner4.setText("开发商建设");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("开发商建设");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_qita:
			rentingSpinner4.setText("其他");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("其他");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_shangpinfang:
			rentingSpinner4.setText("商品房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("商品房");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_wuye_wujingchanfang:
			rentingSpinner4.setText("武警部队军产房");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("武警部队军产房");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		case R.id.renting_fangshi_zhengzu:
			rentingSpinner4.setText("整租");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("整租");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_fangshi_hezu:
			rentingSpinner4.setText("合租");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("合租");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		case R.id.renting_type_gongyu:
			rentingSpinner4.setText("公寓");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("公寓");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;		
		case R.id.renting_type_shangpu:
			rentingSpinner4.setText("商铺");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("商铺");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;		
		case R.id.renting_type_xiezilou:
			rentingSpinner4.setText("写字楼");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("写字楼");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.renting_type_zuzhai:
			rentingSpinner4.setText("住宅");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("住宅");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		case R.id.renting_spinner_cancle:
			rentingSpinner4.setText("更多");
			lastrentings.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setXq("");
			fourdata.setCx("");
			fourdata.setLc("");
			fourdata.setZl("");
			fourdata.setZx("");
			fourdata.setKf("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		default:
			break;
		}
		
	}
	
	@Override
	public void showlistFail(String info) {
		lastrentings.clear();
		adapter.notifyDataSetChanged();
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}


	

}
