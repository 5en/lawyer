package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.presenter.activity.LoginActivity;
import com.ebaonet.lawyer.ui.view.activity.MyCollectionDelegate;
import com.ebaonet.lawyer.util.LoginHelp;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2016/10/12.
 */

public class MyCollectionActivity extends BaseActivity<MyCollectionDelegate> {
    public static void actionActivity(Context context){
        if (LoginHelp.isLogin(context)){
            Intent intent = new Intent(context,MyCollectionActivity.class);
            context.startActivity(intent);
        }else {
            LoginActivity.actionActivity(context);
        }
    }
    private MycollectAdapter mMyConsultationAdapter;
    ArrayList<String> strings;
    @Override
    public void created(Bundle bundle){


        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        mMyConsultationAdapter = new MycollectAdapter(R.layout.view_collection,strings);
        mView.getRecycler_view().setLinearLayout();
        mView.getRecycler_view().setAdapter(mMyConsultationAdapter);
        mView.getRecycler_view().setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mView.getRecycler_view().setPullLoadMoreCompleted();
                mMyConsultationAdapter.addItems(strings);
            }

            @Override
            public void onLoadMore() {
                mView.getRecycler_view().setPullLoadMoreCompleted();
                mMyConsultationAdapter.addItems(strings);
            }
        });

//        mView.getRecycler_view().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyConsultationDetailActivity.actionActivity(MyCollectionActivity.this,ite);
//
//            }
//        });
    }

    private void getData(){
//        PersonBiz.loginOut();
    }
    public class MycollectAdapter extends RecyclerAdapter {


        public MycollectAdapter(int layoutId) {
            super(layoutId);
        }

        public MycollectAdapter(int layoutId, List datas) {
            super(layoutId, datas);
        }

        @Override
        protected void onBindData(RecyclerViewHolder viewHolder, int position, Object item) {
//            viewHolder.setOnClickListener(R.id.consultation_root, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MyConsultationDetailActivity.actionActivity(MyCollectionActivity.this);
//                }
//            });

            //todo 绑定数据
        }



    }

}
