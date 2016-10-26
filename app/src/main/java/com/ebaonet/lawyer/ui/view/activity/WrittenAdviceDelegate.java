package com.ebaonet.lawyer.ui.view.activity;

import android.content.Context;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.adapter.LawyerListAdapter;
import com.ebaonet.lawyer.ui.presenter.activity.WrittenAdviceActivity;
import com.ebaonet.lawyer.ui.weight.ListViewForScrollView;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

import java.util.List;

/**
 * 作者：yzb on 2016/10/8 17:28
 * 邮箱：280766447@qq.com
 */
public class WrittenAdviceDelegate extends AppDelegate {
    private TitleView mTitleView;
    private ListViewForScrollView mListView;
    private LawyerListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wrtten_advice;
    }

    @Override
    public void created() {
        super.created();
        mTitleView = findViewById(R.id.mTitleView);
        mTitleView.bindActivity((WrittenAdviceActivity) mPresenter);
        mListView = findViewById(R.id.mListView);
        TextView top = findViewById(R.id.top);
        top.setFocusable(true);
        top.setFocusableInTouchMode(true);
        top.requestFocus();
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.itemClick(mPresenter,mListView);
    }

    public void setData(Context context, List<String> list) {
        if (adapter == null) {
            adapter = new LawyerListAdapter(context, list, R.layout.list_item_lawyer);
        }
        mListView.setAdapter(adapter);
    }
}
