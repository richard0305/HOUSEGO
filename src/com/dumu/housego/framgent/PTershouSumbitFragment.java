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
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.IPTershouSubmitPresenter;
import com.dumu.housego.presenter.PTershouSubmitPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.WheelpickerData;
import com.dumu.housego.view.IPTershouSubmitView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PTershouSumbitFragment extends Fragment implements IPTershouSubmitView{

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
	//
	private static final int XIAOQUNAME = 15;
	private static final int LOCATION = 0;
	//
	private LinearLayout ll_back_putongershou;
	private TextView tv_ershou_housearea,tv_ershou_xiaoquname,tv_ershou_jingweidu,tv_ershou_louceng,tv_ershou_loucengmenu
	,tv_ershou_uploadPic,tv_ershou_gongbufangshi,tv_ershou_yincangphone;
	
	private EditText et_ershou_chenghu,et_ershou_housrprice,et_ershou_loudong,et_ershou_menpai;
	private Button btn_ershou_submit;
	private double latitude;
	private double longitude;
	protected String PoiString;
	protected String louceng1;
	protected String louceng2;
	protected CharSequence str;
	private UserInfo userinfo;
	private String username;
	private String userid;
	private String modelid;
	private ErShouFangDetails e=new ErShouFangDetails();
	
	private IPTershouSubmitPresenter presenter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pt_ershou_sumbit, null);
		presenter=new PTershouSubmitPresenter(this);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		username=userinfo.getUsername();
		userid=userinfo.getUserid();
		modelid=userinfo.getModelid();
		
		initView(view);
		setListener();
		return view;
	}

	private void initView(View view) {
		ll_back_putongershou = (LinearLayout) view.findViewById(R.id.ll_back_putongershou);
		tv_ershou_gongbufangshi=(TextView) view.findViewById(R.id.tv_ershou_gongbufangshi);
		tv_ershou_housearea=(TextView) view.findViewById(R.id.tv_ershou_housearea);
		tv_ershou_jingweidu=(TextView) view.findViewById(R.id.tv_ershou_jingweidu);
		tv_ershou_louceng=(TextView) view.findViewById(R.id.tv_ershou_louceng);
		tv_ershou_loucengmenu=(TextView) view.findViewById(R.id.tv_ershou_loucengmenu);
		tv_ershou_uploadPic=(TextView) view.findViewById(R.id.tv_ershou_uploadPic);
		tv_ershou_xiaoquname=(TextView) view.findViewById(R.id.tv_ershou_xiaoquname);
		tv_ershou_yincangphone=(TextView) view.findViewById(R.id.tv_ershou_yincangphone);
		
		et_ershou_chenghu=(EditText) view.findViewById(R.id.et_ershou_chenghu);
		et_ershou_housrprice=(EditText) view.findViewById(R.id.et_ershou_housrprice);
		et_ershou_loudong=(EditText) view.findViewById(R.id.et_ershou_loudong);
		et_ershou_menpai=(EditText) view.findViewById(R.id.et_ershou_menpai);
		
		btn_ershou_submit=(Button) view.findViewById(R.id.btn_ershou_submit);

	}

	private void setListener() {
		
		btn_ershou_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				e.setProvince("1");
				e.setCity(pid);
				e.setArea(area1);
				
				e.setXiaoquname(tv_ershou_xiaoquname.getText().toString());
				e.setJingweidu(tv_ershou_jingweidu.getText().toString());
				e.setChenghu(et_ershou_chenghu.getText().toString());
				e.setZongjia(et_ershou_housrprice.getText().toString());
				e.setTitle(tv_ershou_housearea.getText().toString()+tv_ershou_xiaoquname.getText().toString()+
						et_ershou_loudong.getText().toString()+et_ershou_menpai.getText().toString());
				e.setPub_type(tv_ershou_gongbufangshi.getText().toString());
				e.setHidetel(tv_ershou_yincangphone.getText().toString());
				
				presenter.PTershouSubmit(username, userid, modelid, e);
				
			}
		});
		
		
		ll_back_putongershou.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Fragment fragment = new PTershouListFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		
		tv_ershou_housearea.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HouseAreaAlertDialog();
			}
		});
		
		tv_ershou_jingweidu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), GetLocationActivity.class);
				startActivityForResult(i, LOCATION);
			}
		});
		
		tv_ershou_xiaoquname.setOnClickListener(new OnClickListener() {
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
		
		tv_ershou_louceng.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerTwo(WheelpickerData.LOUCENG1, WheelpickerData.LOUCENG2, tv_ershou_louceng, "请选择楼层");
			}
		});
		
		tv_ershou_loucengmenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.LOUCENGMenu, tv_ershou_loucengmenu, "请选择楼层属性");
			}
		});
		
		tv_ershou_gongbufangshi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.FABUFANGSHI, tv_ershou_gongbufangshi, "请选择发布方式");
			}
		});
		
		tv_ershou_yincangphone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.YINCANGPHONE, tv_ershou_yincangphone, "是否隐藏手机号码");
			}
		});
		
	}
	
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
						PTershouSumbitFragment.this.PoiString = result.getAddress();// 解析到的地址
					}
				});
				// 反向地理解析
				geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(position));
				// Log.e("request", "result="+latitude+" "+longitude+" "+PoiString);
				tv_ershou_jingweidu.setText(round(longitude, 6) + "," + round(latitude, 6));
				break;
				
			case XIAOQUNAME:
				String xiaoquname = data.getStringExtra("XIAOQUNAME");
				tv_ershou_xiaoquname.setText(xiaoquname);
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
					PTershouSumbitFragment.this.pid = "2";
				} else if (data.equals("福田区")) {
					PTershouSumbitFragment.this.pid = "4";
				} else if (data.equals("南山区")) {
					PTershouSumbitFragment.this.pid = "5";
				} else if (data.equals("盐田区")) {
					PTershouSumbitFragment.this.pid = "6";
				} else if (data.equals("宝安区")) {
					PTershouSumbitFragment.this.pid = "7";
				} else if (data.equals("龙岗新区")) {
					PTershouSumbitFragment.this.pid = "8";
				} else if (data.equals("龙华新区")) {
					PTershouSumbitFragment.this.pid = "9";
				} else if (data.equals("光明新区")) {
					PTershouSumbitFragment.this.pid = "10";
				} else if (data.equals("坪山新区")) {
					PTershouSumbitFragment.this.pid = "11";
				} else if (data.equals("大鹏新区")) {
					PTershouSumbitFragment.this.pid = "12";
				} else if (data.equals("东莞")) {
					PTershouSumbitFragment.this.pid = "13";
				} else {
					PTershouSumbitFragment.this.pid = "14";
				}

				model2.address(pid, new AsycnCallBack() {
					@Override
					public void onSuccess(Object success) {
						PTershouSumbitFragment.this.minarea = (List<Address>) success;

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
				PTershouSumbitFragment.this.MINAREA = " " + data;
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
				tv_ershou_housearea.setText(q + " " + AREA + MINAREA);
				
				// 求区域id
				for (int i = 0; i < minarea.size(); i++) {
					String area = minarea.get(i).getName();
					Log.e("area", "area=" + area + "   " + MINAREA);

					if (area.equals(MINAREA.trim())) {
						PTershouSumbitFragment.this.area1 = minarea.get(i).getId() + "";
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

	@Override
	public void PTershouSubmit(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		
	}
	
	
	
	
	
}
