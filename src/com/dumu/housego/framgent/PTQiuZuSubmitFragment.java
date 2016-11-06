package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;

import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PTQiuZuSubmitFragment extends Fragment {
	private LinearLayout ll_back_ptqiuzusubmit;
	private RelativeLayout rl_qiuzu_fangyuanquyu,rl_qiuzu_rentingType,rl_qiuzu_qiwangtingshi,rl_qiuzu_qiwangjine;
	private TextView tv_qiuzu_phone,tv_qiuzu_housearea,tv_qiuzu_zulinType,tv_qiuzu_tingshi,tv_qiuzu_jine;
	private Button btn_qiuzu_submit;
	private EditText etChenhu;
	private UserInfo userinfo;
	private AddressModel model=new AddressModel();
	private AddressModel model2=new AddressModel();
	private PopupWindow pop;
	private LinearLayout ll_wheel;
	private LinearLayout ll_popup;
	private WheelPicker wheelpicker;
	
	private String pid;

	String AREA;
	String MINAREA;
	
	private List<Address> minarea;
	private List<Address> area;
	
	private List<String >a=new ArrayList<String>();
	private List<String >shen=new ArrayList<String>();
	private List<String >Area=new ArrayList<String>();
	private List<String >MinArea=new ArrayList<String>();
	String q="深圳";
	String w="2222";
	String e="3333";
	String r="4444";
	String t="5555";
	String y="6666";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View Pview=inflater.inflate(R.layout.fragment_pt_qiuzhusubmit, null);
		shen.add(q);
		a.add(w);
		a.add(r);
		a.add(t);
		a.add(y);
		initView(Pview);
		popWindow();
		setListener(Pview);
		
		return Pview;
	}
	
	
	private void popWindow() {
		pop = new PopupWindow(getActivity().getBaseContext());
		
		   View view = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(
                   R.layout.wheelpicker_ptzufang_address, null);    
		ll_popup = (LinearLayout) view.findViewById(R.id.ll_pop_choice);
		ll_wheel=(LinearLayout) view.findViewById(R.id.ll_wheelPicker);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		
		RelativeLayout parent=(RelativeLayout) view.findViewById(R.id.parent_wheel);
		

		WheelPicker picker1=(WheelPicker) view.findViewById(R.id.wp_b);
		WheelPicker picker2=(WheelPicker) view.findViewById(R.id.wp_c);
//		WheelPicker picker3=(WheelPicker) view.findViewById(R.id.wp_p);
		TextView tv=(TextView) view.findViewById(R.id.wp_a);
		
		picker1.setStyle(WheelPicker.CURVED);
		picker2.setStyle(WheelPicker.CURVED);
//		picker3.setStyle(WheelPicker.CURVED);
		Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaa", "a="+a);
//		picker1.setData(a);
//		picker2.setData(a);
//		picker3.setData(a);
		
		
		picker1.setTextSize(20);
		picker2.setTextSize(20);
//		picker3.setTextSize(20);
		
		
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_wheel.clearAnimation();
			}
		});
		
		
		
		
		picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
			
			@Override
			public void onWheelSelected(int index, String data) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onWheelScrolling() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onWheelScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		
