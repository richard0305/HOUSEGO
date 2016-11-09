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
import com.dumu.housego.entity.Address;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.WheelpickerData;

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

public class ATershouSubmitFragment extends Fragment{
	private String louceng1;
	private String louceng2;
	
	private String ting1;
	private String ting2;
	private String ting3;
	
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
	//房源区域
	private static final int LOCATION=0;
	
	private EditText et_ershou_housrprice,et_ershou_jianzhumianji,et_ershou_fangling,et_ershou_loudong,et_ershou_menpai,et_ershou_taoneimianji;
	private LinearLayout ll_back_agentershousubmit;
	private TextView tv_ershou_housearea,tv_ershou_xiaoquname,tv_ershou_jingweidu,tv_ershou_louceng,tv_ershou_loucengmenu,tv_ershou_wuyeType,
	tv_ershou_diyaxinxi,tv_ershou_huType,tv_ershou_houseSX;
	
	//地图地标返回信息
	double latitude,longitude;
	String PoiString;
	//
	private String str;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_agent_ershou_submit, null);
		//房源区域
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
		//
		
		initView(view);
		setListener();
		return view;
	}
	private void initView(View view) {
		ll_back_agentershousubmit=(LinearLayout) view.findViewById(R.id.ll_back_agentershousubmit);
		//
		tv_ershou_housearea=(TextView) view.findViewById(R.id.tv_ershou_housearea);
		tv_ershou_xiaoquname=(TextView) view.findViewById(R.id.tv_ershou_xiaoquname);
		//
		et_ershou_fangling=(EditText) view.findViewById(R.id.et_ershou_fangling);
		et_ershou_housrprice=(EditText) view.findViewById(R.id.et_ershou_housrprice);
		et_ershou_jianzhumianji=(EditText) view.findViewById(R.id.et_ershou_jianzhumianji);
		et_ershou_loudong=(EditText) view.findViewById(R.id.et_ershou_loudong);
		et_ershou_menpai=(EditText) view.findViewById(R.id.et_ershou_menpai);
		et_ershou_taoneimianji=(EditText) view.findViewById(R.id.et_ershou_taoneimianji);
		//
		tv_ershou_jingweidu=(TextView) view.findViewById(R.id.tv_ershou_jingweidu);
		tv_ershou_diyaxinxi=(TextView) view.findViewById(R.id.tv_ershou_diyaxinxi);
		tv_ershou_houseSX=(TextView) view.findViewById(R.id.tv_ershou_houseSX);
		tv_ershou_huType=(TextView) view.findViewById(R.id.tv_ershou_huType);
		tv_ershou_louceng=(TextView) view.findViewById(R.id.tv_ershou_louceng);
		tv_ershou_loucengmenu=(TextView) view.findViewById(R.id.tv_ershou_loucengmenu);
		tv_ershou_wuyeType=(TextView) view.findViewById(R.id.tv_ershou_wuyeType);
		//
		
	}
	private void setListener() {
		ll_back_agentershousubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new ATershouListFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment);
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
				Intent i=new Intent(getActivity(),GetLocationActivity.class);
				startActivityForResult(i, LOCATION);
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
		
		tv_ershou_wuyeType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.WUYETYPE, tv_ershou_wuyeType, "请选择物业性质");
			}
		});
		
		tv_ershou_diyaxinxi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerOne(WheelpickerData.DIYAXINXI, tv_ershou_diyaxinxi, "请选择抵押信息");
			}
		});
		
		tv_ershou_huType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerThree(WheelpickerData.HUTYPE1, WheelpickerData.HUTYPE2, WheelpickerData.HUTYPE3, tv_ershou_huType, "请选择户型");
				
			}
		});
		
		tv_ershou_houseSX.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WheelPickerThree(WheelpickerData.HOUSESX1, WheelpickerData.HOUSESX2, WheelpickerData.HOUSESX3, tv_ershou_houseSX, "请选择房屋属性");
			}
		});
		
		
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
					ATershouSubmitFragment.this.pid = "2";
				} else if (data.equals("福田区")) {
					ATershouSubmitFragment.this.pid = "4";
				} else if (data.equals("南山区")) {
					ATershouSubmitFragment.this.pid = "5";
				} else if (data.equals("盐田区")) {
					ATershouSubmitFragment.this.pid = "6";
				} else if (data.equals("宝安区")) {
					ATershouSubmitFragment.this.pid = "7";
				} else if (data.equals("龙岗新区")) {
					ATershouSubmitFragment.this.pid = "8";
				} else if (data.equals("龙华新区")) {
					ATershouSubmitFragment.this.pid = "9";
				} else if (data.equals("光明新区")) {
					ATershouSubmitFragment.this.pid = "10";
				} else if (data.equals("坪山新区")) {
					ATershouSubmitFragment.this.pid = "11";
				} else if (data.equals("大鹏新区")) {
					ATershouSubmitFragment.this.pid = "12";
				} else if (data.equals("东莞")) {
					ATershouSubmitFragment.this.pid = "13";
				} else {
					ATershouSubmitFragment.this.pid = "14";
				}

				
				model2.address(pid, new AsycnCallBack() {
					@Override
					public void onSuccess(Object success) {
						ATershouSubmitFragment.this.minarea = (List<Address>) success;

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
				ATershouSubmitFragment.this.MINAREA = " " + data;
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
				tv_ershou_housearea.setText(q+" "+AREA + MINAREA);
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
	 * 地图返回坐标
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case LOCATION:
			 latitude=data.getDoubleExtra("latitude", 0);	
			 longitude=data.getDoubleExtra("longitude", 0);	
			
			LatLng position=new LatLng(latitude, longitude);
			
			// LatLng location; 点击事件得到的location
			GeoCoder geoCoder = GeoCoder.newInstance();
			geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener(){
			    @Override
			    public void onGetGeoCodeResult(GeoCodeResult arg0) {
			    }
			    @Override
			    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
			        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			            return;
			        }
			        ATershouSubmitFragment.this.PoiString = result.getAddress();//解析到的地址
			    }
			});
			// 反向地理解析
			geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(position));
//			Log.e("request", "result="+latitude+" "+longitude+" "+PoiString);
			
			tv_ershou_jingweidu.setText(round(latitude,5)+","+round(longitude,5)+" "+PoiString);
			break;

		
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 将double类型的值，精确小数点
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
	 * @param Data 数据源，需要展示的数据
	 * @param tv 确认后，所需要显示的什么空间上
	 * @param title  中间显示条目的内容
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
	 * @param Data 数据源，需要展示的数据
	 * @param tv 确认后，所需要显示的什么空间上
	 * @param title  中间显示条目的内容
	 */
	public void WheelPickerTwo(List<String> Data1,List<String> Data2, final TextView tv, String title) {
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
				tv.setText(louceng1+" "+louceng2);
				louceng1="";
				louceng2="";
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
	 * @param Data 数据源，需要展示的数据
	 * @param tv 确认后，所需要显示的什么空间上
	 * @param title  中间显示条目的内容
	 */
	public void WheelPickerThree(List<String> Data1,List<String> Data2,List<String> Data3, final TextView tv, String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_wheelpicker_jine);
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
				tv.setText(ting1+" "+ting2+" "+ting3);
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
	

}
