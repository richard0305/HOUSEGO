package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.adapter.BlockTradeLsitAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.BlockTradeProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.BlockTradeProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IBlockTradeProgramaView;
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

public class BlockTradeMainActivity extends Activity
		implements View.OnClickListener, IBlockTradeProgramaView, OnRefreshListener2<ListView> {
	private LinearLayout llBlockTradeBack;
	private PullToRefreshListView lvBlockTrade;
	private IFourDataProgramePresenter presenter;
	private BlockTradeLsitAdapter adapter;
	private List<BlockTradeList> blocktrades;
	private List<BlockTradeList> lastblocks = new ArrayList<BlockTradeList>();
	private FourDataPrograma fourdata;
	private BlockTradeProgramaModel model = new BlockTradeProgramaModel();
	private AddressModel addmodel = new AddressModel();
	int page = 1;

	private TextView blocktrade_quyu_sp1, blocktrade_quyu_sp2, blocktrade_quyu_sp3, blocktrade_quyu_sp4;
	private LinearLayout ll_blocktrade_spinner;
	
	private LinearLayout llpopupSpinnerQuyu;
	private SpinnerAdapter spinneradapter1;

	private String city;
	private List<Address> minarea;
	private List<String> MinArea = new ArrayList<String>();
	protected int posi;
	private SpinnerAdapter spinneradapter;

	private LinearLayout llpopupSpinner2;

	private LinearLayout ll_popup_spinner;
	private PopupWindow Tpop;
	private PopupWindow Mpop;
	private PopupWindow pop;
	private PopupWindow QuYupop;
	private LinearLayout llpopupSpinnerMore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		showPopWindowMianji();
		showPopWindowMore();
		showPopWindowPrice();
		showPopWindowQuyu();
		setListener();
		presenter = new BlockTradeProgramaPresenter(this);
		fourdata = new FourDataPrograma();
		fourdata.setCatid("7");
		fourdata.setPage("1");
		fourdata.setAr("");
		fourdata.setCt("");
		fourdata.setZj("");
		fourdata.setMj("");

		fourdata.setSx("");
		fourdata.setLx("");
		fourdata.setFs("");
		fourdata.setKwds("");
		presenter.LoadProgrameData(fourdata);

	}

	private void setViews() {
		llBlockTradeBack = (LinearLayout) findViewById(R.id.ll_block_trade_back);
		lvBlockTrade = (PullToRefreshListView) findViewById(R.id.lv_blocktrade);
		lvBlockTrade.setMode(PullToRefreshBase.Mode.BOTH);
		lvBlockTrade.setOnRefreshListener(this);

		blocktrade_quyu_sp4 = (TextView) findViewById(R.id.blocktrade_quyu_sp4);
		blocktrade_quyu_sp3 = (TextView) findViewById(R.id.blocktrade_quyu_sp3);
		blocktrade_quyu_sp1 = (TextView) findViewById(R.id.blocktrade_quyu_sp1);
		blocktrade_quyu_sp2 = (TextView) findViewById(R.id.blocktrade_quyu_sp2);
		ll_blocktrade_spinner = (LinearLayout) findViewById(R.id.ll_blocktrade_spinner);
	}

	private void setListener() {
		llBlockTradeBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		lvBlockTrade.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

				Intent i = new Intent(BlockTradeMainActivity.this, BlockTradeDetailActivity.class);
				String Id = lastblocks.get(position - 1).getId();
				String catid = lastblocks.get(position - 1).getCatid();
				String posid = lastblocks.get(position - 1).getPosid();
				i.putExtra("id", Id);
				i.putExtra("catid", catid);
				i.putExtra("posid", posid);
				startActivity(i);
			}
		});

		blocktrade_quyu_sp1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Tpop.dismiss();
				Mpop.dismiss();
				 pop.dismiss();
				Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
						R.anim.activity_translate_out);
				llpopupSpinnerQuyu.setAnimation(anim);
				QuYupop.showAsDropDown(ll_blocktrade_spinner, 0, 0);

			}
		});

		blocktrade_quyu_sp2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					Mpop.dismiss();
					 pop.dismiss();
					 QuYupop.dismiss();
				Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
						R.anim.activity_translate_out);

				llpopupSpinner2.setAnimation(anim);
				Tpop.showAsDropDown(ll_blocktrade_spinner, 0, 0);

			}
		});

		blocktrade_quyu_sp3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Tpop.dismiss();
					Mpop.dismiss();
					 QuYupop.dismiss();
				Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
						R.anim.activity_translate_out);

				ll_popup_spinner.setAnimation(anim);
				pop.showAsDropDown(ll_blocktrade_spinner, 0, 0);

			}
		});

		blocktrade_quyu_sp4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Tpop.dismiss();
					 pop.dismiss();
					 QuYupop.dismiss();
				Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
						R.anim.activity_translate_out);
				llpopupSpinnerMore.setAnimation(anim);
				Mpop.showAsDropDown(ll_blocktrade_spinner, 0, 0);

			}
		});
	}

	@Override
	public void showData(List<BlockTradeList> blocktrades) {
		this.blocktrades = blocktrades;
		this.lastblocks.addAll(blocktrades);
		adapter = new BlockTradeLsitAdapter(lastblocks, getApplicationContext());
		lvBlockTrade.setAdapter(adapter);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		fourdata.setCatid("7");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<BlockTradeList> blocks = (List<BlockTradeList>) success;

				// 将数据追加到集合中
				lastblocks.clear();
				lastblocks.addAll(blocks);
				// 刷新界面
				adapter.notifyDataSetChanged();
				// 关闭上拉加载刷新布局
				lvBlockTrade.onRefreshComplete();
				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.size() + "个房源！");

			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvBlockTrade.onRefreshComplete();

			}
		});

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = page + 1;
		fourdata.setCatid("7");
		fourdata.setPage(page + "");
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<BlockTradeList> blocks = (List<BlockTradeList>) success;
				if (blocks == null) {
					lvBlockTrade.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
				} else {
					// 将数据追加到集合中
					lastblocks.addAll(blocks);
					// 刷新界面
					adapter.notifyDataSetChanged();
					// 关闭上拉加载刷新布局
					lvBlockTrade.onRefreshComplete();
					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.size() + "个房源！");
				}
			}

			@Override
			public void onError(Object error) {
				MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
				lvBlockTrade.onRefreshComplete();

			}
		});

	}

	@Override
	public void showDataFail(String info) {
		lastblocks.clear();
		adapter.notifyDataSetChanged();
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
	}

	// 区域
	private void showPopWindowQuyu() {
		QuYupop = new PopupWindow(BlockTradeMainActivity.this);
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

		spinneradapter1 = new SpinnerAdapter(SpinnerData.Area, getApplicationContext());
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
					blocktrade_quyu_sp1.setText("区域");
					fourdata.setCt("");
					fourdata.setAr("");
					lastblocks.clear();
					posi = 0;
					presenter.LoadProgrameData(fourdata);
					QuYupop.dismiss();
					llpopupSpinnerQuyu.clearAnimation();
					break;
				case 1:
					city = "2";
					posi = 1;
					MinArea.clear();
				case 2:
					city = "4";
					posi = 2;
					MinArea.clear();
					break;
				case 3:
					city = "5";
					posi = 3;
					MinArea.clear();
					break;
				case 4:
					city = "6";
					posi = 4;
					MinArea.clear();
					break;
				case 5:
					city = "7";
					posi = 5;
					MinArea.clear();
					break;
				case 6:
					posi = 6;
					city = "8";
					MinArea.clear();
					break;
				case 7:
					posi = 7;
					city = "9";
					MinArea.clear();
					break;
				case 8:
					posi = 8;
					city = "10";
					MinArea.clear();
					break;
				case 9:
					posi = 9;
					city = "11";
					MinArea.clear();
					break;
				case 10:
					posi = 10;
					city = "12";
					MinArea.clear();
					break;
				case 11:
					posi = 11;
					city = "13";
					MinArea.clear();
					break;
				case 12:
					posi = 12;
					city = "14";
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
							BlockTradeMainActivity.this.MinArea.add(ad);
						}
						spinneradapter1 = new SpinnerAdapter(MinArea, getApplicationContext());
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
				if (position == 0) {
					blocktrade_quyu_sp1.setText(SpinnerData.Area.get(posi));
					fourdata.setCt(city);
					fourdata.setAr("");
					fourdata.setDt("");
				} else {
					String pid = minarea.get(position).getPid();
					blocktrade_quyu_sp1.setText(minarea.get(position).getName());
					fourdata.setCt(city);
					fourdata.setAr(pid);
					fourdata.setDt("");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
					QuYupop.dismiss();
					llpopupSpinnerQuyu.clearAnimation();
				}
			}
		});
	}

	// 金额
	private void showPopWindowPrice() {
		Tpop = new PopupWindow(BlockTradeMainActivity.this);

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
		final EditText etlow = (EditText) view.findViewById(R.id.ershou_lowprice_et);
		final EditText ethigh = (EditText) view.findViewById(R.id.ershou_highprice_et);
		TextView tvlowhigh = (TextView) view.findViewById(R.id.tv_low_high);

		tvlowhigh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String low = etlow.getText().toString();
				String high = ethigh.getText().toString();

				if (low.equals("") && !high.equals("")) {
					fourdata.setZj("0-" + high);
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
					Tpop.dismiss();
					llpopupSpinner2.clearAnimation();
				} else if (!low.equals("") && high.equals("")) {
					fourdata.setZj(low + "-");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
					Tpop.dismiss();
					llpopupSpinner2.clearAnimation();
				} else if (!low.equals("") && !high.equals("")) {
					int l = Integer.valueOf(low);
					int h = Integer.valueOf(high);

					if (l > h) {
						MyToastShowCenter.CenterToast(getApplicationContext(), "最高价必须大于最低价！");
					} else if (l == h) {
						fourdata.setZj(low);
						lastblocks.clear();
						presenter.LoadProgrameData(fourdata);
						Tpop.dismiss();
						llpopupSpinner2.clearAnimation();
					} else {
						fourdata.setZj(low + "-" + high);
						lastblocks.clear();
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

		spinneradapter = new SpinnerAdapter(SpinnerData.JINE, getApplicationContext());
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
				if (position == 0) {
					blocktrade_quyu_sp2.setText("金额");
					fourdata.setZj("");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else if (position == 1) {
					blocktrade_quyu_sp2.setText("1000万以下");
					fourdata.setZj("0-1000");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else if (position == 4) {
					blocktrade_quyu_sp2.setText("5000万-1亿");
					fourdata.setZj("5000-10000");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else if (position == 5) {
					blocktrade_quyu_sp2.setText("1亿以上");
					fourdata.setZj("10000-");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else {
					String price = SpinnerData.JINE.get(position).split("万")[0];
					blocktrade_quyu_sp2.setText(SpinnerData.JINE.get(position));
					fourdata.setZj(price);
					Log.e("````````````````````````", "price=" + price);
					lastblocks.clear();
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
	private void showPopWindowMianji() {
		pop = new PopupWindow(BlockTradeMainActivity.this);

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
		spinneradapter = new SpinnerAdapter(SpinnerData.MIANJI, getApplicationContext());
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
				if (position == 0) {
					blocktrade_quyu_sp3.setText("房型");
					fourdata.setMj("");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else if (position == 1) {
					blocktrade_quyu_sp3.setText("1万平米以下");
					fourdata.setMj("0-10000");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else if (position == 2) {
					blocktrade_quyu_sp3.setText("1-5万平米");
					fourdata.setMj("10000-50000");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else if (position == 3) {
					blocktrade_quyu_sp3.setText("5-10万平米");
					fourdata.setMj("50000-100000");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				} else {
					blocktrade_quyu_sp3.setText("10万平米以上");
					fourdata.setMj("100000-");
					lastblocks.clear();
					presenter.LoadProgrameData(fourdata);
				}
				pop.dismiss();
				ll_popup_spinner.clearAnimation();
			}
		});
	}

	private void showPopWindowMore() {
		Mpop = new PopupWindow(BlockTradeMainActivity.this);
		View view = getLayoutInflater().inflate(R.layout.item_blocktrade_spinner4, null);
		llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
		Mpop.setWidth(LayoutParams.MATCH_PARENT);
		Mpop.setHeight(LayoutParams.MATCH_PARENT);
		Mpop.setBackgroundDrawable(new BitmapDrawable());
		Mpop.setFocusable(true);
		Mpop.setOutsideTouchable(true);
		Mpop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
		view.findViewById(R.id.blockbrade_fangshi_bufenzhuanrang).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_fangshi_guquanrongzi).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_fangshi_kongguquanzhuanrang).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_fangshi_zhaiquanrongzi).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_fangshi_zhengtizhuanrang).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_fangshi_zulinrongzi).setOnClickListener(this);

		view.findViewById(R.id.blockbrade_sx_cunjiti).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_sx_junduiyongdi).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_sx_linyeyongdi).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_sx_shangye).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_sx_shangyongbangong).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_sx_zhuzhaiyongdi).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_sx_zongheyongdi).setOnClickListener(this);

		view.findViewById(R.id.blockbrade_type_gongyeyongfang).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_type_jiti).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_type_jiudian).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_type_junchanfang).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_type_shangyeyong).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_type_xiezilou).setOnClickListener(this);
		view.findViewById(R.id.blockbrade_type_zhuzhaiyong).setOnClickListener(this);
		view.findViewById(R.id.blocktrade_spinner_cancle).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.parent_spinner_more:
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_fangshi_bufenzhuanrang:
			blocktrade_quyu_sp4.setText("部分转让");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("部分转让");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;

		case R.id.blockbrade_fangshi_guquanrongzi:
			blocktrade_quyu_sp4.setText("股权融资");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("股权融资");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_fangshi_kongguquanzhuanrang:
			blocktrade_quyu_sp4.setText("控股权转让");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("控股权转让");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;

		case R.id.blockbrade_fangshi_zhaiquanrongzi:
			blocktrade_quyu_sp4.setText("债权融资");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("债权融资");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_fangshi_zhengtizhuanrang:
			blocktrade_quyu_sp4.setText("整体转让");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("整体转让");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;

		case R.id.blockbrade_fangshi_zulinrongzi:
			blocktrade_quyu_sp4.setText("租赁融资");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("租赁融资");
			presenter.LoadProgrameData(fourdata);

			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;

		case R.id.blockbrade_type_gongyeyongfang:
			blocktrade_quyu_sp4.setText("工业厂房");
			fourdata.setSx("");
			fourdata.setLx("工业厂房");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_type_jiti:
			blocktrade_quyu_sp4.setText("集体");
			fourdata.setSx("");
			fourdata.setLx("集体");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_type_jiudian:
			blocktrade_quyu_sp4.setText("酒店");
			fourdata.setSx("");
			fourdata.setLx("酒店");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_type_junchanfang:
			blocktrade_quyu_sp4.setText("军产房");
			fourdata.setSx("");
			fourdata.setLx("军产房");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_type_shangyeyong:
			blocktrade_quyu_sp4.setText("商业用房");
			fourdata.setSx("");
			fourdata.setLx("商业用房");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_type_xiezilou:
			blocktrade_quyu_sp4.setText("写字楼");
			fourdata.setSx("");
			fourdata.setLx("写字楼");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_type_zhuzhaiyong:
			blocktrade_quyu_sp4.setText("住宅用房");
			fourdata.setSx("");
			fourdata.setLx("住宅用房");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;

		case R.id.blockbrade_sx_cunjiti:
			blocktrade_quyu_sp4.setText("村集体用地");
			fourdata.setSx("村集体用地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_sx_junduiyongdi:
			blocktrade_quyu_sp4.setText("军队用地");
			fourdata.setSx("军队用地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_sx_linyeyongdi:
			blocktrade_quyu_sp4.setText("林业用地");
			fourdata.setSx("林业用地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_sx_shangye:
			blocktrade_quyu_sp4.setText("商业用地");
			fourdata.setSx("商业用地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_sx_shangyongbangong:
			blocktrade_quyu_sp4.setText("商用/办公土地");
			fourdata.setSx("商用/办公土地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_sx_zhuzhaiyongdi:
			blocktrade_quyu_sp4.setText("住宅用地");
			fourdata.setSx("住宅用地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blockbrade_sx_zongheyongdi:
			blocktrade_quyu_sp4.setText("综合用地");
			fourdata.setSx("综合用地");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		case R.id.blocktrade_spinner_cancle:
			blocktrade_quyu_sp4.setText("更多");
			fourdata.setSx("");
			fourdata.setLx("");
			fourdata.setFs("");
			presenter.LoadProgrameData(fourdata);
			Mpop.dismiss();
			llpopupSpinnerMore.clearAnimation();
			break;
		default:
			break;
		}

	}

}
