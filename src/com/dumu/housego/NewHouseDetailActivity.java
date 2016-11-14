package com.dumu.housego;

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
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.presenter.INewHouseDetailPresenter;
import com.dumu.housego.presenter.NewHouseDetailPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.INewHouseDetailView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewHouseDetailActivity extends Activity implements INewHouseDetailView {
	private INewHouseDetailPresenter presenter;
	private NewHouseDetail e;
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
				break;

			default:
				break;
			}
		}
	};
	private BitmapDescriptor mCurrentMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_detail);
		x.view().inject(this);
		initView();

		setListener();
		String id = getIntent().getStringExtra("Id");
		String catid = getIntent().getStringExtra("catid");
		presenter = new NewHouseDetailPresenter(this);
		presenter.FindNewHousedetail(catid, id);

	}

	private void initView() {
		mMapView = (MapView) findViewById(R.id.new_bmapView);
		mBaiduMAP = mMapView.getMap();

		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);

		UiSettings settings = mBaiduMAP.getUiSettings();
		settings.setAllGesturesEnabled(false);
		// settings.setOverlookingGesturesEnabled(false);
		// settings.setScrollGesturesEnabled(false);
		// settings.setZoomGesturesEnabled(false);

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
				MyToastShowCenter.CenterToast(getApplicationContext(), "点击了地图！！！");

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

	}

	@Override
	public void showNewHouseDetailData(NewHouseDetail news) {
		Message msg = new Message();
		msg.what = 1;
		msg.obj = news;

		handler.sendMessage(msg);
	}

}
