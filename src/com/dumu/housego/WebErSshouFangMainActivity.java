package com.dumu.housego;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebErSshouFangMainActivity extends Activity {
	private WebView webView;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_er_sshou_fang_main);
		
		webView = (WebView) findViewById(R.id.web_ershoufang);
		 
		 setwebView();
		
	}
	private void setwebView() {
		
			url = getIntent().getStringExtra("url");
			String urlx=url.replaceAll("amp;", "");
			
			// 不用缓存，从服务器加载最新的
			webView.clearCache(true);
			// String url = "http://176.3.16.123:8080/html4/07jd.html";
			// 加载网页
			
			
			Log.i("yanglijun", "+++++++++++++++++++++++++++++++++++"+urlx);
			webView.loadUrl(urlx);
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
