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
			
			// ���û��棬�ӷ������������µ�
			webView.clearCache(true);
			// String url = "http://176.3.16.123:8080/html4/07jd.html";
			// ������ҳ
			
			
			Log.i("yanglijun", "+++++++++++++++++++++++++++++++++++"+urlx);
			webView.loadUrl(urlx);
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
