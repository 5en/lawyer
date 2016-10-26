package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.view.activity.SelectLawyerDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/10 16:31
 * 邮箱：280766447@qq.com
 */
public class SelectLawyerActivity extends BaseActivity<SelectLawyerDelegate> {
    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context, SelectLawyerActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("asa");
        }
        mView.setData(list);

        mView.getAdapter().setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SubmitConsultActivity.actionActivity(SelectLawyerActivity.this);
            }
        });
    }
}
