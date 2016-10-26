package com.ebaonet.lawyer.ui.view.activity;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.adapter.SelectLawyerAdapter;
import com.ebaonet.lawyer.ui.presenter.activity.SelectLawyerActivity;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yanglaoClient.mvp.view.AppDelegate;

import java.util.List;

/**
 * 作者：yzb on 2016/10/10 16:33
 * 邮箱：280766447@qq.com
 */
public class SelectLawyerDelegate extends AppDelegate {
    private TitleView mTitleView;
    private PullLoadMoreRecyclerView mListView;
    private SelectLawyerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_lawyer;
    }

    @Override
    public void created() {
        super.created();
        mTitleView = findViewById(R.id.mTitleView);
        mListView = findViewById(R.id.mListView);
        mListView.setLinearLayout();
        mListView.setPullRefreshEnable(true);
        mListView.setPushRefreshEnable(true);
        mListView.setFooterViewText("加载中...");
        mTitleView.bindActivity((SelectLawyerActivity) mPresenter);
    }

    public TitleView getmTitleView() {
        return mTitleView;
    }

    public PullLoadMoreRecyclerView getmListView() {
        return mListView;
    }

    public void setData(List<String> list) {
        if(adapter==null){
            adapter=new SelectLawyerAdapter(R.layout.list_item_select_lawyer,list);
        }
        mListView.setAdapter(adapter);
    }

    public SelectLawyerAdapter getAdapter() {
        return adapter;
    }
}
