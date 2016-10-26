package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.presenter.activity.LoginActivity;
import com.ebaonet.lawyer.ui.view.activity.MyKnowledgeDelegate;
import com.ebaonet.lawyer.util.LoginHelp;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2016/10/13.
 */

public class MyKnowledgeActivity extends BaseActivity<MyKnowledgeDelegate>{
    private MyKnowledgeAdapter mConsultingMeAdapter;
    ArrayList<String> strings;

    public static void actionActivity(Context context){
        if (LoginHelp.isLogin(context)){
            Intent intent = new Intent(context,MyKnowledgeActivity.class);
            context.startActivity(intent);
        }else {
            LoginActivity.actionActivity(context);
        }
    }

    @Override
    public void created(Bundle bundle){

        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        mConsultingMeAdapter = new MyKnowledgeAdapter(R.layout.view_my_knowledge,strings);
        mView.getRecycler_view().setLinearLayout();
        mView.getRecycler_view().setAdapter(mConsultingMeAdapter);
        mView.getRecycler_view().setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mView.getRecycler_view().setPullLoadMoreCompleted();
                mConsultingMeAdapter.addItems(strings);
            }

            @Override
            public void onLoadMore() {
                mView.getRecycler_view().setPullLoadMoreCompleted();
                mConsultingMeAdapter.addItems(strings);
            }
        });



//        mView.getRecycler_view().set(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyConsultationDetailActivity.actionActivity(ConsultingMeActivity.this);
//
//            }
//        });
    }

    public class MyKnowledgeAdapter extends RecyclerAdapter<String> {
        public MyKnowledgeAdapter(int layoutId) {
            super(layoutId);
        }

        @Override
        protected void onBindData(RecyclerViewHolder viewHolder, int position, String item) {
//            viewHolder.setOnClickListener(R.id.consultation_root, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MyConsultationDetailActivity.actionActivity(MyKnowledgeActivity.this);
//                }
//            });

        }

        public MyKnowledgeAdapter(int layoutId, List datas) {
            super(layoutId, datas);
        }
    }

}
