package com.dumu.housego;

import com.bumptech.glide.Glide;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.presenter.BlockTradeDetailPresenter;
import com.dumu.housego.presenter.IBlockTradeDetailPresenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IBlockTradeDetailView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BlockTradeDetailActivity extends Activity implements IBlockTradeDetailView{
	private LinearLayout llBlockTradeDetailBack;
	private BlockTradeDetail blocktradedetail;
	private IBlockTradeDetailPresenter presenter;
	private TextView tv_block_trade_goudiimage,tv_block_trade_detail_title,tv_block_trade_detail_content,tv_block_trade_detail_goudi
	,tv_block_trade_detail_date,tv_block_trade_detail_phone,tv_block_trade_detail_area,tv_block_trade_detail_yusuanjine
	,tv_block_trade_detail_hezuofangshi,tv_block_trade_detail_shiyongnianxian,tv_block_trade_detail_wuyeleixing,
	tv_block_trade_detail_jianzhumianji,tv_block_trade_detail_lianxiren,tv_block_trade_detail_address,tv_block_trade_detail_xiangxijieshao;
	private ImageView iv_block_trade_detail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_trade_detail);
		
		setViews();
		setListener();
		
//		int id=getIntent().getStringExtra("id");
//		String catid=getIntent().getStringExtra("catid");
		
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
				Toast.makeText(getApplicationContext(), "Çë×ªÖ§¸¶±¦15005148914", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
	}
	
	
	@Override
	public void showData(BlockTradeDetail blocktradedetail) {
		this.blocktradedetail=blocktradedetail;
		
		if(blocktradedetail.getThumb()!=null){
			String url="http://www.taoshenfang.com"+blocktradedetail.getThumb();
			Log.e("yanglijun", "------========---------+++++++++"+url);
			Glide.with(getApplicationContext()).load(url).into(iv_block_trade_detail);
		}else{
			iv_block_trade_detail.setImageResource(R.drawable.touxiang);
		}
		
		tv_block_trade_detail_title.setText(blocktradedetail.getTitle());
		tv_block_trade_detail_content.setText(blocktradedetail.getTitle());
		tv_block_trade_detail_goudi.setText(blocktradedetail.getGoudijine());
		
		String date=TimeTurnDate.getStandardTime(blocktradedetail.getUpdatetime());
		tv_block_trade_detail_date.setText(date);
		tv_block_trade_detail_phone.setText(blocktradedetail.getTel());
		
		tv_block_trade_detail_area.setText(blocktradedetail.getCityname()+" "+blocktradedetail.getAreaname());
		tv_block_trade_detail_yusuanjine.setText(blocktradedetail.getZongjia());
		tv_block_trade_detail_hezuofangshi.setText(blocktradedetail.getHezuofangshi());
		tv_block_trade_detail_shiyongnianxian.setText(blocktradedetail.getShiyongnianxian());
		tv_block_trade_detail_wuyeleixing.setText(blocktradedetail.getWuyetype());
		tv_block_trade_detail_jianzhumianji.setText(blocktradedetail.getZhandimianji());
		tv_block_trade_detail_lianxiren.setText(blocktradedetail.getUsername());
		tv_block_trade_detail_address.setText(blocktradedetail.getAddress());
		tv_block_trade_detail_xiangxijieshao.setText(blocktradedetail.getDescription());
		
	}
}
