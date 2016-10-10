package com.dumu.housego;

import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.presenter.ErShouFangDetailPresenter;
import com.dumu.housego.presenter.IErShouFangDetailPresenter;
import com.dumu.housego.view.IErShouFangDetailView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class ErShouFangDetailsActivity extends Activity implements IErShouFangDetailView{
	
	private RadioButton rbErshoufangGuanzhu;
	private ErShouFangDetails e;
	private IErShouFangDetailPresenter esfPresenter;
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				e=(ErShouFangDetails) msg.obj;
				Log.e("==============", "`````+++++++++~~~~~~"+e);
				Show();
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_er_shou_fang_details);
		initViews();
		initListener();
		esfPresenter=new ErShouFangDetailPresenter(this);
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		esfPresenter.FindErShouFangdetail(catid, id);
		
		Log.e("2016-10-9 9:10", "yanglijun-------"+catid+"   "+id);
	}
	private void initListener() {
		rbErshoufangGuanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
	}
	private void initViews() {
		rbErshoufangGuanzhu=(RadioButton) findViewById(R.id.rb_ershoufangguanzhu);
		
	}
	
	@Override
	public void showErshoufangData(ErShouFangDetails ershoufangdetail) {
		Message msg=new Message();
		msg.what=1;
		msg.obj=ershoufangdetail;
		handler.sendMessage(msg);
		
	}
	
	
	private void Show(){
	
		
	}

}
