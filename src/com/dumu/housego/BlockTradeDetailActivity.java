package com.dumu.housego;

import com.bumptech.glide.Glide;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.presenter.BlockTradeDetailPresenter;
import com.dumu.housego.presenter.IBlockTradeDetailPresenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IBlockTradeDetailView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BlockTradeDetailActivity extends Activity implements IBlockTradeDetailView{
	private LinearLayout llBlockTradeDetailBack;
	private BlockTradeDetail b;
	private IBlockTradeDetailPresenter presenter;
	private TextView tv_block_trade_goudiimage,tv_block_trade_detail_title,tv_block_trade_detail_content,tv_block_trade_detail_goudi
	,tv_block_trade_detail_date,tv_block_trade_detail_phone,tv_block_trade_detail_area,tv_block_trade_detail_yusuanjine
	,tv_block_trade_detail_hezuofangshi,tv_block_trade_detail_shiyongnianxian,tv_block_trade_detail_wuyeleixing,
	tv_block_trade_detail_jianzhumianji,tv_block_trade_detail_lianxiren,tv_block_trade_detail_address,tv_block_trade_detail_xiangxijieshao;
	private ImageView iv_block_trade_detail;
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				b=(BlockTradeDetail) msg.obj;
				Log.e("==============", "`````+++++++++~~~~~~"+b);
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
		setContentView(R.layout.activity_block_trade_detail);
		
		setViews();
		setListener();
		
		
		String catid=getIntent().getStringExtra("catid");
		String id=getIntent().getStringExtra("id");
		
		iv_block_trade_detail.setImageResource(R.drawable.touxiang);
		presenter=new BlockTradeDetailPresenter(this);
		
		presenter.FindBlockTradedetail(catid, id);
		
	
		
		
		
	}
	
	private void setViews() {
		llBlockTradeDetailBack=(LinearLayout) findViewById(R.id.ll_block_trade_detail_back);
		tv_block_trade_detail_address=(TextView) findViewById(R.id.tv_block_trade_detail_address);
		tv_block_trade_detail_area=(TextView) findViewById(R.id.tv_block_trade_detail_area);
		tv_block_trade_detail_content=(TextView) findViewById(R.id.tv_block_trade_detail_content);
		tv_block_trade_detail_date=(TextView) findViewById(R.id.tv_block_trade_detail_date);
		tv_block_trade_detail_goudi=(TextView) findViewById(R.id.tv_block_trade_detail_goudi);
		tv_block_trade_detail_hezuofangshi=(TextView) findViewById(R.id.tv_block_trade_detail_hezuofangshi);
		tv_block_trade_detail_jianzhumianji=(TextView) findViewById(R.id.tv_block_trade_detail_jianzhumianji);
		tv_block_trade_detail_lianxiren=(TextView) findViewById(R.id.tv_block_trade_detail_lianxiren);
		tv_block_trade_detail_phone=(TextView) findViewById(R.id.tv_block_trade_detail_phone);
		tv_block_trade_detail_shiyongnianxian=(TextView) findViewById(R.id.tv_block_trade_detail_shiyongnianxian);
		tv_block_trade_detail_title=(TextView) findViewById(R.id.tv_block_trade_detail_title);
		tv_block_trade_detail_wuyeleixing=(TextView) findViewById(R.id.tv_block_trade_detail_wuyeleixing);
		tv_block_trade_detail_xiangxijieshao=(TextView) findViewById(R.id.tv_block_trade_detail_xiangxijieshao);
		tv_block_trade_detail_yusuanjine=(TextView) findViewById(R.id.tv_block_trade_detail_yusuanjine);
		tv_block_trade_goudiimage=(TextView) findViewById(R.id.tv_block_trade_goudiimage);
		iv_block_trade_detail=(ImageView) findViewById(R.id.iv_block_trade_detail);
		
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
				Toast.makeText(getApplicationContext(), "15005148914", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
	}
	
	
	@Override
	public void showData(BlockTradeDetail blocktradedetail) {
		
		
				
		Message msg=new Message();
		msg.what=1;
		msg.obj=blocktradedetail;
		handler.sendMessage(msg);
		
				
	}
	
	private void Show(){
		
		if(b.getThumb().startsWith("http://www.tao")){
			
			Glide.with(getApplicationContext()).load(b.getThumb()).into(iv_block_trade_detail);
		}else{
			String url="http://www.taoshenfang.com"+b.getThumb();
			Log.e("yanglijun", "------========---------+++++++++"+url);
			Glide.with(getApplicationContext()).load(url).into(iv_block_trade_detail);
		}
		tv_block_trade_detail_title.setText(b.getTitle());
		tv_block_trade_detail_content.setText(b.getTitle());
		tv_block_trade_detail_goudi.setText(b.getGoudijine()+"Ԫ");
		
		String date=TimeTurnDate.getStringDate(b.getUpdatetime());
		tv_block_trade_detail_date.setText(date);
		tv_block_trade_detail_phone.setText(b.getTel());
		
		tv_block_trade_detail_area.setText(b.getCityname()+" "+b.getAreaname());
		tv_block_trade_detail_yusuanjine.setText(b.getZongjia()+"��Ԫ");
		tv_block_trade_detail_hezuofangshi.setText(b.getHezuofangshi());
		tv_block_trade_detail_shiyongnianxian.setText(b.getShiyongnianxian()+"��");
		tv_block_trade_detail_wuyeleixing.setText(b.getWuyetype());
		tv_block_trade_detail_jianzhumianji.setText(b.getZhandimianji()+"�O");
		tv_block_trade_detail_lianxiren.setText(b.getUsername());
		tv_block_trade_detail_address.setText(b.getAddress());
		tv_block_trade_detail_xiangxijieshao.setText(b.getDescription());
		
	}
	

	
}
