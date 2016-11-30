package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YHQ;
import com.dumu.housego.entity.YHQinfo;
import com.dumu.housego.presenter.INewHouseDetailPresenter;
import com.dumu.housego.presenter.IYHQGetyzmPresenter;
import com.dumu.housego.presenter.NewHouseDetailPresenter;
import com.dumu.housego.presenter.YHQGetYzmPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.INewHouseDetailView;
import com.dumu.housego.view.IYHQGetYzmView;import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewHouseDetailActivity extends Activity implements BaseSliderView.OnSliderClickListener,IYHQGetYzmView,INewHouseDetailView {
	private INewHouseDetailPresenter presenter;
	private IYHQGetyzmPresenter yhqpresenter;
	private NewHouseDetail e;
	private List<YHQinfo> yhqs;
	private TextView tv_yhq_none,tv_new_yhq_1,tv_new_yhq_2;
	private RelativeLayout rl_new_yhq;
	@ViewInject(R.id.ll_new_house_detail_back)
	LinearLayout llNewHouseDetailBack;
	@ViewInject(R.id.tv_newhousedetail_price)
	TextView tvNewhousedetailPrice;
	@ViewInject(R.id.iv_new_house_detail)
	ImageView ivNewHouse;
	@ViewInject(R.id.tv_new_house_detail_title)
	TextView tvTitle;
	@ViewInject(R.id.tv_newhousedetail_title)
	TextView tvTitle1;
	@ViewInject(R.id.tv_newhousedetail_housetype)
	TextView tvHousetype;
	@ViewInject(R.id.tv_newhousedetail_kaipanshijian)
	TextView tvkaipanshijian;
	@ViewInject(R.id.tv_newhousedetail_jiaofangshijian)
	TextView tvjiaofangshijian;
	@ViewInject(R.id.tv_newhousedetail_updatetime)
	TextView tvNewhousedetailUpdatetime;
	@ViewInject(R.id.tv_newhousedetail_maintype)
	TextView tvNewhousedetailMaintype;
	@ViewInject(R.id.tv_newhousedetail_loupandizhi)
	TextView tvNewhousedetailLoupandizhi;

	@ViewInject(R.id.tv_newhousedetail_kaifashang)
	TextView tvNewhousedetailKaifashang;
	@ViewInject(R.id.tv_newhousedetail_zuixinkaipan)
	TextView tvNewhousedetailZuixinkaipan;
	@ViewInject(R.id.tv_newhousedetail_chanquannianxian)
	TextView tvNewhousedetailChanquannianxian;
	@ViewInject(R.id.tv_newhousedetail_zuixinjiaofang)
	TextView tvNewhousedetailZuixinjiaofang;
	@ViewInject(R.id.tv_newhousedetail_dianping)
	TextView tvNewhousedetailDianping;

	private BaiduMap mBaiduMAP;
	private MapView mMapView;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				e = (NewHouseDetail) msg.obj;
				
				Show();
				showPopWindowYHQ();
				break;

			default:
				break;
			}
		}
	};
	private BitmapDescriptor mCurrentMarker;
	private String id;
