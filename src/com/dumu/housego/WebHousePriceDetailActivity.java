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
		ll_house_price_detail_back = (LinearLayout) findViewById(R.id.ll_house_price_detail_back);
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
		// ���û��棬�ӷ������������µ�
		webView.clearCache(true);

		webView.getSettings().setJavaScriptEnabled(true);
		String url = "http://www.taoshenfang.com/index.php?a=lists&catid=57";

		Log.i("yanglijun", "---------------------------------------" + url);
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
