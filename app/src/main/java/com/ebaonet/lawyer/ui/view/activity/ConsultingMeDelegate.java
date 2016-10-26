package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/12.
 */

public class ConsultingMeDelegate extends AppDelegate {
    private PullLoadMoreRecyclerView recycler_view;


    @Override
    public int getLayoutId() {
        return R.layout.actvity_consulting_me;
    }

    @Override
    public void created() {
        recycler_view = findViewById(R.id.recycler_view);
        TitleView title = findViewById(R.id.title);
        title.bindActivity((Activity) mPresenter);

    }

    public PullLoadMoreRecyclerView getRecycler_view() {
        return recycler_view;
    }
}
