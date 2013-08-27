package com.example.kekeplayer.activity;

import com.example.kekeplayer.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KeKeLive_Flash extends Activity{

	public WebView webView;
	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kekelive_flash);
		webView = (WebView)findViewById(R.id.web_flash);
		WebSettings settings = webView.getSettings();
		settings.setPluginsEnabled(true);
		settings.setJavaScriptEnabled(true);
		settings.setAllowFileAccess(true);
		webView.setBackgroundColor(0);
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				webView.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
			
		});
		webView.setWebChromeClient(new WebChromeClient(){
			
		});
		webView.loadUrl("http://www.baidu.com");
	}

}
