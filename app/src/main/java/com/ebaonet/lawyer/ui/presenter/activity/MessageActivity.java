package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ebaonet.lawyer.ui.presenter.fragment.LawyerKnowFragment;
import com.ebaonet.lawyer.ui.presenter.fragment.LawyerServiceFragment;
import com.ebaonet.lawyer.ui.view.activity.MessageDelegate;
import com.yanglaoClient.mvp.adapter.MyFragmentAdapter;
import com.yanglaoClient.mvp.entity.FragmentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/13 14:01
 * 邮箱：280766447@qq.com
 */
public class MessageActivity extends BaseActivity<MessageDelegate> {
    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context,MessageActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        List<FragmentEntity> mListFragmentEntity = new ArrayList();
        mListFragmentEntity.add(getFragmentEntity(new LawyerServiceFragment(), "1"));
        mListFragmentEntity.add(getFragmentEntity(new LawyerKnowFragment(), "2"));
        mView.getViewpager().setAdapter(new MyFragmentAdapter(this, mListFragmentEntity));
        mView.getViewpager().setCurrentItem(0);

        mView.getViewpager().addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (mView.getToolbar()));
        mView.getToolbar().setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (mView.getViewpager()));
    }

    private FragmentEntity getFragmentEntity(Fragment fragment, String label) {
        FragmentEntity fe = new FragmentEntity();
        fe.setFragmentLabel(label);
        fe.setmFragment(fragment);
        return fe;
    }
}
