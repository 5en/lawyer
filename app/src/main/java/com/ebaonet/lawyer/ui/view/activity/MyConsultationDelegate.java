package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/11.
 */

public class MyConsultationDelegate extends AppDelegate {
    private PullLoadMoreRecyclerView consultation;
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_consultation_layout;
    }
    @Override
    public void created(){
        consultation = findViewById(R.id.consultation);
        TitleView title = findViewById(R.id.title);
        title.bindActivity((Activity) mPresenter);
    }


    public PullLoadMoreRecyclerView getConsultation() {
        return consultation;
    }
}
