package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.ConsultingMeEntity;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.presenter.activity.LoginActivity;
import com.ebaonet.lawyer.ui.view.activity.ConsultingMeDelegate;
import com.ebaonet.lawyer.util.LoginHelp;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2016/10/12.
 */

public class ConsultingMeActivity extends BaseActivity<ConsultingMeDelegate> {
    private ConsultingMeAdapter mConsultingMeAdapter;
    ArrayList<String>strings;
    private final  int INIT_DATA =0;
    private int start = INIT_DATA;
    private int end = 100;
    private ArrayList<ConsultingMeEntity>consultingMeEntities ;

    public static void actionActivity(Context context){
        if (LoginHelp.isLogin(context)){
            Intent intent = new Intent(context,ConsultingMeActivity.class);
            context.startActivity(intent);
        }else {
            LoginActivity.actionActivity(context);
        }
    }

    @Override
    public void created(Bundle bundle){
 //consultList

//       strings = new ArrayList<>();
//        strings.add("");
//        strings.add("");
        mConsultingMeAdapter = new ConsultingMeAdapter(R.layout.view_consultation,null);
        mView.getRecycler_view().setLinearLayout();
        mView.getRecycler_view().setAdapter(mConsultingMeAdapter);
        mView.getRecycler_view().setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mView.getRecycler_view().setPullLoadMoreCompleted();
                refreshData(start+"",end+"");
            }

            @Override
            public void onLoadMore() {
                mView.getRecycler_view().setPullLoadMoreCompleted();
                getData(start+"",end+"");
            }
        });

//
        getData(start+"",end+"");

    }

    private void refreshData(String start,String end){

        PersonBiz.getConsultingMelist(ConsultingMeActivity.this,start,end, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
                    consultingMeEntities = data.getArrayData("consultList",ConsultingMeEntity.class);
                    mConsultingMeAdapter.refreshItems(consultingMeEntities);
                }
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });
    }

    private void getData(String start,String end){

        PersonBiz.getConsultingMelist(ConsultingMeActivity.this,start,end, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
                    consultingMeEntities = data.getArrayData("consultList",ConsultingMeEntity.class);
                    mConsultingMeAdapter.addItems(consultingMeEntities);
                }
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });

    }

    public class ConsultingMeAdapter extends RecyclerAdapter<ConsultingMeEntity> {
        public ConsultingMeAdapter(int layoutId) {
            super(layoutId);
        }

        @Override
        protected void onBindData(RecyclerViewHolder viewHolder, int position, final ConsultingMeEntity item) {

            //todo 头像 avatar_user user_head
            viewHolder.setText(R.id.user_name,item.getUser_name());
            viewHolder.setText(R.id.content,item.getContent());
            //todo 内容图片 image_id"

            viewHolder.setText(R.id.content_time,item.getCrt_time());

            viewHolder.setVisibility(R.id.evaluate,View.GONE);

            viewHolder.setOnClickListener(R.id.consultation_root, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyConsultationDetailActivity.actionActivity(ConsultingMeActivity.this,item.getDoc_consult_id(),MyConsultationDetailActivity.CONSULT_ME);
                }
            });

        }

        public ConsultingMeAdapter(int layoutId, ArrayList<ConsultingMeEntity> datas) {
            super(layoutId, datas);
        }
    }

}
