package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ebaonet.lawyer.ui.view.activity.NewDetailDelegate;


/**
 * 作者：yzb on 2016/6/22 10:23
 * 邮箱：280766447@qq.com
 */
public class NewsDetailActivity extends BaseActivity<NewDetailDelegate> {
    public static final String NEWS_URL="news_url";
    public static final String NEWS_TITLE="news_title";

    public static void actionNewsDetailActivity(Context context, String url) {
        Intent it = new Intent(context,NewsDetailActivity.class);
        it.putExtra(NEWS_URL,url);
        context.startActivity(it);
    }
    public static void actionNewsDetailActivity(Context context, String url,String title) {
        Intent it = new Intent(context,NewsDetailActivity.class);
        it.putExtra(NEWS_URL,url);
        it.putExtra(NEWS_TITLE,title);
        context.startActivity(it);
    }
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setData(getIntent().getStringExtra(NEWS_URL),getIntent().getStringExtra(NEWS_TITLE));
    }
}
