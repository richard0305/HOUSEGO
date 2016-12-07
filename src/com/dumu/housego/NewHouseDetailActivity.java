package com.dumu.housego;

import java.io.Serializable;
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
import com.dumu.housego.adapter.NewHouseDongTaiAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewDongTai;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.NewHtml;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YHQ;
import com.dumu.housego.entity.YHQinfo;
import com.dumu.housego.framgent.MessageFramgent;
import com.dumu.housego.presenter.GuanZhuHousePresenter;
import com.dumu.housego.presenter.GuanZhuNewPresenter;
import com.dumu.housego.presenter.IGuanZhuHousePresenter;
import com.dumu.housego.presenter.IGuanZhuNewPresenter;
import com.dumu.housego.presenter.INewHouseDetailPresenter;
import com.dumu.housego.presenter.IYHQGetyzmPresenter;
import com.dumu.housego.presenter.NewHouseDetailPresenter;
import com.dumu.housego.presenter.YHQGetYzmPresenter;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IGuanZhuHouseView;
import com.dumu.housego.view.IGuanZhuNewView;
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
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewHouseDetailActivity extends Activity implements   IGuanZhuNewView,IGuanZhuHouseView, BaseSliderView.OnSliderClickListener,IYHQGetYzmView,INewHouseDetailView {
	private INewHouseDetailPresenter presenter;
	private IYHQGetyzmPresenter yhqpresenter;
	private NewHouseDetail e;
	private List<YHQinfo> yhqs;
	
	private ListViewForScrollView lvLoupanDongtai;
	private TextView tvNoDongTai;
	
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

	@ViewInject(R.id.wb_huxingjieshao)
	WebView wbHuXing;
	@ViewInject(R.id.wb_fukuanfangshi)
	WebView wbfukuanfangshi;
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
	private	NewHtml html;
	private List<NewDongTai>dongtais;
	private List<NewDongTai>oneDongtai=new ArrayList<NewDongTai>();
	//轮播图
		private SliderLayout mDemoSlider;
		private List<Pics> pics=new ArrayList<Pics>();	
		private List<Pics> loupan=new ArrayList<Pics>();	
		private List<Pics> weizhi=new ArrayList<Pics>();	
		private List<Pics> yangban=new ArrayList<Pics>();	
		private List<Pics> shijing=new ArrayList<Pics>();	
		private List<Pics> xiaoqu=new ArrayList<Pics>();	
		
		
		//发送验证码变化
		Thread thread = null;
		private boolean tag12 = true;
		private int i = 60;
		public boolean isChange = false;
	
		private NewHouseDongTaiAdapter dongtaiAdapter;
		
		private TextView tv_html_fukuanfangshi,tv_newhousezixun;
		
		private RelativeLayout rl_loupanxiangqing,rl_new_loupandongtai;
		
		private RadioButton rb_newhouseguanzhu;
		
		private IGuanZhuHousePresenter guanzhupresenter;
		
		private IGuanZhuNewPresenter newpresenter;
		
		private String t;
		
		private List<NewHouseDetail> newhousedetails=new ArrayList<NewHouseDetail>();
		
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
		guanzhupresenter=new GuanZhuHousePresenter(this);
		yhqpresenter=new YHQGetYzmPresenter(this);
		newpresenter=new GuanZhuNewPresenter(this);

	}
	
	@Override
	protected void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		presenter.FindNewHousedetail(catid, id);
		presenter.GetYHQinfo(id, catid);
		if(userinfo!=null){
			userid=userinfo.getUserid();
			username=userinfo.getUsername();
			newpresenter.LoadGuanZhuNew(username, "new");
		}else{
			
		}
		
		super.onResume();
	}

	private void initView() {
		
		
		tv_newhousezixun=(TextView) findViewById(R.id.tv_newhousezixun);
		rb_newhouseguanzhu=(RadioButton) findViewById(R.id.rb_newhouseguanzhu);
		
		rl_loupanxiangqing=(RelativeLayout) findViewById(R.id.rl_loupanxiangqing);
		rl_new_loupandongtai=(RelativeLayout) findViewById(R.id.rl_new_loupandongtai);
		tv_html_fukuanfangshi=(TextView) findViewById(R.id.tv_html_fukuanfangshi);
		lvLoupanDongtai=(ListViewForScrollView) findViewById(R.id.lv_newhouse_loupandongtai);
		tvNoDongTai=(TextView) findViewById(R.id.wudongtai);		
				
				
		wbfukuanfangshi.getSettings().setJavaScriptEnabled(true);
		wbHuXing.getSettings().setJavaScriptEnabled(true);
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
		this.html=e.getHtml();
		this.dongtais=e.getDongtais();
		
		if(dongtais!=null){
			lvLoupanDongtai.setVisibility(View.VISIBLE);
			tvNoDongTai.setVisibility(View.GONE);
			oneDongtai.clear();
			oneDongtai.add(dongtais.get(0));
			dongtaiAdapter=new NewHouseDongTaiAdapter(oneDongtai, getApplicationContext());
			lvLoupanDongtai.setAdapter(dongtaiAdapter);
		}else{
			lvLoupanDongtai.setVisibility(View.GONE);
			tvNoDongTai.setVisibility(View.VISIBLE);
		}
		
		
		if(html!=null){
			if(!html.getHuxingintro().equals("")&&html.getHuxingintro()!=null){
			String intro=html.getHuxingintro();
			String intro1=intro.replace("/d", "http://www.taoshenfang.com/d");
			Log.e("intro1", "intro1="+intro1);
			String customHtml = "<html>"
								+ "<head>"
								+ "<style type=text/css>"
									+ "body{font-size: 12; color: #999;} "
									+ "img{width:200;height:auto;}"
								+ "</style>"
								+ "</head> "
								+ "<body>"+intro1+"</body>"
										+ "</html>";
			wbHuXing.loadData(customHtml,"text/html; charset=UTF-8", null);
//			mLJWebView.loadDataWithBaseURL("about:blank", customHtml, "text/html", "utf-8", null);
			}
			
			if(!html.getFukuanfangshi().equals("")&&html.getFukuanfangshi()!=null){
				
				tv_html_fukuanfangshi.setVisibility(View.GONE);
				wbfukuanfangshi.setVisibility(View.VISIBLE);
				
				String fukun=html.getFukuanfangshi();
				String fukun2=fukun.replace("/d", "http://www.taoshenfang.com/d");
				Log.e("fukun2", "fukun2="+fukun2);
				String customHtml = "<html>"
									+ "<head>"
									+ "<style type=text/css>"
										+ "body{font-size: 12; color: #999;} "
										+ "img{width:200;height:auto;}"
									+ "</style>"
									+ "</head>"
									+ "<body>"+fukun2+"</body>"
											+ "</html>";
				wbfukuanfangshi.loadData(customHtml,"text/html; charset=UTF-8", null);
//				mLJWebView.loadDataWithBaseURL("about:blank", customHtml, "text/html", "utf-8", null);
				}else{
					tv_html_fukuanfangshi.setVisibility(View.VISIBLE);
					wbfukuanfangshi.setVisibility(View.GONE);
				}
		}
		
		try {
			
			if(!newhousedetails.equals(null)){
				for (NewHouseDetail i : newhousedetails) {
						if(id.equals(i.getId())){
						rb_newhouseguanzhu.setChecked(true);
						rb_newhouseguanzhu.setText("已关注");
						t="0";
						}else{
							t="1";
						}
				}
				
			}
			
			
			
			
			
			
			
			
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
			tvNewhousedetailChanquannianxian.setText(e.getChanquannianxian()+"年");
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
		
		tv_newhousezixun.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		rb_newhouseguanzhu.setOnClickListener(new OnClickListener() {
			
			private String fromid;
			private String fromtable;
			private String type;
		
			@Override
			public void onClick(View v) {
				if(userinfo!=null){
					fromtable="new";
					type="新房";
					guanzhupresenter.LoadGuanZhuHouse(id, fromtable, userid, username, type, t);
				}else{
					startActivity(new Intent(getApplicationContext(), LoginActivity.class));
				}
				
				
			}
		});
		
		rl_loupanxiangqing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),HouseAllDetailsActivity.class);
				i.putExtra("xiangqing", (Serializable)e);
				i.putExtra("TAG", "2");
				startActivity(i);
				
			}
		});
		
		rl_new_loupandongtai.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),HouseAllDetailsActivity.class);
				if(dongtais!=null){
					i.putExtra("dongtai", (Serializable)dongtais);
					i.putExtra("dongtaiNull", "y");
				}else{
					i.putExtra("dongtaiNull", "w");
				}
				i.putExtra("TAG", "1");
				startActivity(i);
				
			}
		});
		
		
		
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
		final Button btsend=(Button) view.findViewById(R.id.btn_yhq_sendcode);                                                                                                                                                                                                                                                                                                                                                                                                                                     
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
					isChange = true;

					changeBtnGetCode();
				}
			}

			private  void changeBtnGetCode() {
				thread = new Thread() {
					@Override
					public void run() {
						if (tag12) {
							while (i > 0) {
								i--;
								if (NewHouseDetailActivity.this == null) {
									break;
								}
								// ���ı������ݸı�ʱ������ѭ����
								if (isChange && !btsend.isClickable()) {
									isChange = false;
									break;
								}

								NewHouseDetailActivity.this.runOnUiThread(new Runnable() {
									@Override
									public void run() {
										btsend.setText("重发(" + i + "s)");
										btsend.setClickable(false);
									}
								});
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									throw new RuntimeException(e);
								}
							}
							tag12 = false;
						}

						i = 60;
						tag12 = true;
						if (NewHouseDetailActivity.this != null) {
							NewHouseDetailActivity.this.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									btsend.setText("发送验证码");
									btsend.setClickable(true);
								}
							});
						}
					};
				};
				thread.start();

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
				Mpop.dismiss();
				llpopupSpinnerMore.clearAnimation();
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

	@Override
	public void GuanZhuSuccess(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		rb_newhouseguanzhu.setText("已关注");
		
	}

	@Override
	public void GuanZhuFail(String errorinfo) {
		MyToastShowCenter.CenterToast(getApplicationContext(), errorinfo);
		
	}

	@Override
	public void showGuanZhuSuccess(List<NewHouseDetail> newhousedetails) {
		this.newhousedetails=newhousedetails;
		
	}

	@Override
	public void showGuanZhuFail(String errorinfo) {
		
		
	}

	


	
	
	
	
}
