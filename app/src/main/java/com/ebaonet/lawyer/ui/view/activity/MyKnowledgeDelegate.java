package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/13.
 */

public class MyKnowledgeDelegate extends AppDelegate {
    private PullLoadMoreRecyclerView recycler_view;
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_knowledge;
    }

    @Override
    public void created(){
        recycler_view = findViewById(R.id.recycler_view);
        TitleView titleView = findViewById(R.id.title);
        titleView.bindActivity((Activity) mPresenter);
    }

    public PullLoadMoreRecyclerView getRecycler_view() {
        return recycler_view;
    }
}
