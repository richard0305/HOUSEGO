package com.dumu.housego;

import com.dumu.housego.util.FontHelper;

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
		// ���û��棬�ӷ������������µ�
		webView.clearCache(true);
		
		
		webView.getSettings().setJavaScriptEnabled(true);  
			url = getIntent().getStringExtra("url");
			
			
			Log.i("yanglijun","---------------------------------------"+ url);
			webView.loadUrl(url);
			// ���������ӣ�����ϵͳ�������
			// ������� new webViewClient
			webView.setWebViewClient(new WebViewClient() {
				// ÿ�ε��������ӻ�ִ��
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// ��ͨ�����ӣ���������Ҫ���� webview�����html
					// ִ�����⹦��
					String tag = "tarena:tel/";
					if (url.contains(tag)) {
						// ��绰
						int taglength = tag.length();
						String mobile = url.substring(taglength);
						Uri uri = Uri.parse("tel:" + mobile);
						Intent intent = new Intent(Intent.ACTION_CALL, uri);
						startActivity(intent);
						// ���������android�Ѿ������ˣ�webView���ټ���
						// return false,androidû�д���webview���ؼ������ذ�
						return true;
					}
					return super.shouldOverrideUrlLoading(view, url);
				}
			});
		

	}
}
