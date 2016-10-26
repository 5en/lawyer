package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.ebaonet.lawyer.ui.view.activity.WrittenAdviceDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * 书面咨询
 * 作者：yzb on 2016/10/8 17:28
 * 邮箱：280766447@qq.com
 */
public class WrittenAdviceActivity extends BaseActivity<WrittenAdviceDelegate> implements AdapterView.OnItemClickListener{
    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context, WrittenAdviceActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("xxx");
        }
        mView.setData(WrittenAdviceActivity.this,list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LawyerHomeActivity.actionActivity(this);
    }
}
