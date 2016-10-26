package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/12.
 */

public class MyConsultationDetailDelegate extends AppDelegate {
    private RecyclerView recycler_view;
    private TitleView titleView;
    @Override
    public int getLayoutId() {

        return R.layout.activity_consultation_detail;

    }

    @Override
    public void created(){
        recycler_view = findViewById(R.id.recycler_view);
        titleView = findViewById(R.id.title);
        titleView.bindActivity((Activity) mPresenter);

    }

    public RecyclerView getRecycler_view() {
        return recycler_view;
    }

    public TitleView getTitleView() {
        return titleView;
    }
}
