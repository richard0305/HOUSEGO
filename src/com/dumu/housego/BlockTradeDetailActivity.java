package com.dumu.housego;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.pay.AuthResult;
import com.dumu.housego.pay.H5PayDemoActivity;
import com.dumu.housego.pay.PayResult;
import com.dumu.housego.pay.util.OrderInfoUtil2_0;
import com.dumu.housego.presenter.BlockTradeDetailPresenter;
import com.dumu.housego.presenter.GouDiAddPresenter;
import com.dumu.housego.presenter.IBlockTradeDetailPresenter;
import com.dumu.housego.presenter.IGouDiAddPresenter;
import com.dumu.housego.presenter.IPaySuccessPresenter;
import com.dumu.housego.presenter.PaySuccessPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IBlockTradeDetailView;
import com.dumu.housego.view.IGouDiAddView;
import com.dumu.housego.view.IPaySuccessView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockTradeDetailActivity extends Activity implements IPaySuccessView,IBlockTradeDetailView,IGouDiAddView{
	String 		total_amount;
	
	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016072301657641";
	
	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088421264539813";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "ruianxingye888@126.com";

	/** 商户私钥，pkcs8格式 */
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANPTGHR7P3aPng0zA841Gh6uE19hesCd6NlOGBZKeXzTq5PLTPA0tGGAfIpqx1ze+hWsz5On/P2fBILF3U23S1u7wP/Yem1Ci2T4DYx5AZZe6xz927nNsnJO7vrUohl25/tXuSn/LaY01RABabvt1p5+a50uAqCS0FkapzqPebCFAgMBAAECgYEAkjPXYz5WFU0XN+EINWGtf5OCx4iOozfaqXIfafNJWwD2IfJmTjzya4G1dAwzQkSctC0ssKt4EM2a3XAYSTXECnBb4zJ7ppOhI8+OOPfScEnalCqq7XAC33bcgG17PHLfyAqesh0f+o5uwVptXpGOjX7Cu0qdYCjn3VAybVgi1kECQQD8C6T16v+mj8E9oG2p7IkuZmywqF8zc4dNY5lK7tjk+BnaFPe3Xy8uUZUYEfo11Rg3pjGYlT6/djaNgJm3/VNZAkEA1yXmy8sJ9ix4+QUKODRTN9BpgYh4ummSFgsP/DRMtA0LZCK83s/Kgu9fjuxVo/cSOjqAuywOZ1AYBkDkCfp9DQJARvO8N2I1H51eR8vusyQcJgy9UinDywcdsqJ0F80PD73sASFf7qYD8SUUNJdy+U6Ip7nIQmzZIirUBpeKLmpI2QJBAKmrtkvZn82IXQ7lrp2MhmRp9Aq3eZ5pS1AfAUhAZo1IDEe4LYL6FBcWeCHat99LJhDNul/h6qoHPCsSWcSUyrECQQDKnNGXtAwGQLYxIYIEHiWbmidKuJZoG8XgohLLvUGOx7uBDbU4e4TohyffdGJ9uZqF2v1OCtYLhqu/O56oCnGE";
	
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

private String trade_no;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
				PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				/**
				 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					
					try {
						JSONObject b=new JSONObject(resultInfo);
						JSONObject j=b.getJSONObject("alipay_trade_app_pay_response");
						trade_no=j.getString(trade_no);
						Log.e("resultInfo", "resultInfo="+resultInfo);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					paypresenter.PayInfo(resultStatus,total_amount, order_no,trade_no);
					MyToastShowCenter.CenterToast(BlockTradeDetailActivity.this, "支付成功");
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					MyToastShowCenter.CenterToast(BlockTradeDetailActivity.this, "支付失败");
				}
				break;
			}
			case SDK_AUTH_FLAG: {
				@SuppressWarnings("unchecked")
				AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
				String resultStatus = authResult.getResultStatus();

				// 判断resultStatus 为“9000”且result_code
				// 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
				if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
					// 获取alipay_open_id，调支付时作为参数extern_token 的value
					// 传入，则支付账户为该授权账户
					
					MyToastShowCenter.CenterToast(BlockTradeDetailActivity.this, "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
				} else {
					// 其他状态值则为授权失败
					MyToastShowCenter.CenterToast(BlockTradeDetailActivity.this, "授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
				}
				break;
			}
			default:
				break;
			}
		};
	};
	
	
	
	
	
	
	
	private LinearLayout llBlockTradeDetailBack;
	private BlockTradeDetail b;
	private IBlockTradeDetailPresenter presenter;
	private TextView tv_block_trade_goudiimage, tv_block_trade_detail_title, tv_block_trade_detail_content,
			tv_block_trade_detail_goudi, tv_block_trade_detail_date, tv_block_trade_detail_phone,
			tv_block_trade_detail_area, tv_block_trade_detail_yusuanjine, tv_block_trade_detail_hezuofangshi,
			tv_block_trade_detail_shiyongnianxian, tv_block_trade_detail_wuyeleixing,
			tv_block_trade_detail_jianzhumianji, tv_block_trade_detail_lianxiren, tv_block_trade_detail_address,
			tv_block_trade_detail_xiangxijieshao;
	private ImageView iv_block_trade_detail;
	
	private IPaySuccessPresenter paypresenter;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				b = (BlockTradeDetail) msg.obj;
				Show();
				break;

			default:
				break;
			}
		}
	};
	
	//添加勾地订单
	private IGouDiAddPresenter goudipresenter;

	protected String house_id;

	protected String userid;

	private UserInfo userinfo;

	private String order_no;
	
	private View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_detail);
		goudipresenter=new GouDiAddPresenter(this);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		setViews();
		setListener();

		String catid = getIntent().getStringExtra("catid");
		String id = getIntent().getStringExtra("id");

		iv_block_trade_detail.setImageResource(R.drawable.touxiang);
		presenter = new BlockTradeDetailPresenter(this);
		paypresenter=new PaySuccessPresenter(this);
		presenter.FindBlockTradedetail(catid, id);

	}

	private void setViews() {
		llBlockTradeDetailBack = (LinearLayout) findViewById(R.id.ll_block_trade_detail_back);
		tv_block_trade_detail_address = (TextView) findViewById(R.id.tv_block_trade_detail_address);
		tv_block_trade_detail_area = (TextView) findViewById(R.id.tv_block_trade_detail_area);
		tv_block_trade_detail_content = (TextView) findViewById(R.id.tv_block_trade_detail_content);
		tv_block_trade_detail_date = (TextView) findViewById(R.id.tv_block_trade_detail_date);
		tv_block_trade_detail_goudi = (TextView) findViewById(R.id.tv_block_trade_detail_goudi);
		tv_block_trade_detail_hezuofangshi = (TextView) findViewById(R.id.tv_block_trade_detail_hezuofangshi);
		tv_block_trade_detail_jianzhumianji = (TextView) findViewById(R.id.tv_block_trade_detail_jianzhumianji);
		tv_block_trade_detail_lianxiren = (TextView) findViewById(R.id.tv_block_trade_detail_lianxiren);
		tv_block_trade_detail_phone = (TextView) findViewById(R.id.tv_block_trade_detail_phone);
		tv_block_trade_detail_shiyongnianxian = (TextView) findViewById(R.id.tv_block_trade_detail_shiyongnianxian);
		tv_block_trade_detail_title = (TextView) findViewById(R.id.tv_block_trade_detail_title);
		tv_block_trade_detail_wuyeleixing = (TextView) findViewById(R.id.tv_block_trade_detail_wuyeleixing);
		tv_block_trade_detail_xiangxijieshao = (TextView) findViewById(R.id.tv_block_trade_detail_xiangxijieshao);
		tv_block_trade_detail_yusuanjine = (TextView) findViewById(R.id.tv_block_trade_detail_yusuanjine);
		tv_block_trade_goudiimage = (TextView) findViewById(R.id.tv_block_trade_goudiimage);
		iv_block_trade_detail = (ImageView) findViewById(R.id.iv_block_trade_detail);

	}

	private void setListener() {
		llBlockTradeDetailBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		tv_block_trade_goudiimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(userinfo!=null){
					
					GouDiXuZhi("在网上搜索了以下，有很多，很多，而了以下，过于陈旧，而且描述的过于简单。在网上搜索了以下，有很多这方面的教程，但大部分教程过于陈旧，而且描述的过于简单。在网上搜索了以下，有很多这方面的教程，但大部分教程过于陈旧，而且描述的过于简单。在网上搜索了以下，有很多这方面的教程，但大部分教程过于陈旧，而且描述的过于简单。在网上搜索了以下，有很多这方面的教程，但大部分教程过于陈旧，而且描述的过于简单。而且支付宝提供的接口一直在更新，可能支付宝那边是为了让接口更容易被调用吧，以前有些老的教程稍微跟现在接口有些不能“对号入座”，于是，我决定抽空写一篇关于调用支付宝接口的文章，跟大家分享，让大家以最快的速度掌握如何调用支付宝接口的方法。如果写的不好，请大家多多指教哦。");
				}else{
					startActivity(new Intent(BlockTradeDetailActivity.this, LoginActivity.class));
				}

			}
		});

	}

	@Override
	public void showData(BlockTradeDetail blocktradedetail) {

		Message msg = new Message();
		msg.what = 1;
		msg.obj = blocktradedetail;
		handler.sendMessage(msg);

	}

	private void Show() {

		if (b.getThumb().startsWith("http://www.tao")) {

			Glide.with(getApplicationContext()).load(b.getThumb()).into(iv_block_trade_detail);
		} else {
			String url = "http://www.taoshenfang.com" + b.getThumb();
			Log.e("yanglijun", "------========---------+++++++++" + url);
			Glide.with(getApplicationContext()).load(url).into(iv_block_trade_detail);
		}
		tv_block_trade_detail_title.setText(b.getTitle());
		tv_block_trade_detail_content.setText(b.getTitle());
		tv_block_trade_detail_goudi.setText(b.getGoudijine()+"元");

		String date = TimeTurnDate.getStringDate(b.getUpdatetime());
		tv_block_trade_detail_date.setText(date);
		tv_block_trade_detail_phone.setText(b.getTel());

		tv_block_trade_detail_area.setText(b.getCityname() + " " + b.getAreaname());
		tv_block_trade_detail_yusuanjine.setText(b.getZongjia() + "万元");
		tv_block_trade_detail_hezuofangshi.setText(b.getHezuofangshi());
		tv_block_trade_detail_shiyongnianxian.setText(b.getShiyongnianxian() + "年");
		tv_block_trade_detail_wuyeleixing.setText(b.getWuyetype());
		tv_block_trade_detail_jianzhumianji.setText(b.getZhandimianji() + "㎡");
		tv_block_trade_detail_lianxiren.setText(b.getUsername());
		tv_block_trade_detail_address.setText(b.getAddress());
		tv_block_trade_detail_xiangxijieshao.setText(b.getDescription());

	}
	
	
	
	public void GouDiXuZhi(String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(BlockTradeDetailActivity.this).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.CENTER);
		window.setContentView(R.layout.alerlog_goudi_info);
		window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		TextView tvGoudiInfo = (TextView) window.findViewById(R.id.tv_goudi_info);
		CheckBox cbCheck = (CheckBox) window.findViewById(R.id.cb_goudi_check);
		final Button btnGoudi = (Button) window.findViewById(R.id.btn_goudi_gou);
		btnGoudi.setEnabled(false);
		tvGoudiInfo.setText(title);
		tvGoudiInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
	cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked) {
				btnGoudi.setEnabled(true);
			} else {
				btnGoudi.setEnabled(false);
			}
			
			}
		});

		btnGoudi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BlockTradeDetailActivity.this.view=v;
				goudipresenter.AddGoudi(b.getId(),
						userinfo.getUserid(),
						b.getTitle(),
						b.getGoudijine());
				alertDialog.cancel();
			}
		});

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 支付宝支付业务
	 * 
	 * @param v
	 */
	public void payV2(View v) {
		if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
	
		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * orderInfo的获取必须来自服务端；
		 */
		
		total_amount=b.getGoudijine();
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, total_amount, b.getTitle(), "2.0", order_no);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
		
		final String orderInfo = orderParam + "&" + sign;
		
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(BlockTradeDetailActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());
				
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	
	/**
	 * 支付宝账户授权业务
	 * 
	 * @param v
	 */
	public void authV2(View v) {
		if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)
				|| TextUtils.isEmpty(TARGET_ID)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
						}
					}).show();
			return;
		}

		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * authInfo的获取必须来自服务端；
		 */
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, RSA_PRIVATE);
		final String authInfo = info + "&" + sign;
		Runnable authRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造AuthTask 对象
				AuthTask authTask = new AuthTask(BlockTradeDetailActivity.this);
				// 调用授权接口，获取授权结果
				Map<String, String> result = authTask.authV2(authInfo, true);

				Message msg = new Message();
				msg.what = SDK_AUTH_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread authThread = new Thread(authRunnable);
		authThread.start();
	}
	
	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public String getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
//		MyToastShowCenter.CenterToast(this, version);
		return version;
	}

	/**
	 * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
	 * 
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();
		/**
		 * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
		 * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
		 * 商户可以根据自己的需求来实现
		 */
		String url = "http://m.taobao.com";
		// url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);
	}

	
	@Override
	public void addGoudiSuccess(String info) {
		order_no=info;
		MyToastShowCenter.CenterToast(getApplicationContext(), "勾地成功");
		handler.postDelayed(new Runnable() {
			public void run() {
				Log.e("eeeeeeeeeeeeeee", "order_no="+order_no);
				payV2(view);
			}
		}, 500);
	}

	@Override
	public void addGoudiFail(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}

	@Override
	public void PayInfo(String info) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