private 	String catid ;
	private String coupon_id;
	private String coupon_id1;
	private String coupon_id2;
	private UserInfo userinfo;
	
	private YHQinfo y1;
	private YHQinfo y2;
	private LinearLayout llpopupSpinnerMore;
	private PopupWindow Mpop;
	private String userid;
	private String username;
	private String title="";
	
	
	//轮播图
		private SliderLayout mDemoSlider;
		private List<Pics> pics=new ArrayList<Pics>();	
		private List<Pics> loupan=new ArrayList<Pics>();	
		private List<Pics> weizhi=new ArrayList<Pics>();	
		private List<Pics> yangban=new ArrayList<Pics>();	
		private List<Pics> shijing=new ArrayList<Pics>();	
		private List<Pics> xiaoqu=new ArrayList<Pics>();	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_detail);
		x.view().inject(this);
		initView();
		
		setListener();
		 id = getIntent().getStringExtra("Id");
		 catid = getIntent().getStringExtra("catid");
		 
		presenter = new NewHouseDetailPresenter(this);
		yhqpresenter=new YHQGetYzmPresenter(this);
	

	}
	
	@Override
	protected void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		presenter.FindNewHousedetail(catid, id);
		presenter.GetYHQinfo(id, catid);
		
		super.onResume();
	}

	private void initView() {
		rl_new_yhq=(RelativeLayout) findViewById(R.id.rl_new_yhq);
		tv_yhq_none=(TextView) findViewById(R.id.tv_yhq_none);
		tv_new_yhq_1=(TextView) findViewById(R.id.tv_new_yhq_1);
		tv_new_yhq_2=(TextView) findViewById(R.id.tv_new_yhq_2);
		mDemoSlider=(SliderLayout) findViewById(R.id.slider_new);
		
		
		mMapView = (MapView) findViewById(R.id.new_bmapView);
		mBaiduMAP = mMapView.getMap();

		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);

		UiSettings settings = mBaiduMAP.getUiSettings();
		settings.setAllGesturesEnabled(false);

		/**
		 * �ı��ͼ�ı�����
		 */
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
		mBaiduMAP.setMapStatus(msu);

	}

	protected void Show() {

		try {
			if (e.getThumb().startsWith("http://www.tao")) {
				Glide.with(this).load(e.getThumb()).into(ivNewHouse);
			} else {
				String url = "http://www.taoshenfang.com" + e.getThumb();
				Glide.with(this).load(url).into(ivNewHouse);
			}
			tvNewhousedetailLoupandizhi.setText(e.getLoupandizhi());
			tvNewhousedetailMaintype.setText(e.getZhulihuxing());
			tvNewhousedetailUpdatetime.setText(TimeTurnDate.getStringDate(e.getUpdatetime()));

			tvHousetype.setText(e.getFangwuyongtu() + "");
			tvjiaofangshijian.setText(e.getJiaofangdate() + "");
			tvkaipanshijian.setText(e.getKaipandate() + "");
			tvNewhousedetailPrice.setText(e.getJunjia() + "元/平米");
			tvTitle.setText(e.getTitle() + "");
			tvTitle1.setText(e.getTitle() + "");

			tvNewhousedetailKaifashang.setText(e.getKaifashang());
			;
			tvNewhousedetailZuixinkaipan.setText(e.getKaipandate());
			;
			tvNewhousedetailChanquannianxian.setText(e.getChanquannianxian());
			;
			tvNewhousedetailZuixinjiaofang.setText(e.getJiaofangdate());
			;
			tvNewhousedetailDianping.setText(e.getDianping());

			/**
			 * ���ðٶȵ�ͼ ��λ����Դ��γ��
			 */

			String jwd = e.getJingweidu();
			String[] arr = jwd.split(",");
			String j = arr[0].toString();
			String w = arr[1].toString();

			double latitude = Double.valueOf(j);
			double longitude = Double.valueOf(w);

			LatLng latLng = new LatLng(longitude, latitude);

			MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
			
			loupan=e.getLoupantupian();
			yangban=e.getYangbantu();
			shijing=e.getShijingtu();
			weizhi=e.getWeizhitu();
			xiaoqu=e.getXiaoqutu();
			
			pics.addAll(loupan);
			pics.addAll(yangban);
			pics.addAll(shijing);
			pics.addAll(weizhi);
			pics.addAll(xiaoqu);

			
			//轮播图
			 for(Pics p : pics ){
		            TextSliderView textSliderView = new TextSliderView(this);
		            // initialize a SliderLayout
		            textSliderView
		                    .description(p.getAlt())
		                    .image(p.getUrl())
		                    .setScaleType(BaseSliderView.ScaleType.Fit)
		                    .setOnSliderClickListener(this);

		            //add your extra information
		            textSliderView.getBundle()
		                    .putString("extra",p.getAlt());

		           mDemoSlider.addSlider(textSliderView);
		        }
		        
		        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
		        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
		        mDemoSlider.setDuration(4000);
			
			
			
			
			
			

			/**
			 * 
			 */
			// MapStatus.Builder builder=new MapStatus.Builder();
			// mBaiduMAP.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
			/**
			 * 
			 */

			// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩
			mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
			// ����MarkerOption�������ڵ�ͼ�����Marker
			OverlayOptions option = new MarkerOptions().position(latLng).icon(mCurrentMarker);
			// �ڵ�ͼ�����Marker������ʾ
			mBaiduMAP.addOverlay(option);
			mBaiduMAP.animateMapStatus(msu);

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("TAG", "TAG" + e.getMessage());
		}

	}

	private void setListener() {
		llNewHouseDetailBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {

				if (e.getJingweidu() == null) {
					MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
				} else {
					String jwd = e.getJingweidu();
					String[] arr = jwd.split(",");
					String j = arr[0].toString();
					String w = arr[1].toString();

					double latitude = Double.valueOf(j);
					double longitude = Double.valueOf(w);

					Intent i = new Intent(getApplicationContext(), BaiduMapActivity.class);
					i.putExtra("latitude", latitude);
					i.putExtra("longitude", longitude);
					startActivity(i);
				}

			}
		});
		
		
		tv_new_yhq_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				coupon_id=coupon_id1;
				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
					startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
				}else{
//					YHQinfo(e.getTitle());
					Animation anim = AnimationUtils.loadAnimation(NewHouseDetailActivity.this,
							R.anim.activity_translate_out);
					llpopupSpinnerMore.setAnimation(anim);
					Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
				}
			}
		});
		tv_new_yhq_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				coupon_id=coupon_id2;
				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
					startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
				}else{
//					YHQinfo(e.getTitle());
					Animation anim = AnimationUtils.loadAnimation(NewHouseDetailActivity.this,
							R.anim.activity_translate_out);
					llpopupSpinnerMore.setAnimation(anim);
					Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
				}
			}
		});

	}

	@Override
	public void showNewHouseDetailData(NewHouseDetail news) {
		Message msg = new Message();
		msg.what = 1;
		msg.obj = news;

		handler.sendMessage(msg);
	}

	@Override
	public void YHQgetInfoSuccess(List<YHQinfo> yhqs) {
		this.yhqs=yhqs;
		rl_new_yhq.setVisibility(View.VISIBLE);
		tv_yhq_none.setVisibility(View.GONE);
		switch (yhqs.size()) {
		case 1:
			tv_new_yhq_1.setVisibility(View.VISIBLE);
			tv_new_yhq_2.setVisibility(View.GONE);
			YHQinfo y= yhqs.get(0);
			this.coupon_id1=y.getId();
			tv_new_yhq_1.setText(y.getTitle());
			break;
		case 2:
			tv_new_yhq_1.setVisibility(View.VISIBLE);
			tv_new_yhq_2.setVisibility(View.VISIBLE);
			y1= yhqs.get(0);
			this.coupon_id1=y1.getId();
			 y2= yhqs.get(1);
			this.coupon_id2=y2.getId();
			tv_new_yhq_1.setText(y1.getTitle());
			tv_new_yhq_2.setText(y2.getTitle());
			break;
		default:
			break;
		}
		
		
	}

	@Override
	public void YHQgetInfoFail(String info) {
		rl_new_yhq.setVisibility(View.GONE);
		tv_yhq_none.setVisibility(View.VISIBLE);
	}

	
	
	


	/**
	 * 优惠券弹窗
	 */
	private void showPopWindowYHQ() {
		Mpop = new PopupWindow(NewHouseDetailActivity.this);
		View view = getLayoutInflater().inflate(R.layout.popwin_new_yhq, null);
		llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_yhq);
		Mpop.setWidth(LayoutParams.MATCH_PARENT);
		Mpop.setHeight(LayoutParams.MATCH_PARENT);
		Mpop.setBackgroundDrawable(new BitmapDrawable());
		Mpop.setFocusable(true);
		Mpop.setOutsideTouchable(true);
		Mpop.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_yhq);
		TextView tvTitle=(TextView) view.findViewById(R.id.tv_yhq_title);
		final EditText etname=(EditText) view.findViewById(R.id.et_goufangren);
		final EditText etphone=(EditText) view.findViewById(R.id.et_shoujihao);
		final EditText etyzm=(EditText) view.findViewById(R.id.et_yanzhenma);
		Button btsend=(Button) view.findViewById(R.id.btn_yhq_sendcode);
		TextView tvLijiGou=(TextView) view.findViewById(R.id.tv_lijiqianggou);
		
	
		
		tvTitle.setText(e.getTitle());
		btsend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String phone=etphone.getText().toString();
				if(phone.equals("")||phone==null){
					//电话为空时，提示电话必须填
					MyToastShowCenter.CenterToast(getApplicationContext(), "请填写手机号！");
				}else{
					//发送验证码
					yhqpresenter.YzmInfo(phone);
				}
			}
		});
		
		//立即抢购
		tvLijiGou.setOnClickListener(new OnClickListener() {
	

			@Override
			public void onClick(View v) {
				//设置弹窗的title
				final String mob=etyzm.getText().toString().trim();
				final String buyname=etname.getText().toString();
				final String buytel=etphone.getText().toString();
				final String yzm=etyzm.getText().toString();
				userid=userinfo.getUserid();
				username=userinfo.getUsername();
				Log.e("aaaaaaaaaaaaaaaaaa", "id="+id  + " coupon_id="+ coupon_id  +" userid="+userid+" buyname="+  buyname+" buytel=" +buytel+ " username="+username+" yzm="+yzm);
				yhqpresenter.AddYHQ(id, coupon_id, userid, buyname, buytel, username, yzm);
			}
		});
		
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Mpop.dismiss();
				llpopupSpinnerMore.clearAnimation();
			}
		});
		
	}
	
	
	
	
	
	
	
	
	
	//发送验证码请求返回数据
	@Override
	public void YzmInfo(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}
	//发送添加优惠券请求，成功返回数据
	@Override
	public void AddYhqsuccess(YHQ y) {
		
		MyToastShowCenter.CenterToast(getApplicationContext(), "购买成功");
	}
	
	
	//发送添加优惠券请求，失败返回数据
	@Override
	public void AddYhqfail(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
	}

	@Override
	public void onSliderClick(BaseSliderView slider) {
		// TODO Auto-generated method stub
		
	}

	


	
	
	
	
}
