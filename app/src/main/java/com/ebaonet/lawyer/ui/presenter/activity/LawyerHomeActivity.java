package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.fragment.LawyerKnowFragment;
import com.ebaonet.lawyer.ui.presenter.fragment.LawyerServiceFragment;
import com.ebaonet.lawyer.ui.view.activity.LawyerHomeDelegate;
import com.yanglaoClient.mvp.adapter.MyFragmentAdapter;
import com.yanglaoClient.mvp.entity.FragmentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/9 16:11
 * 邮箱：280766447@qq.com
 */
public class LawyerHomeActivity extends BaseActivity<LawyerHomeDelegate> implements View.OnClickListener{

    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context, LawyerHomeActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.getAppBarLayout().addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -900) {
                    mView.getDescription().setVisibility(View.INVISIBLE);
                } else {
                    mView.getDescription().setVisibility(View.VISIBLE);
                }
            }
        });
        List<FragmentEntity> mListFragmentEntity = new ArrayList();
        mListFragmentEntity.add(getFragmentEntity(new LawyerServiceFragment(), "1"));
        mListFragmentEntity.add(getFragmentEntity(new LawyerKnowFragment(), "2"));
        mView.getViewPager().setAdapter(new MyFragmentAdapter(this, mListFragmentEntity));
        mView.getViewPager().setCurrentItem(0);

        mView.getViewPager().addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (mView.getTabLayout()));
        mView.getTabLayout().setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (mView.getViewPager()));
    }

    private FragmentEntity getFragmentEntity(Fragment fragment, String label) {
        FragmentEntity fe = new FragmentEntity();
        fe.setFragmentLabel(label);
        fe.setmFragment(fragment);
        return fe;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.select:
                SelectLawyerActivity.actionActivity(this);
                break;
        }
    }
}