//		picker3.setOnWheelChangeListener(new OnWheelChangeListener() {
//			
//			@Override
//			public void onWheelSelected(int index, String data) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onWheelScrolling() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onWheelScrollStateChanged(int state) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		picker2.setOnWheelChangeListener(new OnWheelChangeListener() {
			
			@Override
			public void onWheelSelected(int index, String data) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onWheelScrolling() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onWheelScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	}


	@Override
	public void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		String phone=userinfo.getUsername();
		tv_qiuzu_phone.setText(phone+"");
		super.onResume();
	}
	
	private void initView(View view) {
		ll_back_ptqiuzusubmit=(LinearLayout) view.findViewById(R.id.ll_back_ptqiuzusubmit);
		rl_qiuzu_fangyuanquyu=(RelativeLayout) view.findViewById(R.id.rl_qiuzu_fangyuanquyu);
		rl_qiuzu_rentingType=(RelativeLayout) view.findViewById(R.id.rl_qiuzu_rentingType);
		rl_qiuzu_qiwangtingshi=(RelativeLayout) view.findViewById(R.id.rl_qiuzu_qiwangtingshi);
		rl_qiuzu_qiwangjine=(RelativeLayout) view.findViewById(R.id.rl_qiuzu_qiwangjine);
		
		tv_qiuzu_housearea=(TextView) view.findViewById(R.id.tv_qiuzu_housearea);
		tv_qiuzu_phone=(TextView) view.findViewById(R.id.tv_qiuzu_phone);
		tv_qiuzu_zulinType=(TextView) view.findViewById(R.id.tv_qiuzu_zulinType);
		tv_qiuzu_tingshi=(TextView) view.findViewById(R.id.tv_qiuzu_tingshi);
		tv_qiuzu_jine=(TextView) view.findViewById(R.id.tv_qiuzu_jine);
		
		etChenhu=(EditText) view.findViewById(R.id.et_qiuzu_cehnghu);
		
		btn_qiuzu_submit=(Button) view.findViewById(R.id.btn_qiuzu_submit);
		
		wheelpicker=(com.aigestudio.wheelpicker.widget.WheelZodiacPicker) view.findViewById(R.id.wheelpicker);
//		wheelpicker.setData(a);
		wheelpicker.setTextColor(android.graphics.Color.RED);
		wheelpicker.setStyle(WheelPicker.STRAIGHT);
		wheelpicker.setOnWheelChangeListener(new OnWheelChangeListener() {
			
			@Override
			public void onWheelSelected(int index, String data) {
				tv_qiuzu_jine.setText(data);
				
			}
			
			@Override
			public void onWheelScrolling() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onWheelScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void setListener(final View pview) {
		ll_back_ptqiuzusubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment=new PTQiuZuListFragment();
				FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});
		rl_qiuzu_fangyuanquyu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	Animation anim=AnimationUtils.loadAnimation(getActivity().getBaseContext(), R.anim.activity_translate_in);
				
				ll_popup.setAnimation(anim);
				ll_wheel.setAnimation(anim);
				pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
				
			}
		});
		
		rl_qiuzu_qiwangtingshi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				HouseAreaAlertDialog() ;
			}
		});
		
		
	}
	
	
protected void HouseAreaAlertDialog() {
		
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();  
		alertDialog.show();  
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.ac_main_dialog);  
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		
		WheelPicker picker1=(WheelPicker) window.findViewById(R.id.main_dialog_container1);
		final WheelPicker picker2=(WheelPicker) window.findViewById(R.id.main_dialog_container2);
		final WheelPicker picker3=(WheelPicker) window.findViewById(R.id.main_dialog_container3);
		Button btncancle=(Button) window.findViewById(R.id.btn_straight); 
		Button btnTitle=(Button) window.findViewById(R.id.btn_obtain); 
		Button btnSure=(Button) window.findViewById(R.id.btn_curved); 
	
		picker1.setBackgroundColor(0xFFF0DF98);
		picker1.setTextColor(0xFF3F96C3);
		picker1.setLabelFor(android.graphics.Color.RED);
		
		picker2.setBackgroundColor(0xFFF0DF98);
		picker2.setTextColor(0xFF3F96C3);
		picker2.setLabelFor(android.graphics.Color.RED);
		
		picker3.setBackgroundColor(0xFFF0DF98);
		picker3.setTextColor(0xFF3F96C3);
		picker3.setLabelFor(android.graphics.Color.RED);
		
		picker1.setData(shen);
		
		model.address("1", new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				area=(List<Address>) success;
				for (Address address : area) {
					String add=address.getName();
					
					Area.add(add);
				}
				
				picker2.setData(Area);
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
			}
		});
		
		
	
		
		
//		picker2.setData(a);
//		picker3.setData(a);
		
		picker1.setStyle(WheelPicker.STRAIGHT);
		picker2.setStyle(WheelPicker.STRAIGHT);
		picker3.setStyle(WheelPicker.STRAIGHT);
		
		picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				tv_qiuzu_tingshi.setText(data);
				AREA=data;
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
				AREA+=" "+data;
			}
			@Override
			public void onWheelScrolling() {
			}
			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
		
		model2.address(pid,new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				minarea=(List<Address>) success;
				
				for (Address address : minarea) {
					String ad=address.getName();
					MinArea.add(ad);
				}
				picker3.setData(MinArea);
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
		picker3.setOnWheelChangeListener(new OnWheelChangeListener() {
			@Override
			public void onWheelSelected(int index, String data) {
				AREA+=" "+data;
			}
			@Override
			public void onWheelScrolling() {
			}
			@Override
			public void onWheelScrollStateChanged(int state) {
			}
		});
	}



}
