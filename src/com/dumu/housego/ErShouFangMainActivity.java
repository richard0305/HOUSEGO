package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.baidu.mapapi.map.Text;
import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.ErShouFangProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ErShouFangProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IAddressView;
import com.dumu.housego.view.IErShouFangRecommendView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ErShouFangMainActivity extends Activity implements View.OnClickListener,IErShouFangRecommendView, OnRefreshListener2<ListView> {
	private LinearLayout llErshoufang;
	private ErShouFangRecommendAdapter adapter;
	private IFourDataProgramePresenter presenter;
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private List<ErShouFangRecommendData> lastershous = new ArrayList<ErShouFangRecommendData>();
	private FourDataPrograma fourdata;
	private TextView ershoufangQuyuSp1;
	private TextView ershoufangQuyuSp2;
	private TextView ershoufangQuyuSp3;
	private TextView ershoufangQuyuSp4;
	
	private SimpleAdapter adapter1;
	private ArrayAdapter<String>Spinneradapter;
	
	
	private ErShouFangProgramaModel model = new ErShouFangProgramaModel();

	private List<String> spinnerList1 = new ArrayList<String>();
	private List<String> spinnerList2 = new ArrayList<String>();
	private List<String> spinnerList3 = new ArrayList<String>();
	private List<String> spinnerList4 = new ArrayList<String>();
	public  SpinnerAdapter spinneradapter;
	public  SpinnerAdapter spinneradapter1;
	private PullToRefreshListView refreshListview;
	private boolean isFirstIn = true;
	private int page = 1;
	private PopupWindow pop;
	private LinearLayout ll_popup_spinner,ll_ershoufang_spinner;
	private PopupWindow Tpop;
	private LinearLayout llpopupSpinner2;
	private PopupWindow QuYupop;
	private LinearLayout llpopupSpinnerQuyu;
	private List<String>SpinnerQuYu=new ArrayList<String>();
	private PopupWindow Mpop;
	private LinearLayout llpopupSpinnerMore;
	private AddressModel addmodel = new AddressModel();
	
	private String city="2";
	private String area;
	private List<Address> minarea;
	private List<String>MinArea=new ArrayList<String>();
	
	private int posi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_er_shou_fang_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		SpinnerQuYu=SpinnerData.Area;
		setViews();
		 showPopWindow();
		 showPopWindow2();
		 showPopWindowQuyu();
		 showPopWindowMore();
		setListener();


		presenter = new ErShouFangProgramaPresenter(this);

		fourdata = new FourDataPrograma();
		
		fourdata.setCatid("6");
		fourdata.setPage("1");
		fourdata.setCt("");
		fourdata.setAr("");
		fourdata.setDt("");
		fourdata.setZj("");
		fourdata.setShi("");
		fourdata.setMj("");
		fourdata.setQs("");
		fourdata.setLy("");
		
		fourdata.setYt("");
		fourdata.setZx("");
		fourdata.setLc("");
		fourdata.setCx("");
		fourdata.setWy("");
		fourdata.setKwds("");
		
		presenter.LoadProgrameData(fourdata);
	}

	private void setListener() {
		llErshoufang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ErShouFangMainActivity.this.finish();

			}
		});

		refreshListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				//
				Intent i = new Intent(ErShouFangMainActivity.this, ErShouFangDetailsActivity.class);
				String catid = lastershous.get(position-1).getCatid();
				String ID = lastershous.get(position-1).getId();
				i.putExtra("catid", catid);
				i.putExtra("id", ID);
				startActivity(i);
				
			}
		});
		
		ershoufangQuyuSp3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);

				ll_popup_spinner.setAnimation(anim);
				pop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
				
			}
		});
		
		ershoufangQuyuSp2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);

				llpopupSpinner2.setAnimation(anim);
				Tpop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
			}
		});
		
	ershoufangQuyuSp1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);

				llpopupSpinnerQuyu.
				setAnimation(anim);
				QuYupop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
			}
		});
	
	ershoufangQuyuSp4.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);

			llpopupSpinnerMore.
			setAnimation(anim);
			Mpop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
		}
	});
		
	}

	private void setViews() {
		// 刷新
		refreshListview = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		refreshListview.setMode(PullToRefreshBase.Mode.BOTH);
		refreshListview.setOnRefreshListener(this);
		// 设置刷新声音
		// SoundPullEventListener<ListView> soundListener=new
		// SoundPullEventListener<ListView>(this);
		// soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.);
		//
		//
		llErshoufang = (LinearLayout) findViewById(R.id.ll_ershoufang_back);
		ershoufangQuyuSp1 = (TextView) findViewById(R.id.ershoufang_quyu_sp1);
		ershoufangQuyuSp2 = (TextView) findViewById(R.id.ershoufang_quyu_sp2);
		ershoufangQuyuSp3 = (TextView) findViewById(R.id.ershoufang_quyu_sp3);
		ershoufangQuyuSp4 = (TextView) findViewById(R.id.ershoufang_quyu_sp4);
		ll_ershoufang_spinner=(LinearLayout) findViewById(R.id.ll_ershoufang_spinner);
	}

	@Override
	public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends = ershoufangrecommends;

		this.lastershous.addAll(ershoufangrecommends);
		Log.e("2016-10-10", "2016-10-10" + lastershous);
		// if(page==1){
		adapter = new ErShouFangRecommendAdapter(lastershous, getApplicationContext());
		refreshListview.setAdapter(adapter);

	}
	
	
	
	
	
	
	
	/**
	 * 刷新
	 */

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		this.lastershous.clear();
		page = 1;
		fourdata.setCatid("6");
		fourdata.setPage(page + "");
		presenter.LoadProgrameData(fourdata);

		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;

				// 将数据追加到集合中
				lastershous.clear();
				lastershous.addAll(ershous);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				refreshListview.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastershous.size() + "个房源！");
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				refreshListview.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;
				if (ershous!=null) {
					lastershous.addAll(ershous);
					// 刷新界面
					adapter.notifyDataSetChanged();
					// 关闭上拉加载刷新布局
					refreshListview.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastershous.size() + "个房源！");
				}else{
					refreshListview.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				}
				
			
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				refreshListview.onRefreshComplete();

			}
		});

	}
	
	/**
	 * spinner
	 */
	private void showPopWindow() {
		pop = new PopupWindow(ErShouFangMainActivity.this);

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
					ershoufangQuyuSp3.setText("房型");
					fourdata.setShi("");
					lastershous.clear();
					presenter.LoadProgrameData(fourdata);
				}else if(position==6){
					ershoufangQuyuSp3.setText("5室以上");
					fourdata.setShi("5-");
					lastershous.clear();
					presenter.LoadProgrameData(fourdata);
				}else{
					String shi=SpinnerData.HouseType.get(position).split("室")[0];
					
					ershoufangQuyuSp3.setText(SpinnerData.HouseType.get(position));
					fourdata.setShi(shi);
					lastershous.clear();
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
	private void showPopWindow2() {
		Tpop = new PopupWindow(ErShouFangMainActivity.this);

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
					lastershous.clear();
					presenter.LoadProgrameData(fourdata);
					Tpop.dismiss();
					llpopupSpinner2.clearAnimation();
				}else if(!low.equals("")&&high.equals("")){
					fourdata.setZj(low+"-");
					lastershous.clear();
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
						lastershous.clear();
						presenter.LoadProgrameData(fourdata);
						Tpop.dismiss();
						llpopupSpinner2.clearAnimation();
					}else{
						fourdata.setZj(low+"-"+high);
						lastershous.clear();
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
		
		
		spinneradapter=new SpinnerAdapter(SpinnerData.Price, getApplicationContext());
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
					ershoufangQuyuSp2.setText("价格");
					fourdata.setZj("");
					lastershous.clear();
					presenter.LoadProgrameData(fourdata);
				}else if(position==5){
					ershoufangQuyuSp2.setText("400万以上");
					fourdata.setZj("400-");
					lastershous.clear();
					presenter.LoadProgrameData(fourdata);
				}
				else{
				
					String price=SpinnerData.Price.get(position).split("万")[0];
					ershoufangQuyuSp2.setText(SpinnerData.Price.get(position));
					fourdata.setZj(price);
					Log.e("````````````````````````", "price="+price);
					lastershous.clear();
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
		QuYupop = new PopupWindow(ErShouFangMainActivity.this);

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
						ershoufangQuyuSp1.setText("区域");
						fourdata.setCt("");
						fourdata.setAr("");
						fourdata.setDt("");
						lastershous.clear();
						presenter.LoadProgrameData(fourdata);
						QuYupop.dismiss();
						llpopupSpinnerQuyu.clearAnimation();
					}else{
						ershoufangQuyuSp1.setText(SpinnerQuYu.get(position));
						String dt=SpinnerQuYu.get(position).split("号")[0];
						fourdata.setCt("");
						fourdata.setAr("");
						fourdata.setDt(dt);
						lastershous.clear();
						presenter.LoadProgrameData(fourdata);
						QuYupop.dismiss();
						llpopupSpinnerQuyu.clearAnimation();
					}
				}else{
				
					switch (position) {
					case 0:
						ershoufangQuyuSp1.setText("区域");
						fourdata.setCt("");
						fourdata.setAr("");
						fourdata.setDt("");
						lastershous.clear();
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
								ErShouFangMainActivity.this.MinArea.add(ad);
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
					ershoufangQuyuSp1.setText(SpinnerData.Area.get(posi));
					fourdata.setCt(city);
					fourdata.setAr("");
					fourdata.setDt("");
				}else{
				String pid=minarea.get(position).getPid();
				ershoufangQuyuSp1.setText(minarea.get(position).getName());
				fourdata.setCt(city);
				fourdata.setAr(pid);
				fourdata.setDt("");
				}
				lastershous.clear();
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
		Mpop = new PopupWindow(ErShouFangMainActivity.this);
		View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner4, null);
		llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
		Mpop.setWidth(LayoutParams.MATCH_PARENT);
		Mpop.setHeight(LayoutParams.MATCH_PARENT);
		Mpop.setBackgroundDrawable(new BitmapDrawable());
		Mpop.setFocusable(true);
		Mpop.setOutsideTouchable(true);
		Mpop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
		view.findViewById(R.id.ershou_chaoxiang_dong).setOnClickListener(this);
		view.findViewById(R.id.ershou_chaoxiang_nan).setOnClickListener(this);
		view.findViewById(R.id.ershou_chaoxiang_xi).setOnClickListener(this);
		view.findViewById(R.id.ershou_chaoxiang_bei).setOnClickListener(this);
		view.findViewById(R.id.ershou_chaoxiang_nanbei).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_wushi).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_wu_qi).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_qi_jiu).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_jiu_shiyi).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_shiyi_shisi).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_shisi_shiqi).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_shiqi_ershi).setOnClickListener(this);
		view.findViewById(R.id.ershou_mianji_ershi).setOnClickListener(this);
		view.findViewById(R.id.ershou_biaoqian_jing).setOnClickListener(this);
		view.findViewById(R.id.ershou_biaoqian_weiyi).setOnClickListener(this);
		view.findViewById(R.id.ershou_louceng_low).setOnClickListener(this);
		view.findViewById(R.id.ershou_louceng_center).setOnClickListener(this);
		view.findViewById(R.id.ershou_louceng_high).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_shangpinfang).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_cunweitongjian).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_kaifangshangjianshe).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_gerenzijianshe).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_junquchanfang).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_wujingchanfang).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_gongyechangzufang).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_gongyechanquanfang).setOnClickListener(this);
		view.findViewById(R.id.ershou_wuye_qita).setOnClickListener(this);
		view.findViewById(R.id.ershou_from_yezhu).setOnClickListener(this);
		view.findViewById(R.id.ershou_from_agent).setOnClickListener(this);
		view.findViewById(R.id.ershou_type_zuzhai).setOnClickListener(this);
		view.findViewById(R.id.ershou_type_gongyu).setOnClickListener(this);
		view.findViewById(R.id.ershou_type_shangpu).setOnClickListener(this);
		view.findViewById(R.id.ershou_type_xiezilou).setOnClickListener(this);
		view.findViewById(R.id.ershou_spinner_cancle).setOnClickListener(this);
		
		
	}
	
	

	
	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.parent_spinner_more:
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_chaoxiang_dong:
			ershoufangQuyuSp4.setText("东");
			lastershous.clear();
			fourdata.setCx("东");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_chaoxiang_nan:
			ershoufangQuyuSp4.setText("南");
			lastershous.clear();
			fourdata.setCx("南");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_chaoxiang_xi:
			ershoufangQuyuSp4.setText("西");
			lastershous.clear();
			fourdata.setCx("西");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_chaoxiang_bei:
			ershoufangQuyuSp4.setText("北");
			lastershous.clear();
			fourdata.setCx("北");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_chaoxiang_nanbei:
			ershoufangQuyuSp4.setText("南北");
			lastershous.clear();
			fourdata.setCx("南北");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_wushi:
			ershoufangQuyuSp4.setText("50平以下");
			lastershous.clear();
			fourdata.setMj("50");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_wu_qi:
			ershoufangQuyuSp4.setText("50-70平");
			lastershous.clear();
			fourdata.setMj("50-70");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_mianji_qi_jiu:
			ershoufangQuyuSp4.setText("70-90平");
			lastershous.clear();
			fourdata.setMj("70-90");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_jiu_shiyi:
			ershoufangQuyuSp4.setText("90-110平");
			lastershous.clear();
			fourdata.setMj("90-110");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_shiyi_shisi:
			ershoufangQuyuSp4.setText("110-140平");
			lastershous.clear();
			fourdata.setMj("110-140");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_shisi_shiqi:
			ershoufangQuyuSp4.setText("140-170平");
			lastershous.clear();
			fourdata.setMj("140-170");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_shiqi_ershi:
			ershoufangQuyuSp4.setText("170-200平");
			lastershous.clear();
			fourdata.setMj("170-200");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_mianji_ershi:
			ershoufangQuyuSp4.setText("200平以上");
			lastershous.clear();
			fourdata.setMj("200-");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_biaoqian_jing:
			ershoufangQuyuSp4.setText("精装修");
			lastershous.clear();
			fourdata.setZx("精装");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_biaoqian_weiyi:
			ershoufangQuyuSp4.setText("唯一住房");
			lastershous.clear();
			fourdata.setWy("是");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
			
		case R.id.ershou_louceng_low:
			ershoufangQuyuSp4.setText("低层");
			lastershous.clear();
			fourdata.setLc("低层");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_louceng_center:
			ershoufangQuyuSp4.setText("中层");
			lastershous.clear();
			fourdata.setLc("中层");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_louceng_high:
			ershoufangQuyuSp4.setText("高层");
			lastershous.clear();
			fourdata.setLc("高层");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_wuye_shangpinfang:
			ershoufangQuyuSp4.setText("商品房");
			lastershous.clear();
			fourdata.setQs("商品房");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_cunweitongjian:
			ershoufangQuyuSp4.setText("村委统建");
			lastershous.clear();
			fourdata.setQs("村委统建");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.ershou_wuye_kaifangshangjianshe:
			ershoufangQuyuSp4.setText("开发商建设");
			lastershous.clear();
			fourdata.setQs("开发商建设");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_gerenzijianshe:
			ershoufangQuyuSp4.setText("个人自建房");
			lastershous.clear();
			fourdata.setQs("个人自建房");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_junquchanfang:
			ershoufangQuyuSp4.setText("广东省军区军产房");
			lastershous.clear();
			fourdata.setQs("广东省军区军产房");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_wujingchanfang:
			ershoufangQuyuSp4.setText("武警部队军产房");
			lastershous.clear();
			fourdata.setQs("武警部队军产房");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_gongyechangzufang:
			ershoufangQuyuSp4.setText("工业长租房");
			lastershous.clear();
			fourdata.setQs("工业长租房");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_gongyechanquanfang:
			ershoufangQuyuSp4.setText("工业产权房");
			lastershous.clear();
			fourdata.setQs("工业产权房");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_wuye_qita:
			ershoufangQuyuSp4.setText("其他");
			lastershous.clear();
			fourdata.setQs("其他");
			fourdata.setMj("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_from_yezhu:
			ershoufangQuyuSp4.setText("业主");
			lastershous.clear();
			fourdata.setLy("业主");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;		
		case R.id.ershou_from_agent:
			ershoufangQuyuSp4.setText("经纪人");
			lastershous.clear();
			fourdata.setLy("经纪人");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_type_zuzhai:
			ershoufangQuyuSp4.setText("住宅");
			lastershous.clear();
			fourdata.setYt("住宅");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;		
		case R.id.ershou_type_gongyu:
			ershoufangQuyuSp4.setText("公寓");
			lastershous.clear();
			fourdata.setYt("公寓");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_type_shangpu:
			ershoufangQuyuSp4.setText("商铺");
			lastershous.clear();
			fourdata.setYt("商铺");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
		case R.id.ershou_type_xiezilou:
			ershoufangQuyuSp4.setText("写字楼");
			lastershous.clear();
			fourdata.setYt("写字楼");
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		case R.id.ershou_spinner_cancle:
			ershoufangQuyuSp4.setText("更多");
			lastershous.clear();
			fourdata.setMj("");
			fourdata.setQs("");
			fourdata.setLy("");
			fourdata.setYt("");
			fourdata.setZx("");
			fourdata.setLc("");
			fourdata.setCx("");
			fourdata.setWy("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;	
			
		default:
			break;
		}
		
	}
	
	
	
	/**
	 * listview adapter
	 * @author Administrator
	 *
	 */
	public static class SpinnerAdapter extends BaseAdapter {
		
		private List<String> comments;
		private Context context;
		private LayoutInflater Inflater;
		
		
		
		public SpinnerAdapter(List<String> comments, Context context) {
			super();
			this.comments = comments;
			this.context = context;
			this.Inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			
			return comments.size();
		}

		@Override
		public String getItem(int position) {
			return comments.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = Inflater.inflate(R.layout.item_spinner_show, null);
				holder = new ViewHolder();
				holder.tvSpinnershow=(TextView) convertView.findViewById(R.id.tv_spinner_show);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			String str=comments.get(position);
			holder.tvSpinnershow.setText(str);
			return convertView;
		}
		
		class ViewHolder {
			TextView tvSpinnershow;
		}
		
	}



	@Override
	public void showDatafail(String info) {
		lastershous.clear();
		adapter.notifyDataSetChanged();
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}
	
}
