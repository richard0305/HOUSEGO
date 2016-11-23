package com.dumu.housego.framgent;

import java.util.List;
import java.util.Map;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.dumu.housego.R;
import com.dumu.housego.adapter.AgentSubmitErShouAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.GouDiInfo;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.pay.AuthResult;
import com.dumu.housego.pay.H5PayDemoActivity;
import com.dumu.housego.pay.PayResult;
import com.dumu.housego.pay.util.OrderInfoUtil2_0;
import com.dumu.housego.presenter.GoudiListPresenter;
import com.dumu.housego.presenter.IGoudiListPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IGoudiListView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ATGouDiListFragment extends Fragment implements IGoudiListView{
	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016072301657641";
	
	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088421264539813";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "ruianxingye888@126.com";

	/** 商户私钥，pkcs8格式 */
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhk"
			+ "iG9w0BAQEFAASCAmAwggJcAgEAAoGBAMVCIS8JtPMwQtwM4clQKW/NAev"
			+ "AZCxnNUsf9z2ZSpSBMoOzg7Xj4NHCfuwlcXmobLOOlZ3vl6m3kve1H/Ckw+0lxij"
			+ "fP3kgNsAVJaxx+f4Y+ulXgys0xyBqxK8+yjOicl7bXCQZVSuOpQ1U/nW+8jT30paBFzW"
			+ "OtWVJh+rMFZTZAgMBAAECgYA7W5fyuKAL4gTshK/YzVd6vh0K3R0Hw5Lk5VyX2632nB"
			+ "4gRX+n10fKtxVMaEQKUOVM7uYU3Yks2AHQql9PIbWW+tvwhQl7tf3GWDA5RdUQ+8B"
			+ "oOK66czwelJvHZyrnwiLk2xUogWVhm1/filvJzwXNcZWmbp9kiDQ8xOHjd7P2AQJBAOLtLLl"
			+ "6XAM194fTI5q0UIPNO5rJIA8gnZ7lByfBBDQ+TptHe3C/tE8QIPy7jm8wWl0Yzfex7YPpeODgvG"
			+ "MqbEECQQDeh+MezJFxkYtLdkP2aM6pMLDMsbHqYmbMucpmUgKX4I5mm77PX3BBNguqRYYIo5Age0r"
			+ "MYVvvNvqVJMgfymKZAkEAjzKzomfAYJZRmXh3Y0yd8k40ary4alNeo8JK/Z8jlAQtfndhQgj3M10u"
			+ "M833V9rfaxgYAVJaW3+dM+sddkZzAQJAe++KmtisGGhLABg5+b6derVXgmllAXkUb+j5xMrgDt5N8O/E8"
			+ "ZHh3y/Oh2J5J86QssZ3/iQrI6unG/GEU9BP4QJAerlafELCke1mP7U/Z5Z4IZvZi+1d4JwO4taB+3pyAK2M+n"
			+ "WVN3F9ZyLw1GmVox0N2pvV/ZieTAwFCcNTLuicEA==";
	
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

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
					MyToastShowCenter.CenterToast(getActivity(), "支付成功");
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					MyToastShowCenter.CenterToast(getActivity(), "支付失败");
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
					
					MyToastShowCenter.CenterToast(getActivity(), "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
				} else {
					// 其他状态值则为授权失败
					MyToastShowCenter.CenterToast(getActivity(), "授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
				}
				break;
			}
			default:
				break;
			}
		};
	};
	
	
	
	
	
	private LinearLayout llAtGoudilistBack;
	private ListView lvAtGoudilist;
	private List<GouDiInfo> goudis;
	private GouDiListAdapter listadapter;
	private IGoudiListPresenter listpresenter;
	private String userid;
	private UserInfo userinfo;
	private int posi;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_at_goudilist, null);
		initview(view);
		setListener();
		return view;
	}
	
	@Override
	public void onResume() {
		listpresenter=new GoudiListPresenter(this);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		userid=userinfo.getUserid();
		listpresenter.GoudiList(userid);
		super.onResume();
	}
	
	private void initview(View view) {
		llAtGoudilistBack=(LinearLayout) view.findViewById(R.id.ll_at_goudilist_back);
		lvAtGoudilist=(ListView) view.findViewById(R.id.lv_at_goudilist);
		
	}

	private void setListener() {
		llAtGoudilistBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
	}

	@Override
	public void GouDilistSuccess(List<GouDiInfo> goudis) {
		this.goudis=goudis;
		listadapter=new GouDiListAdapter(goudis, getActivity());
		lvAtGoudilist.setAdapter(listadapter);
		
	}

	@Override
	public void GouDiFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
	}
	
	@Override
	public void GouDiDelete(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		if(info.equals("订单删除成功")){
			mHandler.postDelayed(new Runnable() {
				public void run() {
					goudis.remove(posi);
					listadapter.notifyDataSetChanged();
				}
			}, 500);
		}
	}
	
	
	
	/**
	 * adapter
	 * @author Administrator
	 *
	 */
	
	public class GouDiListAdapter extends BaseAdapter {
		private List<GouDiInfo> goudis;
		private Context context;
		private LayoutInflater Inflater;
		private GouDiInfo goudi;
		ViewHolder1 holder1;
		ViewHolder0 holder0;
		public GouDiListAdapter(List<GouDiInfo> goudis, Context context) {
			super();
			this.goudis = goudis;
			this.context = context;
			this.Inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return goudis.size();
		}

		@Override
		public GouDiInfo getItem(int position) {

			return goudis.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			posi=position;
			goudi =(GouDiInfo)goudis.get(position);
			int type = getItemViewType(position);
			if (convertView == null) {
				
				switch (type) {
				
				case 0:
					holder0=new ViewHolder0();
					convertView = Inflater.inflate(R.layout.item_goudi_list2, null);
					holder0.iv_delete=(ImageView) convertView.findViewById(R.id.iv_delete);
					holder0.tv_goudi_dingdanhao=(TextView) convertView.findViewById(R.id.tv_goudi_dingdanhao);
					holder0.tv_goudi_loupan=(TextView) convertView.findViewById(R.id.tv_goudi_loupan);
					holder0.tv_goudi_paystatus=(TextView) convertView.findViewById(R.id.tv_goudi_paystatus);
					holder0.tv_zhuangtai=(TextView) convertView.findViewById(R.id.tv_zhuangtai);
					
					convertView.setTag(holder0);
					break;

				case 1:
					holder1=new ViewHolder1();
					convertView = Inflater.inflate(R.layout.item_goudi_list, null);
					holder1.tv_zhifu_liushuihao=(TextView) convertView.findViewById(R.id.tv_zhifu_liushuihao);
					holder1.tv_zhifu_loupan=(TextView) convertView.findViewById(R.id.tv_zhifu_loupan);
					holder1.tv_zhifu_zhanghao=(TextView) convertView.findViewById(R.id.tv_zhifu_zhanghao);
					holder1.tv_zhifu_zhifujine=(TextView) convertView.findViewById(R.id.tv_zhifu_zhifujine);
					holder1.tv_zhifu_zhifustatus=(TextView) convertView.findViewById(R.id.tv_zhifu_zhifustatus);
					holder1.tv_zhifu_zhifutime=(TextView) convertView.findViewById(R.id.tv_zhifu_zhifutime);
					holder1.tvZhifuDingdanhao=(TextView) convertView.findViewById(R.id.tv_zhifu_dingdanhao);
					convertView.setTag(holder1);
					break;
				}
			} else {
				switch (type) {
	            case 0:
	                holder0 = (ViewHolder0) convertView.getTag();
	                break;
	            case 1:
	                holder1 = (ViewHolder1) convertView.getTag();
	                break;
	            }
			}
			
			
			   switch (type) {
		        case 0:
		            holder0.iv_delete.setOnClickListener(new OnClickListener() {
						private String id;

						@Override
						public void onClick(View v) {
							id=goudi.getId();
							listpresenter.GouDiDelete(id, userid);
						}
					});
		            
		            holder0.tv_zhuangtai.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							payV2(v);
						}
					});
		            
		            holder0.tv_goudi_dingdanhao.setText(goudi.getOrder_no());
		            holder0.tv_goudi_loupan.setText(goudi.getTitle());
		            
		            holder0.tv_goudi_paystatus.setText("未支付");
		            
		            break;
		        case 1:
		            holder1.tv_zhifu_liushuihao.setText(goudi.getTrade_no());
		            holder1.tv_zhifu_loupan.setText(goudi.getTitle());
		            holder1.tv_zhifu_zhanghao.setText(goudi.getBuyer_email());
		            holder1.tv_zhifu_zhifujine.setText(goudi.getJine());
		            holder1.tv_zhifu_zhifustatus.setText("已支付");
		            //转换时间戳
		            long timeStamp=Long.valueOf(goudi.getPaytime());
		            String time=AgentSubmitErShouAdapter.formatData(timeStamp);
		            holder1.tv_zhifu_zhifutime.setText(time);
		            holder1.tvZhifuDingdanhao.setText(goudi.getOrder_no());
		            
		            break;
		        }
		
			return convertView;

		}

		class ViewHolder1 {
			TextView tvZhifuDingdanhao;
			TextView tv_zhifu_loupan;
			TextView tv_zhifu_liushuihao;
			TextView tv_zhifu_zhanghao;
			TextView tv_zhifu_zhifujine;
			TextView tv_zhifu_zhifutime;
			TextView tv_zhifu_zhifustatus;
		}
		
		class ViewHolder0 {
			TextView tv_goudi_dingdanhao;
			TextView tv_goudi_loupan;
			TextView tv_goudi_paystatus;
			TextView tv_zhuangtai;
			ImageView iv_delete;
		}
		
		 @Override
		    public int getViewTypeCount() {
		        return 2;
		    }
		 
		  @Override
		    public int getItemViewType(int position) {
		        goudi = (GouDiInfo) getItem(position);
		        if (goudi.getPay_status().equals("0")) {
		            return 0;
		        } else {
		            return 1;
		        }
		    }
	}
	
	
	
	//

	/**
	 * 支付宝支付业务
	 * 
	 * @param v
	 */
	public void payV2(View v) {
		if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
			new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							getActivity().finish();
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
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,goudis.get(posi).getJine(), goudis.get(posi).getTitle(), getSDKVersion(), goudis.get(posi).getOrder_no());
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
		final String orderInfo = orderParam + "&" + sign;
		
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(getActivity());
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
			new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
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
				AuthTask authTask = new AuthTask(getActivity());
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
		PayTask payTask = new PayTask(getActivity());
		String version = payTask.getVersion();
//		MyToastShowCenter.CenterToast(getActivity(), version);
		return version;
	}

	/**
	 * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
	 * 
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(getActivity(), H5PayDemoActivity.class);
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

	
	
	
	
	
	
	
	
	
}
