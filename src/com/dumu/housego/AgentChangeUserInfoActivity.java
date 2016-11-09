package com.dumu.housego;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IChangeUserInfoView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AgentChangeUserInfoActivity extends Activity implements IChangeUserInfoView{
	private IChangeUserInfoPresenter userinfopresenter;
	private LoginUserInfoModel infomodel=new LoginUserInfoModel();
	
	private LinearLayout ll_agent_back;
	
	private EditText et_agent_changrealname,et_agent_changegerenjieshao,et_agent_changeshenfenzheng,et_agent_changecompanyname
	,et_agent_change_currentpassword,et_agent_change_Newpassword,et_agent_change_repassword;
	private LinearLayout ll_agent_changerealname,ll_agent_changerealname_back,ll__aegnt_changfenjiahao,
	ll_agent_changefenjihao_back,agent_fenjihao_shenqing,agent_fenjihao_jiebang,
	ll_agent_gerenjieshao,ll_agent_changegerenjieshao_back,ll_agent_changeshenfenzheng,ll_agent_changeshenfenzheng_back
	,ll_agent_changecompanyname,ll_agent_changecompanyname_back,ll_agent_changepassword,ll_agent_changepassword_back
	;
	
	private RelativeLayout rl_agent_shenfenzheng,rl_agent_mainarea,rl_agent_zhiyeType
	,rl_agent_companyName,rl_agent_chongyeshijian,rl_agent_upload,rl_agent_changepassword
	,rl_agent_realname,rl_agent_sex,rl_agent_fenjihao,rl_agent_gerenjieshao;
	
	private TextView tv_agent_shenfenzheng,tv_agent_mainarea,tv_agent_zhiyeType,tv_agent_realname,
	tv_agent_chongyeshijian,tv_agent_phone,tv_agent_viptype,tv_agent_sex,tv_agent_fenjihao,
	tv_agent_fenjihao_show,tv_agent_fenjihao_show_1,tv_agent_changegerenjieshao_save
	,tv_agent_changeshenfenzheng_save,tv_agent_changecompanyname_save
	;
	private Button btn_agent_changefenjihao_shenqing,btn_agent_changefenjihao_jiebang,btn_agent_changepassword_submit;
	
	private TextView tv_agent_changerealname_save;
	
	private CircleImageView iv_agent_Photo;
	private UserInfo userinfo	=HouseGoApp.getContext().getCurrentUserInfo();;
	
	private  MyReboundScrollView   agent_changeshow;

	
	protected Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			
			
			String userid=userinfo.getUserid();
			
			infomodel.login(userid,new  AsycnCallBack() {
				@Override
				public void onSuccess(Object success) {
					UserInfo Nuserinfo=(UserInfo) success;
					HouseGoApp.getContext().SaveCurrentUserInfo(Nuserinfo);
					HouseGoApp.saveLoginInfo(getApplicationContext(), Nuserinfo);
				}
				@Override
				public void onError(Object error) {
				}
			});
			userinfo=HouseGoApp.getContext().getCurrentUserInfo();
			
			
			onResume();
			
			MyToastShowCenter.CenterToast(getApplicationContext(), "我已经刷新了数据");
			
		}
	};
	private PopupWindow pop;
	private LinearLayout ll_popup;
	
	private View parentView;
	private LinearLayout ll_cancle;
	private PopupWindow popType;
	private LinearLayout ll_popup_zhiyeType;
	private LinearLayout ll_cancle_zhiyeType;
	private PopupWindow popWorkTime;
	private LinearLayout ll_popup_worktime;
	private LinearLayout ll_cancle_worktime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		parentView=getLayoutInflater().inflate(R.layout.activity_agent_change_user_info, null);
		setContentView(parentView);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		userinfopresenter=new ChangeUserInfoPresenter(this);
		showPopWindowSex();
		showPopWindowType();
		showPopWindowWorkTime();
		setListener();
	}
	
	private void showPopWindowWorkTime() {
popWorkTime = new PopupWindow(AgentChangeUserInfoActivity.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_worktime, null);

		ll_popup_worktime = (LinearLayout) view.findViewById(R.id.ll_popup_worktime);
		ll_cancle_worktime=(LinearLayout) view.findViewById(R.id.ll_cancle_worktime);
		popWorkTime.setWidth(LayoutParams.MATCH_PARENT);
		popWorkTime.setHeight(LayoutParams.WRAP_CONTENT);
		popWorkTime.setBackgroundDrawable(new BitmapDrawable());
		popWorkTime.setFocusable(true);
		popWorkTime.setOutsideTouchable(true);
		popWorkTime.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_worktime);
		Button bt4 = (Button) view
				.findViewById(R.id.item_popupwindows_worktime_btn4);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_worktime_btn2);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_worktime_btn3);
		Button cancle = (Button) view
				.findViewById(R.id.item_popupwindows_worktime_cancel);
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});

		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			
				String userid=userinfo.getUserid();
				String worktime="1-2";
				userinfopresenter.ChangeWorkTime(userid, worktime);
				onResume();
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String userid=userinfo.getUserid();
				String worktime="2-5";
				userinfopresenter.ChangeWorkTime(userid, worktime);
				onResume();
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});
		
		bt4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String userid=userinfo.getUserid();
				String worktime="5";
				userinfopresenter.ChangeWorkTime(userid, worktime);
				onResume();
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});
		
	}

	private void showPopWindowType() {
popType = new PopupWindow(AgentChangeUserInfoActivity.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_zhiyetype, null);

		ll_popup_zhiyeType = (LinearLayout) view.findViewById(R.id.ll_popup_yizhitype);
		ll_cancle_zhiyeType=(LinearLayout) view.findViewById(R.id.ll_cancle_yizhitype);
		popType.setWidth(LayoutParams.MATCH_PARENT);
		popType.setHeight(LayoutParams.WRAP_CONTENT);
		popType.setBackgroundDrawable(new BitmapDrawable());
		popType.setFocusable(true);
		popType.setOutsideTouchable(true);
		popType.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_yizhitype);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_yizhitype_btn1);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_yizhitype_btn2);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_yizhitype_cancel);
		Button bt4 = (Button) view
				.findViewById(R.id.item_popupwindows_yizhitype_btn3);
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});

		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			
				String userid=userinfo.getUserid();
				String leixing="个人";
				userinfopresenter.ChangeLeixing(userid, leixing);
				onResume();
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});
		bt4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userid=userinfo.getUserid();
				String leixing="公司";
				userinfopresenter.ChangeLeixing(userid, leixing);
				onResume();
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});
		
		
	}

	private void showPopWindowSex() {
		pop = new PopupWindow(AgentChangeUserInfoActivity.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		ll_cancle=(LinearLayout) view.findViewById(R.id.ll_cancle);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_btn1);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_btn2);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_cancel);
		
		bt1.setText("男");
		bt2.setText("女");
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
//				tv_agent_sex.setText("男");
				
				String userid=userinfo.getUserid();
				String sex="1";
				userinfopresenter.ChangeSex(userid, sex);
				
				onResume();
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				tv_agent_sex.setText("女");
				String userid=userinfo.getUserid();
				String sex="2";
				userinfopresenter.ChangeSex(userid, sex);
				onResume();
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		
	}

	@Override
	protected void onResume() {
	
//		userinfo=HouseGoApp.getLoginInfo(getApplicationContext());
		if(userinfo==null){
			startActivity(new Intent(this, MainActivity.class));
			
		}else{
			String url=userinfo.getUserpic();
			Glide.with(getApplicationContext()).load(url).into(iv_agent_Photo);
			
			if(userinfo.getModelid().equals("35")){
				tv_agent_viptype.setText("普通会员");
			}else{
				tv_agent_viptype.setText("经纪人");
			}
			
			
			if(userinfo.getSex().equals("0")){
				tv_agent_sex.setText("未知");
			}else if(userinfo.getSex().equals("1")){
				tv_agent_sex.setText("男");
			}else{
				tv_agent_sex.setText("女");
			}
			tv_agent_fenjihao_show.setText(userinfo.getUsername());
			tv_agent_fenjihao_show_1.setText(userinfo.getVtel());
			
			tv_agent_realname.setText(userinfo.getRealname());
			tv_agent_phone.setText(userinfo.getUsername()+"");
			tv_agent_fenjihao.setText(userinfo.getVtel());
			tv_agent_shenfenzheng.setText(userinfo.getCardnumber());
			tv_agent_mainarea.setText(userinfo.getMainarea());
			tv_agent_zhiyeType.setText(userinfo.getLeixing());
			tv_agent_chongyeshijian.setText(userinfo.getWorktime()+"年");
			et_agent_changrealname.setText(userinfo.getRealname());
			et_agent_changegerenjieshao.setText(userinfo.getAbout());
			
			
			
			
		}
		
		super.onResume();
	}
	
	
	private void setListener() {
		ll_agent_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		iv_agent_Photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyToastShowCenter.CenterToast(getApplicationContext(), "选择图片");
			}
		});
		
		rl_agent_realname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changerealname.setVisibility(View.VISIBLE);
				
			}
		});
		
		rl_agent_sex.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim=AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this, R.anim.activity_translate_in);
				
				ll_popup.setAnimation(anim);
				ll_cancle.setAnimation(anim);
				pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				
			}
			
		});
		
		rl_agent_fenjihao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				
				if(userinfo.getZhuanjie().equals("0")){
					agent_fenjihao_shenqing.setVisibility(View.VISIBLE);
				}else{
					agent_fenjihao_jiebang.setVisibility(View.VISIBLE);
				}
				ll__aegnt_changfenjiahao.setVisibility(View.VISIBLE);
				
				
			}
		});
		
		rl_agent_gerenjieshao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_gerenjieshao.setVisibility(View.VISIBLE);
			}
		});
		
		rl_agent_shenfenzheng.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changeshenfenzheng.setVisibility(View.VISIBLE);
			}
		});
		
		rl_agent_mainarea.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		rl_agent_zhiyeType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim=AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this, R.anim.activity_translate_in);
				
				ll_popup_zhiyeType.setAnimation(anim);
				ll_cancle_zhiyeType.setAnimation(anim);
				popType.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			}
		});
		
		rl_agent_companyName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changecompanyname.setVisibility(View.VISIBLE);
			}
		});
		
		rl_agent_chongyeshijian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		Animation anim=AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this, R.anim.activity_translate_in);
				
				ll_popup_worktime.setAnimation(anim);
				ll_cancle_worktime.setAnimation(anim);
				popWorkTime.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			}
		});
		
		rl_agent_upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		rl_agent_changepassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changepassword.setVisibility(View.VISIBLE);
			}
		});
		
		
		/**
		 * 各个模型功能
		 * 真实姓名
		 * 
		 */
		
		ll_agent_changerealname_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_agent_changerealname.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
				
			}
		});
		
		tv_agent_changerealname_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userid=userinfo.getUserid();
				String realname=et_agent_changrealname.getText().toString();
				String modelid=userinfo.getModelid();
				userinfopresenter.ChangeRealName(userid, realname, modelid);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changerealname.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
			}
		});
		
		
		/**
		 * 各个模型功能
		 * 分机号
		 */
		ll_agent_changefenjihao_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				agent_fenjihao_jiebang.setVisibility(View.GONE);
				agent_fenjihao_shenqing.setVisibility(View.GONE);
				ll__aegnt_changfenjiahao.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
				
			}
		});
		
		btn_agent_changefenjihao_shenqing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String tel=userinfo.getUsername();
				String userid=userinfo.getUserid();
				userinfopresenter.ChangeSQfenjihao(tel, userid);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						agent_fenjihao_shenqing.setVisibility(View.GONE);
						ll__aegnt_changfenjiahao.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
				
			}
		});
		btn_agent_changefenjihao_jiebang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String tel=userinfo.getUsername();
				String userid=userinfo.getUserid();
				userinfopresenter.ChangeSQfenjihao(tel, userid);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						agent_fenjihao_jiebang.setVisibility(View.GONE);
						ll__aegnt_changfenjiahao.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
			}
		});
		
		/**
		 * 各个模型功能
		 * 个人介绍
		 */
		ll_agent_changegerenjieshao_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_agent_gerenjieshao.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});
		
		tv_agent_changegerenjieshao_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String userid=userinfo.getUserid();
				String about=et_agent_changegerenjieshao.getText().toString();
				userinfopresenter.ChangeGeRenJieshao(userid, about);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_gerenjieshao.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
			}
		});
		
		/**
		 * 各个模型功能
		 * 身份证号码
		 */
		ll_agent_changeshenfenzheng_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_agent_changeshenfenzheng.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});
		
		tv_agent_changeshenfenzheng_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String userid=userinfo.getUserid();
				String cardnumber=et_agent_changeshenfenzheng.getText().toString();
				userinfopresenter.ChangeShenFenZheng(userid, cardnumber);
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changeshenfenzheng.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
			}
		});
		
		
		/**
		 * 各个模型功能
		 * 公司名称
		 */
		ll_agent_changecompanyname_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_agent_changecompanyname.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});
		
		tv_agent_changecompanyname_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String userid=userinfo.getUserid();
				String coname=et_agent_changecompanyname.getText().toString();
				userinfopresenter.ChangeConame(userid, coname);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changecompanyname.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);
			}
		});
		
		/**
		 * 各个模型功能
		 * 密码修改
		 */
		ll_agent_changepassword_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_agent_changepassword.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});
		
		btn_agent_changepassword_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String username=userinfo.getUsername();
				String userid=userinfo.getUserid();
				String oldpwd=et_agent_change_currentpassword.getText().toString();
				String pwd1=et_agent_change_Newpassword.getText().toString();
				String pwd2=et_agent_change_repassword.getText().toString();
				userinfopresenter.ChangePassword(username, userid, oldpwd, pwd1, pwd2);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changepassword.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);
				
			}
		});
		
	}
	
	
	
	private void setViews() {
		ll_agent_back=(LinearLayout) findViewById(R.id.ll_agent_changeinfo_back);
		//各个条目
		rl_agent_changepassword=(RelativeLayout) findViewById(R.id.rl_agent_changepassword);
		rl_agent_chongyeshijian=(RelativeLayout) findViewById(R.id.rl_agent_chongyeshijian);
		rl_agent_companyName=(RelativeLayout) findViewById(R.id.rl_agent_companyName);
		rl_agent_fenjihao=(RelativeLayout) findViewById(R.id.rl_agent_fenjihao);
		rl_agent_gerenjieshao=(RelativeLayout) findViewById(R.id.rl_agent_gerenjieshao);
		rl_agent_mainarea=(RelativeLayout) findViewById(R.id.rl_agent_mainarea);
		rl_agent_realname=(RelativeLayout) findViewById(R.id.rl_agent_realname);
		rl_agent_sex=(RelativeLayout) findViewById(R.id.rl_agent_sex);
		rl_agent_shenfenzheng=(RelativeLayout) findViewById(R.id.rl_agent_shenfenzheng);
		rl_agent_upload=(RelativeLayout) findViewById(R.id.rl_agent_upload);
		rl_agent_zhiyeType=(RelativeLayout) findViewById(R.id.rl_agent_zhiyeType);
		//显示文本
		tv_agent_chongyeshijian=(TextView) findViewById(R.id.tv_agent_chongyeshijian);
		tv_agent_fenjihao=(TextView) findViewById(R.id.tv_agent_fenjihao);
		tv_agent_mainarea=(TextView) findViewById(R.id.tv_agent_mainarea);
		tv_agent_phone=(TextView) findViewById(R.id.tv_agent_phone);
		tv_agent_sex=(TextView) findViewById(R.id.tv_agent_sex);
		tv_agent_shenfenzheng=(TextView) findViewById(R.id.tv_agent_shenfenzheng);
		tv_agent_viptype=(TextView) findViewById(R.id.tv_agent_viptype);
		tv_agent_zhiyeType=(TextView) findViewById(R.id.tv_agent_zhiyeType);
		tv_agent_realname=(TextView) findViewById(R.id.tv_agent_realname);
		//图片
		iv_agent_Photo=(CircleImageView) findViewById(R.id.iv_agent_Photo);
		//整个框架
		agent_changeshow=(MyReboundScrollView) findViewById(R.id.agent_changeshow);
		
		//真实姓名栏目
		ll_agent_changerealname=(LinearLayout) findViewById(R.id.ll_agent_changerealname);
		ll_agent_changerealname_back=(LinearLayout) findViewById(R.id.ll_agent_changerealname_back);
		tv_agent_changerealname_save=(TextView) findViewById(R.id.tv_agent_changerealname_save);
		et_agent_changrealname=(EditText) findViewById(R.id.et_agent_changrealname);
		//分机号
		ll__aegnt_changfenjiahao=(LinearLayout) findViewById(R.id.ll_aegnt_changfenjiahao);
		ll_agent_changefenjihao_back=(LinearLayout) findViewById(R.id.ll_agent_changefenjihao_back);
		agent_fenjihao_jiebang=(LinearLayout) findViewById(R.id.agent_fenjihao_jiebang);
		agent_fenjihao_shenqing=(LinearLayout) findViewById(R.id.agent_fenjihao_shenqing);
		tv_agent_fenjihao_show=(TextView) findViewById(R.id.tv_agent_fenjihao_show);
		tv_agent_fenjihao_show_1=(TextView) findViewById(R.id.tv_agent_fenjihao_show_1);
		btn_agent_changefenjihao_shenqing=(Button) findViewById(R.id.btn_agent_changefenjihao_shenqing);
		btn_agent_changefenjihao_jiebang=(Button) findViewById(R.id.btn_agent_changefenjihao_jiebang);
		//个人说明
		ll_agent_gerenjieshao=(LinearLayout) findViewById(R.id.ll_agent_gerenjieshao);
		ll_agent_changegerenjieshao_back=(LinearLayout) findViewById(R.id.ll_agent_changegerenjieshao_back);
		tv_agent_changegerenjieshao_save=(TextView) findViewById(R.id.tv_agent_changegerenjieshao_save);
		et_agent_changegerenjieshao=(EditText) findViewById(R.id.et_agent_changegerenjieshao);
		//身份证
		ll_agent_changeshenfenzheng=(LinearLayout) findViewById(R.id.ll_agent_changeshenfenzheng);
		ll_agent_changeshenfenzheng_back=(LinearLayout) findViewById(R.id.ll_agent_changeshenfenzheng_back);
		tv_agent_changeshenfenzheng_save=(TextView) findViewById(R.id.tv_agent_changeshenfenzheng_save);
		et_agent_changeshenfenzheng=(EditText) findViewById(R.id.et_agent_changeshenfenzheng);
		//公司名称
		ll_agent_changecompanyname=(LinearLayout) findViewById(R.id.ll_agent_changecompanyname);
		ll_agent_changecompanyname_back=(LinearLayout) findViewById(R.id.ll_agent_changecompanyname_back);
		tv_agent_changecompanyname_save=(TextView) findViewById(R.id.tv_agent_changecompanyname_save);
		et_agent_changecompanyname=(EditText) findViewById(R.id.et_agent_changecompanyname);
		//密码修改
		ll_agent_changepassword=(LinearLayout) findViewById(R.id.ll_agent_changepassword);
		ll_agent_changepassword_back=(LinearLayout) findViewById(R.id.ll_agent_changepassword_back);
		et_agent_change_currentpassword=(EditText) findViewById(R.id.et_agent_change_currentpassword);
		et_agent_change_Newpassword=(EditText) findViewById(R.id.et_agent_change_Newpassword);
		et_agent_change_repassword=(EditText) findViewById(R.id.et_agent_change_repassword);
		btn_agent_changepassword_submit=(Button) findViewById(R.id.btn_agent_changepassword_submit);
		
		
	}

	@Override
	public void changeInfo(final String info) {
		
		Message msg=new Message();
		handler.sendMessage(msg);
		
	
		
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
		handler.postDelayed(new Runnable() {
			public void run() {
				if(info.equals("密码修改成功")){
					HouseGoApp.clearData(userinfo);
					onResume();
					startActivity(new Intent(getBaseContext(), LoginActivity.class));
				}	
			}
		}, 1000);
		
		
	}
	


	
}
