package com.ebaonet.lawyer.ui.view.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * 作者：yzb on 2016/10/9 16:11
 * 邮箱：280766447@qq.com
 */
public class LawyerHomeDelegate extends AppDelegate {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView description;
    private TextView select;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_lawyer_home;
    }

    @Override
    public void created() {
        super.created();
        appBarLayout=findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_layout);
        description=findViewById(R.id.description);
        viewPager=findViewById(R.id.main_vp_container);
        tabLayout=findViewById(R.id.toolbar_tab);
        select=findViewById(R.id.select);
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.click(mPresenter,select);
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    public CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return collapsingToolbarLayout;
    }

    public TextView getDescription() {
        return description;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
