package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;

import com.ebaonet.lawyer.util.LogUtil;
import com.yanglaoClient.mvp.util.core.ScreenUtil;


public class ProgressWebView extends WebView {

	private NumberProgressBar progressbar;
	private boolean blockNetworkImage = false;

	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		progressbar = new NumberProgressBar(context, null);
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, ScreenUtil.dip2px(10), 0, 0));
		//
		getSettings().setJavaScriptEnabled(true);
		getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//		getSettings().setSupportMultipleWindows(true);// 链接中有goto的把这个打开
		getSettings().setUseWideViewPort(true);
		getSettings().setLoadWithOverviewMode(true);
		getSettings().setSupportZoom(false);
		getSettings().setBuiltInZoomControls(false);
		getSettings().setRenderPriority(RenderPriority.HIGH);
		getSettings().setBlockNetworkImage(true);
		setWebChromeClient(new WebChromeClient());
	}

	public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			LogUtil.getLogger().d(newProgress);
			if (newProgress == 100) {
				getSettings().setBlockNetworkImage(false);
				progressbar.setVisibility(GONE);
			} else {
				getSettings().setBlockNetworkImage(true);
				if (progressbar.getVisibility() == GONE)
					progressbar.setVisibility(VISIBLE);
				progressbar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
