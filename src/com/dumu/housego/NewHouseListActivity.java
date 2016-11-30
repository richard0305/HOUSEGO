package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.adapter.NewHouseListAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.NewHouseListModel;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.NewHouseListPresenter;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.INewHouseListView;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewHouseListActivity extends Activity implements View.OnClickListener,INewHouseListView, OnRefreshListener2<ListView> {
	private LinearLayout llNewhouselistBack;
	private List<NewHouseList> newhouselists;
	private NewHouseListAdapter listadapter;
	private IFourDataProgramePresenter presenter;
	private FourDataPrograma fourdata;
	private PullToRefreshListView lv_newhouselist;
	private List<NewHouseList> lastnews = new ArrayList<NewHouseList>();
	int page = 1;
	private AddressModel addmodel = new AddressModel();
	private NewHouseListModel model = new NewHouseListModel();
	private PopupWindow QuYupop;
	private LinearLayout llpopupSpinnerQuyu;
	private SpinnerAdapter spinneradapter1;
	private String city;
	private List<Address> minarea;
	private List<String>MinArea=new ArrayList<String>();
	private TextView newhousespinner1,newhousespinner2,newhousespinner3,newhousespinner4;
	protected int posi;
	private PopupWindow Tpop;
	private LinearLayout llpopupSpinner2;
	private SpinnerAdapter spinneradapter;
	protected LinearLayout ll_newhouse_spinner;
	private PopupWindow pop;
	private LinearLayout ll_popup_spinner;
	private PopupWindow Mpop;
	private LinearLayout llpopupSpinnerMore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_list);
		setViews();
		showPopWindowPrice();
		showPopWindowQuyu();
		showPopWindowType() ;
		 showPopWindowMore();
		setListener();
		presenter = new NewHouseListPresenter(this);
		fourdata = new FourDataPrograma();
		fourdata.setCatid("3");
		fourdata.setPage("1");
		fourdata.setCt("");
		fourdata.setAr("");
		fourdata.setZj("");
		fourdata.setShi("");
		fourdata.setZx("");
		fourdata.setYt("");
		fourdata.setWy("");
		fourdata.setXq("");
		fourdata.setKwds("");
		presenter.LoadProgrameData(fourdata);

	}

	private void setViews() {
		llNewhouselistBack = (LinearLayout) findViewById(R.id.ll_newhouselist_back);
		lv_newhouselist = (PullToRefreshListView) findViewById(R.id.lv_newhouselist);
		lv_newhouselist.setMode(PullToRefreshBase.Mode.BOTH);
		lv_newhouselist.setOnRefreshListener(this);
		
		newhousespinner1=(TextView) findViewById(R.id.newhouse_quyu_sp1);
		newhousespinner2=(TextView) findViewById(R.id.newhouse_quyu_sp2);
		newhousespinner3=(TextView) findViewById(R.id.newhouse_quyu_sp3);
		newhousespinner4=(TextView) findViewById(R.id.newhouse_quyu_sp4);
		
		ll_newhouse_spinner=(LinearLayout) findViewById(R.id.ll_newhouse_spinner);
	}

	private void setListener() {
		llNewhouselistBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		lv_newhouselist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NewHouseList n = lastnews.get(position-1);
				Intent i = new Intent(getApplicationContext(), NewHouseDetailActivity.class);
				String Id = n.getId();
				String catid = n.getCatid();
				i.putExtra("Id", Id);
				i.putExtra("catid", catid);
				startActivity(i);
			}
		});
		
		newhousespinner1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
				llpopupSpinnerQuyu.
				setAnimation(anim);
				QuYupop.showAsDropDown(ll_newhouse_spinner, 0, 0);
			}
		});
		newhousespinner2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
				llpopupSpinner2.
				setAnimation(anim);
				Tpop.showAsDropDown(ll_newhouse_spinner, 0, 0);
			}
		});
		
	newhousespinner3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
				ll_popup_spinner.
				setAnimation(anim);
				pop.showAsDropDown(ll_newhouse_spinner, 0, 0);
			}
		});
		newhousespinner4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
				llpopupSpinnerMore.setAnimation(anim);;
				Mpop.showAsDropDown(ll_newhouse_spinner, 0, 0);
			}
		});
	}

	@Override
	public void showNewHouseList(List<NewHouseList> newhouselists) {
		this.newhouselists = newhouselists;
		this.lastnews.addAll(newhouselists);
		listadapter = new NewHouseListAdapter(lastnews, getApplicationContext());
		lv_newhouselist.setAdapter(listadapter);

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		fourdata.setCatid("3");
		fourdata.setPage(page + "");

		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<NewHouseList> news = (List<NewHouseList>) success;

				// 将数据追加到集合中
				lastnews.clear();
				lastnews.addAll(news);
				// 刷新界面
				listadapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lv_newhouselist.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastnews.size() + "个房源！");

			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lv_newhouselist.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setCatid("3");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<NewHouseList> news = (List<NewHouseList>) success;
				if (news==null) {
					lv_newhouselist.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				}else{
					// 将数据追加到集合中
					lastnews.addAll(news);
					// 刷新界面
					listadapter.notifyDataSetChanged();
					// 关闭上拉加载刷新布局
					lv_newhouselist.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastnews.size() + "个房源！");
				}
			
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lv_newhouselist.onRefreshComplete();

			}
		});
	}
	
	
	//区域
	private void showPopWindowQuyu() {
		QuYupop = new PopupWindow(NewHouseListActivity.this);
		View view = getLayoutInflater().inflate(R.layout.item_newhouse_spinner1, null);
		llpopupSpinnerQuyu = (LinearLayout) view.findViewById(R.id.ll_popup_spinner);
		QuYupop.setWidth(LayoutParams.MATCH_PARENT);
		QuYupop.setHeight(LayoutParams.MATCH_PARENT);
		QuYupop.setBackgroundDrawable(new BitmapDrawable());
		QuYupop.setFocusable(true);
		QuYupop.setOutsideTouchable(true);
		QuYupop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner);
		final ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list);
		final ListView lvspinner2 = (ListView) view.findViewById(R.id.lv_popwin_list2);
		
		spinneradapter1=new SpinnerAdapter(SpinnerData.Area, getApplicationContext());
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
				switch (position) {
				case 0:
					newhousespinner1.setText("区域");
					fourdata.setCt("");
					fourdata.setAr("");
					lastnews.clear();
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
					posi=6;
					city="8";
					MinArea.clear();
					break;
				case 7:
					posi=7;
					city="9";
					MinArea.clear();
					break;
				case 8:
					posi=8;
					city="10";
					MinArea.clear();
					break;
				case 9:
					posi=9;
					city="11";
					MinArea.clear();
					break;
				case 10:
					posi=10;
					city="12";
					MinArea.clear();
					break;
				case 11:
					posi=11;
					city="13";
					MinArea.clear();
					break;
				case 12:
					posi=12;
					city="14";
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
							NewHouseListActivity.this.MinArea.add(ad);
						}
						spinneradapter1=new SpinnerAdapter(MinArea, getApplicationContext());
						lvspinner2.setAdapter(spinneradapter1);
					}
					@Override
					public void onError(Object error) {
					}
				});
				
			}
		});
		
		lvspinner2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0){
					newhousespinner1.setText(SpinnerData.Area.get(posi));
					fourdata.setCt(city);
					fourdata.setAr("");
					fourdata.setDt("");
				}else{
				String pid=minarea.get(position).getPid();
				newhousespinner1.setText(minarea.get(position).getName());
				fourdata.setCt(city);
				fourdata.setAr(pid);
				fourdata.setDt("");
				lastnews.clear();
				presenter.LoadProgrameData(fourdata);
				QuYupop.dismiss();
				llpopupSpinnerQuyu.clearAnimation();
				}
			}
		});
	}
	
	/**
	 * spinner 价格
	 */
	private void showPopWindowPrice() {
		Tpop = new PopupWindow(NewHouseListActivity.this);

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
					lastnews.clear();
					presenter.LoadProgrameData(fourdata);
					Tpop.dismiss();
					llpopupSpinner2.clearAnimation();
				}else if(!low.equals("")&&high.equals("")){
					fourdata.setZj(low+"-");
					lastnews.clear();
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
						lastnews.clear();
						presenter.LoadProgrameData(fourdata);
						Tpop.dismiss();
						llpopupSpinner2.clearAnimation();
					}else{
						fourdata.setZj(low+"-"+high);
						lastnews.clear();
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
		
		
		spinneradapter=new SpinnerAdapter(SpinnerData.NewPrice, getApplicationContext());
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
					newhousespinner2.setText("价格");
					fourdata.setZj("");
					lastnews.clear();
					presenter.LoadProgrameData(fourdata);
				}else if(position==7){
					newhousespinner2.setText("300万以上");
					fourdata.setZj("300-");
					lastnews.clear();
					presenter.LoadProgrameData(fourdata);
				}
				else{
				
					String price=SpinnerData.Price.get(position).split("万")[0];
					newhousespinner2.setText(SpinnerData.Price.get(position));
					fourdata.setZj(price);
					Log.e("````````````````````````", "price="+price);
					lastnews.clear();
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
	private void showPopWindowType() {
		pop = new PopupWindow(NewHouseListActivity.this);

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
					newhousespinner3.setText("房型");
					fourdata.setShi("");
					lastnews.clear();
					presenter.LoadProgrameData(fourdata);
				}else if(position==6){
					newhousespinner3.setText("5室以上");
					fourdata.setShi("5-");
					lastnews.clear();
					presenter.LoadProgrameData(fourdata);
				}else{
					String shi=SpinnerData.HouseType.get(position).split("室")[0];
					
					newhousespinner3.setText(SpinnerData.HouseType.get(position));
					fourdata.setShi(shi);
					lastnews.clear();
					presenter.LoadProgrameData(fourdata);
				}
				pop.dismiss();
				ll_popup_spinner.clearAnimation();
			}
		});
		
	}
	//
	private void showPopWindowMore() {
		Mpop = new PopupWindow(NewHouseListActivity.this);
		View view = getLayoutInflater().inflate(R.layout.item_newhouse_spinner4, null);
		llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
		Mpop.setWidth(LayoutParams.MATCH_PARENT);
		Mpop.setHeight(LayoutParams.MATCH_PARENT);
		Mpop.setBackgroundDrawable(new BitmapDrawable());
		Mpop.setFocusable(true);
		Mpop.setOutsideTouchable(true);
		Mpop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
		view.findViewById(R.id.newhouse_zx_jing).setOnClickListener(this);
		view.findViewById(R.id.newhouse_zx_jian).setOnClickListener(this);
		view.findViewById(R.id.newhouse_zx_mao).setOnClickListener(this);
		view.findViewById(R.id.newhouse_type_bangong).setOnClickListener(this);
		view.findViewById(R.id.newhouse_type_changfang).setOnClickListener(this);
		view.findViewById(R.id.newhouse_type_juzhu).setOnClickListener(this);
		view.findViewById(R.id.newhouse_type_shangye).setOnClickListener(this);
		view.findViewById(R.id.newhouse_type_shangzhu).setOnClickListener(this);
		view.findViewById(R.id.newhouse_type_zomngheti).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_cunwei).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_geren).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_gongyechangzu).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_gongyechanquan).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_junjuchanfang).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_kaifangshang).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_qita).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_shangpin).setOnClickListener(this);
		view.findViewById(R.id.newhouse_sx_wujingchanfang).setOnClickListener(this);
		view.findViewById(R.id.newhouse_xq_dudong).setOnClickListener(this);
		view.findViewById(R.id.newhouse_xq_xiaoqu).setOnClickListener(this);
		view.findViewById(R.id.newhouse_all_cancle).setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.parent_spinner_more:
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_zx_jing:
			newhousespinner4.setText("精装");
			lastnews.clear();
			fourdata.setZx("精装");
			fourdata.setYt("");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_zx_jian:
			newhousespinner4.setText("简装");
			lastnews.clear();
			fourdata.setZx("简装");
			fourdata.setYt("");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_zx_mao:
			newhousespinner4.setText("毛坯");
			lastnews.clear();
			fourdata.setZx("毛坯");
			fourdata.setYt("");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_type_bangong:
			newhousespinner4.setText("办公");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("办公");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_type_changfang:
			newhousespinner4.setText("厂房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("厂房");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_type_juzhu:
			newhousespinner4.setText("居住");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("居住");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_type_shangye:
			newhousespinner4.setText("商业");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("商业");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_type_shangzhu:
			newhousespinner4.setText("商住两用");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("商住两用");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_type_zomngheti:
			newhousespinner4.setText("综合体");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("综合体");
			fourdata.setWy("");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_cunwei:
			newhousespinner4.setText("村委统建");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("村委统建");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_geren:
			newhousespinner4.setText("个人自建房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("个人自建房");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_gongyechangzu:
			newhousespinner4.setText("工业长租房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("工业长租房");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_gongyechanquan:
			newhousespinner4.setText("工业产权房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("工业产权房");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_junjuchanfang:
			newhousespinner4.setText("广东省军区军产房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("广东省军区军产房");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_kaifangshang:
			newhousespinner4.setText("开发商建设");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("开发商建设");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_qita:
			newhousespinner4.setText("其他");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("其他");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_shangpin:
			newhousespinner4.setText("商品房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("商品房");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.newhouse_sx_wujingchanfang:
			newhousespinner4.setText("武警部队军产房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("武警部队军产房");
			fourdata.setXq("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
			
		case R.id.newhouse_xq_dudong:
			newhousespinner4.setText("独栋");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("");
			fourdata.setXq("独栋");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case  R.id.newhouse_xq_xiaoqu:
			newhousespinner4.setText("小区房");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("");
			fourdata.setXq("小区房");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case  R.id.newhouse_all_cancle:
			newhousespinner4.setText("更多");
			lastnews.clear();
			fourdata.setZx("");
			fourdata.setYt("");
			fourdata.setWy("");
			fourdata.setXq("");
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
		lastnews.clear();
		listadapter.notifyDataSetChanged();
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}
	
	
	

}
