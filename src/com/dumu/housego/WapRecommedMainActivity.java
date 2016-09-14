package com.dumu.housego;

import com.bumptech.glide.Glide;
import com.dumu.housego.util.FontHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WapRecommedMainActivity extends Activity {
	private TextView tvTitle,tvContent;
	private ImageView ivPic;
	private LinearLayout llRecommendBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_recommed_news_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		String url = getIntent().getStringExtra("url");
		String title = getIntent().getStringExtra("title");
		String content = getIntent().getStringExtra("content");
		
		tvTitle=(TextView) findViewById(R.id.tv_recommend_title_web);
		tvContent=(TextView) findViewById(R.id.tv_recommend_rcontent_web);
		ivPic=(ImageView) findViewById(R.id.iv_recommend_pic_web);
		llRecommendBack=(LinearLayout) findViewById(R.id.ll_recommend_back_web);
		
		tvTitle.setText(title);
		tvContent.setText(content);
		Glide.with(getApplicationContext()).load(url).into(ivPic);
		
		
		llRecommendBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					finish();
			}
		});

	}

}
