package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.ErShouFangMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.adapter.MyYuYueDateLsitAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.presenter.IMyYuYueDeletePresenter;
import com.dumu.housego.presenter.IMyYuYueHousePresenter;
import com.dumu.housego.presenter.MyYuYueDeletePresenter;
import com.dumu.housego.presenter.MyYuYueHousePresenter;
import com.dumu.housego.util.CarouselPagerAdapter;
import com.dumu.housego.util.CarouselViewPager;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.YuYueDataComparatpor;
import com.dumu.housego.view.IMyYuYueDeleteView;
import com.dumu.housego.view.IMyYuYueHouseView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LookDateFragment extends Fragment implements IMyYuYueDeleteView,IMyYuYueHouseView,CarouselViewPager.OnPageChangeListener{
	private Button btnHouseDateLogin;
	private CarouselViewPager mCarouselView;
	private List<ImageView> ivList = new ArrayList<ImageView>();
    private int[] ivIds = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
    private UserInfo userinfo;
    private ImageView[] indicationPoint;//ָʾ��ؼ�
    private LinearLayout pointLayout;
    private LinearLayout ll_kanfangdate;
    private List<YuYueData>yuyuedatas;
  private ListView  lv_kandangdate;
  private RelativeLayout ll_kanfangdate_list;
  private MyYuYueDateLsitAdapter yuyueadapter;
  private IMyYuYueHousePresenter yuyuepresenter;
  private IMyYuYueDeletePresenter deleteyuyuepresenter;
  private String username; 
  private String userid;
  
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				YuYueData y=yuyuedatas.get(msg.arg1);
				
				if(y.getLock().equals("0")){
					yuyuedatas.remove(msg.arg1);
					yuyueadapter.notifyDataSetChanged();
				}
			
				;
				break;

			default:
				break;
			}
		}
	};
	
	
  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_look_date, null);
		 initViews(rootView);
	        initData();
	        initListener();
	        yuyuepresenter=new MyYuYueHousePresenter(this);
	        deleteyuyuepresenter=new MyYuYueDeletePresenter(this);
	        
	        return rootView;
	    }
	
	@Override
	public void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		if(userinfo!=null){
			
			username= userinfo.getUsername();
			userid=userinfo.getUserid();
			yuyuepresenter.LoadMyYuYueHosue(username);
		}else{
			
		}
		super.onResume();
	}

	    private void initListener() {
	    	btnHouseDateLogin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), ErShouFangMainActivity.class));
					
					
				}
			});
	    	
	    	lv_kandangdate.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
					String yuyueid=yuyuedatas.get(position).getId();
					NewAlertDialog(yuyueid,position);
					
					return true;
				}
			});
	    	
	    	lv_kandangdate.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String Id=yuyuedatas.get(position).getFromid();
					Intent i=new Intent(getActivity(), ErShouFangDetailsActivity.class);
					i.putExtra("catid", "6");
					i.putExtra("id",Id );
					startActivity(i);
					
				}
			});
		
	}

		protected void NewAlertDialog(final String id ,final int position) {
			final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();  
			alertDialog.show();  
			Window window = alertDialog.getWindow();  
			window.setContentView(R.layout.dialog_main_info);  
			TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);  
			tv_title.setText("是否取消预约");  
			TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);  
			Button btnCancle=(Button) window.findViewById(R.id.btn_dialog_cancle);
			Button btnSure=(Button) window.findViewById(R.id.btn_dialog_sure);
			btnCancle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					alertDialog.cancel();
				}
			});
			
			
			btnSure.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					deleteyuyuepresenter.DeleteMyYuYueHosue(id, userid, username);
					Message msg=new Message();
					msg.what=1;
					msg.arg1=position;
					handler.sendMessage(msg);
					
					
					alertDialog.cancel();
				}
			});
			
			
		}

		private void initViews(View rootView) {
	        mCarouselView = (CarouselViewPager) rootView.findViewById(R.id.mCarouselView);
	        pointLayout = (LinearLayout) rootView.findViewById(R.id.pointLayout);
	        btnHouseDateLogin=(Button) rootView.findViewById(R.id.btn_house_Login);
	        ll_kanfangdate=(LinearLayout) rootView.findViewById(R.id.ll_kanfangdate);
	        lv_kandangdate=(ListView) rootView.findViewById(R.id.lv_kandangdate);
	        ll_kanfangdate_list=(RelativeLayout) rootView.findViewById(R.id.ll_kanfangdate_list);
	        
	    }

	    private void initData() {
	        for (int i = 0; i < ivIds.length; i++) {
	            ImageView iv = new ImageView(getActivity());
	            iv.setImageResource(ivIds[i]);
	            ivList.add(iv);
	        }

	        //��̬����ָʾ��
	        indicationPoint = new ImageView[ivList.size()];
	        for (int i = 0; i < indicationPoint.length; i++) {
	            ImageView point = new ImageView(getActivity());
	            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(10, 10);
	            layout.setMargins(10, 0, 10, 0);
	            point.setLayoutParams(layout);

	            indicationPoint[i] = point;
	            if (i == 0) {
	                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
	            } else {
	                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
	            }
	            pointLayout.addView(point);
	        }

	        mCarouselView.setAdapter(new CarouselPagerAdapter(ivList));
	        mCarouselView.addOnPageChangeListener(this);
	        //����ͼƬչʾ��ʱ��
	        mCarouselView.setDisplayTime(2000);
	        //ͼƬ��ʼ�ֲ�
	        mCarouselView.start();

	    }

	    @Override
	    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	    }

	    @Override
	    public void onPageSelected(int position) {
	        setPointColor(position % ivList.size());

	    }

	    @Override
	    public void onPageScrollStateChanged(int state) {

	    }

	    private void setPointColor(int selectItem) {
	        for (int i = 0; i < indicationPoint.length; i++) {
	            if (i == selectItem) {
	                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
	            } else {
	                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
	            }

	        }
	    }

		@Override
		public void LoadYuYueDatasuccess(List<YuYueData> yuyuedatas) {
			YuYueDataComparatpor com=new YuYueDataComparatpor("2");
			Collections.sort(yuyuedatas,com);
			this.yuyuedatas=yuyuedatas;
			
			if(yuyuedatas==null){
				ll_kanfangdate.setVisibility(View.VISIBLE);
			}else{
				ll_kanfangdate_list.setVisibility(View.VISIBLE);
			}
			Log.e("aaaaaaaaaaaaaaa", "cccccccccccccc"+yuyuedatas);
			yuyueadapter=new MyYuYueDateLsitAdapter(yuyuedatas, getContext());
			lv_kandangdate.setAdapter(yuyueadapter);
			
		}

		@Override
		public void LoadYuYueDataFaid(String info) {
			MyToastShowCenter.CenterToast(getContext(), info);
			
		}

		@Override
		public void deleteYuYue(String info) {
			MyToastShowCenter.CenterToast(getContext(), info);
			
		}
}
