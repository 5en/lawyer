package com.ebaonet.lawyer.ui.view.frgament;

import android.content.Context;
import android.widget.ListView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.adapter.LawyerServiceAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.LawyerKnowAdapter;
import com.yanglaoClient.mvp.view.AppDelegate;

import java.util.List;

/**
 * 作者：yzb on 2016/10/10 14:52
 * 邮箱：280766447@qq.com
 */
public class LawyerKnowDelegate extends AppDelegate {
    private ListView mListView;
    private LawyerKnowAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lawyer_service;
    }

    @Override
    public void created() {
        super.created();
        mListView = findViewById(R.id.mListView);


    }

    public void setData(Context context, List<String> list) {
        if (adapter == null) {
            adapter = new LawyerKnowAdapter(context, list, R.layout.list_item_lawyer_know);
        }
        mListView.setAdapter(adapter);
    }
}
