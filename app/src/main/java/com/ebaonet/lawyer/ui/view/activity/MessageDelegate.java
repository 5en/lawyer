package com.ebaonet.lawyer.ui.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.activity.MessageActivity;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * 作者：yzb on 2016/10/13 14:02
 * 邮箱：280766447@qq.com
 */
public class MessageDelegate extends AppDelegate {
    private TitleView mTitleView;
    private TabLayout toolbar;
    private ViewPager viewpager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void created() {
        super.created();
        mTitleView=findViewById(R.id.mTitleView);
        mTitleView.bindActivity((MessageActivity)mPresenter);
        toolbar=findViewById(R.id.toolbar_tab);
        viewpager=findViewById(R.id.viewpager);
    }

    public ViewPager getViewpager() {
        return viewpager;
    }

    public TabLayout getToolbar() {
        return toolbar;
    }
}
