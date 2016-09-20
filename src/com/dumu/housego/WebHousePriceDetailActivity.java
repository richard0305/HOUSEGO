package com.dumu.housego;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebHousePriceDetailActivity extends Activity {
	private WebView webView;
	private LinearLayout ll_house_price_detail_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_house_price_detail);
		webView = (WebView) findViewById(R.id.web_house_price);
		ll_house_price_detail_back=(LinearLayout) findViewById(R.id.ll_house_price_detail_back) ;
		setListener();
		 setwebView();
		 
	}
	
	
	
	private void setListener() {
		ll_house_price_detail_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}



	private void setwebView() {
		// 不用缓存，从服务器加载最新的
		webView.clearCache(true);
		
		
		webView.getSettings().setJavaScriptEnabled(true);  
		String	url = "http://www.wsting.site/demo/tracy.html";
			
			
			Log.i("yanglijun","---------------------------------------"+ url);
			webView.loadUrl(url);
			// 单击超链接，启动系统的浏览器
			// 解决方法 new webViewClient
			webView.setWebViewClient(new WebViewClient() {
				// 每次单击超链接会执行
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// 普通超链接，链到，不要处理 webview会加载html
					// 执行特殊功能
					String tag = "tarena:tel/";
					if (url.contains(tag)) {
						// 打电话
						int taglength = tag.length();
						String mobile = url.substring(taglength);
						Uri uri = Uri.parse("tel:" + mobile);
						Intent intent = new Intent(Intent.ACTION_CALL, uri);
						startActivity(intent);
						// 这个超链接android已经处理了，webView不再加载
						// return false,android没有处理，webview加载继续加载吧
						return true;
					}
					return super.shouldOverrideUrlLoading(view, url);
				}
			});
		

	}

}
