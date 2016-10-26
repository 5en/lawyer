package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.ConsultingMeEntity;
import com.ebaonet.lawyer.core.entity.MyConsultingEntity;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.presenter.activity.LoginActivity;
import com.ebaonet.lawyer.ui.view.activity.MyConsultationDelegate;
import com.ebaonet.lawyer.util.LoginHelp;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2016/10/11.
 */

public class MyConsultationActivity extends BaseActivity <MyConsultationDelegate>{

    private final  int INIT_DATA =0;
    private int start = INIT_DATA;
    private int end = 100;

    public static void actionActivity(Context context){
        if (LoginHelp.isLogin(context)){
            Intent intent = new Intent(context,MyConsultationActivity.class);
            context.startActivity(intent);
        }else {
            LoginActivity.actionActivity(context);
        }
    }
    private MyConsultationAdapter mMyConsultationAdapter;
    ArrayList<String> strings;
    ArrayList<MyConsultingEntity> myConsultingEntities ;
    @Override
    public void created(Bundle bundle){

        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        mMyConsultationAdapter = new MyConsultationAdapter(R.layout.view_consultation,null);
        mView.getConsultation().setLinearLayout();
        mView.getConsultation().setAdapter(mMyConsultationAdapter);
        mView.getConsultation().setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                mView.getConsultation().setPullLoadMoreCompleted();
                getData(start+"",end +"");

            }

            @Override
            public void onLoadMore() {
                mView.getConsultation().setPullLoadMoreCompleted();
                loadMore(start+"",end +"");
            }
        });

        getData(start+"",end +"");
    }

  //加载更多
    private void loadMore(String start,String end){

        PersonBiz.getMyConsultinglist(MyConsultationActivity.this,start,end, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
                    myConsultingEntities = data.getArrayData("consultList",MyConsultingEntity.class);
                    mMyConsultationAdapter.addItems(myConsultingEntities);
                }
            }

            @Override
            public void onError(NetBaseBean data) {

                alert(data.getMessage());
            }
        });

    }
    //网络获取数据，或刷新数据
    private void getData(String start,String end){

        PersonBiz.getMyConsultinglist(MyConsultationActivity.this,start,end, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
                    myConsultingEntities = data.getArrayData("consultList",MyConsultingEntity.class);
                    mMyConsultationAdapter.refreshItems(myConsultingEntities);
                }
            }

            @Override
            public void onError(NetBaseBean data) {

                alert(data.getMessage());
            }
        });

    }
    public class MyConsultationAdapter extends RecyclerAdapter<MyConsultingEntity>{


        public MyConsultationAdapter(int layoutId) {
            super(layoutId);
        }

        public MyConsultationAdapter(int layoutId, List datas) {
            super(layoutId, datas);
        }

        @Override
        protected void onBindData(RecyclerViewHolder viewHolder, int position, final MyConsultingEntity item) {
            viewHolder.setOnClickListener(R.id.consultation_root, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyConsultationDetailActivity.actionActivity(MyConsultationActivity.this,item.getDoc_consult_id(),MyConsultationDetailActivity.MY_CONSULT);
                }
            });
            //todo 头像 avatar_user user_head
            viewHolder.setText(R.id.user_name,item.getUser_name());
            viewHolder.setText(R.id.content,item.getContent());
            viewHolder.setFresco(R.id.image1, GlobalConfig.API_URL+GlobalConfig.getImageUrl+item.getImage_id());
            viewHolder.setFresco(R.id.image2,GlobalConfig.API_URL+GlobalConfig.getImageUrl+item.getImage_id1());
            viewHolder.setFresco(R.id.image3,GlobalConfig.API_URL+GlobalConfig.getImageUrl+item.getImage_id2());

            //todo 内容图片 image_id"

            viewHolder.setText(R.id.content_time,item.getCrt_time());

            viewHolder.setVisibility(R.id.evaluate,View.GONE);


            // 绑定数据
        }



    }

}
