package com.example.addndemo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HtmlShowActivity extends Activity {
	public WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.html_activity);
		webView = (WebView) findViewById(R.id.wv_webView);
		webView.getSettings().setJavaScriptEnabled(true);
//		webView.setScrollBarStyle(0);
		WebSettings webSettings = webView.getSettings();
		webSettings.setAllowFileAccess(true);
		webSettings.setBuiltInZoomControls(true);
		webView.loadUrl("http://www.baidu.com");
		// load data
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					HtmlShowActivity.this.setTitle("finish loading");
				} else {
					HtmlShowActivity.this.setTitle("loading.......");

				}
			}
		});
		// when click on this web view
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(final WebView view,
					final String url) {
//				loadurl(view, url);
				return true;
			}
		});
	}

	// goBack() means go back to the previous page.
	public boolean onKeyDown(int keyCoder, KeyEvent event) {
		if (webView.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
			webView.goBack();
			return true;
		}
		return false;
	}

	final class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			Log.d("MyWebChromeClient", message);
			result.confirm();
			return true;
		}
	}
}
