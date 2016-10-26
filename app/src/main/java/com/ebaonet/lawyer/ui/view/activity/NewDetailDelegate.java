package com.ebaonet.lawyer.ui.view.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.activity.NewsDetailActivity;
import com.ebaonet.lawyer.ui.weight.ProgressWebView;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.ebaonet.lawyer.util.LogUtil;
import com.yanglaoClient.mvp.util.core.StringUtils;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * 作者：yzb on 2016/6/22 10:23
 * 邮箱：280766447@qq.com
 */
public class NewDetailDelegate extends AppDelegate {

    private ProgressWebView mWebView;
    private TitleView mTitleView;
    private TextView mProgressText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void created() {
        super.created();
        mWebView = findViewById(R.id.mWebView);
        mTitleView = findViewById(R.id.mTitleView);
        mProgressText = findViewById(R.id.progress_text);
        mTitleView.bindActivity((NewsDetailActivity) mPresenter);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.setBackgroundColor(Color.parseColor("#00000000"));
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setSupportMultipleWindows(false);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
    }

    public void setData(String url, final String title) {
        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    mWebView.getSettings().setBlockNetworkImage(false);
                    mProgressText.setVisibility(View.GONE);
                } else {
                    mWebView.getSettings().setBlockNetworkImage(true);
                    if (mProgressText.getVisibility() == View.GONE)
                        mProgressText.setVisibility(View.VISIBLE);
                    mProgressText.setText(progress + "%");
                }
                super.onProgressChanged(view, progress);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (StringUtils.isEmpty(title)) {
                    if (StringUtils.isEmpty(view.getTitle())) {
                        mTitleView.setTitleText("详情");
                    } else {
                        mTitleView.setTitleText(view.getTitle());
                    }
                }else {
                    mTitleView.setTitleText(title);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                LogUtil.getLogger().d("onReceivedError");
                view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            }
        });
    }
}
