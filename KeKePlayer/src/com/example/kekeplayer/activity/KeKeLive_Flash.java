package com.example.kekeplayer.activity;

import com.example.kekeplayer.R;
import com.example.kekeplayer.player.JieLiveVideoPlayer;
import com.yixia.vparser.VParser;
import com.yixia.vparser.model.Video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KeKeLive_Flash extends Activity{

	public WebView webView;
	VParser vParser;
	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kekelive_flash);
		webView = (WebView)findViewById(R.id.web_flash);
		vParser = new VParser(this);
		WebSettings settings = webView.getSettings();
		settings.setPluginsEnabled(true);
		settings.setJavaScriptEnabled(true);
		settings.setAllowFileAccess(true);
		webView.setBackgroundColor(0);
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("http://v.youku.com/v_show/id_")) {
					Video video = vParser.parse(url);
					Intent intent = new Intent();
					intent.putExtra("path", video.videoUri);
					intent.putExtra("title", video.title);
					intent.setClass(KeKeLive_Flash.this,JieLiveVideoPlayer.class);
					startActivity(intent);
					return true;
				}else {
					webView.loadUrl(url);
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
			
		});
		webView.setWebChromeClient(new WebChromeClient(){
			
		});
		webView.loadUrl("http://3g.youku.com");
	}

}
