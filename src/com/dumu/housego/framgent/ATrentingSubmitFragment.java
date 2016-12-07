package com.dumu.housego.framgent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.dumu.housego.GetLocationActivity;
import com.dumu.housego.R;
import com.dumu.housego.SearchActivity;
import com.dumu.housego.SubmitEdittextActivity;
import com.dumu.housego.activity.ImageGrallyMain;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ATrentingSubmitPresenter;
import com.dumu.housego.presenter.IATrentingSubmitPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.WheelpickerData;
import com.dumu.housego.view.IATrentingSubmitView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ATrentingSubmitFragment extends Fragment implements IATrentingSubmitView{
	private static final int LOCATION = 0;
	private static final int BIAOTI = 1;
	private static final int XIAOQUNAME = 15;
	private static final int FANGYUANMIAOSHU = 2;
	
	private static final int HUXINGJIESHAO = 4;
	private static final int XIAOQUJIESHAO = 5;
	private static final int ZHUANGXIUMIAOSHU = 7;
	private static final int ZHOUBIANPEITAO = 8;
	private static final int JIAOTONGCHUXING = 10;
	
	private static final int HOUSEADDRESS = 16;
	private static final int HOUSEBEST = 17;
	private static final int CHUZUCAUSE = 18;
	private static final int YEZHUSHUO = 19;
	
	
	//
	private String tcb1 = "";
	private String tcb2 = "";
	private String tcb3 = "";
	private String tcb4 = "";
	private String tcb5 = "";
	private String tcb6 = "";
	//
	private String bqcb1 = "";
	private String bqcb2 = "";
	private String bqcb3 = "";
	private String bqcb4 = "";
	private String bqcb5 = "";
	private String bqcb6 = "";
	private String bqcb7 = "";
	private String bqcb8 = "";
	private String bqcb9 = "";
	//
	private String ting1;
	private String ting2;
	private String ting3;
	//
	private List<String> Area = new ArrayList<String>();
	private List<String> MinArea = new ArrayList<String>();
	private AddressModel model2 = new AddressModel();
	String AREA;
	String MINAREA;
	private List<Address> minarea;
	private List<Address> area;
	private List<String> shen = new ArrayList<String>();
	private String pid;
	String area1 = null;
	String w1 = "罗湖区";
	String w2 = "福田区";
	String w3 = "南山区";
	String w4 = "盐田区";
	String w5 = "宝安区";
	String w6 = "龙岗新区";
	String w7 = "龙华新区";
	String w8 = "光明新区";
	String w9 = "坪山新区";
	String w10 = "大鹏新区";
	String w11 = "东莞";
	String w12 = "惠州";
	String q = "深圳";
	
	private LinearLayout ll_back_agentrentingsubmit;
	private TextView tv_renting_housearea,tv_renting_xiaoquname;
	private EditText et_renting_housrprice,et_renting_mianji,et_renting_fangling;
	private TextView tv_renting_jingweidu,tv_renting_huType,tv_renting_houseSX,tv_renting_uploadPic,tv_renting_louceng
	,tv_renting_jianzhuType,tv_renting_chuzutype,tv_renting_zhifuType,tv_renting_housepeizhi,tv_renting_ditieline,tv_renting_biaoqian
	,tv_renting_biaoti,tv_renting_houseDesc,tv_renting_houseaddress,tv_renting_huxingjieshao,tv_renting_houseBest,tv_renting_chuzuCause
	,tv_renting_xiaoquintro,tv_ershou_zhuangxiumiaoshu,tv_ershou_zhoubianpeitao,tv_renting_jiaotongchuxing,tv_renting_yezhushuo;
	
	private Button btn_renting_submit;
	private double latitude;
	private double longitude;
	protected String PoiString;
	protected String louceng1;
	protected String louceng2;
	protected String str;
	private String biaoqian1;
	private String biaoqian2;
	
	private IATrentingSubmitPresenter presenter;
	
	private String username;
	private String userid;
	private String modelid;
	private UserInfo userinfo;
	
	private Handler handler=new Handler();
	private List<Pics> pics=new ArrayList<Pics>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_agent_renting_submit, null);
		presenter=new ATrentingSubmitPresenter(this);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		username=userinfo.getUsername();
		userid=userinfo.getUserid();
		modelid=userinfo.getModelid();
		
		shen.add(q);
		Area.add(w1);
		Area.add(w2);
		Area.add(w3);
		Area.add(w4);
		Area.add(w5);
		Area.add(w6);
		Area.add(w7);
		Area.add(w8);
		Area.add(w9);
		Area.add(w10);
		Area.add(w11);
		Area.add(w12);
		initView(view);
		setListener();
		return view;
	}

	private void setListener() {
		btn_renting_submit.setOnClickListener(new OnClickListener() {
			private RentingDetail m=new RentingDetail();

			@Override
			public void onClick(View v) {
				m.setUsername(username);
				m.setModelid(modelid);
				m.setUserid(userid);
				
				m.setProvince("1");
				m.setCity(pid);
				m.setArea(area1);
				m.setXiaoquname(tv_renting_xiaoquname.getText().toString());
				m.setJingweidu(tv_renting_jingweidu.getText().toString());
				
					
					String h=tv_renting_huType.getText().toString();
					String shi=h.split(" ")[0].split("室")[0];
					String ting=h.split(" ")[1].split("厅")[0];
					String wei=h.split(" ")[2].split("卫")[0];
					
					m.setShi(shi);
					m.setTing(ting);
					m.setWei(wei);
				
				m.setMianji(et_renting_mianji.getText().toString());
				m.setZujin(et_renting_housrprice.getText().toString());
				m.setTitle(tv_renting_biaoti.getText().toString());
				m.setDesc(tv_renting_houseDesc.getText().toString());
				m.setAddress(tv_renting_houseaddress.getText().toString());
				//楼层
				String c=tv_renting_louceng.getText().toString();
				String c1=c.split(" ")[0];
				String c2=c.split(" ")[1].split("共")[1].split("层")[0];
				m.setCeng(c1.toString());
				m.setZongceng(c2.toString());
				m.setFangling(et_renting_fangling.getText().toString());
				
				//可传
				if(tv_renting_houseSX.getText().toString().length()>0){
					String zhuangxiu=tv_renting_houseSX.getText().toString().split(" ")[0];
					String chaoxiang=tv_renting_houseSX.getText().toString().split(" ")[1];
					String leixing=tv_renting_houseSX.getText().toString().split(" ")[2];
					
					m.setZhuangxiu(zhuangxiu);
					m.setChaoxiang(chaoxiang);
					m.setLeixing(leixing);
				}else{
					m.setZhuangxiu("");
					m.setChaoxiang("");
					m.setLeixing("");
				}
				
				m.setPics(pics);
				m.setJianzhutype(tv_renting_jianzhuType.getText().toString());
				m.setZulin(tv_renting_chuzutype.getText().toString());
				m.setFukuan(tv_renting_zhifuType.getText().toString());
				m.setFangwupeitao(tv_renting_housepeizhi.getText().toString());
				m.setDitiexian(tv_renting_ditieline.getText().toString());
				m.setBiaoqian(tv_renting_biaoqian.getText().toString());
				m.setHuxingjieshao(tv_renting_huxingjieshao.getText().toString());
				m.setLiangdian(tv_renting_houseBest.getText().toString());
				m.setCzreason(tv_renting_chuzuCause.getText().toString());
				m.setXiaoquintro(tv_renting_xiaoquintro.getText().toString());
				m.setZxdesc(tv_ershou_zhuangxiumiaoshu.getText().toString());
				m.setShenghuopeitao(tv_ershou_zhoubianpeitao.getText().toString());
				m.setJiaotong(tv_renting_jiaotongchuxing.getText().toString());
				m.setYezhushuo(tv_renting_yezhushuo.getText().toString());
				
				
				
				presenter.ATrentingSubmit(m);
			}
		});
		
		ll_back_agentrentingsubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new ATrentingListFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		
		tv_renting_housearea.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HouseAreaAlertDialog();
			}
		});
		
		tv_renting_xiaoquname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(area1==null){
					MyToastShowCenter.CenterToast(getActivity(), "请先选择房源区域！");
				}else{
					Intent i = new Intent(getActivity(), SearchActivity.class);
					i.putExtra("Area", area1);
					startActivityForResult(i, XIAOQUNAME);
				}
			}
		});
		
		tv_renting_jingweidu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), GetLocationActivity.class);
				startActivityForResult(i, LOCATION);
			}
		});
		
		tv_renting_huType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerThree(WheelpickerData.HUTYPE1, WheelpickerData.HUTYPE2, WheelpickerData.HUTYPE3, tv_renting_huType, "请选择户型");
			}
		});
		
		tv_renting_houseSX.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerThree(WheelpickerData.HOUSESX2, WheelpickerData.HOUSESX3, WheelpickerData.HOUSEUSE, tv_renting_houseSX, "请选择房屋属性");
			}
		});
		
		tv_renting_louceng.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerTwo(WheelpickerData.LOUCENGMenu, WheelpickerData.LOUCENG2, tv_renting_louceng, "请选择楼层");
			}
		});
		
		tv_renting_jianzhuType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.JIANZHUTYPE, tv_renting_jianzhuType, "请选择建筑类型");
			}
		});
		
		tv_renting_chuzutype.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.CHUZUTYPE, tv_renting_chuzutype, "请选择出租方式");
			}
		});
		
		tv_renting_zhifuType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.ZHIFUTYPE, tv_renting_zhifuType, "请选择支付方式");
			}
		});
		
		tv_renting_housepeizhi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NineCheckDialog(tv_renting_housepeizhi);
			}
		});
		
		tv_renting_ditieline.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SIXCheckDialog(WheelpickerData.DITIELINE1, WheelpickerData.DITIELINE2, WheelpickerData.DITIELINE3,
			WheelpickerData.DITIELINE4, WheelpickerData.DITIELINE5, WheelpickerData.DITIELINE6, tv_renting_ditieline, "请选择地铁线");
			}
		});
		
		tv_renting_biaoqian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TWOCheckDialog(tv_renting_biaoqian);
			}
		});
		
		tv_renting_biaoti.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String biaoti = tv_renting_biaoti.getText().toString();
				i.putExtra("S1", biaoti);
				i.putExtra("X", 1);
				startActivityForResult(i, BIAOTI);
			}
		});

		tv_renting_houseDesc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_houseDesc.getText().toString();
				i.putExtra("S2", houseDesc);
				i.putExtra("X", 2);
				startActivityForResult(i, FANGYUANMIAOSHU);
			}
		});
		
		tv_renting_houseaddress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_houseaddress.getText().toString();
				i.putExtra("S16", houseDesc);
				i.putExtra("X", 16);
				startActivityForResult(i, HOUSEADDRESS);
			}
		});
		
		tv_renting_huxingjieshao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_huxingjieshao.getText().toString();
				i.putExtra("S4", houseDesc);
				i.putExtra("X", 4);
				startActivityForResult(i, HUXINGJIESHAO);
			}
		});
		
		tv_renting_houseBest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_houseBest.getText().toString();
				i.putExtra("S17", houseDesc);
				i.putExtra("X",17);
				startActivityForResult(i, HOUSEBEST);
			}
		});
		
		tv_renting_chuzuCause.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_chuzuCause.getText().toString();
				i.putExtra("S18", houseDesc);
				i.putExtra("X",18);
				startActivityForResult(i, CHUZUCAUSE);
			}
		});
		tv_renting_xiaoquintro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_xiaoquintro.getText().toString();
				i.putExtra("S5", houseDesc);
				i.putExtra("X",5);
				startActivityForResult(i, XIAOQUJIESHAO);
			}
		});
		
		tv_ershou_zhuangxiumiaoshu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_ershou_zhuangxiumiaoshu.getText().toString();
				i.putExtra("S7", houseDesc);
				i.putExtra("X",7);
				startActivityForResult(i, ZHUANGXIUMIAOSHU);
				
			}
		});
		
		tv_ershou_zhoubianpeitao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_ershou_zhoubianpeitao.getText().toString();
				i.putExtra("S8", houseDesc);
				i.putExtra("X",8);
				startActivityForResult(i, ZHOUBIANPEITAO);
			}
		});
		
		tv_renting_jiaotongchuxing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_jiaotongchuxing.getText().toString();
				i.putExtra("S10", houseDesc);
				i.putExtra("X",10);
				startActivityForResult(i, JIAOTONGCHUXING);
			}
		});
		
		tv_renting_yezhushuo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SubmitEdittextActivity.class);
				String houseDesc = tv_renting_yezhushuo.getText().toString();
				i.putExtra("S19", houseDesc);
				i.putExtra("X",19);
				startActivityForResult(i, YEZHUSHUO);
			}
		});
		tv_renting_uploadPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ImageGrallyMain.class);
				i.putExtra("TAG", 4);
				startActivityForResult(i, ImageGrallyMain.ATRentingPIC);
				
			}
		});
		
		
		
		
		
		
	}



	private void initView(View view) {
		btn_renting_submit=(Button) view.findViewById(R.id.btn_renting_submit);
		
		ll_back_agentrentingsubmit = (LinearLayout) view.findViewById(R.id.ll_back_agentrentingsubmit);
		et_renting_mianji=(EditText) view.findViewById(R.id.et_renting_mianji);
		et_renting_housrprice=(EditText) view.findViewById(R.id.et_renting_housrprice);
		et_renting_fangling=(EditText) view.findViewById(R.id.et_renting_fangling);
		
		tv_ershou_zhoubianpeitao=(TextView) view.findViewById(R.id.tv_ershou_zhoubianpeitao);
		tv_ershou_zhuangxiumiaoshu=(TextView) view.findViewById(R.id.tv_ershou_zhuangxiumiaoshu);
		tv_renting_biaoqian=(TextView) view.findViewById(R.id.tv_renting_biaoqian);
		tv_renting_biaoti=(TextView) view.findViewById(R.id.tv_renting_biaoti);
		tv_renting_chuzuCause=(TextView) view.findViewById(R.id.tv_renting_chuzuCause);
		tv_renting_chuzutype=(TextView) view.findViewById(R.id.tv_renting_chuzutype);
		tv_renting_ditieline=(TextView) view.findViewById(R.id.tv_renting_ditieline);
		tv_renting_houseaddress=(TextView) view.findViewById(R.id.tv_renting_houseaddress);
		tv_renting_housearea=(TextView) view.findViewById(R.id.tv_renting_housearea);
		tv_renting_houseBest=(TextView) view.findViewById(R.id.tv_renting_houseBest);
		tv_renting_houseDesc=(TextView) view.findViewById(R.id.tv_renting_houseDesc);
		tv_renting_housepeizhi=(TextView) view.findViewById(R.id.tv_renting_housepeizhi);
		tv_renting_houseSX=(TextView) view.findViewById(R.id.tv_renting_houseSX);
		tv_renting_huType=(TextView) view.findViewById(R.id.tv_renting_huType);
		tv_renting_huxingjieshao=(TextView) view.findViewById(R.id.tv_renting_huxingjieshao);
		tv_renting_jianzhuType=(TextView) view.findViewById(R.id.tv_renting_jianzhuType);
		tv_renting_jiaotongchuxing=(TextView) view.findViewById(R.id.tv_renting_jiaotongchuxing);
		tv_renting_jingweidu=(TextView) view.findViewById(R.id.tv_renting_jingweidu);
		tv_renting_louceng=(TextView) view.findViewById(R.id.tv_renting_louceng);
		tv_renting_uploadPic=(TextView) view.findViewById(R.id.tv_renting_uploadPic);
		tv_renting_xiaoquintro=(TextView) view.findViewById(R.id.tv_renting_xiaoquintro);
		tv_renting_xiaoquname=(TextView) view.findViewById(R.id.tv_renting_xiaoquname);
		tv_renting_yezhushuo=(TextView) view.findViewById(R.id.tv_renting_yezhushuo);
		tv_renting_zhifuType=(TextView) view.findViewById(R.id.tv_renting_zhifuType);
		
		tv_renting_uploadPic.setText("已经上传"+pics.size()+"张");
	}
	
	
	
	/**
	 * 房源区域选择
	 * 
	 */
	protected void HouseAreaAlertDialog() {

		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.ac_main_dialog);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		WheelPicker picker1 = (WheelPicker) window.findViewById(R.id.main_dialog_container1);
		final WheelPicker picker2 = (WheelPicker) window.findViewById(R.id.main_dialog_container2);
		final WheelPicker picker3 = (WheelPicker) window.findViewById(R.id.main_dialog_container3);
		Button btncancle = (Button) window.findViewById(R.id.btn_straight);
		Button btnTitle = (Button) window.findViewById(R.id.btn_obtain);
		Button btnSure = (Button) window.findViewById(R.id.btn_curved);

		picker2.setSelected(true);
		picker3.setSelected(true);

		picker1.setBackgroundColor(0xFFF);
		picker1.setSelected(true);
		picker1.setTextColor(android.graphics.Color.RED);
		picker2.setBackgroundColor(0xFFF);
		picker2.setTextColor(0xFF3F96C3);
		picker2.setLabelFor(android.graphics.Color.RED);
		picker3.setBackgroundColor(0xFFF);
		picker3.setTextColor(0xFF3F96C3);
		picker3.setLabelFor(android.graphics.Color.RED);

		picker1.setData(shen);
		picker2.setData(Area);

		picker1.setStyle(WheelPicker.STRAIGHT);
		picker2.setStyle(WheelPicker.STRAIGHT);
		picker3.setStyle(WheelPicker.STRAIGHT);

		picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				AREA = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});

		picker2.setOnWheelChangeListener(new OnWheelChangeListener() {

			@Override
			public void onWheelSelected(int index, String data) {
				MinArea.clear();
				if (data.equals("罗湖区")) {
					ATrentingSubmitFragment.this.pid = "2";
				} else if (data.equals("福田区")) {
					ATrentingSubmitFragment.this.pid = "4";
				} else if (data.equals("南山区")) {
					ATrentingSubmitFragment.this.pid = "5";
				} else if (data.equals("盐田区")) {
					ATrentingSubmitFragment.this.pid = "6";
				} else if (data.equals("宝安区")) {
					ATrentingSubmitFragment.this.pid = "7";
				} else if (data.equals("龙岗新区")) {
					ATrentingSubmitFragment.this.pid = "8";
				} else if (data.equals("龙华新区")) {
					ATrentingSubmitFragment.this.pid = "9";
				} else if (data.equals("光明新区")) {
					ATrentingSubmitFragment.this.pid = "10";
				} else if (data.equals("坪山新区")) {
					ATrentingSubmitFragment.this.pid = "11";
				} else if (data.equals("大鹏新区")) {
					ATrentingSubmitFragment.this.pid = "12";
				} else if (data.equals("东莞")) {
					ATrentingSubmitFragment.this.pid = "13";
				} else {
					ATrentingSubmitFragment.this.pid = "14";
				}

				model2.address(pid, new AsycnCallBack() {
					@Override
					public void onSuccess(Object success) {
						ATrentingSubmitFragment.this.minarea = (List<Address>) success;

						for (Address address : minarea) {
							String ad = address.getName();
							MinArea.add(ad);
						}
						picker3.setData(MinArea);
					}

					@Override
					public void onError(Object error) {

					}
				});

				AREA = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {

			}
		});

		picker3.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				ATrentingSubmitFragment.this.MINAREA = " " + data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_renting_housearea.setText(q + " " + AREA + MINAREA);
				
				// 求区域id
				for (int i = 0; i < minarea.size(); i++) {
					String area = minarea.get(i).getName();
					Log.e("area", "area=" + area + "   " + MINAREA);

					if (area.equals(MINAREA.trim())) {
						ATrentingSubmitFragment.this.area1 = minarea.get(i).getId() + "";
						Log.e("area1", "area1=" + area1 + "   " + MINAREA);
						break;
					} else {
						Log.e("xxxx--area1", "xxx---area1=" + area1 + "   " + MINAREA);
					}
				}
				
				alertDialog.cancel();
			}
		});
		btncancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
		btnTitle.setText("请选择房源区域");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data!=null){
			switch (requestCode) {
			case LOCATION:
				latitude = data.getDoubleExtra("latitude", 0);
				longitude = data.getDoubleExtra("longitude", 0);

				LatLng position = new LatLng(latitude, longitude);

				// LatLng location; 点击事件得到的location
				GeoCoder geoCoder = GeoCoder.newInstance();
				geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
					@Override
					public void onGetGeoCodeResult(GeoCodeResult arg0) {
					}
					@Override
					public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
						if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
							return;
						}
						ATrentingSubmitFragment.this.PoiString = result.getAddress();// 解析到的地址
					}
				});
				// 反向地理解析
				geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(position));
				// Log.e("request", "result="+latitude+" "+longitude+" "+PoiString);
				tv_renting_jingweidu.setText(round(longitude, 6) + "," + round(latitude, 6));
				break;
			case XIAOQUNAME:
				String xiaoquname = data.getStringExtra("XIAOQUNAME");
				tv_renting_xiaoquname.setText(xiaoquname);
				break;
			case BIAOTI:
				String str1 = data.getStringExtra("M1");
				tv_renting_biaoti.setText(str1);
			break;
		case FANGYUANMIAOSHU:
			String str2 = data.getStringExtra("M2");
			tv_renting_houseDesc.setText(str2);
			break;
		case HUXINGJIESHAO:
			String str4 = data.getStringExtra("M4");
			tv_renting_huxingjieshao.setText(str4);
			break;
		case JIAOTONGCHUXING:
			String str10 = data.getStringExtra("M10");
			tv_renting_jiaotongchuxing.setText(str10);
			break;	
		case XIAOQUJIESHAO:
			String str5 = data.getStringExtra("M5");
			tv_renting_xiaoquintro.setText(str5);
			break;
		case ZHOUBIANPEITAO:
			String str8 = data.getStringExtra("M8");
			tv_ershou_zhoubianpeitao.setText(str8);
			break;

		case ZHUANGXIUMIAOSHU:
			String str7 = data.getStringExtra("M7");
			tv_ershou_zhuangxiumiaoshu.setText(str7);
			break;
		
			
		case HOUSEADDRESS:
			String str16 = data.getStringExtra("M16");
			tv_renting_houseaddress.setText(str16);
		break;
		case HOUSEBEST:
			String str17 = data.getStringExtra("M17");
			tv_renting_houseBest.setText(str17);
			break;
		case CHUZUCAUSE:
			String str18 = data.getStringExtra("M18");
			tv_renting_chuzuCause.setText(str18);
			break;
		
		case YEZHUSHUO:
			String str19 = data.getStringExtra("M19");
			tv_renting_yezhushuo.setText(str19);
			break;
		case ImageGrallyMain.ATRentingPIC:
			pics.clear();
			this.pics=(List<Pics>) data.getSerializableExtra("pics");
			tv_renting_uploadPic.setText("已经上传"+pics.size()+"张");
			break;
		}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	/**
	 * 将double类型的值，精确小数点
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 
	 * @param Data
	 *            数据源，需要展示的数据
	 * @param tv
	 *            确认后，所需要显示的什么空间上
	 * @param title
	 *            中间显示条目的内容
	 */
	public void WheelPickerThree(List<String> Data1, List<String> Data2, List<String> Data3, final TextView tv,
			String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_wheelpicker_tingshi);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		WheelPicker picker1 = (WheelPicker) window.findViewById(R.id.zulin_wheel1);
		WheelPicker picker2 = (WheelPicker) window.findViewById(R.id.zulin_wheel2);
		WheelPicker picker3 = (WheelPicker) window.findViewById(R.id.zulin_wheel3);

		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
		tvTitle.setText(title);
		picker1.setData(Data1);
		picker2.setData(Data2);
		picker3.setData(Data3);
		picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				ting1 = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
		picker2.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				ting2 = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
		picker3.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				ting3 = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});

		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText(ting1 + " " + ting2 + " " + ting3);
				alertDialog.cancel();
			}
		});

		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});

	}
	
	
	/**
	 * 
	 * @param Data
	 *            数据源，需要展示的数据
	 * @param tv
	 *            确认后，所需要显示的什么空间上
	 * @param title
	 *            中间显示条目的内容
	 */
	public void WheelPickerTwo(List<String> Data1, List<String> Data2, final TextView tv, String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_wheelpicker_jine);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		WheelPicker picker1 = (WheelPicker) window.findViewById(R.id.zulin_wheel1);
		WheelPicker picker2 = (WheelPicker) window.findViewById(R.id.zulin_wheel2);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
		tvTitle.setText(title);
		picker1.setData(Data1);
		picker2.setData(Data2);
		picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				louceng1 = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
		picker2.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				louceng2 = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});

		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText(louceng1 + " " + louceng2);
				louceng1 = "";
				louceng2 = "";
				alertDialog.cancel();
			}
		});

		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});

	}

	/**
	 * 
	 * @param Data
	 *            数据源，需要展示的数据
	 * @param tv
	 *            确认后，所需要显示的什么空间上
	 * @param title
	 *            中间显示条目的内容
	 */
	public void WheelPickerOne(List<String> Data, final TextView tv, String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_wheelpicker_zulin);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		WheelPicker picker = (WheelPicker) window.findViewById(R.id.zulin_wheel);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
		tvTitle.setText(title);
		picker.setData(Data);
		picker.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				str = data;
			}

			@Override
			public void onWheelScrolling() {
			}

			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText(str);
				alertDialog.cancel();
			}
		});

		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});

	}
	
	/**
	 * 标签，与地铁线
	 * 
	 * @param Data
	 *            数据源，需要展示的数据
	 * @param tv
	 *            确认后，所需要显示的什么空间上
	 * @param title
	 *            中间显示条目的内容
	 */

	public void SIXCheckDialog(String Data1, String Data2, String Data3, String Data4, String Data5, String Data6,
			final TextView tv, String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_check_bqdtl);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);

		tvTitle.setText(title);

		final CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
		final CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
		final CheckBox cb3 = (CheckBox) window.findViewById(R.id.check_3);
		final CheckBox cb4 = (CheckBox) window.findViewById(R.id.check_4);
		final CheckBox cb5 = (CheckBox) window.findViewById(R.id.check_5);
		final CheckBox cb6 = (CheckBox) window.findViewById(R.id.check_6);

		cb1.setText(Data1);
		cb2.setText(Data2);
		cb3.setText(Data3);
		cb4.setText(Data4);
		cb5.setText(Data5);
		cb6.setText(Data6);

		tcb1 = "";
		tcb2 = "";
		tcb3 = "";
		tcb4 = "";
		tcb5 = "";
		tcb6 = "";

		cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb6 = ","+(String) buttonView.getText().toString();
				} else {
					tcb6 = "";
				}
			}
		});
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb1 = ","+(String) buttonView.getText().toString();
				} else {
					tcb1 = "";
				}
			}
		});
		cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb2 = ","+(String) buttonView.getText().toString();
				} else {
					tcb2 = "";
				}
			}
		});
		cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb3 = ","+(String) buttonView.getText().toString();
				} else {
					tcb3 = "";
				}
			}
		});
		cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb4 = ","+(String) buttonView.getText().toString();
				} else {
					tcb4 = "";
				}
			}
		});
		cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb5 = ","+(String) buttonView.getText().toString();
				} else {
					tcb5 = "";
				}
			}
		});

		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String cb=tcb1+tcb2+tcb3+tcb4+tcb5+tcb6;
				
				if(cb.startsWith(",")){
					String tx=cb.substring(1);
					tv.setText(tx.toString());
				}else{
					String tx=cb;
					tv.setText(tx.toString());
				}
				
				alertDialog.cancel();
			}
		});
		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});

	}

	/**
	 * 标签
	 * 
	 * @param Data
	 *            数据源，需要展示的数据
	 * @param tv
	 *            确认后，所需要显示的什么空间上
	 * @param title
	 *            中间显示条目的内容
	 */

	public void NineCheckDialog(final TextView tv) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_check_city);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);


		final CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
		final CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
		final CheckBox cb3 = (CheckBox) window.findViewById(R.id.check_3);
		final CheckBox cb4 = (CheckBox) window.findViewById(R.id.check_4);
		final CheckBox cb5 = (CheckBox) window.findViewById(R.id.check_5);
		final CheckBox cb6 = (CheckBox) window.findViewById(R.id.check_6);
		final CheckBox cb7 = (CheckBox) window.findViewById(R.id.check_7);
		final CheckBox cb8 = (CheckBox) window.findViewById(R.id.check_8);
		final CheckBox cb9 = (CheckBox) window.findViewById(R.id.check_9);

		bqcb1 = "";
		bqcb2 = "";
		bqcb3 = "";
		bqcb4 = "";
		bqcb5 = "";
		bqcb6 = "";
		bqcb7 = "";
		bqcb8 = "";
		bqcb9 = "";
		cb9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb9 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb9 = "";
				}
			}
		});
		
		cb8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb8 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb8 = "";
				}
			}
		});
		cb7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb7 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb7 = "";
				}
			}
		});
		cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb6 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb6 = "";
				}
			}
		});
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb1 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb1 = "";
				}
			}
		});
		cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb2 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb2 = "";
				}
			}
		});
		cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb3 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb3 = "";
				}
			}
		});
		cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb4 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb4 = "";
				}
			}
		});
		cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					bqcb5 = ","+(String) buttonView.getText().toString();
				} else {
					bqcb5 = "";
				}
			}
		});

		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String cb=bqcb1+bqcb2+bqcb3+bqcb4+bqcb5+bqcb6+bqcb7+bqcb8+bqcb9;
				
				if(cb.startsWith(",")){
					String tx=cb.substring(1);
					tv.setText(tx.toString());
				}else{
					String tx=cb;
					tv.setText(tx.toString());
				}
				
				alertDialog.cancel();
			}
		});
		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
	}
	
	public void TWOCheckDialog(final TextView tv) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_check_biaoqian);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
		final CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
		final CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
		biaoqian1="";
		biaoqian2="";
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					biaoqian1 = ","+(String) buttonView.getText().toString();
				} else {
					biaoqian1 = "";
				}
			}
		});
		cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					biaoqian2 = ","+(String) buttonView.getText().toString();
				} else {
					biaoqian2 = "";
				}
			}
		});

		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String cb=biaoqian1+biaoqian2;
				if(cb.startsWith(",")){
					String tx=cb.substring(1);
					tv.setText(tx.toString());
				}else{
					String tx=cb;
					tv.setText(tx.toString());
				}
				alertDialog.cancel();
			}
		});
		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
	}

	@Override
	public void ATrentingSubmit(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		if(info.equals("发布成功")){
			
			handler.postDelayed(new Runnable() {
				public void run() {
					Fragment fragment = new ATrentingListFragment();
					FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
					trans.replace(R.id.fl_agent_fragment, fragment);
					trans.commitAllowingStateLoss();
				}
			}, 1000);
			
		}
	}
	

	


}
