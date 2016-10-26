package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.MyConsultingEntity;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.view.activity.MyConsultationDetailDelegate;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2016/10/12.
 */

public class MyConsultationDetailActivity extends BaseActivity<MyConsultationDetailDelegate> {

    //咨询类型
     public static  String  MY_CONSULT = "myConsult";
     public static String CONSULT_ME = "consult_me";

    public static void actionActivity(Context context,String  consultId,String consultType){
        Intent intent = new Intent(context, MyConsultationDetailActivity.class);
        intent.putExtra("consultId",consultId);
        intent.putExtra("consultType",consultType);
        context.startActivity(intent);
    }

    private ConsultationDetailAdapter mConsultingMeAdapter;
    ArrayList<String> strings;
    private final  int INIT_DATA =0;
    private int start = INIT_DATA;
    private int end = 100;
    private String consultId;
    private String consultType;

    @Override
    public void created(Bundle bundle){



        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        mConsultingMeAdapter = new ConsultationDetailAdapter(R.layout.view_consultation,strings);
        mView.getRecycler_view().setLayoutManager(new LinearLayoutManager(MyConsultationDetailActivity.this));
        mView.getRecycler_view().setAdapter(mConsultingMeAdapter);


        mView.getRecycler_view().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //网络获取详情
        consultId = bundle.getString("consultId");
        consultType  = bundle.getString("consultType");

        if (consultType.equals(MY_CONSULT)){
            // 我的咨询 网络加载，及 标题更改。

            mView.getTitleView().setTitleText("我的咨询");
            getData();

        }else if (consultType.equals(CONSULT_ME)){
            // 咨询我的 网络加载，及 标题更改。
            mView.getTitleView().setTitleText("咨询我的");
            getData();
        }

    }

    private void getData(){

        PersonBiz.queryConsultingDetail(MyConsultationDetailActivity.this,consultId, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
//                    myConsultingEntities = data.getArrayData("consultList",MyConsultingEntity.class);
//                    mConsultingMeAdapter.refreshItems(myConsultingEntities);
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

        PersonBiz.getConsultingMelist(MyConsultationDetailActivity.this,start,end, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
//                    myConsultingEntities = data.getArrayData("consultList",MyConsultingEntity.class);
//                    mConsultingMeAdapter.refreshItems(myConsultingEntities);
                }
            }

            @Override
            public void onError(NetBaseBean data) {

                alert(data.getMessage());
            }
        });

    }
    public class ConsultationDetailAdapter extends RecyclerAdapter<String> {
        public ConsultationDetailAdapter(int layoutId) {
            super(layoutId);
        }

        @Override
        protected void onBindData(RecyclerViewHolder viewHolder, int position, String item) {

        }

        public ConsultationDetailAdapter(int layoutId, List datas) {
            super(layoutId, datas);
        }
    }

}
